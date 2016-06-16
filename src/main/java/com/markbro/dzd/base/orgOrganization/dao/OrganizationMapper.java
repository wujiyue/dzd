package com.markbro.dzd.base.orgOrganization.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.orgOrganization.bean.Organization;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * Organization dao
 * Created by wujiyue on 2016-06-12 22:38:53.
 */
@Repository
public interface OrganizationMapper{
    public Organization get(java.lang.String id);
    public void add(Organization organization);
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
