package com.markbro.dzd.cms.channel_big.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.cms.channel_big.bean.ChannelBig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 栏目大类 dao
 * Created by wujiyue on 2016-08-04 21:14:29.
 */
@Repository
public interface ChannelBigMapper{
    public ChannelBig get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(ChannelBig channelBig);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<ChannelBig> channelBigs);
    public void update(ChannelBig channelBig);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除、有效数据的条件（deleted=0,available=1）
    public List<ChannelBig> find(PageBounds pageBounds,Map<String,Object> map);
    public List<ChannelBig> findByMap(PageBounds pageBounds,Map<String,Object> map);

    public Integer getMaxSort();
	public int updateSort(@Param("id")String id, @Param("sort")String sort);

}
