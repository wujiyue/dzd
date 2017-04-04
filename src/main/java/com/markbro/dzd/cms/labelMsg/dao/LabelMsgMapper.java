package com.markbro.dzd.cms.labelMsg.dao;
import org.springframework.stereotype.Repository;
import com.markbro.asoiaf.core.repo.CrudRepo;
import com.markbro.dzd.cms.labelMsg.bean.LabelMsg;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import java.util.List;
import java.util.Map;
/**
 * 便签管理 dao
 * Created by wujiyue on 2017-02-05 22:21:02.
 */
@Repository
public interface LabelMsgMapper{

    public LabelMsg get(java.lang.Integer id);
    public Map<String,Object> getMap(java.lang.Integer id);
    public void add(LabelMsg labelMsg);
    public void addByMap(Map<String,Object> map);
    public void addBatch(List<LabelMsg> labelMsgs);
    public void update(LabelMsg labelMsg);
    public void updateByMap(Map<String,Object> map);
    public void updateByMapBatch(Map<String,Object> map);
    public void delete(java.lang.Integer id);
    public void deleteBatch(java.lang.Integer[] ids);
    //find与findByMap的唯一的区别是在find方法在where条件中多了未删除的条件（deleted=0）
    public List<LabelMsg> find(PageBounds pageBounds,Map<String,Object> map);
    public List<LabelMsg> findByMap(PageBounds pageBounds,Map<String,Object> map);



}
