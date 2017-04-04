package com.markbro.dzd.cms.onlineupdate.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.onlineupdate.bean.Onlineupdate;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 在线升级 dao
 * Created by wujiyue on 2016-12-02 21:19:14.
 */
@Repository
public interface OnlineupdateMapper{
    public Onlineupdate get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(Onlineupdate onlineupdate);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Onlineupdate> onlineupdates);
    public void update(Onlineupdate onlineupdate);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Onlineupdate> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Onlineupdate> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
