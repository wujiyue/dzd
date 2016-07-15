package com.markbro.dzd.base.orgRole.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.orgRole.bean.Role;
import com.markbro.dzd.base.orgRole.dao.RoleMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.common.util.PatternUtil;
import com.markbro.dzd.sys.permission.dao.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
     /*基础公共方法*/
    public Role get(java.lang.String id){
        return roleMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return roleMapper.getMap(id);
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
    //保存角色并且保存角色的权限
    @Transactional
    public Object saveRoleAndRolePermissions(Map<String,Object> map){
        Msg msg=new Msg();
        try{
            String id= (String) map.get("id");
            id= PatternUtil.isNull(id);
            String ids= (String) map.get("ids");//权限id用逗号分割的字符串
            if("".equals(id)){
                id=keyService.getStringId();
                map.put("id",id);
                roleMapper.addMap(map);
                //保存角色的权限
                String[] arr=ids.split(",");
                int len=arr.length;
                if(len>0){
                    permissionMapper.deleteRolePermission(id);
                    for(int i=0;i<len;i++){
                        permissionMapper.addRolePermission(arr[i],id);
                    }
                }
                msg.setType(Msg.MsgType.success);
                msg.setContent("新增角色并授权成功");
            }else{
                roleMapper.updateByMap(map);
                //保存角色的权限
                String[] arr=ids.split(",");
                int len=arr.length;
                if(len>0){
                    permissionMapper.deleteRolePermission((String) map.get("id"));
                    for(int i=0;i<len;i++){
                        permissionMapper.addRolePermission(arr[i],(String) map.get("id"));
                    }
                }
                msg.setType(Msg.MsgType.success);
                msg.setContent("更新角色和角色权限成功");
            }

        }catch (Exception ex){
            msg.setType(Msg.MsgType.error);
            msg.setContent("保存角色和角色权限信息失败");
        }
        return msg;
    }
}
