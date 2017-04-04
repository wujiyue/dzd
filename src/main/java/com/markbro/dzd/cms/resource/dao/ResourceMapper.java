package com.markbro.dzd.cms.resource.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.cms.resource.bean.Resource;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 资源 dao
 * Created by wujiyue on 2016-07-27 22:30:43.
 */
@Repository
public interface ResourceMapper{
    public Resource get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Resource resource);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Resource> resources);
    public void update(Resource resource);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Resource> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Resource> findByMap(PageBounds pageBounds,Map<String,Object> map);

}
