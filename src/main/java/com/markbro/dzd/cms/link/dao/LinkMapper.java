package com.markbro.dzd.cms.link.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.link.bean.Link;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 链接 dao
 * Created by wujiyue on 2016-08-14 22:55:33.
 */
@Repository
public interface LinkMapper{
    public Link get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Link link);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Link> links);
    public void update(Link link);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Link> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Link> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
