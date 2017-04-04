package com.markbro.dzd.cms.labelMsg.web;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.cms.labelMsg.bean.LabelMsg;
import com.markbro.dzd.cms.labelMsg.service.LabelMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 便签管理管理
 * Created by wujiyue on 2017-02-05 22:30:39.
 */

@Controller
@RequestMapping("/cms/labelMsg")
public class LabelMsgController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected LabelMsgService labelMsgService;

    @RequestMapping(value={"","/"})
    public String index(){
        return "/cms/labelMsg/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(LabelMsg labelMsg,Model model){
        return "/cms/labelMsg/add";
    }

   /**
    * 跳转到编辑页面
    */
    @RequestMapping(value = "/edit")
    public String toEdit(LabelMsg labelMsg,Model model){
        if(labelMsg!=null&&labelMsg.getId()!=null){
            labelMsg=labelMsgService.get(labelMsg.getId());
        }
         model.addAttribute("labelMsg",labelMsg);
         return "/cms/labelMsg/edit";
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
		return labelMsgService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.Integer[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		labelMsgService.updateByMapBatch(map);
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
		return labelMsgService.findByMap(getPageBounds(),map);
	}

    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.Integer id) {
        return labelMsgService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(labelMsgService.find(getPageBounds(),getMap(request)));
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
        resultMap=getPageMap(labelMsgService.find(pageBounds,map));
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    public void add(LabelMsg m) {
        
        labelMsgService.add(m);
    }


    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(LabelMsg m) {
        labelMsgService.update(m);
    }


    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    public Object save() {
        Map map=getMap(request);
        return labelMsgService.save(map);
    }

    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.Integer id){
	Msg msg=new Msg();
	try{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("id",id);
		labelMsgService.updateByMap(map);
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
	public Object removes(@PathVariable java.lang.Integer[] ids){
	Msg msg=new Msg();
	try{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("ids",ids);
		labelMsgService.updateByMapBatch(map);
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
    public Object delete(@PathVariable java.lang.Integer id) {
    	Msg msg=new Msg();
    	try{
    	    
            labelMsgService.delete(id);
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
             labelMsgService.deleteBatch(ids);
             msg.setType(Msg.MsgType.success);
             msg.setContent("删除成功！");
         }catch (Exception e){
         	 msg.setType(Msg.MsgType.error);
         	 msg.setContent("删除失败！");
         }
         return msg;
    }
}