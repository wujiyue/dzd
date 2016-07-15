package com.markbro.dzd.base.orgUser.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.orgUser.bean.User;
import com.markbro.dzd.base.orgUser.dao.UserMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统用户 Service
 * Created by wujiyue on 2016-07-05 22:52:55.
 */
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TableKeyService yhKeyService;
     /*基础公共方法*/
    public User get(String id){
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
    public Object save(User user){
          Msg msg=new Msg();
                 try{
                     if(user.getId()==null||"".equals(user.getId().toString())){
                         String id= yhKeyService.getStringId();
                         user.setId(id);
                         userMapper.add(user);
                     }else{
                         userMapper.update(user);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
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
    public void delete(String id){
        userMapper.delete(id);
    }
    public void deleteBatch(String[] ids){
        userMapper.deleteBatch(ids);
    }
     /*自定义方法*/

}
