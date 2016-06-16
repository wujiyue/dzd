package com.markbro.dzd.sys.permission.service;
import com.markbro.dzd.sys.permission.bean.Permission;
import com.markbro.dzd.sys.permission.dao.PermissionMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Permission Service
 * Created by wujiyue on 2016-06-13 21:13:46.
 */
@Service
public class PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
     /*基础公共方法*/
    public Permission get(java.lang.Integer id){
        return permissionMapper.get(id);
    }
    public List<Permission> find(PageBounds pageBounds,Map<String,Object> map){
        return permissionMapper.find(pageBounds,map);
    }
    public List<Permission> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return permissionMapper.findByMap(pageBounds,map);
    }
    public void add(Permission permission){
        permissionMapper.add(permission);
    }
    public void addBatch(List<Permission> permissions){
        permissionMapper.addBatch(permissions);
    }
    public void update(Permission permission){
        permissionMapper.update(permission);
    }
    public void updateByMap(Map<String,Object> map){
        permissionMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        permissionMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        permissionMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        permissionMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Permission> findByParentid(PageBounds pageBounds,java.lang.Integer parentid){
		return permissionMapper.findByParentid(pageBounds,parentid);
	}
}
