package com.markbro.dzd.base.account.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.account.bean.SecurityQueston;
import com.markbro.dzd.base.account.dao.SecurityQuestonMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 安全问题 service
 * Created by wujiyue on 2016-08-22 23:47:02.
 */
@Service
public class SecurityQuestonService{
    @Autowired
    private SecurityQuestonMapper securityQuestonMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public SecurityQueston get(String id){
        return securityQuestonMapper.get(id);
    }
    public Map<String,Object> getMap(String id){
        return securityQuestonMapper.getMap(id);
    }
    public List<SecurityQueston> find(PageBounds pageBounds,Map<String,Object> map){
        return securityQuestonMapper.find(pageBounds,map);
    }
    public List<SecurityQueston> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return securityQuestonMapper.findByMap(pageBounds,map);
    }
    public void add(SecurityQueston securityQueston){
        securityQuestonMapper.add(securityQueston);
    }
    public Object save(SecurityQueston securityQueston){
          Msg msg=new Msg();
                 try{
                     if(securityQueston.getId()==null||"".equals(securityQueston.getId().toString())){
                         String id= keyService.getStringId();


                         securityQueston.setId(id);
                         securityQuestonMapper.add(securityQueston);
                     }else{

                         securityQuestonMapper.update(securityQueston);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<SecurityQueston> securityQuestons){
        securityQuestonMapper.addBatch(securityQuestons);
    }
    public void update(SecurityQueston securityQueston){
        securityQuestonMapper.update(securityQueston);
    }
    public void updateByMap(Map<String,Object> map){
        securityQuestonMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        securityQuestonMapper.updateByMapBatch(map);
    }
    public void delete(String id){
        securityQuestonMapper.delete(id);
    }
    public void deleteBatch(String[] ids){
        securityQuestonMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
