package com.markbro.dzd.cms.channel.web;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.cms.channel.bean.Channel;
import com.markbro.dzd.cms.channel.service.ChannelService;
import com.markbro.dzd.interceptor.ActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * 栏目管理
 * Created by wujiyue on 2016-08-04 21:03:50.
 */
@Controller
@RequestMapping("/cms/channel")
public class ChannelController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected ChannelService channelService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/cms/channel/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Model model){
        Map map=getMap(request);
        return "/cms/channel/add";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/edit")
    public String toEdit(Model model){
        Map map=getMap(request);
        String id=(String)map.get("id");
        Map<String,Object> channel=channelService.getMap(id);
        model.addAttribute("channel",channel);
        return "/cms/channel/edit";
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
                    channelService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/cms/channel/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object channels=null;
        channels=channelService.find(getPageBounds(pageParam),getMap(request));
        model.addAttribute("channels",channels);
        model.addAttribute("pageParam",pageParam);
        return "/cms/channel/list";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(Channel channel,
                           RedirectAttributes redirectAttributes,Model model){
            if(channel.getId()==null){//新增保存
                try {
                    channelService.add(channel);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("channel", channel);
                    return "redirect:/cms/channel/add";
                }
            }else{//编辑保存
                try {
                    channel.setAvailable(1);//如果不设置1，默认0会有问题的
                    channelService.update(channel);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("channel", channel);
                    return "redirect:/cms/channel/edit";
                }
            }
            return "redirect:/cms/channel/list";
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
		return channelService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		channelService.updateByMapBatch(map);
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
		return channelService.findByMap(getPageBounds(),map);
	}
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return channelService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(channelService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    @ActionLog(description="新增栏目")
    public void add(Channel m) {
        
        channelService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Channel m) {
        channelService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    @ActionLog(description="保存栏目")
    public Object save(Channel m) {
        return  channelService.save(m);
    }
    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.String id){
	Msg msg=new Msg();
	try{
		int count=channelService.checkForDelete(id);
		if(count>0){
			msg.setType(Msg.MsgType.error);
			msg.setContent("删除的记录下不能有子记录！");
			return msg;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("id",id);
		channelService.updateByMap(map);
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
		String ids_str= StringUtil.arrToString(ids, ",");
		int count=channelService.checkForDelete(ids_str);
		if(count>0){
			msg.setType(Msg.MsgType.error);
			msg.setContent("删除的记录下不能有子记录！");
			return msg;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("ids",ids);
		channelService.updateByMapBatch(map);
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
        	
			int count=channelService.checkForDelete(id);
			if(count>0){
				msg.setType(Msg.MsgType.error);
				msg.setContent("删除的记录下不能有子记录！");
				return msg;
			}
            channelService.delete(id);
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
        	 
			String ids_str= StringUtil.arrToString(ids, ",");
			int count=channelService.checkForDelete(ids_str);
			if(count>0){
				msg.setType(Msg.MsgType.error);
				msg.setContent("删除的记录下不能有子记录！");
				return msg;
			}
             channelService.deleteBatch(ids);
             msg.setType(Msg.MsgType.success);
             msg.setContent("删除成功！");
        }catch (Exception e){
             msg.setType(Msg.MsgType.error);
             msg.setContent("删除失败！");
        }
             return msg;
     }

	@ResponseBody
	@RequestMapping("/json/findByParentid/{parentid}")
	public Object findByParentid(@PathVariable String parentid) {
		return channelService.findByParentid(getPageBounds(),parentid);
	}

	@ResponseBody
	@RequestMapping("/json/saveSort")
	public Object saveSort() {
		return channelService.saveSort(getMap(request));
	}

	//zTree简单数据格式的 动态树
	@ResponseBody
	@RequestMapping("/json/ztree")
	public Object tree() {
		Map<String, Object> map = getMap(request);
		return channelService.tree(map);
	}
}
