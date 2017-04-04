package com.markbro.dzd.base.account.web;

import com.markbro.asoiaf.core.utils.SysPara;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Area管理
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Controller
@RequestMapping
public class AccountController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    TableKeyService bmKeyService;


    /**
     * 跳转到个人信息设置页面
     */
    @RequestMapping("/account/personalInfo")
    public String personalInfo(){
        return "/sys/account/personalInfo";
    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping(value="/reg",method = RequestMethod.GET)
    public String toReg(){
       String regpage= "";
        try {
            regpage = SysPara.getValue("reg_page");
        } catch (Exception e) {
            regpage = "reg";
        }
        if(regpage.indexOf('.')>0)
        {
            regpage=regpage.substring(0,regpage.indexOf('.'));
        }
        if(!regpage.startsWith("/")){
            regpage="/"+regpage;
        }
        return regpage;
    }

}
