package com.markbro.dzd.base.mkdm.service;
import com.markbro.dzd.base.mkdm.bean.Mkdm;
import com.markbro.dzd.base.mkdm.dao.MkdmMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import com.markbro.asoiaf.core.model.Msg;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
/**
 * 模块代码 service
 * Created by wujiyue on 2016-07-17 11:08:13.
 */
@Service
public class MkdmService{
    @Autowired
    private MkdmMapper mkdmMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Mkdm get(java.lang.String id){
        return mkdmMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return mkdmMapper.getMap(id);
    }
    public List<Mkdm> find(PageBounds pageBounds,Map<String,Object> map){
        return mkdmMapper.find(pageBounds,map);
    }
    public List<Mkdm> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return mkdmMapper.findByMap(pageBounds,map);
    }
    public void add(Mkdm mkdm){
        mkdmMapper.add(mkdm);
    }
    public Object save(Mkdm mkdm){
          Msg msg=new Msg();
                 try{
                     if(mkdm.getId()==null||"".equals(mkdm.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         mkdm.setId(id);
                         mkdmMapper.add(mkdm);
                     }else{
                         mkdmMapper.update(mkdm);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Mkdm> mkdms){
        mkdmMapper.addBatch(mkdms);
    }
    public void update(Mkdm mkdm){
        mkdmMapper.update(mkdm);
    }
    public void updateByMap(Map<String,Object> map){
        mkdmMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        mkdmMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        mkdmMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        mkdmMapper.deleteBatch(ids);
    }
     /*自定义方法*/

}
