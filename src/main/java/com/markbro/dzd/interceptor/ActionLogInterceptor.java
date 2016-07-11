package com.markbro.dzd.interceptor;

import com.google.common.collect.Lists;
import com.markbro.asoiaf.core.TmConstant;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.dzd.base.actionlog.bean.Actionlog;
import com.markbro.dzd.base.actionlog.service.ActionlogService;
import com.markbro.dzd.base.login.bean.LoginBean;
import com.markbro.dzd.common.util.ConstantUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2015/6/16.
 * 自定义注解式的日志拦截器
 * @ActionLog可以用在类或者方法上，description说明该动作的含义
 */
public class ActionLogInterceptor extends HandlerInterceptorAdapter {
    //解决多线程变量共享的问题
    private ThreadLocal<Long> timeLocal=new ThreadLocal<Long>();
    Logger logger= LoggerFactory.getLogger(ActionLogInterceptor.class);
    @Autowired
    ActionlogService actionlogService;
    //Action之前执行:
    //如果返回false则中断请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long start=System.currentTimeMillis();
        timeLocal.set(start);


        return super.preHandle(request, response, handler);
    }
//生成视图之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
//最后执行，可用于释放资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long times=System.currentTimeMillis()-timeLocal.get();
        if(handler instanceof HandlerMethod){
            //获得session中的用户，用来判断该用户的行为是否该记录
            String yhid=(String)request.getSession().getAttribute(TmConstant.KEY_LOGIN_USER);
            LoginBean lb=(LoginBean) EhCacheUtils.getUserInfo(ConstantUtil.CACHE_YH_USERBEAN, yhid);
            //request.getServletContext().getInitParameter()
            ActionLog actionLog=null;
            HandlerMethod method=(HandlerMethod) handler;
            String mthd= method.getMethod().getName();

            JSONObject json=JSONObject.fromObject( getMap(request));
            //@ActionLog放在方法上
            actionLog=method.getMethod().getAnnotation(ActionLog.class);

            if(actionLog==null){//说明方法上没有注解，尝试从类上获取
                //@ActionLog放在类上
                actionLog=method.getBeanType().getAnnotation(ActionLog.class);
            }
            if(actionLog!=null){//
                String desc=actionLog.description();
                String  uri=request.getRequestURI();
                ActionLog.RecordType type= actionLog.recordTypes();

                Actionlog actionlog=new  Actionlog();
                actionlog.setDescription(desc);
                actionlog.setUri(uri);
                actionlog.setYhid(yhid);
                actionlog.setYhmc(lb.getXm());
                actionlog.setMethod(mthd);
                if(json!=null){
                    actionlog.setParams(json.toString());
                }
                actionlog.setActionTime(times.intValue());
                if(type.equals(ActionLog.RecordType.ALL)){//都记录

                    actionlogService.add(actionlog);
                }else if(type.equals(ActionLog.RecordType.ROLE)){//按角色

                    List<Map<String, String>> roleList=lb.getJsList();//登录用户的角色
                    String[] roleName=actionLog.roleNames();//来自注解要求做记录的角色名
                    List<String> roleNames= Lists.newArrayList(roleName);
                    List<String> names=roleList.stream().map(r->{return (String)r.get("mc");}).collect(Collectors.toList());//收集登录用户的角色名称
                    boolean shouldRecord=  names.stream().anyMatch(name->{return roleNames.stream().anyMatch(s->{return s.equals(name);});});
                    if(shouldRecord){
                        actionlogService.add(actionlog);
                    }
                }else {//按黑名单
                    if("2".equals(lb.getState())){
                        actionlogService.add(actionlog);
                    }
                }

            }
        }

        super.afterCompletion(request, response, handler, ex);
    }
    /**
     * 将HttpServletRequest对象转换成Map
     * @param req
     * @return
     */
    private  Map<String, Object> getMap(HttpServletRequest req) {
        Map mapReq = new HashMap();
        Enumeration enu = req.getParameterNames();
        while (enu.hasMoreElements()) {
            String paramName = (String) enu.nextElement();
            String[] values = req.getParameterValues(paramName);
            String value = "";
            for (int i = 0; i < values.length; i++) {
                value = value + (i == 0 ? "" : "~") + values[i];
            }
            if(paramName.startsWith("cx_")){
                paramName=paramName.replace("cx_","");
            }
            mapReq.put(paramName, value);
        }

        return mapReq;
    }
}
