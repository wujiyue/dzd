package com.markbro.dzd.sys.permission.web;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.sys.permission.bean.Permission;
import com.markbro.dzd.sys.permission.service.PermissionService;
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
 * Permission管理
 * Created by wujiyue on 2016-06-13 21:13:46.
 */
@Controller
@RequestMapping("/sys/permission")
public class PermissionController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected PermissionService permissionService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/sys/permission/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Permission permission,Model model){
        return "/sys/permission/add";
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
                    permissionService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/sys/permission/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object permissions=null;
        if(pageParam!=null&& StringUtil.notEmpty(pageParam.getSearchWords())){
              //todo 这里应该根据搜索关键词模糊查询
              permissions=permissionService.find(getPageBounds(pageParam),getMap(request));
        }else{
              permissions=permissionService.find(getPageBounds(pageParam),getMap(request));
        }
        model.addAttribute("permissions",permissions);
        model.addAttribute("pageParam",pageParam);
        return "/sys/permission/list";
    }
   /**
    * 跳转到编辑页面
    */
    @RequestMapping(value = "/edit")
    public String toEdit(Permission permission,Model model){
        if(permission!=null&&permission.getId()!=null){
            permission=permissionService.get(permission.getId());
        }
         model.addAttribute("permission",permission);
         return "/sys/permission/edit";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(Permission permission,
                           RedirectAttributes redirectAttributes,Model model){
            if(permission.getId()==null){//新增保存
                try {
                    permissionService.add(permission);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("permission", permission);
                    return "redirect:/sys/permission/add";
                }
            }else{//编辑保存
                try {
                    permission.setAvailable(1);//如果不设置1，默认0会有问题的
                    permissionService.update(permission);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("permission", permission);
                    return "redirect:/sys/permission/edit";
                }
            }
            return "redirect:/sys/permission/list";
        }
    //-----------json数据接口--------------------
    
	@ResponseBody
	@RequestMapping("/json/findByParentid/{parentid}")
	public Object findByParentid(@PathVariable java.lang.Integer parentid) {
		return permissionService.findByParentid(getPageBounds(),parentid);
	}
    /**
	*找到已删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/findDeleted")
	public Object findDeleted() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		return permissionService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.Integer[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		permissionService.updateByMapBatch(map);
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
		return permissionService.findByMap(getPageBounds(),map);
	}
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.Integer id) {
        return permissionService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        return permissionService.find(getPageBounds(),getMap(request));
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    public void add(Permission m) {
        permissionService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Permission m) {
        permissionService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    public void save(Permission m) {
        if(m.getId()==null||"".equals(m.getId().toString())){
            permissionService.add(m);
        }else{
            permissionService.update(m);
        }
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
		permissionService.updateByMap(map);
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
		permissionService.updateByMapBatch(map);
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
        permissionService.delete(id);
    }
    @ResponseBody
    @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
    public void deletes(@PathVariable java.lang.Integer[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
         permissionService.deleteBatch(ids);
    }
}
