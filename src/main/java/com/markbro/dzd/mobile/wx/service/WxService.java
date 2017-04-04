package com.markbro.dzd.mobile.wx.service;

import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.DbUtil;
import com.markbro.dzd.base.util.JspJsonData;
import com.markbro.dzd.common.exception.ServiceException;
import com.markbro.dzd.common.util.Guid;
import com.markbro.dzd.common.util.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 便捷开票和微信扫描Service
 * Created by Administrator on 2017-03-25.
 */
@Service
public class WxService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 用户注册
     * @return
     */
    public Object addUser(Map map) {
        Msg msg=new Msg();
        try {

            boolean b=this.checkUser(map);
            if(b){
                String flag=(String) map.get("flag");
                if("1".equals(flag)){//二维码扫描用户
                    String sql="insert into t_zcpt_wxuser(username,qymc,password,cjsj,flag) values('"+String.valueOf(map.get("username"))+"','"+String.valueOf(map.get("qymc"))+"','"+String.valueOf(map.get("password"))+"',now(),'1')";
                    DbUtil.getJtN().execute(sql);

                }else{
                    String sql="insert into t_zcpt_wxuser(username,password,cjsj) values('"+String.valueOf(map.get("username"))+"','"+String.valueOf(map.get("password"))+"',now())";
                    DbUtil.getJtN().execute(sql);
                }
                msg.setType(Msg.MsgType.success);
                msg.setContent("注册成功！");
            }else{
                msg.setType(Msg.MsgType.error);
                msg.setContent("用户名已经存在！");
            }
        } catch (Exception e) {
            msg.setType(Msg.MsgType.error);
            msg.setContent("便捷开票注册用户时出现错误！");
            logger.error("便捷开票注册用户时出现错误!");
            throw new ServiceException("便捷开票注册用户时异常");// 抛出此异常以触发回滚
        }
        return msg;
    }

    /**
     * 检测用户名
     * @param
     * @return
     */
    public boolean checkUser(Map map) {
        try {
            String flag="0";
            flag=String.valueOf(map.get("flag"));
            if(!"1".equals(flag)){
                flag="0";
            }
            String username=String.valueOf(map.get("username"));
            String sql="select * from t_zcpt_wxuser where username='"+username+"' and flag='"+flag+"'";
            List ls=     DbUtil.getJtN().queryForList(sql);
            if(ls!=null&&ls.size()>0){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            logger.error("检测便捷开票用户是否存在时出现错误");
            throw new ServiceException("检测便捷开票用户是否存在时异常");
        }
    }

    /**
     * 用户登录
     * @return
     */
    public Object login(HttpServletRequest request,Map map) {
        Msg msg=new Msg();
        try {
            String flag=String.valueOf(map.get("flag"));
            if(!"1".equals(flag)){
                flag="0";
            }
            String username=String.valueOf(map.get("username"));
            if(!"".equals(username)&&!"null".equals(username)){
                String sql="select * from t_zcpt_wxuser where username='"+username+"' and flag='"+flag+"' ";
                List<Map<String,Object>> list=DbUtil.getJtN().queryForList(sql);
                if(list!=null&&list.size()>0){
                    Map temp=list.get(0);
                    String pw=String.valueOf(temp.get("password"));
                    String front_pw=String.valueOf(map.get("password"));
                    if(pw.equals(front_pw)){
                        request.getSession().setAttribute("wx_login_username", username);
                        if("1".equals(flag)){
                            //发票扫描用户登录
                            String qymc=String.valueOf(temp.get("qymc"));
                         /*   mapRes.put("qymc", qymc);*/
                        }
                        msg.setType(Msg.MsgType.success);
                        msg.setContent("登录成功");
                    }else{

                        msg.setType(Msg.MsgType.error);
                        msg.setContent("用户名或者密码错误!");
                    }
                }else{
                    msg.setType(Msg.MsgType.error);
                    msg.setContent("用户名或者密码错误!");

                }
            }else{
                msg.setType(Msg.MsgType.error);
                msg.setContent("登录名不能为空!");
            }
        } catch (Exception e) {
            msg.setType(Msg.MsgType.error);
            msg.setContent("便捷开票用户登录时出现异常!");
            logger.info("便捷开票用户登录时出现异常");
            throw new ServiceException("便捷开票用户登录时异常");// 抛出此异常以触发回滚
        }
        return msg;
    }


    private String getRandomStr(){
        String s = "1234567890abcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        char[] chs=new char[6];
        char[] allowedCodes=s.toCharArray();
        int len=allowedCodes.length;
        for(int i=0;i<6;i++){
            int t=r.nextInt(len);
            chs[i]=allowedCodes[t];
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<6;i++){
            sb.append(chs[i]);
        }
        return sb.toString();
    }
    /**
     * 生成六位短尾ID
     * @return
     */
    public String generateId(){
        try {
            boolean flag=true;
            String id=getRandomStr();
            String sql="";
            List list=null;
            while(flag){
                sql="select * from t_zcpt_ewm where id='"+id+"'";
                list=DbUtil.getJtN().queryForList(sql);
                if(list!=null&&list.size()>0){
                    id=getRandomStr();
                }else{
                    flag=false;
                }
            }
            return id;
        }catch (Exception e) {
            throw new ServiceException("生成主键时时出现错误");
        }
    }
    public Object saverwm(Map map){
        Msg msg=new Msg();
        try {
            String username=String.valueOf(map.get("username"));
            String nsrmc=java.net.URLDecoder.decode(String.valueOf(map.get("nsrmc")),   "utf-8");
            String yhzh=java.net.URLDecoder.decode(String.valueOf(map.get("yhzh")),   "utf-8");
            String dz=java.net.URLDecoder.decode(String.valueOf(map.get("dz")),   "utf-8");
            String id=this.generateId();
            String str=id+","+String.valueOf(map.get("nsrsbh"))+","+nsrmc+","+yhzh+","+dz;
            //加密后的字符串
            //str=Encrypt.jiami(str, "qftj,071");
            String sql="insert into t_zcpt_ewm(id,username,nsrsbh,nsrmc,yhzh,dz,createtime,updatetime) values('"+id+"','"+username+"','"+map.get("nsrsbh")+"','"+nsrmc+"','"+yhzh+"','"+dz+"',now(),now())";
            DbUtil.getJtN().execute(sql);
            msg.setType(Msg.MsgType.success);
            msg.setContent("生成二维码保存企业信息成功！");
        } catch (Exception e) {
            msg.setType(Msg.MsgType.error);
            msg.setContent("生成二维码保存企业信息时出现错误！");
            throw new ServiceException("生成二维码保存企业信息时异常");
        }
        return msg;
    }

    public Object updaterwm(Map map){
        Msg msg=new Msg();
        try {
            String id=String.valueOf(map.get("id"));
            String username=String.valueOf(map.get("username"));
            String nsrmc=java.net.URLDecoder.decode(String.valueOf(map.get("nsrmc")),   "utf-8");
            String yhzh=java.net.URLDecoder.decode(String.valueOf(map.get("yhzh")),   "utf-8");
            String dz=java.net.URLDecoder.decode(String.valueOf(map.get("dz")),   "utf-8");
            String nsrsbh=String.valueOf(map.get("nsrsbh"));
            String str=id+","+nsrsbh+","+nsrmc+","+yhzh+","+dz;
            //加密后的字符串
            String sql="update t_zcpt_ewm set  nsrsbh='"+map.get("nsrsbh")+"',nsrmc= '"+nsrmc+"',yhzh='"+yhzh+"',dz='"+dz+"' where id='"+id+"' ";
            DbUtil.getJtN().execute(sql);
            msg.setType(Msg.MsgType.success);
            msg.setContent("生成二维码并更新企业信息成功!");
        } catch (Exception e) {
            msg.setType(Msg.MsgType.error);
            msg.setContent("生成二维码并更新企业信息时出现错误!");
            throw new ServiceException("生成二维码并更新企业信息时异常");// 抛出此异常以触发回滚
        }
        return msg;
    }

    //根据主键查询单条企业名片信息详情
   /* public Object queryrwmByid(Map map){
        Msg msg=new Msg();
        try {
            String sql="select * from  t_zcpt_ewm where id='"+map.get("id")+"'";
            Map res=null;
            List<Map<String,Object>> list=DbUtil.getJtN().queryForList(sql);
            if(list!=null&&list.size()>0){
                res=list.get(0);
				*//*String nsrsbh=String.valueOf(res.get("nsrsbh"));
				String nsrmc=String.valueOf(res.get("nsrmc"));
				String yhzh=String.valueOf(res.get("yhzh"));
				String dz=String.valueOf(res.get("dz"));
				String str=nsrsbh+","+nsrmc+","+yhzh+","+dz;*//*
                jjd.setResult(true, "查询企业名片信息成功！");
                jjd.setForm(res);
                logItem.setMethod("query");
                logItem.setLevel("info");
                Log.write(logItem);
            }else{
                jjd.setResult(true, "查询企业名片信息成功！");
                jjd.setExtend("insert_flag", true);
                logItem.setMethod("query");
                logItem.setLevel("info");
                Log.write(logItem);
            }

        } catch (Exception e) {

            jjd.setResult(false, "查询企业名片信息时出现错误，错误代码:" + guid);
            throw new ServiceException("查询企业名片信息时异常");// 抛出此异常以触发回滚
        }
        return jjd.getData();
    }*/
    //根据登录用户查询该用户的企业名片列表
    public Map queryrwmlist(Map map){
        JspJsonData jjd=new JspJsonData();
        try {
            String sql="select * from  t_zcpt_ewm where username='"+map.get("username")+"' ORDER BY updatetime DESC ";
            Map res=null;
            List<Map<String,Object>> list=DbUtil.getJtN().queryForList(sql);
            if(list!=null&&list.size()>0){
				/*String nsrsbh=String.valueOf(res.get("nsrsbh"));
				String nsrmc=String.valueOf(res.get("nsrmc"));
				String yhzh=String.valueOf(res.get("yhzh"));
				String dz=String.valueOf(res.get("dz"));
				String str=nsrsbh+","+nsrmc+","+yhzh+","+dz;*/
                int total=list.size();
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("total", total);
                m.put("rows", list);
             /*   m.put("result",true);
                m.put("msg","查询企业名片信息列表成功！");*/
                jjd.setResult(true,"查询企业名片信息列表成功！");
                jjd.setForm(m);
            }else{
                jjd.setResult(true, "查询企业名片信息列表成功！");
                jjd.setExtend("insert_flag", true);

            }

        } catch (Exception e) {

            jjd.setResult(false, "查询企业名片信息列表时出现错误!");
            throw new ServiceException("查询企业名片信息列表时异常");// 抛出此异常以触发回滚
        }
        return jjd.getData();
    }
    //根据主键删除单条企业名片信息详情
    public Object deleterwmByid(Map map){
        Msg msg=new Msg();
        try {
            String sql="delete from  t_zcpt_ewm where id='"+map.get("id")+"'";
            DbUtil.getJtN().execute(sql);
            msg.setType(Msg.MsgType.success);
            msg.setContent("删除企业名片成功！");
        } catch (Exception e) {
            msg.setType(Msg.MsgType.error);
            msg.setContent("删除企业名片失败！");
            throw new ServiceException("删除企业名片时异常");
        }
        return msg;
    }
    /**********************微信发票二维码扫描****************************/

    public Object saverwmsm(Map map){
        Msg msg=new Msg();
        try {
            String username=java.net.URLDecoder.decode(String.valueOf(map.get("username")),"utf-8");
            //String nsrmc=java.net.URLDecoder.decode(String.valueOf(map.get("qysh")),   "utf-8");
            //String yhzh=java.net.URLDecoder.decode(String.valueOf(map.get("yhzh")),   "utf-8");
            //String dz=java.net.URLDecoder.decode(String.valueOf(map.get("dz")),   "utf-8");
            //String id=this.generateId();
            //String str=id+","+String.valueOf(map.get("nsrsbh"))+","+nsrmc+","+yhzh+","+dz;
            String qysh=String.valueOf(map.get("qysh"));
            qysh= PatternUtil.isNull(qysh);
            String se=String.valueOf(map.get("se"));
            se=PatternUtil.isNull(se);
            String checksql="select count(1) from t_zcpt_ewmsm where qysh='"+username+"' and fpdm='"+map.get("fpdm")+"' and fphm='"+map.get("fphm")+"' ";
            int n=DbUtil.getJtN().queryForInt(checksql);
            if(n>0){
                msg.setType(Msg.MsgType.error);
                msg.setContent("该发票已经扫描过了！");
            }else{
                String sql="insert into t_zcpt_ewmsm(guid,qysh,fpdm,fphm,je,se,kpsj,smsj) values('"+ Guid.get()+"','"+username+"','"+map.get("fpdm")+"','"+map.get("fphm")+"','"+map.get("je")+"','"+se+"','"+map.get("kpsj")+"',now())";
                DbUtil.getJtN().execute(sql);
                msg.setType(Msg.MsgType.success);
                msg.setContent("保存发票二维码扫描信息成功！");
            }
        } catch (Exception e) {
            logger.error("保存发票二维码扫描信息时出现错误"+e.toString());
            msg.setType(Msg.MsgType.error);
            msg.setContent("保存发票二维码扫描信息时异常！");
            throw new ServiceException("保存发票二维码扫描信息时异常");
        }
        return msg;
    }



}
