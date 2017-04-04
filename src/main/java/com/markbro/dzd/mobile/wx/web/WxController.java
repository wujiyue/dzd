package com.markbro.dzd.mobile.wx.web;

import com.markbro.dzd.mobile.wx.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 便捷开票和微信扫描
 */
@Controller
@RequestMapping("/mobile/wx")
public class WxController extends com.markbro.asoiaf.core.web.BaseController{

    @Autowired
    WxService wxService;

    @ResponseBody
    @RequestMapping("/addUser")
    public Object addUser() throws Exception {

        return wxService.addUser(getMap(request));
    }
    //跳转到登录界面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginJump() throws Exception {
        return  "/mobile/wx/login";
    }
    //跳转到登录界面
    @RequestMapping(value = "/rwm",method = RequestMethod.GET)
    public String rwm() throws Exception {
        return  "/mobile/wx/rwm";
    }
    @RequestMapping(value = "/addrwm",method = RequestMethod.GET)
    public String addrwm() throws Exception {
        return  "/mobile/wx/addrwm";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login() throws Exception {
        return  wxService.login(request,getMap(request));
    }

    /**
     * 生成二维码保存信息
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/saverwm")
    public Object saverwm() throws Exception {

        return wxService.saverwm(getMap(request));
    }
    /**
     * 生成二维码更新信息
     */
    @ResponseBody
    @RequestMapping("/updaterwm")
    public Object updaterwm() throws Exception {

        return wxService.updaterwm(getMap(request));
    }


    /**
     * 根据主键查询企业名片信息
     */
  /*  @RequestMapping("/queryrwm")
    public Object queryrwm() throws Exception {
        WxService z = new WxService();
        return z.queryrwmByid(getMap(request));
    }*/
    /**
     * 查询企业名片信息列表
     */
    @ResponseBody
    @RequestMapping("/queryrwmlist")
    public Object queryrwmlist() throws Exception {

        return wxService.queryrwmlist(getMap(request));
    }
    /**
     * 根据主键删除企业名片
     */
    @ResponseBody
    @RequestMapping("/deleterwm")
    public Object deleterwm() throws Exception {

        return wxService.deleterwmByid(getMap(request));
    }
    /******************发票二维码********************/

   /* @RequestMapping(value = "/login?flag=1",method = RequestMethod.GET)
    public String loginWxSmJump() throws Exception {
        return  "/mobile/wxsm/login";
    }
    @RequestMapping(value = "/wxsm/index",method = RequestMethod.GET)
    public String indexWxSmJump() throws Exception {
        return  "/mobile/wx/wxsm/index";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object loginWxSm() throws Exception {
        return  wxService.login(request,getMap(request));
    }

    @ResponseBody
    @RequestMapping("/saverwmsm")
    public Object saverwmsm() throws Exception {
        return wxService.saverwmsm(getMap(request));
    }*/
    //查询发票扫描列表
  /*  @ResponseBody
    @RequestMapping("/queryFpsmList")
    public Object queryFpList(HttpServletRequest request) throws Exception {

        return  wxService.queryrwmsmlist(getMap(request));
    }
    @ResponseBody
    @RequestMapping("/deleteFpsm")
    public Object deleteFpsm(HttpServletRequest request) throws Exception {

        return wxService.deleteFpsm(getMap(request));
    }*/


}
