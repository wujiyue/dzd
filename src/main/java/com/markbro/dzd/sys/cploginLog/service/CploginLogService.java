package com.markbro.dzd.sys.cploginLog.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.sys.cploginLog.bean.CploginLog;
import com.markbro.dzd.sys.cploginLog.dao.CploginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 登录日志 service
 * Created by wujiyue on 2016-12-05 21:59:57.
 */
@Service
public class CploginLogService{
    @Autowired
    private CploginLogMapper cploginLogMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public CploginLog get(java.lang.Integer id){
        return cploginLogMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return cploginLogMapper.getMap(id);
    }
    public List<CploginLog> find(PageBounds pageBounds,Map<String,Object> map){
        return cploginLogMapper.find(pageBounds,map);
    }
    public List<CploginLog> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return cploginLogMapper.findByMap(pageBounds,map);
    }
    public void add(CploginLog cploginLog){
        cploginLogMapper.add(cploginLog);
    }
    public Object save(CploginLog cploginLog){
          Msg msg=new Msg();
                 try{
                     if(cploginLog.getId()==null||"".equals(cploginLog.getId().toString())){
                         cploginLogMapper.add(cploginLog);
                     }else{
                         cploginLogMapper.update(cploginLog);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<CploginLog> cploginLogs){
        cploginLogMapper.addBatch(cploginLogs);
    }
    public void update(CploginLog cploginLog){
        cploginLogMapper.update(cploginLog);
    }
    public void updateByMap(Map<String,Object> map){
        cploginLogMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        cploginLogMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        cploginLogMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        cploginLogMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
