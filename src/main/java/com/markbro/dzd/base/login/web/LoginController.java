package com.markbro.dzd.base.login.web;

import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.SysPara;
import com.markbro.asoiaf.core.web.BaseController;
import com.markbro.dzd.base.login.service.LoginService;
import com.markbro.dzd.common.util.RequestUtil;
import com.markbro.dzd.interceptor.ActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登陆控制器
 *
 */
@Controller
public class LoginController extends BaseController {
    @Autowired
    protected LoginService loginService;

    @RequestMapping (value = {"/login","/"}, method = RequestMethod.GET)
     public String toLogin () {
        //return "/login";
        String loginpage= "";
        try {
            loginpage = SysPara.getValue("login_page");
        } catch (Exception e) {
            loginpage = "login";
        }
        if(loginpage.indexOf('.')>0)
        {
            loginpage=loginpage.substring(0,loginpage.indexOf('.'));
        }
        if(!loginpage.startsWith("/")){
            loginpage="/"+loginpage;
        }
        return loginpage;
    }

    /**
     * 登录验证
     * @param request
     * @param model
     * @return
     */
    @ActionLog(description = "后台登录")
    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model) {
        return loginService.validate(request, response,model);
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @ActionLog(description = "后台退出")
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        return loginService.logout(request, response);
    }


    @RequestMapping ("/index")
    public String index () {
        return "/index";
    }


    @ResponseBody
    @RequestMapping(value="/checkValidateCode",method = RequestMethod.POST)
    public Object save(HttpServletRequest request) {

        Msg msg=new Msg();
        Map<String, Object> map=  RequestUtil.getMap(request);
        String frontCode=String.valueOf(map.get("verycode"));
        /*if(checkValidateCode(request,frontCode)){
            msg.setType(Msg.MsgType.success);
            msg.setContent("验证码正确");
        }else{
            msg.setType(Msg.MsgType.error);
            msg.setContent("验证码错误");
        }*/
        return msg;
    }

}
