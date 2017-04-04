package com.markbro.dzd.cms.timeline.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.timeline.bean.Timeline;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 时光轴 dao
 * Created by wujiyue on 2016-12-06 22:31:55.
 */
@Repository
public interface TimelineMapper{
    public Timeline get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(Timeline timeline);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Timeline> timelines);
    public void update(Timeline timeline);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Timeline> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Timeline> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
