package com.markbro.dzd.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class OaHandlerExceptionResolve implements HandlerExceptionResolver {
	private Log log = LogFactory.getLog(getClass());

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		this.log.warn("Handle exception: " + ex.getClass().getName());
		if ((ex instanceof NeedLoginException))
			return new ModelAndView("redirect:login.do");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex.getClass().getSimpleName());
		model.put("message", ex.getMessage());
		return new ModelAndView("error", model);
	}
}