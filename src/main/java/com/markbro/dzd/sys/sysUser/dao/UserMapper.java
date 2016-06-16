package com.markbro.dzd.sys.sysUser.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.sys.sysUser.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * User dao
 * Created by wujiyue on 2016-06-13 19:57:42.
 */
@Repository
public interface UserMapper{
    public User get(java.lang.String id);
    public void add(User user);
    public void addBatch(List<User> users);
    public void update(User user);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<User> find(PageBounds pageBounds,Map<String,Object> map);
    public List<User> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<User> findByOrgid(PageBounds pageBounds,java.lang.String orgid);

    //查询用户Map
    public Map<String,Object> queryUserMapByYhid(@Param("yhid")String yhid);
}
