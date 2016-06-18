package com.markbro.dzd.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求性能检测器
 */
public class MonitorInterceptor extends HandlerInterceptorAdapter {
    //解决多线程变量共享的问题
    private ThreadLocal<Long> threadLocal=new ThreadLocal<Long>();
    Logger logger= LoggerFactory.getLogger(MonitorInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long start=System.currentTimeMillis();
        threadLocal.set(start);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long times=System.currentTimeMillis()-threadLocal.get();
        logger.info("请求uri:{}花费:{}毫秒",request.getRequestURI(),times);
        logger.info("最大内存: {}m", Runtime.getRuntime().maxMemory()/1024/1024);
        logger.info("已分配内存: {}m", Runtime.getRuntime().totalMemory()/1024/1024);
        logger.info("已分配内存中的剩余空间: {}m", Runtime.getRuntime().freeMemory()/1024/1024);
        logger.info("最大可用内存: {}m", (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);

        super.afterCompletion(request, response, handler, ex);
    }
}
