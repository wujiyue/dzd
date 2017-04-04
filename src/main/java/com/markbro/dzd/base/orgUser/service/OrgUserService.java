package com.markbro.dzd.base.orgUser.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.orgUser.bean.OrgUser;
import com.markbro.dzd.base.orgUser.dao.OrgUserMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统用户 service
 * Created by wujiyue on 2016-07-05 22:52:55.
 */
@Service
public class OrgUserService {
    @Autowired
    private OrgUserMapper userMapper;
    @Autowired
    private TableKeyService yhKeyService;
     /*基础公共方法*/
    public OrgUser get(String id){
        return userMapper.get(id);
    }
    public List<OrgUser> find(PageBounds pageBounds,Map<String,Object> map){
        return userMapper.find(pageBounds,map);
    }
    public List<OrgUser> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return userMapper.findByMap(pageBounds,map);
    }
    public void add(OrgUser user){
        userMapper.add(user);
    }
    public Object save(Map<String,Object> map){
          Msg msg=new Msg();
                 try{
                     OrgUser user=com.markbro.asoiaf.utils.string.MapUtils.toObject(OrgUser.class,map);
                     String emailflag=(String)map.get("emailflag");
                     String phoneflag=(String)map.get("phoneflag");
                     if("on".equals(emailflag)||"1".equals("emailflag"))
                     {
                         user.setEmailflag(1);
                     }else{
                         user.setEmailflag(0);
                     }
                     if("on".equals(phoneflag)||"1".equals(phoneflag))
                     {
                         user.setPhoneflag(1);
                     }else{
                         user.setPhoneflag(0);
                     }
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
    public void addBatch(List<OrgUser> users){
        userMapper.addBatch(users);
    }
    public void update(OrgUser user){
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
