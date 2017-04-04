package com.markbro.dzd.gen.config.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.gen.config.bean.Config;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 配置 dao
 * Created by wujiyue on 2016-11-23 16:38:40.
 */
@Repository
public interface ConfigMapper{
    public Config get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(Config config);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Config> configs);
    public void update(Config config);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Config> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Config> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
