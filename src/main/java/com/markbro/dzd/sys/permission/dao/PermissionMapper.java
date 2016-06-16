package com.markbro.dzd.sys.permission.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.sys.permission.bean.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * Permission dao
 * Created by wujiyue on 2016-06-13 21:13:46.
 */
@Repository
public interface PermissionMapper{
    public Permission get(java.lang.Integer id);
    public void add(Permission permission);
    public void addBatch(List<Permission> permissions);
    public void update(Permission permission);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Permission> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Permission> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Permission> findByParentid(PageBounds pageBounds,java.lang.Integer parentid);

    public List<Map<String,Object>> queryQxByYhid(String yhid);
}
