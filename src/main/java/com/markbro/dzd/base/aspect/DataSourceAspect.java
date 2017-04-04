package com.markbro.dzd.base.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 切换数据源(不同方法调用不同数据源)
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:17:52
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class DataSourceAspect {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Pointcut("execution(* com.markbro.dzd..service..*.*(..))")
	public void aspect() {
	}
	@Pointcut("execution(* *.sleep(..))")
	public void sleep() {
	}
	@After("sleep()")
	public void AfterSleep(JoinPoint point) {
		System.out.println("====After sleep========");
	}
	@Before("sleep()")
	public void beforeSleep(JoinPoint point) {
		System.out.println("====before sleep========");
	}
	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		//System.out.println("=========开始选择读、写数据源================");
		String className = point.getTarget().getClass().getName();
		String method = point.getSignature().getName();
		//logger.info("==============>"+className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
		try {
			L: for (String key : ChooseDataSource.METHODTYPE.keySet()) {
				for (String type : ChooseDataSource.METHODTYPE.get(key)) {
					if(type.endsWith("*")){
						type=type.substring(0,type.length()-1);
						if(type.startsWith(method)){
							//logger.info("======>选定"+key+"数据源");
							HandleDataSource.putDataSource(key);
							break L;
						}
					}else{
						if (method.startsWith(type)) {
							//logger.info("======>选定"+key+"数据源");
							HandleDataSource.putDataSource(key);
							break L;
						}
					}

				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
			HandleDataSource.putDataSource("write");
		}
	}

	@After("aspect()")
	public void after(JoinPoint point) {
		HandleDataSource.clear();
	}
}
