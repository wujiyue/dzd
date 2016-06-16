package com.markbro.dzd.sys.sysUser.service;
import com.markbro.dzd.sys.sysUser.bean.User;
import com.markbro.dzd.sys.sysUser.dao.UserMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * User Service
 * Created by wujiyue on 2016-06-13 19:57:43.
 */
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;
     /*基础公共方法*/
    public User get(java.lang.String id){
        return userMapper.get(id);
    }
    public List<User> find(PageBounds pageBounds,Map<String,Object> map){
        return userMapper.find(pageBounds,map);
    }
    public List<User> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return userMapper.findByMap(pageBounds,map);
    }
    public void add(User user){
        userMapper.add(user);
    }
    public void addBatch(List<User> users){
        userMapper.addBatch(users);
    }
    public void update(User user){
        userMapper.update(user);
    }
    public void updateByMap(Map<String,Object> map){
        userMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        userMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        userMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        userMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<User> findByOrgid(PageBounds pageBounds,java.lang.String orgid){
		return userMapper.findByOrgid(pageBounds,orgid);
	}
}
