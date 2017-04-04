package com.markbro.dzd.sys.cploginLog.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.sys.cploginLog.bean.CploginLog;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 登录日志 dao
 * Created by wujiyue on 2016-12-05 21:59:57.
 */
@Repository
public interface CploginLogMapper{
    public CploginLog get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(CploginLog cploginLog);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<CploginLog> cploginLogs);
    public void update(CploginLog cploginLog);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<CploginLog> find(PageBounds pageBounds,Map<String,Object> map);
    public List<CploginLog> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
