package com.markbro.dzd.base.orgRole.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.orgRole.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * Role dao
 * Created by wujiyue on 2016-06-12 22:35:18.
 */
@Repository
public interface RoleMapper{
    public Role get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Role role);
    public void addMap(Map<String,Object> map);
    public void addBatch(List<Role> roles);
    public void update(Role role);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Role> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Role> findByMap(PageBounds pageBounds,Map<String,Object> map);

}
