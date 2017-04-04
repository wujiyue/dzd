package com.markbro.dzd.cms.resource.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.resource.bean.Resource;
import com.markbro.dzd.cms.resource.dao.ResourceMapper;
import com.markbro.dzd.common.util.MyBatisRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 资源 service
 * Created by wujiyue on 2016-07-27 22:30:43.
 */
@Service
public class ResourceService{
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Resource get(java.lang.String id){
        return resourceMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return resourceMapper.getMap(id);
    }
    public List<Resource> find(PageBounds pageBounds,Map<String,Object> map){
        return resourceMapper.find(pageBounds,map);
    }
    public List<Resource> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return resourceMapper.findByMap(pageBounds,map);
    }
    public void add(Resource resource){
        resourceMapper.add(resource);
    }
    public Object save(Map map){
          Msg msg=new Msg();
                 try{
                     Resource resource= MyBatisRequestUtil.convertToBean(map,new Resource());
                     if(resource.getId()==null||"".equals(resource.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         resource.setId(id);
                         resourceMapper.add(resource);
                     }else{
                         resourceMapper.update(resource);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Resource> resources){
        resourceMapper.addBatch(resources);
    }
    public void update(Resource resource){
        resourceMapper.update(resource);
    }
    public void updateByMap(Map<String,Object> map){
        resourceMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        resourceMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        resourceMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        resourceMapper.deleteBatch(ids);
    }
     /*自定义方法*/

}
