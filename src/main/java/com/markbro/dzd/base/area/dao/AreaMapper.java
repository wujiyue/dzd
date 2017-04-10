package com.markbro.dzd.base.area.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.area.bean.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 区域 dao
 * Created by wujiyue on 2016-07-17 01:33:47.
 */
@Repository
public interface AreaMapper{
    public Area get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Area area);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Area> areas);
    public void update(Area area);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Area> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Area> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Area> findByParentid(PageBounds pageBounds,java.lang.String parentid);

    public Integer checkForDelete(@Param("ids")String ids);

    public String getParentidsById(@Param("id")String id);

    public int updateSort(@Param("id")String id, @Param("sort")String sort);

    public Integer getMaxSortByParentid(@Param("parentid")String parentid);
}
