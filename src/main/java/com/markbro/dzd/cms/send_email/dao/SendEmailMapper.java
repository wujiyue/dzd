package com.markbro.dzd.cms.send_email.dao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.cms.send_email.bean.SendEmail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 发送邮件 dao
 * Created by wujiyue on 2016-12-23 21:26:17.
 */
@Repository
public interface SendEmailMapper{

    public SendEmail get(java.lang.String id);
    public Map<String,Object> getMap(java.lang.String id);
    public void add(SendEmail sendEmail);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<SendEmail> sendEmails);
    public void update(SendEmail sendEmail);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.String id);
    public void deleteBatch(java.lang.String[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除的条件（deleted=0）
    public List<SendEmail> find(PageBounds pageBounds,Map<String,Object> map);
    public List<SendEmail> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
