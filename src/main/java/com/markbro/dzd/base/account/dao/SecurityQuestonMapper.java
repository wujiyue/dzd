package com.markbro.dzd.base.account.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.account.bean.SecurityQueston;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 安全问题 dao
 * Created by wujiyue on 2016-08-22 23:47:01.
 */
@Repository
public interface SecurityQuestonMapper{
    public SecurityQueston get(String id);
    public Map<String,Object> getMap(String id);
    public void add(SecurityQueston securityQueston);
    public void addByMap(Map<String, Object> map);
    public void addBatch(List<SecurityQueston> securityQuestons);
    public void update(SecurityQueston securityQueston);
    public void updateByMap(Map<String, Object> map);
    public void updateByMapBatch(Map<String, Object> map);
    public void delete(String id);
    public void deleteBatch(String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<SecurityQueston> find(PageBounds pageBounds, Map<String, Object> map);
    public List<SecurityQueston> findByMap(PageBounds pageBounds, Map<String, Object> map);



}
