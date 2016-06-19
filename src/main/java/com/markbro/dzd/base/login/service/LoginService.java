
package com.markbro.dzd.base.login.service;

import com.markbro.asoiaf.core.TmConstant;
import com.markbro.asoiaf.core.exception.ApplicationException;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.core.utils.Global;
import com.markbro.asoiaf.core.utils.Md5;
import com.markbro.asoiaf.core.utils.SysPara;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.filter.Token;
import com.markbro.dzd.base.login.bean.LoginBean;
import com.markbro.dzd.base.login.dao.LoginMapper;
import com.markbro.dzd.common.util.ConstantUtil;
import com.markbro.dzd.common.util.Des;
import com.markbro.dzd.common.util.Guid;
import com.markbro.dzd.sys.permission.dao.PermissionMapper;
import com.markbro.dzd.sys.sysUser.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Area Service
 * Created by wujiyue on 2016-03-13 03:24:07.
 */

@Service
public class LoginService {
    protected Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper qxMapper;
    private final String pageInputDd = "account";//登录页面帐户名
    private final String pageInputMm = "password";//登录页面密码
    private final String pageInputCode = "verycode";//登录页面验证码
    public String getYhidByToken(String token) {
        String str = "";
        if (token == null){
            return str;
        }
        if (token.startsWith("khd_")) {
            String yhid = loginMapper.queryUserSession(token);
            str = StringUtil.isNull(yhid);
        } else {

            str = (String) EhCacheUtils.getSysInfo(ConstantUtil.YHID_KEY, token);

        }
        return str;
    }
    public void updateActiveTime(String token) {
        try {
            //session存储方式：1-放在t_session中；2-放在cache中
            if (SysPara.compareValue("session_storage", ConstantUtil.NUM_ONE, ConstantUtil.NUM_ONE)){
                loginMapper.updateUserSessionATime(token);
            }else{
                EhCacheUtils.putSysInfo("active_time", token, Long.valueOf(new Date().getTime()));
            }
        } catch (Exception localException) {
            String str = "更新active_time时出现异常:" + localException.toString();
            log.error(str);
            throw new ApplicationException("更新t_session数据时异常");
        }
    }



    /**
     * 后台登录验证
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @Transactional
    public String validate(HttpServletRequest request, HttpServletResponse response) {
        String errpage = "";

//		String key = PatternUtil.isNull(String.valueOf(request.getSession().getAttribute(sessionCapKey)));
//		String isFLogin = PatternUtil.isNull(String.valueOf(request.getSession().getAttribute(sessionFLKey)));
//		String cap = PatternUtil.isNull(request.getParameter(pageInputCap));
        String dlmc = StringUtil.isNull(request.getParameter(pageInputDd));
        String dlmm = StringUtil.isNull(request.getParameter(pageInputMm));
        String code = StringUtil.isNull(request.getParameter(pageInputCode));//验证码
        String token = "";
        String mainpage = "";
        String validateStr = "";
        try {

            errpage = SysPara.getValue("login_errorPage");
            errpage=StringUtil.subEndStr(errpage,".jsp");
            if(Global.loginWithValidateCode()){
                if(!checkValidateCode(request,code)){
                    request.setAttribute(ConstantUtil.CODE_WARNING, "验证码输入错误，请重新输入");
                    return errpage;
                }
            }

            token = SysPara.getValue("sys_token");//0：默认，采用原有cookie方式，1：采用无cookie方式
            mainpage = SysPara.getValue("sys_mainpage");//登录后的主页面地址

            if (!mainpage.startsWith(ConstantUtil.SYMB_XIEXIAN)){//如果目标开始的第一个字符不是/则添加一个/
                mainpage = ConstantUtil.SYMB_XIEXIAN + mainpage;
            }
            errpage=StringUtil.subEndStr(errpage,".jsp");
            mainpage=StringUtil.subEndStr(mainpage,".jsp");

            validateStr = this.validateUser(request, response, token);
        } catch (Exception e) {
            errpage = "/login";
            e.printStackTrace();
            return errpage;
        }

        if(!"".equals(validateStr) && !validateStr.equals(ConstantUtil.NUM_ZERO)){
            log.info("{}试图登录系统时验证失败", dlmc);
            request.setAttribute(ConstantUtil.CODE_WARNING, validateStr);
            return errpage;
        }
        if(validateStr.equals(ConstantUtil.NUM_ZERO)){
            request.setAttribute(ConstantUtil.CODE_WARNING, "您已登录，不能重复登录！");
        }

        return mainpage;
    }

    public boolean checkValidateCode(HttpServletRequest req,String code){
        String sessionCode=(String) req.getSession().getAttribute(TmConstant.KEY_VERYCODE);
        return code.equalsIgnoreCase(sessionCode);
    }

    private final String pageInputMem = "mem_pass";
    private final String cookieNameKey = "sdtjqf_ict_username";//cookie中用户名key
    private final String cookiePassKey = "sdtjqf_ict_password";
    private final String cookieTokenKey = "sdtjqf_ict_token";

    /**
     * 验证用户
     * @param request
     * @return
     * @throws Exception
     */

