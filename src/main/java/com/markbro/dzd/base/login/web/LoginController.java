package com.markbro.dzd.base.login.web;

import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.web.BaseController;
import com.markbro.dzd.base.login.service.LoginService;
import com.markbro.dzd.common.util.RequestUtil;
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
    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String toindex () {
        return "/index";
    }
    @RequestMapping (value = "/login", method = RequestMethod.GET)
     public String toLogin () {
        return "/login";
    }

    /**
     * 登录验证
     * @param request
     * @param model
     * @return
     */
    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model) {
        return loginService.validate(request, response);
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
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
