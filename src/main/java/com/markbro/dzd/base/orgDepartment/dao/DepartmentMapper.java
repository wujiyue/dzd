package com.markbro.dzd.base.orgDepartment.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.orgDepartment.bean.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 部门 dao
 * Created by wujiyue on 2016-07-17 11:52:55.
 */
@Repository
public interface DepartmentMapper{
    public Department get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Department department);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Department> departments);
    public void update(Department department);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Department> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Department> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Department> findByParentid(PageBounds pageBounds,Map<String,Object> map);

    public Integer findByParentidCount(@Param("parentid")String parentid,@Param("orgid")String orgid);

    public Integer findByOrgidCount(String parentid);
    //检测要删除的记录下是否有孩子
    public Integer checkForDelete(@Param("ids")String ids);

    public String getParentidsById(@Param("id")String id);

    public int updateSort(@Param("id")String id, @Param("sort")String sort);

    public Integer getMaxSortByParentid(@Param("parentid")String parentid);
}
