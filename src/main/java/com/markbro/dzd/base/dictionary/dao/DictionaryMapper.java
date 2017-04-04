package com.markbro.dzd.base.dictionary.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.dictionary.bean.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 数据字典 dao
 * Created by wujiyue on 2016-07-05 22:19:54.
 */
@Repository
public interface DictionaryMapper{
    public Dictionary get(java.lang.Integer id);
    public void add(Dictionary dictionary);
    public void addBatch(List<Dictionary> dictionarys);
    public void update(Dictionary dictionary);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Dictionary> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Dictionary> findByMap(PageBounds pageBounds,Map<String,Object> map);
    public List<Dictionary> findByType(String type);
}
