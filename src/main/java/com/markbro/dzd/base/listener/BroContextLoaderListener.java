package com.markbro.dzd.base.listener;

import com.markbro.asoiaf.core.utils.Global;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

public class BroContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+ Global.getConfig("productName")+"  - Powered By http://markbro.com\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return super.initWebApplicationContext(servletContext);
	}
}
