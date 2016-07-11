package com.markbro.dzd.base.orgRole.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.orgRole.bean.Role;
import com.markbro.dzd.base.orgRole.dao.RoleMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * Role Service
 * Created by wujiyue on 2016-06-12 22:35:18.
 */
@Service
public class RoleService{
    @Autowired
    private TableKeyService keyService;
    @Autowired
    private RoleMapper roleMapper;
     /*基础公共方法*/
    public Role get(java.lang.String id){
        return roleMapper.get(id);
    }
    public List<Role> find(PageBounds pageBounds,Map<String,Object> map){
        return roleMapper.find(pageBounds,map);
    }
    public List<Role> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return roleMapper.findByMap(pageBounds,map);
    }
    public void add(Role role){
        roleMapper.add(role);
    }
    public void addBatch(List<Role> roles){
        roleMapper.addBatch(roles);
    }
    public void update(Role role){
        roleMapper.update(role);
    }
    public void updateByMap(Map<String,Object> map){
        roleMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        roleMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        roleMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        roleMapper.deleteBatch(ids);
    }
     /*自定义方法*/
     public Object save(Role m){
         Msg msg=new Msg();
         try{
             if(m.getId()==null||"".equals(m.getId().toString())){
                 //java.lang.String id= IdGen.getGuid();
                 String id=keyService.getStringId();
                 m.setId(id);
                 roleMapper.add(m);
             }else{
                 roleMapper.update(m);
             }
             msg.setType(Msg.MsgType.success);
             msg.setContent("保存信息成功");
         }catch (Exception ex){
             msg.setType(Msg.MsgType.error);
             msg.setContent("保存信息失败");
         }
        return msg;
     }
}
