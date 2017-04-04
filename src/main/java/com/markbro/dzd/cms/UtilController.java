package com.markbro.dzd.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Area管理
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Controller("cmsUtilController")
@RequestMapping
public class UtilController extends com.markbro.asoiaf.core.web.BaseController{

    /**
     * 跳转联系人页面
     */
    @RequestMapping("/cms/contacts")
    public String lxr(Model model){
       // Map map=getMap(request);
        return "/cms/user/contacts";
    }
    /**
     * 跳转标签墙页面
     */
    @RequestMapping("/cms/pin_board")
    public String pin_board(Model model){
        // Map map=getMap(request);
        return "/cms/labelMsg/pin_board";
    }

    /**
     * 跳转联系人页面
     */
    @RequestMapping("/cms/tpq")
    public String tpq(Model model){
        // Map map=getMap(request);
        return "/cms/image/blueimp";
    }
    /**
     * 跳转到资源分类选择页面
     */
    @RequestMapping("/cms/util/zyflxz")
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
        return "/cms/util/zyflxz";
    }

}
