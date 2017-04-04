package com.markbro.dzd.base.orgTree.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.orgTree.bean.OrgTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 组织目录 dao
 * Created by wujiyue on 2016-07-14 12:01:01.
 */
@Repository
public interface OrgTreeMapper {
    public OrgTree get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(OrgTree tree);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<OrgTree> trees);
    public void update(OrgTree tree);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<OrgTree> find(PageBounds pageBounds,Map<String,Object> map);
    public List<OrgTree> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<OrgTree> findByOrgid(PageBounds pageBounds,java.lang.String orgid);
	public List<OrgTree> findByParentid(PageBounds pageBounds,java.lang.String parentid);

    public Integer findByParentidCount(@Param("parentid")String parentid,@Param("orgid")String orgid);

    public OrgTree getByLxidAndType(@Param("lxid")java.lang.String lxid,@Param("type")String type);

    public void deleteByLxidAndType(@Param("lxid")java.lang.String lxid,@Param("type")String type);
}
