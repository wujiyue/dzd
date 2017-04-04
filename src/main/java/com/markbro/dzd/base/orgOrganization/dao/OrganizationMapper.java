package com.markbro.dzd.base.orgOrganization.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.orgOrganization.bean.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 组织机构 dao
 * Created by wujiyue on 2016-07-18 22:52:35.
 */
@Repository
public interface OrganizationMapper{
    public Organization get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Organization organization);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Organization> organizations);
    public void update(Organization organization);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Organization> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Organization> findByMap(PageBounds pageBounds,Map<String,Object> map);

}
