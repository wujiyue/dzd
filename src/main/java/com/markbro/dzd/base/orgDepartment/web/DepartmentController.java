package com.markbro.dzd.base.orgDepartment.web;
import com.markbro.dzd.base.orgDepartment.bean.Department;
import com.markbro.dzd.base.orgDepartment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.markbro.asoiaf.utils.string.StringUtil;
import org.springframework.ui.Model;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.markbro.asoiaf.core.utils.IdGen;
/**
 * Department管理
 * Created by wujiyue on 2016-06-12 22:39:32.
 */
@Controller
@RequestMapping("/base/department")
public class DepartmentController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected DepartmentService departmentService;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/base/department/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Department department,Model model){
        return "/base/department/add";
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
                    departmentService.delete(id);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.success, "删除成功!"));
                } catch (Exception e) {
                    logger.error("删除失败!", e);
                    redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MsgType.error, "删除失败!"));
                }
            }
            return "redirect:/base/department/list";
        }
    /**
     * 跳转到列表页面
     */
    @RequestMapping(value={"/list"})
    public String list(PageParam pageParam,Model model){
        Object departments=null;
        if(pageParam!=null&& StringUtil.notEmpty(pageParam.getSearchWords())){
              //todo 这里应该根据搜索关键词模糊查询
              departments=departmentService.find(getPageBounds(pageParam),getMap(request));
        }else{
              departments=departmentService.find(getPageBounds(pageParam),getMap(request));
        }
        model.addAttribute("departments",departments);
        model.addAttribute("pageParam",pageParam);
        return "/base/department/list";
    }
   /**
    * 跳转到编辑页面
    */
    @RequestMapping(value = "/edit")
    public String toEdit(Department department,Model model){
        if(department!=null&&department.getId()!=null){
            department=departmentService.get(department.getId());
        }
         model.addAttribute("department",department);
         return "/base/department/edit";
    }
   /**
    * 保存新增或者编辑的数据并重定向到列表页面
    */
    @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(Department department,
                           RedirectAttributes redirectAttributes,Model model){
            if(department.getId()==null){//新增保存
                try {
                    departmentService.add(department);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "新增成功!"));
                } catch (Exception e) {
                    logger.error("新增失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "新增失败!"));
                    redirectAttributes.addFlashAttribute("department", department);
                    return "redirect:/base/department/add";
                }
            }else{//编辑保存
                try {
                    department.setAvailable(1);//如果不设置1，默认0会有问题的
                    departmentService.update(department);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.success, "更新成功!"));
                } catch (Exception e) {
                    logger.error("更新失败!", e);
                    redirectAttributes.addFlashAttribute("msgObj", new Msg(Msg.MsgType.error, "更新失败!"));
                    redirectAttributes.addFlashAttribute("department", department);
                    return "redirect:/base/department/edit";
                }
            }
            return "redirect:/base/department/list";
        }
    //-----------json数据接口--------------------
    
	@ResponseBody
	@RequestMapping("/json/findByOrgid/{orgid}")
	public Object findByOrgid(@PathVariable java.lang.String orgid) {
		return departmentService.findByOrgid(getPageBounds(),orgid);
	}
    /**
	*找到已删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/findDeleted")
	public Object findDeleted() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",1);
		return departmentService.findByMap(getPageBounds(),map);
	}
    
	@ResponseBody
	@RequestMapping("/json/findBack/{ids}")
	public void findBack(@PathVariable java.lang.String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted",0);
		map.put("ids",ids);
		departmentService.updateByMapBatch(map);
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
		return departmentService.findByMap(getPageBounds(),map);
	}
    /**
     * 根据主键获得数据
     */
    @ResponseBody
    @RequestMapping(value = "/json/get/{id}")
    public Object get(@PathVariable java.lang.String id) {
        return departmentService.get(id);
    }
    /**
     * 获得分页json数据
     */
    @ResponseBody
    @RequestMapping("/json/find")
    public Object find() {
        return departmentService.find(getPageBounds(),getMap(request));
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    public void add(Department m) {
        java.lang.String id=IdGen.getGuid();
			m.setId(id);
        departmentService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Department m) {
        departmentService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    public void save(Department m) {
        if(m.getId()==null||"".equals(m.getId().toString())){
            java.lang.String id=IdGen.getGuid();
			m.setId(id);
            departmentService.add(m);
        }else{
            departmentService.update(m);
        }
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
		departmentService.updateByMap(map);
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
		departmentService.updateByMapBatch(map);
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
    public void delete(@PathVariable java.lang.String id) {
        departmentService.delete(id);
    }
    @ResponseBody
    @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
    public void deletes(@PathVariable java.lang.String[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组
         departmentService.deleteBatch(ids);
    }
}
