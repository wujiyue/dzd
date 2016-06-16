package com.markbro.dzd.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Area管理
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Controller
@RequestMapping("/base")
public class BaseController extends com.markbro.asoiaf.core.web.BaseController{

    /**
     * 跳转到敏感词页面
     */
    @RequestMapping("/sensitivewords")
    public String sensitivewords(){
        return "/base/sensitivewords";
    }
    /**
     * 跳转到基础设置页面
     */
    @RequestMapping("/settings")
    public String settings(){
        return "/base/settings";
    }
    /**
     * 跳转到系统日志页面
     */
    @RequestMapping("/syslog")
    public String syslog(){
        return "/base/syslog";
    }
}
