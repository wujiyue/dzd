package com.markbro.dzd.cms.timeline.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.timeline.bean.Timeline;
import com.markbro.dzd.cms.timeline.dao.TimelineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 时光轴 service
 * Created by wujiyue on 2016-12-06 22:31:55.
 */
@Service
public class TimelineService{
    @Autowired
    private TimelineMapper timelineMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Timeline get(java.lang.Integer id){
        return timelineMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return timelineMapper.getMap(id);
    }
    public List<Timeline> find(PageBounds pageBounds,Map<String,Object> map){
        return timelineMapper.find(pageBounds,map);
    }
    public List<Timeline> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return timelineMapper.findByMap(pageBounds,map);
    }
    public void add(Timeline timeline){
        timelineMapper.add(timeline);
    }
    public Object save(Map map){
          Msg msg=new Msg();
                 try{
                     if(map.get("id")==null||"".equals(map.get("id").toString())){
                         timelineMapper.addByMap(map);
                     }else{
                         timelineMapper.updateByMap(map);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Timeline> timelines){
        timelineMapper.addBatch(timelines);
    }
    public void update(Timeline timeline){
        timelineMapper.update(timeline);
    }
    public void updateByMap(Map<String,Object> map){
        timelineMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        timelineMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        timelineMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        timelineMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
