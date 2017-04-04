package com.markbro.dzd.base.actionlog.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.actionlog.bean.Actionlog;
import com.markbro.dzd.base.actionlog.dao.ActionlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 操作日志 service
 * Created by wujiyue on 2016-07-03 21:07:56.
 */
@Service
public class ActionlogService{
    @Autowired
    private ActionlogMapper actionlogMapper;
     /*基础公共方法*/
    public Actionlog get(java.lang.Integer id){
        return actionlogMapper.get(id);
    }
    public List<Actionlog> find(PageBounds pageBounds,Map<String,Object> map){
        return actionlogMapper.find(pageBounds,map);
    }
    public List<Actionlog> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return actionlogMapper.findByMap(pageBounds,map);
    }
    public void add(Actionlog actionlog){
        actionlogMapper.add(actionlog);
    }
    public Object save(Actionlog actionlog){
          Msg msg=new Msg();
                 try{
                     if(actionlog.getId()==null||"".equals(actionlog.getId().toString())){
                         actionlogMapper.add(actionlog);
                     }else{
                         actionlogMapper.update(actionlog);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Actionlog> actionlogs){
        actionlogMapper.addBatch(actionlogs);
    }
    public void update(Actionlog actionlog){
        actionlogMapper.update(actionlog);
    }
    public void updateByMap(Map<String,Object> map){
        actionlogMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        actionlogMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        actionlogMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        actionlogMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Actionlog> findByYhid(PageBounds pageBounds,java.lang.String yhid){
		return actionlogMapper.findByYhid(pageBounds,yhid);
	}
}
