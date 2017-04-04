package com.markbro.dzd.cms.cpupdatelog.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.cpupdatelog.bean.CpupdateLog;
import com.markbro.dzd.cms.cpupdatelog.dao.CpupdateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 更新日志 service
 * Created by wujiyue on 2016-12-04 19:49:45.
 */
@Service
public class CpupdateLogService{
    @Autowired
    private CpupdateLogMapper cpupdateLogMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public CpupdateLog get(java.lang.Integer id){
        return cpupdateLogMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return cpupdateLogMapper.getMap(id);
    }
    public List<CpupdateLog> find(PageBounds pageBounds,Map<String,Object> map){
        return cpupdateLogMapper.find(pageBounds,map);
    }
    public List<CpupdateLog> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return cpupdateLogMapper.findByMap(pageBounds,map);
    }
    public void add(CpupdateLog cpupdateLog){
        cpupdateLogMapper.add(cpupdateLog);
    }
    public Object save(CpupdateLog cpupdateLog){
          Msg msg=new Msg();
                 try{
                     if(cpupdateLog.getId()==null||"".equals(cpupdateLog.getId().toString())){
                         cpupdateLogMapper.add(cpupdateLog);
                     }else{

                         cpupdateLogMapper.update(cpupdateLog);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<CpupdateLog> cpupdateLogs){
        cpupdateLogMapper.addBatch(cpupdateLogs);
    }
    public void update(CpupdateLog cpupdateLog){
        cpupdateLogMapper.update(cpupdateLog);
    }
    public void updateByMap(Map<String,Object> map){
        cpupdateLogMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        cpupdateLogMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        cpupdateLogMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        cpupdateLogMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
