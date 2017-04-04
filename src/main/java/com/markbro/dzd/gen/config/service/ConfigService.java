package com.markbro.dzd.gen.config.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.gen.config.bean.Config;
import com.markbro.dzd.gen.config.dao.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 配置 service
 * Created by wujiyue on 2016-11-23 16:38:41.
 */
@Service
public class ConfigService{
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Config get(java.lang.Integer id){
        return configMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return configMapper.getMap(id);
    }
    public List<Config> find(PageBounds pageBounds,Map<String,Object> map){
        return configMapper.find(pageBounds,map);
    }
    public List<Config> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return configMapper.findByMap(pageBounds,map);
    }
    public void add(Config config){
        configMapper.add(config);
    }
    public Object save(Config config){
          Msg msg=new Msg();
                 try{
                     if(config.getId()==null||"".equals(config.getId().toString())){
                         configMapper.add(config);
                     }else{

                         configMapper.update(config);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Config> configs){
        configMapper.addBatch(configs);
    }
    public void update(Config config){
        configMapper.update(config);
    }
    public void updateByMap(Map<String,Object> map){
        configMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        configMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        configMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        configMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
