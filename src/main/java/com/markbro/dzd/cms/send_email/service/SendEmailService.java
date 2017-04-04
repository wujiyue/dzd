package com.markbro.dzd.cms.send_email.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.send_email.bean.SendEmail;
import com.markbro.dzd.cms.send_email.dao.SendEmailMapper;
import com.markbro.dzd.common.util.Guid;
import com.markbro.dzd.common.util.MyBatisRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
/**
 * 发送邮件 service
 * Created by wujiyue on 2016-12-23 21:26:18.
 */
@Service
public class SendEmailService{
    @Autowired
    private TableKeyService keyService;
    @Autowired
    private SendEmailMapper sendEmailMapper;

     /*基础公共方法*/
    public SendEmail get(java.lang.String id){
        return sendEmailMapper.get(id);
    }

    public List<SendEmail> find(PageBounds pageBounds,Map<String,Object> map){
        return sendEmailMapper.find(pageBounds,map);
    }
    public List<SendEmail> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return sendEmailMapper.findByMap(pageBounds,map);
    }

    public void add(SendEmail sendEmail){
        sendEmailMapper.add(sendEmail);
    }
    public Object save(Map map){
        SendEmail sendEmail= null;
        try {
            sendEmail = MyBatisRequestUtil.convertToBean(map, new SendEmail());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Msg msg=new Msg();
          try{
             if(sendEmail.getId()==null||"".equals(sendEmail.getId().toString())){
                 //java.lang.String id= keyService.getStringId();
                 sendEmail.setId(Guid.get());
                 sendEmail.setUserid((String)map.get("yhid"));
                 sendEmailMapper.add(sendEmail);
              }else{
                 sendEmailMapper.update(sendEmail);
              }
              msg.setType(Msg.MsgType.success);
              msg.setContent("保存信息成功");
          }catch (Exception ex){
              msg.setType(Msg.MsgType.error);
              msg.setContent("保存信息失败");
          }
          return msg;

    }
    public void addBatch(List<SendEmail> sendEmails) {
        sendEmailMapper.addBatch(sendEmails);
    }

    public void update(SendEmail sendEmail) {
        sendEmailMapper.update(sendEmail);
    }

    public void updateByMap(Map<String,Object> map) {
        sendEmailMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        sendEmailMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        sendEmailMapper.delete(id);
    }

    public void deleteBatch(java.lang.String[] ids){
        sendEmailMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
