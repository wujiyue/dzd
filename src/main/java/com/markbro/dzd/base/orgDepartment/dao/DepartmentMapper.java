package com.markbro.dzd.base.orgDepartment.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.orgDepartment.bean.Department;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * Department dao
 * Created by wujiyue on 2016-06-12 22:39:32.
 */
@Repository
public interface DepartmentMapper{
    public Department get(java.lang.String id);
    public void add(Department department);
    public void addBatch(List<Department> departments);
    public void update(Department department);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Department> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Department> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Department> findByOrgid(PageBounds pageBounds,java.lang.String orgid);
}
