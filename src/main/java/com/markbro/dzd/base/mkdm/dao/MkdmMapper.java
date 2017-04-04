package com.markbro.dzd.base.mkdm.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.mkdm.bean.Mkdm;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 模块代码 dao
 * Created by wujiyue on 2016-07-17 11:08:13.
 */
@Repository
public interface MkdmMapper{
    public Mkdm get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Mkdm mkdm);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Mkdm> mkdms);
    public void update(Mkdm mkdm);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Mkdm> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Mkdm> findByMap(PageBounds pageBounds,Map<String,Object> map);

}
