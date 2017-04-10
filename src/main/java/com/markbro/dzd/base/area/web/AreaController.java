package com.markbro.dzd.base.area.web;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.area.bean.Area;
import com.markbro.dzd.base.area.service.AreaService;
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
 * 区域管理
 * Created by wujiyue on 2016-07-17 01:33:47.
 */
@Controller
@RequestMapping("/base/area")
public class AreaController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected AreaService areaService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/base/area/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Model model){
        Map map=getMap(request);
        String parentid= (String) map.get("parentid");
        String parentname= (String) map.get("parentname");
        String sjareatype= (String) map.get("areatype");//这是上级区域的区域类型
        String areatype="";
        String areatypename="";
        if(sjareatype.equalsIgnoreCase("ROOT")){
            areatype="PROVINCE";
            areatypename="省、自治区、直辖市";
        }
        if(sjareatype.equalsIgnoreCase("PROVINCE")){
            areatype="CITY";
            areatypename="地级市";
        }
        if(sjareatype.equalsIgnoreCase("CITY")){
            areatype="AREA";
            areatypename="区、县";
        }
        model.addAttribute("parentid",parentid);
        model.addAttribute("parentname",parentname);
        model.addAttribute("areatype",areatype);
        model.addAttribute("areatypename",areatypename);
        return "/base/area/add";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/edit")
    public String toEdit(Model model){
        Map map=getMap(request);
        String id=(String)map.get("id");
        Map<String,Object> res=areaService.getMap(id);
        String parentid=(String)res.get("parentid");
        String areatype= (String) res.get("areatype");
        if("0".equals(parentid)){
            res.put("parentname","区域目录");
        }
        String areatypename="";
        if(areatype.equalsIgnoreCase("PROVINCE")){
            areatypename="省、自治区、直辖市";
        }else if(areatype.equalsIgnoreCase("CITY")){
            areatypename="地级市";
        }
        else{
            areatypename="区、县";
        }
        res.put("areatypename",areatypename);
        model.addAttribute("area",res);
        return "/base/area/edit";
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
                    areaService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/base/area/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object areas=null;
        areas=areaService.find(getPageBounds(pageParam),getMap(request));
        model.addAttribute("areas",areas);
        model.addAttribute("pageParam",pageParam);
        return "/base/area/list";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(Area area,
                           RedirectAttributes redirectAttributes,Model model){
            if(area.getId()==null){//新增保存
                try {
                    areaService.add(area);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("area", area);
                    return "redirect:/base/area/add";
                }
            }else{//编辑保存
                try {
                    area.setAvailable(1);//如果不设置1，默认0会有问题的
                    areaService.update(area);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("area", area);
                    return "redirect:/base/area/edit";
                }
            }
            return "redirect:/base/area/list";
        }
    //-----------json数据接口--------------------
    
	@ResponseBody
	@RequestMapping("/json/findByParentid/{parentid}")
	public Object findByParentid(@PathVariable java.lang.String parentid) {
		return areaService.findByParentid(getPageBounds(),parentid);
	}
    /**
	*找到已删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/findDeleted")
	public Object findDeleted() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		return areaService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		areaService.updateByMapBatch(map);
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
		return areaService.findByMap(getPageBounds(),map);
	}
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return areaService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        resultMap=getPageMap(areaService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    @ActionLog(description="新增区域")
    public void add(Area m) {
        
        areaService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Area m) {
        areaService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    @ActionLog(description="保存区域")
    public Object save(Area m) {
        return  areaService.save(m);
    }
    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.String id){
	Msg msg=new Msg();
	try{
        int count=areaService.checkForDelete(id);
        if(count>0){
            msg.setType(Msg.MsgType.error);
            msg.setContent("删除的区域下不能有子区域！");
            return msg;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("deleted",1);
            map.put("id",id);
            areaService.updateByMap(map);
            msg.setType(Msg.MsgType.success);
            msg.setContent("删除成功！");
        }

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
        int count=areaService.checkForDelete(ids_str);
        if(count>0){
            msg.setType(Msg.MsgType.error);
            msg.setContent("删除的区域下不能有子区域！");
            return msg;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("deleted",1);
            map.put("ids",ids);
            areaService.updateByMapBatch(map);
            msg.setType(Msg.MsgType.success);
            msg.setContent("批量删除成功！");
        }

	}catch (Exception e){
		msg.setType(Msg.MsgType.error);
		msg.setContent("批量删除失败！");
	}
	return msg;
	}
    @ResponseBody
    @RequestMapping(value = "/json/delete/{id}", method = RequestMethod.POST)
    @ActionLog(description="物理删除区域")
    public void delete(@PathVariable java.lang.String id) {
        areaService.delete(id);
    }
    @ResponseBody
    @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
    @ActionLog(description="批量物理删除区域")
    public void deletes(@PathVariable java.lang.String[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
         areaService.deleteBatch(ids);
    }

    @ResponseBody
    @RequestMapping("/json/tree")
    public Object tree() {
        Map<String, Object> map = getMap(request);
        return areaService.tree(map);
    }
    @ResponseBody
    @RequestMapping("/json/saveSort")
    public Object saveSort() {
        return areaService.saveSort(getMap(request));
    }
}
