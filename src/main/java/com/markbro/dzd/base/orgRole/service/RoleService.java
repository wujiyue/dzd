package com.markbro.dzd.base.orgRole.service;
import com.markbro.dzd.base.orgRole.bean.Role;
import com.markbro.dzd.base.orgRole.dao.RoleMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Role Service
 * Created by wujiyue on 2016-06-12 22:35:18.
 */
@Service
public class RoleService{
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

}
