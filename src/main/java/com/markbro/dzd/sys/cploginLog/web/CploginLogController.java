package com.markbro.dzd.sys.cploginLog.web;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.interceptor.ActionLog;
import com.markbro.dzd.sys.cploginLog.bean.CploginLog;
import com.markbro.dzd.sys.cploginLog.service.CploginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * 登录日志管理
 * Created by wujiyue on 2016-12-05 21:59:57.
 */
@Controller
@RequestMapping("/sys/cploginLog")
public class CploginLogController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected CploginLogService cploginLogService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/sys/cploginLog/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Model model){
        Map map=getMap(request);
        return "/sys/cploginLog/add";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/edit")
    public String toEdit(Model model){
        Map map=getMap(request);
        Integer id=(Integer)map.get("id");
        Map<String,Object> cploginLog=cploginLogService.getMap(id);
        model.addAttribute("cploginLog",cploginLog);
        return "/sys/cploginLog/edit";
    }
    /**
     * 删除数据并重定向到列表页面
     */
     @RequestMapping ("/delete/{id}")
     public String delete (@PathVariable java.lang.Integer id, RedirectAttributes redirectAttributes) {
            if (false){//判断不能删除的条件
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "不能删除当前数据!"));
            }else {
                try {
                    cploginLogService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/sys/cploginLog/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object cploginLogs=null;
        cploginLogs=cploginLogService.find(getPageBounds(pageParam),getMap(request));
        model.addAttribute("cploginLogs",cploginLogs);
        model.addAttribute("pageParam",pageParam);
        return "/sys/cploginLog/list";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(CploginLog cploginLog,
                           RedirectAttributes redirectAttributes,Model model){
            if(cploginLog.getId()==null){//新增保存
                try {
                    cploginLogService.add(cploginLog);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("cploginLog", cploginLog);
                    return "redirect:/sys/cploginLog/add";
                }
            }else{//编辑保存
                try {
                    cploginLogService.update(cploginLog);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("cploginLog", cploginLog);
                    return "redirect:/sys/cploginLog/edit";
                }
            }
            return "redirect:/sys/cploginLog/list";
        }
    //-----------json数据接口--------------------
    
    
    
    
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.Integer id) {
        return cploginLogService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(cploginLogService.find(getPageBounds(),getMap(request)));
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
        resultMap=getPageMap(cploginLogService.find(pageBounds,map));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    @ActionLog(description="新增登录日志")
    public void add(CploginLog m) {
        
        cploginLogService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(CploginLog m) {
        cploginLogService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    @ActionLog(description="保存登录日志")
    public Object save(CploginLog m) {
        return  cploginLogService.save(m);
    }
    
    
     @ResponseBody
     @RequestMapping(value = "/json/delete/{id}", method = RequestMethod.POST)
     public Object delete(@PathVariable java.lang.Integer id) {
        Msg msg=new Msg();
        try{
        	
            cploginLogService.delete(id);
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
     public Object deletes(@PathVariable java.lang.Integer[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
        Msg msg=new Msg();
        try{
             cploginLogService.deleteBatch(ids);
             msg.setType(Msg.MsgType.success);
             msg.setContent("删除成功！");
        }catch (Exception e){
             msg.setType(Msg.MsgType.error);
             msg.setContent("删除失败！");
        }
             return msg;
     }



}
