package com.markbro.dzd.cms.channel_big.web;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.cms.channel_big.bean.ChannelBig;
import com.markbro.dzd.cms.channel_big.service.ChannelBigService;
import com.markbro.dzd.interceptor.ActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栏目大类管理
 * Created by wujiyue on 2016-08-04 21:14:30.
 */
@Controller
@RequestMapping("/cms/channelBig")
public class ChannelBigController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected ChannelBigService channelBigService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/cms/channelBig/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Model model){
        Map map=getMap(request);
        return "/cms/channelBig/add";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/edit")
    public String toEdit(Model model){
        Map map=getMap(request);
        String id=(String)map.get("id");
        Map<String,Object> channelBig=channelBigService.getMap(id);
        model.addAttribute("channelBig",channelBig);
        return "/cms/channelBig/edit";
    }
    /**
     * 删除数据并重定向到列表页面
     */
     @RequestMapping ("/delete/{id}")
     public String delete (@PathVariable java.lang.String id, RedirectAttributes redirectAttributes) {
            if (false){//判断不能删除的条件
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "不能删除当前数据!"));
            }else {
                try {
                    channelBigService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/cms/channelBig/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object channelBigs=null;
        channelBigs=channelBigService.find(getPageBounds(pageParam),getMap(request));
        model.addAttribute("channelBigs",channelBigs);
        model.addAttribute("pageParam",pageParam);
        return "/cms/channelBig/list";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(ChannelBig channelBig,
                           RedirectAttributes redirectAttributes,Model model){
            if(channelBig.getId()==null){//新增保存
                try {
                    channelBigService.add(channelBig);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("channelBig", channelBig);
                    return "redirect:/cms/channelBig/add";
                }
            }else{//编辑保存
                try {
                    channelBig.setAvailable(1);//如果不设置1，默认0会有问题的
                    channelBigService.update(channelBig);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("channelBig", channelBig);
                    return "redirect:/cms/channelBig/edit";
                }
            }
            return "redirect:/cms/channelBig/list";
        }
    //-----------json数据接口--------------------
    
    /**
	*找到已删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/findDeleted")
	public Object findDeleted() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		return channelBigService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		channelBigService.updateByMapBatch(map);
	}
    /**
	* 找到无效的数据（未删除deleted=1，但是available=0）
	*/
	@ResponseBody
	 @RequestMapping("/json/findUnAvailable")
	public Object findUnAvailable() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("available",0);
		map.put("deleted",0);
		return channelBigService.findByMap(getPageBounds(),map);
	}
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return channelBigService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(channelBigService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/json/findAll")
    public Object findByPage() {
        Map map=getMap(request);
        String sortString=String.valueOf(map.get("sort"));
        PageBounds pageBounds=null;
        if(StringUtil.notEmpty(sortString)){
             pageBounds=new PageBounds(Order.formString(sortString));
        }else{
             pageBounds=new PageBounds();
        }
        return channelBigService.find(pageBounds,map);
    }
    @ResponseBody
    @RequestMapping("/json/find2")
    public Object find2() {
        resultMap=getPageMap(channelBigService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    @ActionLog(description="新增栏目大类")
    public void add(ChannelBig m) {
        
        channelBigService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(ChannelBig m) {
        channelBigService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    @ActionLog(description="保存栏目大类")
    public Object save(ChannelBig m) {
        return  channelBigService.save(m);
    }
    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.String id){
	Msg msg=new Msg();
	try{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("id",id);
		channelBigService.updateByMap(map);
		msg.setType(Msg.MsgType.success);
		msg.setContent("删除成功！");
	}catch (Exception e){
		msg.setType(Msg.MsgType.error);
		msg.setContent("删除失败！");
	}
	return msg;
	}
    /**
	* 批量逻辑删除的数据
	*/
	@ResponseBody
	@RequestMapping("/json/removes/{ids}")
	public Object removes(@PathVariable java.lang.String[] ids){
	Msg msg=new Msg();
	try{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("ids",ids);
		channelBigService.updateByMapBatch(map);
		msg.setType(Msg.MsgType.success);
		msg.setContent("批量删除成功！");
	}catch (Exception e){
		msg.setType(Msg.MsgType.error);
		msg.setContent("批量删除失败！");
	}
	return msg;
	}
     @ResponseBody
     @RequestMapping(value = "/json/delete/{id}", method = RequestMethod.POST)
     public Object delete(@PathVariable java.lang.String id) {
        Msg msg=new Msg();
        try{
        	
            channelBigService.delete(id);
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
        	 
             channelBigService.deleteBatch(ids);
             msg.setType(Msg.MsgType.success);
             msg.setContent("删除成功！");
        }catch (Exception e){
             msg.setType(Msg.MsgType.error);
             msg.setContent("删除失败！");
        }
             return msg;
     }

	@ResponseBody
	@RequestMapping("/json/saveSort")
	public Object saveSort() {
		return channelBigService.saveSort(getMap(request));
	}
    @ResponseBody
    @RequestMapping("/json/select")
    public Object select() {
        Map<String, Object> map = getMap(request);
        String sortString=String.valueOf(map.get("sort"));
        PageBounds pageBounds=null;
        if(StringUtil.notEmpty(sortString)){
            pageBounds=new PageBounds(Order.formString(sortString));
        }else{
            pageBounds=new PageBounds();
        }
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        List<ChannelBig> cs=channelBigService.find(pageBounds,map);
        Map<String,Object> temp=null;
        for(ChannelBig c:cs){
            temp=new HashMap<String,Object>();
            temp.put("dm",c.getId());
            temp.put("mc",c.getName());
            list.add(temp);
        }
        resultMap.put("channelBigSelect",list);
        return resultMap;
    }
}