    private String validateUser(HttpServletRequest request, HttpServletResponse response, String token) throws Exception{
        String dlmc = StringUtil.isNull(request.getParameter(pageInputDd));
        String dlmm = StringUtil.isNull(request.getParameter(pageInputMm));
        int cookieOrPage = 0;//0=page;1=cookie
        if ("".equals(dlmc)) {//如果调用的方法中Resquest里面没有用户名，则去Cookie中查询用户名与密码
            dlmc = ConstantUtil.getCookieValue(request, cookieNameKey);
            dlmm = ConstantUtil.getCookieValue(request, cookiePassKey);
            Des des = new Des();
            dlmm = des.getDesString(dlmm);
            cookieOrPage = 1;
        }
        if(dlmc.equals("")){
            return "错误：请重新登录";
        }
        Map<String, String> yhxx = loginMapper.queryYhidByDlmc(dlmc);
        if(yhxx==null){
            return "用户名或密码错误！！";
        }
        String yhid = "";
        String yhxm = "";
        if(yhxx.get("yhid").length() > 0){
            yhid = yhxx.get("yhid");
            yhxm = yhxx.get("xm");
        }else{
            return "用户名不存在！！";
        }


        if (!validate(dlmc, dlmm)) {
            return "用户名或密码错误！！";
        }
        String ipAddr = ConstantUtil.getRequestIp(request);
        //如果不允许重复登录
        String sysLogin = SysPara.getValue("sys_login");//登录控制；1:后登录用户踢出之前登录用户；2：不允许重复登录；3：无限制
        if ((sysLogin.indexOf("2") != -1) && (!validateToken(yhid, ipAddr))) {
            return ConstantUtil.NUM_ZERO;
        }
        request.setAttribute(ConstantUtil.YHID_KEY, yhid);
        if (token.equals(ConstantUtil.NUM_ZERO)) {
            Cookie ckie = new Cookie("sdtjqf_ict_session_cookie", Guid.get());
            ckie.setMaxAge(-1);
            ckie.setPath("/");
            response.addCookie(ckie);
            if (cookieOrPage == 0) {//登录信息来自页面
                Cookie ckieu = new Cookie(cookieNameKey, dlmc);
                ckieu.setMaxAge(31536000);
                response.addCookie(ckieu);
                if ("1".equals(request.getParameter(pageInputMem))) {//记住密码
                    Cookie ckiep = new Cookie(cookiePassKey, new Des().getEncString(dlmm));
                    ckiep.setMaxAge(31536000);
                    response.addCookie(ckiep);
                }
                //this.setLoginInfo(request, response, yhid, dlmc);
                String gentoken = "";
                try {
                    gentoken = Token.getToken(Guid.get());
                } catch (Exception localException) {
                    gentoken = Md5.getMd5(Guid.get() + "eqioz;d238*l");
                }
                String addrIP = request.getRemoteAddr();//客户端真实IP
                String[] arrayOfString = this.insertSessionInfo(yhid, addrIP, gentoken);
                if (!"".equals(arrayOfString[0])){
                    if (addrIP.equals(arrayOfString[1])) {
                        if (SysPara.compareValue("session_storage", ConstantUtil.NUM_ONE, ConstantUtil.NUM_ONE)){
                           loginMapper.deleteUserSession(yhid);
                        }else{
                           // this.resetCacheYhid(arrayOfString[0]);
                            EhCacheUtils.removeSysInfo(ConstantUtil.YHID_KEY,arrayOfString[0]);
                            EhCacheUtils.removeSysInfo(ConstantUtil.CACHE_LOGIN_TIME,arrayOfString[0]);
                            EhCacheUtils.removeSysInfo(ConstantUtil.CACHE_ACTIVE_TIME,arrayOfString[0]);
                            //Cache.removeInfo(ConstantUtil.ZTDM_KEY, arrayOfString[0]);
                            EhCacheUtils.removeSysInfo(ConstantUtil.IP_KEY,arrayOfString[0]);
                        }
                    } else {
                        String str1 = "在" + arrayOfString[1] + "上登录的用户" + dlmc + "将自动退出";
                        request.setAttribute(ConstantUtil.IP_KEY, arrayOfString[1]);
                        request.setAttribute("prompt", str1);
                    }
                }
                EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_DLMC, yhid, dlmc);
                this.cacheInfo(yhid);

                Cookie localCookie = new Cookie(cookieTokenKey, gentoken);
                localCookie.setPath("/");
                localCookie.setMaxAge(31536000);
                response.addCookie(localCookie);
                request.setAttribute(cookieTokenKey, gentoken);
                //ydxsLoginMapper.updateDlcs(yhid);


            }else{//来自cookie
                this.resetLoginInfo(request, response, dlmc, yhid);
            }
        }
        log.info("{}授权进入系统", yhxm);
        return "";
    }
    /**
     * 重置登录信息
     * @param request
     * @param response
     * @param dlmc
     * @param yhid
     */
    private void resetLoginInfo(HttpServletRequest request,
                                HttpServletResponse response, String dlmc,
                                String yhid) {
        String token = loginMapper.queryLoginToken(yhid);
        String tmpToken = StringUtil.isNull(token);
        if (!tmpToken.equals("")) {
            request.setAttribute(cookieTokenKey, tmpToken);
        }
        EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_DLMC,yhid, dlmc);
        //查询Cache中是否有该用户，没有则将用户信息放入Cache中
        if (EhCacheUtils.getUserInfo(ConstantUtil.XM_KEY,yhid) == null){
            this.cacheInfo(yhid);
        }
        //ydxsLoginMapper.updateDlcs(yhid);
    }
    /**
     * 将部分信息存入Cache中
     * @param yhid
     * @throws Exception
     */

    public void cacheInfo(String yhid){
        LoginBean lbean = this.getLoginBean(yhid);
        EhCacheUtils.putUserInfo(ConstantUtil.ZZID_KEY,yhid,  lbean.getZzid());
        EhCacheUtils.putUserInfo(ConstantUtil.BMID_KEY,yhid,  lbean.getBmid());
        EhCacheUtils.putUserInfo(ConstantUtil.GWID_KEY,yhid,  lbean.getGwid());
        EhCacheUtils.putUserInfo(ConstantUtil.XM_KEY,yhid,  lbean.getXm());
        EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_USERBEAN,yhid,lbean);
        EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_JS,yhid,  lbean.getJsList());
        if(!"admin".equals(yhid)&&!"1".equals(yhid)){
            EhCacheUtils.putUserInfo("orgMap",yhid,  lbean.getOrgMap());
            EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_ORG,yhid,  this.getOrgInfo(yhid));
            EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_URL,yhid,  this.getUrlByYhid(yhid));
        }
        //EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_GW_SEL,yhid,  ydxsLoginMapper.queryYhGwId(yhid));
        //EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_METHOD,yhid,  this.getMethodByYhid(yhid));
        //EhCacheUtils.putUserInfo(ConstantUtil.CACHE_YH_ISMANAGER, yhid, this.isManagerYhid(yhid));
        //非admin账号时将用户的部门信息放到缓存中
       /* if (!ConstantUtil.CON_ADMIN.equals(yhid)){
            this.setUserBmZzid(yhid, lbean);
        }
        if (Cache.getGlobalInfo("", ConstantUtil.CACHE_GLOBAL_METHOD) == null){
            Cache.setGlobalInfo("", ConstantUtil.CACHE_GLOBAL_METHOD, ydxsLoginMapper.queryQxMethodUrl(""));
        }
        if (Cache.getGlobalInfo("", ConstantUtil.CACHE_GLOBAL_URL) == null){
            Cache.setGlobalInfo("", ConstantUtil.CACHE_GLOBAL_URL, ydxsLoginMapper.queryQxMxUrl());
        }
        if (Cache.getGlobalInfo("", ConstantUtil.CACHE_GLOBAL_ISGROUP) == null){
            Cache.setGlobalInfo("", ConstantUtil.CACHE_GLOBAL_ISGROUP, Boolean.valueOf(Group.isGroup()));
        }*/
    }
    private LoginBean getLoginBean(String yhid){
        LoginBean lBean = new LoginBean();
        Map<String, Object> userMap = userMapper.queryUserMapByYhid(yhid);
        lBean.setUserMap(userMap);
        if (ConstantUtil.CON_ADMIN.equals(yhid)||"1".equals(yhid)) {
            lBean.setBmid(ConstantUtil.NUM_ZERO);
            lBean.setGwid(ConstantUtil.NUM_ZERO);
            lBean.setYhid(yhid);
            lBean.setZzid(ConstantUtil.NUM_ZERO);
            lBean.setXm(String.valueOf(userMap.get("nickname")));
            lBean.setJsList(new ArrayList<Map<String, String>>());
            return lBean;
        }
        String zzid = "";
        String zzmc = "";
        //登录用户的组织信息
        Map<String, Object> orgMap = loginMapper.queryOrgMapByYhid(yhid);
        if(orgMap!=null){

            zzid = orgMap.get("id").toString();

        }

        List<Map<String, String>> bmList = loginMapper.queryLoginBmList(zzid,yhid);
        List<Map<String, String>> gwList = loginMapper.queryLoginGwList(zzid,yhid);
        List<Map<String, String>> jsList = loginMapper.queryLoginJsList(zzid,yhid);

        if(userMap!=null){
            String xm=String.valueOf(userMap.get("nickname"));
            lBean.setXm(xm);
        }
        lBean.setBmList(bmList);
        if (bmList.size() > 0){
            lBean.setBmid(String.valueOf(bmList.get(0).get(ConstantUtil.DM_KEY)));
        }
        lBean.setGwList(gwList);
        if (gwList.size() > 0){
            lBean.setGwid(String.valueOf(gwList.get(0).get(ConstantUtil.DM_KEY)));
        }
        lBean.setJsList(jsList);
        lBean.setYhid(yhid);
        lBean.setZzid(zzid);
        lBean.setOrgMap(orgMap);

        return lBean;
    }
    /**
     * 用户的权限列表
     * @param yhid
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getQxByYhid(String yhid) {
        List<Map<String,Object>> qxList=qxMapper.queryQxByYhid(yhid);

        return qxList;
    }
    /**
     * 用户的url权限列表,除去url为空的
     * @param yhid
     * @return
     */
    private List<String> getUrlByYhid(String yhid) {
        List<Map<String,Object>> qxList=this.getQxByYhid(yhid);
        List<String> urls=new ArrayList<String>();
        if(qxList!=null&&qxList.size()>0){
            String url=null;
            for(Map<String,Object> map:qxList){
                url=String.valueOf(map.get("url"));
                if(!"".equals(url)&&!"null".equals(url)){
                    urls.add(url);
                }
            }
        }
        return urls;
    }
    /**
     * 登录用户的部门岗位信息
     * @param yhid
     * @return
     */
    private String getOrgInfo(String yhid) {
        StringBuffer sb = new StringBuffer();
        //登录用户的部门岗位信息（bm_mc,gw_mc,bmid,gwid）
        List<Map<String, Object>> org = loginMapper.queryLoginOrgInfo(yhid);
        if(org.size() > 0){
            for(int i=0;i<org.size();i++){
                Map<String, Object> m = org.get(i);
                sb.append(ConstantUtil.SYMB_FENHAO);
                sb.append(m.get("bm_mc"));
                sb.append(ConstantUtil.SYMB_MH);
                sb.append(m.get("gw_mc"));
                sb.append(ConstantUtil.SYMB_SHUXIAN);
                sb.append(m.get(ConstantUtil.BMID_KEY));
                sb.append(ConstantUtil.SYMB_MH);
                sb.append(m.get(ConstantUtil.GWID_KEY));
            }
        }
        sb.replace(0, 1, "");
        return sb.toString();
    }
    /**
     * 保存Session信息
     * @param yhid
     * @param ip
     * @param token
     * @return
     */

    private String[] insertSessionInfo(String yhid, String ip, String token) {
        String[] arrayOfString = { "", "" };
        String str1 = "";
        try {
            str1 = SysPara.getValue("sys_login");
        } catch (Exception localException1) {
            str1 = "1_3";
        }
        try {
            String topTk = "";
            if (str1.indexOf("1") != -1) {
                topTk = (String) EhCacheUtils.getUserInfo(ConstantUtil.CACHE_TOKEN,yhid);
                if (topTk != null){
                    arrayOfString[0] = yhid;
                    arrayOfString[1] = (String) EhCacheUtils.getSysInfo(ConstantUtil.IP_KEY, topTk);
                    EhCacheUtils.putSysInfo(ConstantUtil.CACHE_GLOBAL_ITOKEN, topTk, yhid);
                }
                EhCacheUtils.removeSysInfo(ConstantUtil.YHID_KEY , token);
                EhCacheUtils.removeSysInfo(ConstantUtil.CACHE_LOGIN_TIME , topTk);
                EhCacheUtils.removeSysInfo(ConstantUtil.CACHE_ACTIVE_TIME , topTk);
               // Cache.removeInfo(ConstantUtil.ZTDM_KEY, topTk);
                EhCacheUtils.removeSysInfo(ConstantUtil.IP_KEY, topTk);
            }
            EhCacheUtils.putSysInfo(ConstantUtil.YHID_KEY,token, yhid);//设置token与yhid对应关系,根据yhid获取token
            EhCacheUtils.putSysInfo(ConstantUtil.CACHE_LOGIN_TIME,token, yhid);
            EhCacheUtils.putSysInfo(ConstantUtil.CACHE_ACTIVE_TIME,token, Long.valueOf(new Date().getTime()));
            //EhCacheUtils.putSysInfo(ConstantUtil.ZTDM_KEY, token, Integer.valueOf(1));
            EhCacheUtils.putSysInfo(ConstantUtil.IP_KEY,token, ip);
            EhCacheUtils.putSysInfo(ConstantUtil.CACHE_TOKEN, yhid, token);//设置token与yhid对应关系,根据token获取yhid

            loginMapper.updateUserSession(yhid);
            loginMapper.insertUserSession(yhid, ip, token, "");
        } catch (Exception localException2) {
            String str3 = "缓存用户登录信息时出现异常:" + localException2.toString();
            log.error(str3);
            throw new ApplicationException("缓存用户登录信息时出现异常");
        }
        return arrayOfString;
    }

    /**
     * 验证token:yhid与ip对应，则正常;表示，在一个IP地址登录后，其它的地址不能登录
     * @param yhid
     * @param ip
     * @return
     */

    private boolean validateToken(String yhid, String ip) {
        String yhtoken = (String) EhCacheUtils.getUserInfo(ConstantUtil.CACHE_TOKEN,yhid);
        if (yhtoken != null) {
            String str2 = (String) EhCacheUtils.getSysInfo(ConstantUtil.IP_KEY, yhtoken);
            if (!ip.equals(str2)){
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户名与密码是否正确
     * @param dlmc
     * @param dlmm
     * @return
     */

    private boolean validate(String dlmc, String dlmm) {
        Map<String, String> localList = loginMapper.queryYhidByDlmc(dlmc);
        String yhid = localList.get(ConstantUtil.YHID_KEY);
        String sjkdlmm = localList.get("dlmm");


        //加密模式
        String enc_mode="4";//明文
        try{
            enc_mode=SysPara.getValue("sys_encryptPassword_mode");
        }catch (Exception ex){

        }
        if("1".equals(enc_mode)){//md5(盐+密码)
            dlmm = Md5.getMd5(yhid.toString()+dlmm);
        }else if("2".equals(enc_mode)){//md5(密码)
            dlmm = Md5.getMd5(dlmm);
        }else if("3".equals(enc_mode)){//md5(md5(密码))
            dlmm = Md5.getMd5(Md5.getMd5(dlmm));
        }else{//明文
        }
        return dlmm.equalsIgnoreCase(sjkdlmm);

    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String token = ConstantUtil.getCookieValue(request, cookieTokenKey);
        String yhid = this.getYhidByToken(token);
        String str2 = "";
        try {
            str2 = SysPara.getValue("login_redirect");
            str2=StringUtil.subEndStr(str2,".jsp");
        } catch (Exception localException) {
            str2 = "/login";
        }
        Cookie[] arrayOfCookie = request.getCookies();
        if (arrayOfCookie != null){
            for (int i = 0; i < arrayOfCookie.length; i++) {
                if (arrayOfCookie[i].getName().equals(cookieNameKey)){
                    continue;
                }
                arrayOfCookie[i].setValue(null);
                arrayOfCookie[i].setMaxAge(0);
                response.addCookie(arrayOfCookie[i]);
            }
        }
        if (!"".equals(token)) {
            loginMapper.deleteUserSessionToken(yhid, token);
            this.removeUserCacheYhid(yhid);
        }
        //request.getSession().removeAttribute(sessionFLKey);
        return str2;
    }
    /**
     * 移除登录用户的Cache
     * @param yhid
     */
    private void removeUserCacheYhid(String yhid) {
        EhCacheUtils.clearUserInfo(yhid);
       /* EhCacheUtils.removeUserInfo(ConstantUtil.YHID_KEY, yhid);
        EhCacheUtils.removeUserInfo(ConstantUtil.CACHE_YH_DLMC, yhid);
        EhCacheUtils.removeUserInfo(ConstantUtil.CACHE_LOGIN_TIME, yhid);
        EhCacheUtils.removeUserInfo(ConstantUtil.CACHE_ACTIVE_TIME, yhid);
        //Cache.removeInfo(ConstantUtil.ZTDM_KEY, yhid);
        EhCacheUtils.removeUserInfo(ConstantUtil.IP_KEY, yhid);*/
    }

}

