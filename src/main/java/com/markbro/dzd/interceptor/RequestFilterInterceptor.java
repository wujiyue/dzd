package com.markbro.dzd.interceptor;

import com.markbro.asoiaf.core.exception.ForbiddenException;
import com.markbro.asoiaf.core.exception.UnAuthorizedException;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.core.utils.SysPara;
import com.markbro.dzd.base.filter.AclVerify;
import com.markbro.dzd.base.login.service.LoginService;
import com.markbro.dzd.common.util.ConstantUtil;
import com.markbro.dzd.common.util.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求性能检测器
 */
public class RequestFilterInterceptor extends HandlerInterceptorAdapter {
    private String baseUrl;
    private final String sysTokenKey = "sys_token";
    private final String sdtjqfIctTokenKey = "sdtjqf_ict_token";
    Logger log= LoggerFactory.getLogger(MonitorInterceptor.class);
    @Autowired
    LoginService loginService;
    public RequestFilterInterceptor(){
        try {
            this.baseUrl=SysPara.getValue("baseUrl");
        } catch (Exception e) {
            e.printStackTrace();
            this.baseUrl="";
        }
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getServletPath();
        if (this.baseUrl.indexOf(uri) >= 0 || uri.indexOf("/wap/")>=0|| uri.indexOf("/vendorgys/")>=0){

            return true;
        }
        String sysToken = ConstantUtil.NUM_ZERO;//0采用原有cookie方式，1：采用无cookie方式
        try {
            sysToken = SysPara.getValue(sysTokenKey);
        } catch (Exception e) {
            sysToken = ConstantUtil.NUM_ZERO;
        }
        String token = request.getParameter("sys_token_khd");
        token = PatternUtil.isNull(token, String.valueOf(request.getParameter("sdtjqficttoken")));
        if(token.equals("")){
            if(PatternUtil.isNull(sysToken).equals(ConstantUtil.NUM_ZERO)){
                token = ConstantUtil.getCookieValue(request, sdtjqfIctTokenKey);
            }else{
                token = request.getParameter(sysTokenKey);
            }
        }else{
            Cookie cookie = new Cookie(sdtjqfIctTokenKey, token);
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        String yhid = loginService.getYhidByToken(token);
        yhid=PatternUtil.isNull(yhid);
        if (!yhid.equals("")) {
            if (!SysPara.compareValue("session_timeout", ConstantUtil.NUM_ZERO, "30")) {
                loginService.updateActiveTime(token);
            }
            request.setAttribute(ConstantUtil.YHID_KEY, yhid);
        } else {
            response.setContentType(ConstantUtil.HTML_CONTENTTYPE);
            String tishi = "";
            String yhidToken = (String) EhCacheUtils.getSysInfo("invalid_token", token);
            if ((yhidToken != null) && (!"".equals(yhidToken))){
                tishi = "您的帐号在另一地点登录，您被迫退出系统。";
            }else {
                tishi = "登录信息失效，请重新登录系统。";
            }
            request.setAttribute(ConstantUtil.CODE_WARNING, tishi);
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
            //throw new UnAuthorizedException(tishi);

		/*	PrintWriter writer = response.getWriter();
			String msg = this.createResponseStr(tishi, request.getContextPath());
			writer.write(msg);
			writer.close();
			return;*/
        }
        String method = request.getParameter("method");
        try {
            if(!ConstantUtil.CON_ADMIN.equals(yhid)&&!"1".equals(yhid)&&!(uri.indexOf("/json/")>0)){//登录用户调用json数据接口不检测权限
                AclVerify.verify(yhid, uri);
            }
        } catch (ForbiddenException e) {
            log.warn(yhid + "没有权限访问服务：" + uri + " ");
            throw new ForbiddenException(yhid + "没有权限访问服务：" + uri + " ");
 			/*response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("{alert:'您没有访问uri:" + uri + "的权限'}");
			response.getWriter().flush();
			response.getWriter().close();*/
            //return;
        } catch (UnAuthorizedException e) {
            log.warn(yhid + "没有权限访问服务的方法：" + uri + " " + " " + method);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(
                    "{alert:'您没有执行method:" + method + "的权限'}");
            response.getWriter().flush();
            response.getWriter().close();
            //return;
        }
        return true;
    }


}
