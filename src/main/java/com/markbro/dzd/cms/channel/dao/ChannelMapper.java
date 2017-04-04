package com.markbro.dzd.cms.channel.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;
import com.markbro.dzd.cms.channel.bean.Channel;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 栏目 dao
 * Created by wujiyue on 2016-08-04 21:03:50.
 */
@Repository
public interface ChannelMapper{
    public Channel get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(Channel channel);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<Channel> channels);
    public void update(Channel channel);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<Channel> find(PageBounds pageBounds,Map<String,Object> map);
    public List<Channel> findByMap(PageBounds pageBounds,Map<String,Object> map);

	public List<Channel> findByParentid(PageBounds pageBounds,java.lang.String parentid);
	public Integer findByParentidCount(@Param("parentid")String parentid);
	public Integer checkForDelete(@Param("ids")String ids);

	public int updateSort(@Param("id")String id, @Param("sort")String sort);
	public Integer getMaxSortByParentid(@Param("parentid")String parentid);

}
