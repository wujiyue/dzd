package com.markbro.dzd.base.orgPosition.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.orgPosition.bean.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 岗位管理 dao
 * Created by wujiyue on 2017-02-08 23:04:11.
 */
@Repository
public interface PositionMapper{

    public Position get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Position position);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Position> positions);
    public void update(Position position);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除的条件（deleted=0）
    public List<Position> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Position> findByMap(PageBounds pageBounds,Map<String,Object> map);

	//public List<Position> findByParentid(PageBounds pageBounds,java.lang.String parentid);
    public List<Position> findByParentid(PageBounds pageBounds,Map<String,Object> map);
	//public Integer findByParentidCount(@Param("parentid")String parentid);
    public Integer findByParentidCount(@Param("parentid")String parentid,@Param("orgid")String orgid);
    public Integer checkForDelete(@Param("ids")String ids);
	public String getParentidsById(@Param("id")String id);

	public int updateSort(@Param("id")String id, @Param("sort")String sort);
	public Integer getMaxSortByParentid(@Param("parentid")String parentid);

}
