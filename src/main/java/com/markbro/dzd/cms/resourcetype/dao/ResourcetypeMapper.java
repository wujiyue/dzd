package com.markbro.dzd.cms.resourcetype.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.cms.resourcetype.bean.Resourcetype;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 资源分类 dao
 * Created by wujiyue on 2016-07-21 21:21:06.
 */
@Repository
public interface ResourcetypeMapper{
    public Resourcetype get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Resourcetype resourcetype);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Resourcetype> resourcetypes);
    public void update(Resourcetype resourcetype);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Resourcetype> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Resourcetype> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Resourcetype> findByParentid(PageBounds pageBounds,java.lang.String parentid);

    public Integer findByParentidCount(@Param("parentid")String parentid);

    //检测要删除的记录下是否有孩子
    public Integer checkForDelete(@Param("ids")String ids);

    public String getParentidsById(@Param("id")String id);

    public int updateSort(@Param("id")String id, @Param("sort")String sort);

    public Integer getMaxSortByParentid(@Param("parentid")String parentid);

    public List<Map<String,Object>> treeSelect();
}
