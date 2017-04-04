package com.markbro.dzd.cms.onlineupdate.service;
import com.markbro.dzd.cms.onlineupdate.bean.Onlineupdate;
import com.markbro.dzd.cms.onlineupdate.dao.OnlineupdateMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import com.markbro.asoiaf.core.model.Msg;
import java.util.List;
import java.util.ArrayList;
import com.markbro.dzd.common.util.MyBatisRequestUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
/**
 * 在线升级 service
 * Created by wujiyue on 2016-12-02 21:19:14.
 */
@Service
public class OnlineupdateService{
    @Autowired
    private OnlineupdateMapper onlineupdateMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Onlineupdate get(java.lang.Integer id){
        return onlineupdateMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return onlineupdateMapper.getMap(id);
    }
    public List<Onlineupdate> find(PageBounds pageBounds,Map<String,Object> map){
        return onlineupdateMapper.find(pageBounds,map);
    }
    public List<Onlineupdate> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return onlineupdateMapper.findByMap(pageBounds,map);
    }
    public void add(Onlineupdate onlineupdate){
        onlineupdateMapper.add(onlineupdate);
    }
    public Object save(Onlineupdate onlineupdate){
          Msg msg=new Msg();
                 try{
                     if(onlineupdate.getId()==null||"".equals(onlineupdate.getId().toString())){
                         onlineupdateMapper.add(onlineupdate);
                     }else{

                         onlineupdateMapper.update(onlineupdate);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Onlineupdate> onlineupdates){
        onlineupdateMapper.addBatch(onlineupdates);
    }
    public void update(Onlineupdate onlineupdate){
        onlineupdateMapper.update(onlineupdate);
    }
    public void updateByMap(Map<String,Object> map){
        onlineupdateMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        onlineupdateMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        onlineupdateMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        onlineupdateMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
