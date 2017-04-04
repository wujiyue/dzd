package com.markbro.dzd.cms.send_email.web;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.cms.send_email.bean.SendEmail;
import com.markbro.dzd.cms.send_email.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 发送邮件管理
 * Created by wujiyue on 2016-12-23 22:18:45.
 */

@Controller
@RequestMapping("/cms/sendEmail")
public class SendEmailController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected SendEmailService sendEmailService;

    @RequestMapping(value={"","/"})
    public String index(){
        return "/cms/sendEmail/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(SendEmail sendEmail,Model model){
        return "/cms/sendEmail/add";
    }

   /**
    * 跳转到编辑页面
    */
    @RequestMapping(value = "/edit")
    public String toEdit(SendEmail sendEmail,Model model){
        if(sendEmail!=null&&sendEmail.getId()!=null){
            sendEmail=sendEmailService.get(sendEmail.getId());
        }
         model.addAttribute("sendEmail",sendEmail);
         return "/cms/sendEmail/edit";
    }
    //-----------json数据接口--------------------
    
    
    
    

    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return sendEmailService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(sendEmailService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }
    /**
     * 不分页查询数据
     */
    @ResponseBody
    @RequestMapping("/json/findAll")
    public Object findAll() {
        Map map=getMap(request);
        String sortString=String.valueOf(map.get("sortString"));
        PageBounds pageBounds=null;
        if(StringUtil.notEmpty(sortString)){
             pageBounds=new PageBounds(Order.formString(sortString));
        }else{
             pageBounds=new PageBounds();
        }
        resultMap=getPageMap(sendEmailService.find(pageBounds,map));
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    public void add(SendEmail m) {
        
        sendEmailService.add(m);
    }


    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(SendEmail m) {
        sendEmailService.update(m);
    }


    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    public Object save() {
           return sendEmailService.save(getMap(request));
    }

    @ResponseBody
    @RequestMapping(value = "/json/delete/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable java.lang.String id) {
    	Msg msg=new Msg();
    	try{
    	    
            sendEmailService.delete(id);
            msg.setType(Msg.MsgType.success);
            msg.setContent("删除成功！");
        }catch (Exception e){
        		msg.setType(Msg.MsgType.error);
        		msg.setContent("删除失败！");
        }
        return msg;
    }


    @ResponseBody
    @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
    public Object deletes(@PathVariable java.lang.String[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
        Msg msg=new Msg();
    	try{
    	     
             sendEmailService.deleteBatch(ids);
             msg.setType(Msg.MsgType.success);
             msg.setContent("删除成功！");
         }catch (Exception e){
         	 msg.setType(Msg.MsgType.error);
         	 msg.setContent("删除失败！");
         }
         return msg;
    }

    
    
    
}
