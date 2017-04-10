package com.markbro.dzd.base.orgPosition.web;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.orgPosition.bean.Position;
import com.markbro.dzd.base.orgPosition.service.PositionService;
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
 * 岗位管理管理
 * Created by wujiyue on 2017-02-08 23:04:11.
 */

@Controller
@RequestMapping("/org/position")
public class PositionController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected PositionService positionService;

    @RequestMapping(value={"","/"})
    public String index(){
        return "/base/position/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Position position,Model model){
        return "/base/position/add";
    }

   /**
    * 跳转到编辑页面
    */
    @RequestMapping(value = "/edit")
    public String toEdit(Position position,Model model){
        if(position!=null&&position.getId()!=null){
            position=positionService.get(position.getId());
        }
         model.addAttribute("position",position);
         return "/base/position/edit";
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
		return positionService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		positionService.updateByMapBatch(map);
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
		return positionService.findByMap(getPageBounds(),map);
	}

    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return positionService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(positionService.find(getPageBounds(),getMap(request)));
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
        resultMap=getPageMap(positionService.find(pageBounds,map));
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    public void add(Position m) {
        
        positionService.add(m);
    }


    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Position m) {
        positionService.update(m);
    }


    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    public Object save(Position m) {
           return positionService.save(m);
    }

    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.String id){
	Msg msg=new Msg();
	try{
		int count=positionService.checkForDelete(id);
		if(count>0){
			msg.setType(Msg.MsgType.error);
			msg.setContent("删除的记录下不能有子记录！");
			return msg;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("id",id);
		positionService.updateByMap(map);
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
		int count=positionService.checkForDelete(ids_str);
		if(count>0){
			msg.setType(Msg.MsgType.error);
			msg.setContent("删除的记录下不能有子记录！");
			return msg;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		map.put("ids",ids);
		positionService.updateByMapBatch(map);
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
    	    
			int count=positionService.checkForDelete(id);
			if(count>0){
				msg.setType(Msg.MsgType.error);
				msg.setContent("删除的记录下不能有子记录！");
				return msg;
			}
            positionService.delete(id);
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
			int count=positionService.checkForDelete(ids_str);
			if(count>0){
				msg.setType(Msg.MsgType.error);
				msg.setContent("删除的记录下不能有子记录！");
				return msg;
			}
             positionService.deleteBatch(ids);
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
		Map<String, Object> map = getMap(request);
		map.put("parentid",parentid);
		return positionService.findByParentid(getPageBounds(),map);
	}

	//easyui 动态树
	@ResponseBody
	@RequestMapping("/json/tree")
	public Object tree() {
		Map<String, Object> map = getMap(request);
		return positionService.tree(map);
	}

    @ResponseBody
    @RequestMapping("/json/saveSort")
    public Object saveSort() {
        return positionService.saveSort(getMap(request));
    }

}