package com.markbro.dzd.cms.link.service;
import com.markbro.dzd.cms.link.bean.Link;
import com.markbro.dzd.cms.link.dao.LinkMapper;
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
 * 链接 service
 * Created by wujiyue on 2016-08-14 22:55:33.
 */
@Service
public class LinkService{
    @Autowired
    private LinkMapper linkMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Link get(java.lang.String id){
        return linkMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return linkMapper.getMap(id);
    }
    public List<Link> find(PageBounds pageBounds,Map<String,Object> map){
        return linkMapper.find(pageBounds,map);
    }
    public List<Link> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return linkMapper.findByMap(pageBounds,map);
    }
    public void add(Link link){
        linkMapper.add(link);
    }
    public Object save(Link link){
          Msg msg=new Msg();
                 try{
                     if(link.getId()==null||"".equals(link.getId().toString())){
                         java.lang.String id= keyService.getStringId();


                         link.setId(id);
                         linkMapper.add(link);
                     }else{

                         linkMapper.update(link);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Link> links){
        linkMapper.addBatch(links);
    }
    public void update(Link link){
        linkMapper.update(link);
    }
    public void updateByMap(Map<String,Object> map){
        linkMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        linkMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        linkMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        linkMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
