package com.markbro.dzd.base.orgUser.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.orgUser.bean.OrgUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统用户 dao
 * Created by wujiyue on 2016-07-05 22:52:54.
 */
@Repository
public interface OrgUserMapper {
    public OrgUser get(String id);
    public void add(OrgUser user);
    public void addBatch(List<OrgUser> users);
    public void update(OrgUser user);
    public void updateByMap(Map<String, Object> map);
    public void updateByMapBatch(Map<String, Object> map);
    public void delete(String id);
    public void deleteBatch(String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<OrgUser> find(PageBounds pageBounds, Map<String, Object> map);
    public List<OrgUser> findByMap(PageBounds pageBounds, Map<String, Object> map);

	public List<OrgUser> findByOrgid(PageBounds pageBounds, String orgid);

    //查询用户Map
    public Map<String,Object> queryUserMapByYhid(@Param("yhid") String yhid);
}
