package com.markbro.dzd.cms.brand.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.brand.bean.Brand;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 品牌 dao
 * Created by wujiyue on 2016-09-17 17:58:15.
 */
@Repository
public interface BrandMapper{
    public Brand get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(Brand brand);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Brand> brands);
    public void update(Brand brand);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Brand> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Brand> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
