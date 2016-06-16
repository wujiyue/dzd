package com.markbro.dzd.base.area.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.area.bean.Area;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * Area dao
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Repository
public interface AreaMapper{
    public Area get(java.lang.Integer id);
    public void add(Area area);
    public void addBatch(List<Area> areas);
    public void update(Area area);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Area> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Area> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Area> findByParentid(PageBounds pageBounds,java.lang.Integer parentid);
}
