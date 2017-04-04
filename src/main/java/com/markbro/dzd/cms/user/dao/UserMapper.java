package com.markbro.dzd.cms.user.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.user.bean.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 用户 dao
 * Created by wujiyue on 2016-12-11 22:58:09.
 */
@Repository
public interface UserMapper{
    public User get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(User user);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<User> users);
    public void update(User user);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<User> find(PageBounds pageBounds,Map<String,Object> map);
    public List<User> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
