package com.markbro.dzd.base.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Area管理
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Controller("baseUtilController")
@RequestMapping
public class UtilController extends com.markbro.asoiaf.core.web.BaseController{


    /**
     * 跳转到用户选择页面
     */
    @RequestMapping("/base/util/yhxz")
    public String yhxz(Model model){
        Map map=getMap(request);
        String idInput= (String) map.get("idInput");
        String mcInput= (String) map.get("mcInput");
        String limit= String.valueOf(map.get("limit"));
        if("".equals(limit)||"null".equals(limit)){
            limit="1";
        }
        model.addAttribute("idInput",idInput);
        model.addAttribute("mcInput",mcInput);
        model.addAttribute("limit",limit);
        return "/base/util/yhxz";
    }
    /**
     * 图标选择
     */
    @RequestMapping(value = "/base/util/iconxz")
    public String iconselect(HttpServletRequest request, Model model) {
        Map map=getMap(request);
        String value= (String) map.get("value");
        String idInput= (String) map.get("idInput");
        model.addAttribute("value", value);
        model.addAttribute("idInput", idInput);
        return "/base/util/iconxz";
    }
}
