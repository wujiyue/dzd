package com.markbro.dzd.base.area.web;

import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import com.markbro.dzd.base.area.bean.Area;
import com.markbro.dzd.base.area.service.AreaService;
import com.markbro.dzd.common.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Area管理
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Controller
@RequestMapping("/base/area")
public class AreaController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected AreaService areaService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/base/area/manager";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(){
        return "/base/area/add";
    }
    @RequestMapping("/list")
    public String toList(Model model){
      // Map params= RequestUtil.getMap(request);
       // model.addAttribute("params",params);
        return "/base/area/list";
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
    * 跳转到编辑页面
    */
    @RequestMapping(value = "/edit")
    public String toEdit(Area area,Model model){
        if(area!=null&&area.getId()!=null){
            area=areaService.get(area.getId());
        }
         model.addAttribute("area",area);
         return "/base/area/edit";
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
    @ResponseBody
    @RequestMapping("/test")
    public void test() {
         areaService.test();
    }
    //-----------json数据接口--------------------
    @ResponseBody
    @RequestMapping("/json/tree")
    public Object tree() {
        Map<String, Object> map =RequestUtil.getMap(request);
       return areaService.tree(map);
       /* List<Area> list= areaService.findByParentid(getPageBounds(), parentid);
        List<Area> childrenlist=null;//每个区域的孩子集合
        Map<String, Object> attributes=null;//每个区域的属性
        List<Map<String, Object>> nodelist =new ArrayList<Map<String,Object>>();//要返回的节点集合
        Map<String, Object> node=null;//节点

        for(Area areaMap:list){
            node=new HashMap<String, Object>();
            attributes=new HashMap<String, Object>();
            Integer id=areaMap.getId();

            node.put("id", areaMap.getId());
            node.put("text", areaMap.getName());

            attributes.put("areatype", areaMap.getAreatype());
            attributes.put("areano",areaMap.getCode());
            node.put("attributes", attributes);
            childrenlist=areaService.findByParentid(new PageBounds(),id);
            if(childrenlist!=null&childrenlist.size()>0){
                node.put("state", "closed");
            }
            nodelist.add(node);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//自动为我排除circle。
        JSONArray jsonArray = JSONArray.fromObject(nodelist, jsonConfig);
        return jsonArray.toString();*/

    }
	@ResponseBody
	@RequestMapping("/json/findByParentid/{parentid}")
	public Object findByParentid(@PathVariable java.lang.Integer parentid) {

        Object o=areaService.findByParentid(getPageBounds(),parentid);
       return o;
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
	public void findBack(@PathVariable java.lang.Integer[] ids) {
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
    public Object get(@PathVariable java.lang.Integer id) {
        return areaService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        return areaService.find(getPageBounds(),getMap(request));
    }

    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
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
    public Object save(HttpServletRequest request) {
        Area m=null;
        Msg msg=new Msg();
        Map<String, Object> map=  RequestUtil.getMap(request);
        try {

           m= MyBatisRequestUtil.convertToBean(map,new Area());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(m.getId()==null||"".equals(m.getId().toString())){
            areaService.add(m);
            msg.setType(Msg.MsgType.success);
            msg.setContent("新增区域信息成功！");
        }else{
            areaService.update(m);
            msg.setType(Msg.MsgType.success);
            msg.setContent("更新区域信息成功！");
        }
        return msg;
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
		areaService.updateByMap(map);
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
		areaService.updateByMapBatch(map);
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
    public void delete(@PathVariable java.lang.Integer id) {
        areaService.delete(id);
    }
    @ResponseBody
    @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
    public void deletes(@PathVariable java.lang.Integer[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
         areaService.deleteBatch(ids);
    }
}
