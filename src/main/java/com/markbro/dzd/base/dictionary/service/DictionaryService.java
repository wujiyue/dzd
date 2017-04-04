package com.markbro.dzd.base.dictionary.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.dictionary.bean.Dictionary;
import com.markbro.dzd.base.dictionary.dao.DictionaryMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 数据字典 service
 * Created by wujiyue on 2016-07-05 22:19:54.
 */
@Service
public class DictionaryService{
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Dictionary get(java.lang.Integer id){
        return dictionaryMapper.get(id);
    }
    public List<Dictionary> find(PageBounds pageBounds,Map<String,Object> map){
        return dictionaryMapper.find(pageBounds,map);
    }
    public List<Dictionary> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return dictionaryMapper.findByMap(pageBounds,map);
    }
    public void add(Dictionary dictionary){
        dictionaryMapper.add(dictionary);
    }
    public Object save(Dictionary dictionary){
          Msg msg=new Msg();
                 try{
                     if(dictionary.getId()==null||"".equals(dictionary.getId().toString())){
                         java.lang.Integer id= keyService.getIntegerId();
                         dictionary.setId(id);
                         dictionaryMapper.add(dictionary);
                     }else{
                         dictionaryMapper.update(dictionary);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Dictionary> dictionarys){
        dictionaryMapper.addBatch(dictionarys);
    }
    public void update(Dictionary dictionary){
        dictionaryMapper.update(dictionary);
    }
    public void updateByMap(Map<String,Object> map){
        dictionaryMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        dictionaryMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        dictionaryMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        dictionaryMapper.deleteBatch(ids);
    }
     /*自定义方法*/
     public Object  select(Map<String,Object> map){
         Map<String,Object> returnMap=new HashMap<String,Object>();
         String type=(String) map.get("type");
         List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
         List<Dictionary> list=dictionaryMapper.findByType(type);
         Map<String,Object> tmap=null;
         for(Dictionary d:list){
             tmap=new HashMap<String,Object>();
             tmap.put("dm",d.getValue());
             tmap.put("mc",d.getLabel());
             res.add(tmap);
         }
         returnMap.put("dicSelect_"+type,res);
         return returnMap;
     }
}
