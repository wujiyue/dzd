package com.markbro.dzd.base.orgDepartment.service;
import com.markbro.dzd.base.orgDepartment.bean.Department;
import com.markbro.dzd.base.orgDepartment.dao.DepartmentMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Department Service
 * Created by wujiyue on 2016-06-12 22:39:32.
 */
@Service
public class DepartmentService{
    @Autowired
    private DepartmentMapper departmentMapper;
     /*基础公共方法*/
    public Department get(java.lang.String id){
        return departmentMapper.get(id);
    }
    public List<Department> find(PageBounds pageBounds,Map<String,Object> map){
        return departmentMapper.find(pageBounds,map);
    }
    public List<Department> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return departmentMapper.findByMap(pageBounds,map);
    }
    public void add(Department department){
        departmentMapper.add(department);
    }
    public void addBatch(List<Department> departments){
        departmentMapper.addBatch(departments);
    }
    public void update(Department department){
        departmentMapper.update(department);
    }
    public void updateByMap(Map<String,Object> map){
        departmentMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        departmentMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        departmentMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        departmentMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Department> findByOrgid(PageBounds pageBounds,java.lang.String orgid){
		return departmentMapper.findByOrgid(pageBounds,orgid);
	}
}
