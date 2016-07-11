package com.markbro.dzd.sys.permission.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.sys.permission.bean.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 菜单权限 dao
 * Created by wujiyue on 2016-07-07 21:47:44.
 */
@Repository
public interface PermissionMapper{
    public Map<String,Object> get(java.lang.String id);
    public void add(Permission permission);
    public void addBatch(List<Permission> permissions);
    public void update(Permission permission);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Permission> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Permission> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Permission> findByParentid(PageBounds pageBounds,java.lang.String parentid);
    public List<Map<String,Object>> queryQxByYhid(String yhid);

    public Integer checkForDelete(@Param("ids")String ids);

    public String getParentidsById(@Param("id")String id);

    public int updateMenuSort(@Param("id")String id, @Param("sort")String sort);

    public Integer getMaxSortByParentid(@Param("parentid")String parentid);
}
