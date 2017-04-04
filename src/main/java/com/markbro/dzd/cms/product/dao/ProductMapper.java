package com.markbro.dzd.cms.product.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.product.bean.Product;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 产品 dao
 * Created by wujiyue on 2016-09-17 18:05:19.
 */
@Repository
public interface ProductMapper{
    public Product get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(Product product);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Product> products);
    public void update(Product product);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Product> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Product> findByMap(PageBounds pageBounds,Map<String,Object> map);


	public int updateSort(@Param("id")String id, @Param("sort")String sort);
	public Integer getMaxSort();

}
