package com.markbro.dzd.cms.cpupdatelog.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.cpupdatelog.bean.CpupdateLog;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 更新日志 dao
 * Created by wujiyue on 2016-12-04 19:49:45.
 */
@Repository
public interface CpupdateLogMapper{
    public CpupdateLog get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(CpupdateLog cpupdateLog);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<CpupdateLog> cpupdateLogs);
    public void update(CpupdateLog cpupdateLog);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<CpupdateLog> find(PageBounds pageBounds,Map<String,Object> map);
    public List<CpupdateLog> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
