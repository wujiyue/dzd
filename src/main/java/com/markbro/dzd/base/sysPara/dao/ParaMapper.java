package com.markbro.dzd.base.sysPara.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.sysPara.bean.Para;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 系统参数 dao
 * Created by wujiyue on 2016-07-03 19:25:36.
 */
@Repository
public interface ParaMapper{
    public Para get(java.lang.String id);
    public void add(Para para);
    public void addBatch(List<Para> paras);
    public void update(Para para);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Para> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Para> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Para> findByMk_dm(PageBounds pageBounds,java.lang.Integer mk_dm);
}
