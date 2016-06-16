package com.markbro.dzd.base.orgTree.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.base.orgTree.bean.Tree;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * Tree dao
 * Created by wujiyue on 2016-06-12 22:41:02.
 */
@Repository
public interface TreeMapper{
    public Tree get(java.lang.String id);
    public void add(Tree tree);
    public void addBatch(List<Tree> trees);
    public void update(Tree tree);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Tree> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Tree> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Tree> findByOrgid(PageBounds pageBounds,java.lang.String orgid);
}
