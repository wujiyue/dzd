package com.markbro.dzd.base.actionlog.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.actionlog.bean.Actionlog;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 操作日志 dao
 * Created by wujiyue on 2016-07-03 21:07:55.
 */
@Repository
public interface ActionlogMapper{
    public Actionlog get(java.lang.Integer id);
    public void add(Actionlog actionlog);
    public void addBatch(List<Actionlog> actionlogs);
    public void update(Actionlog actionlog);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Actionlog> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Actionlog> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Actionlog> findByYhid(PageBounds pageBounds,java.lang.String yhid);
}
