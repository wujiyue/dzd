package com.markbro.dzd.base.orgDepartment.web;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.model.PageParam;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.orgDepartment.bean.Department;
import com.markbro.dzd.base.orgDepartment.dao.DepartmentMapper;
import com.markbro.dzd.base.orgDepartment.service.DepartmentService;
import com.markbro.dzd.base.orgTree.dao.OrgTreeMapper;
import com.markbro.dzd.interceptor.ActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * 部门管理
 * Created by wujiyue on 2016-07-17 11:52:55.
 */
@Controller
@RequestMapping("/org/department")
public class DepartmentController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    protected DepartmentService departmentService;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private OrgTreeMapper orgTreeMapper;
    @RequestMapping(value={"","/"})
    public String index(){
        return "/base/department/list";
    }
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/add")
    public String toAdd(Model model){
        Map map=getMap(request);
        model.addAttribute("parentid",(String) map.get("parentid"));
        model.addAttribute("parentname",(String) map.get("parentname"));
        return "/base/department/add";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/edit")
    public String toEdit(Model model){
        Map map=getMap(request);
        String id=(String)map.get("id");
        Map<String,Object> res=departmentService.getMap(id);
        String parentid= (String) res.get("parentid");
        if("0".equals(parentid)){
            String yhid= (String) map.get("yhid");
            Map<String,Object> orgMap= (Map<String, Object>) EhCacheUtils.getUserInfo("orgMap",yhid);
            res.put("parentname",(String)orgMap.get("name"));
        }
        model.addAttribute("department",res);
        return "/base/department/edit";
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
        departments=departmentService.find(getPageBounds(pageParam),getMap(request));
        model.addAttribute("departments",departments);
        model.addAttribute("pageParam",pageParam);
        return "/base/department/list";
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
	@RequestMapping("/json/findByParentid")
	public Object findByParentid() {
        Map map=getMap(request);
		return departmentService.findByParentid(getPageBounds(),map);
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
        resultMap=getPageMap(departmentService.find(getPageBounds(),getMap(request)));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value="/json/add",method = RequestMethod.POST)
    @ActionLog(description="新增部门")
    public void add(Department m) {
        
        departmentService.add(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/update",method = RequestMethod.POST)
    public void update(Department m) {
        departmentService.update(m);
    }
    @ResponseBody
    @RequestMapping(value="/json/save",method = RequestMethod.POST)
    @ActionLog(description="保存部门")
    public Object save() {
        Map map=getMap(request);
        return  departmentService.save(map);
    }
    /**
	* 逻辑删除的数据（deleted=1）
	*/
	@ResponseBody
	@RequestMapping("/json/remove/{id}")
	public Object remove(@PathVariable java.lang.String id){
	Msg msg=new Msg();
	try{
        int count=departmentService.checkForDelete(id);
        if(count>0){
            msg.setType(Msg.MsgType.error);
            msg.setContent("删除的部门下不能有子部门！");
            return msg;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("deleted",1);
            map.put("id",id);
            departmentService.updateByMap(map);
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
    @Transactional
	public Object removes(@PathVariable java.lang.String[] ids){
	Msg msg=new Msg();
	try{
        for(int i=0;i<ids.length;i++)
        {
            Department department=departmentMapper.get(ids[0]);
            orgTreeMapper.deleteByLxidAndType(department.getId(),"bm");
        }
        String ids_str= StringUtil.arrToString(ids, ",");
        int count=departmentService.checkForDelete(ids_str);
        if(count>0){
            msg.setType(Msg.MsgType.error);
            msg.setContent("删除的部门下不能有子部门！");
            return msg;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("deleted",1);
            map.put("ids",ids);
            departmentService.updateByMapBatch(map);
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
    @ActionLog(description="物理删除部门")
    public void delete(@PathVariable java.lang.String id) {
        departmentService.delete(id);
    }
    @ResponseBody
    @RequestMapping(value = "/json/deletes/{ids}", method = RequestMethod.POST)
    @ActionLog(description="批量物理删除部门")
    public void deletes(@PathVariable java.lang.String[] ids) {//前端传送一个用逗号隔开的id字符串，后端用数组接收，springMVC就可以完成自动转换成数组

         departmentService.deleteBatch(ids);
    }
    //easyui 动态树
    @ResponseBody
    @RequestMapping("/json/tree")
    public Object tree() {
        Map<String, Object> map = getMap(request);
        return departmentService.tree(map);
    }

    @ResponseBody
    @RequestMapping("/json/saveSort")
    public Object saveSort() {
        return departmentService.saveSort(getMap(request));
    }
}
