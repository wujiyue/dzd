package com.markbro.dzd.base.orgPosition.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.orgPosition.bean.Position;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * Position dao
 * Created by wujiyue on 2016-06-12 22:37:39.
 */
@Repository
public interface PositionMapper{
    public Position get(java.lang.String id);
    public void add(Position position);
    public void addBatch(List<Position> positions);
    public void update(Position position);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Position> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Position> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Position> findByOrgid(PageBounds pageBounds,java.lang.String orgid);
}
