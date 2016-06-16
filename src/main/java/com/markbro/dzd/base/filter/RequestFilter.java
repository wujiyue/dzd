package com.markbro.dzd.base.filter;


import com.markbro.asoiaf.core.exception.ForbiddenException;
import com.markbro.asoiaf.core.exception.UnAuthorizedException;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.core.utils.SysPara;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.login.service.LoginService;
import com.markbro.dzd.common.util.ConstantUtil;
import com.markbro.dzd.common.util.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * filter工具类修改
 * @author yangsl
 */
public class RequestFilter implements Filter {
	
	private String baseUrl;
	private ServletContext servletContext;
	Logger log = LoggerFactory.getLogger(RequestFilter.class);
	
	private final String sysTokenKey = "sys_token";
	private final String sdtjqfIctTokenKey = "sdtjqf_ict_token";
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
			String uri = request.getServletPath();

		req.setCharacterEncoding("UTF-8");
		if (this.baseUrl.indexOf(uri) >= 0 || uri.indexOf("/wap/")>=0|| uri.indexOf("/vendorgys/")>=0||isStaticResources(uri)){
			chain.doFilter(request, response);
			return;
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
		WebApplicationContext context = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		LoginService loginService = (LoginService)context.getBean("loginService");
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

			//
			PrintWriter writer = response.getWriter();
			String msg = this.createResponseStr(tishi, request.getContextPath());
			writer.write(msg);
			writer.close();
			return;
		}
		String method = request.getParameter("method");
		try {
			if(!ConstantUtil.CON_ADMIN.equals(yhid)&&!"1".equals(yhid)){
				AclVerify.verify(yhid, uri);
			}
		} catch (ForbiddenException e) {
			log.warn(yhid + "没有权限访问服务：" + uri + " ");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("{alert:'您没有访问uri:" + uri + "的权限'}");
			response.getWriter().flush();
			response.getWriter().close();
			return;
		} catch (UnAuthorizedException e) {
			log.warn(yhid + "没有权限访问服务的方法：" + uri + " " + " " + method);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println(
					"{alert:'您没有执行method:" + method + "的权限'}");
			response.getWriter().flush();
			response.getWriter().close();
			return;
		}
		chain.doFilter(request, response);
	}
	
	private String createResponseStr(String msg, String basePath){
		String	errpage ="";
		try {
			errpage = SysPara.getValue("login_errorPage");
			errpage= StringUtil.subEndStr(errpage,".jsp");
		} catch (Exception e) {
			errpage="/login";
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("top.document.location.href='" + basePath + errpage);
		sb.append("</script>");
		return sb.toString();
	}
	/**
	 * 过滤链接与配置文件初始化。
	 */
	public void init(FilterConfig config) throws ServletException {
		this.baseUrl = config.getInitParameter("baseUrl");
		servletContext = config.getServletContext();  
	}
	public boolean isStaticResources(String uri){
		if(uri.endsWith(".js")||uri.endsWith(".css")||uri.endsWith(".jpg")||uri.endsWith(".png")||uri.endsWith(".bmp")||uri.endsWith(".gif")||uri.endsWith(".eot")||uri.endsWith(".svg")||uri.endsWith(".ttf")||uri.endsWith(".woff")){
			return true;
		}else{
			return false;
		}
	}
	public void destroy() {
	}
}
