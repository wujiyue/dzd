package com.markbro.dzd.cms.labelMsg.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.labelMsg.bean.LabelMsg;
import com.markbro.dzd.cms.labelMsg.dao.LabelMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 便签管理 service
 * Created by wujiyue on 2017-02-05 22:21:03.
 */
@Service
public class LabelMsgService{

    @Autowired
    private TableKeyService keyService;
    @Autowired
    private LabelMsgMapper labelMsgMapper;

     /*基础公共方法*/
    public LabelMsg get(java.lang.Integer id){
        return labelMsgMapper.get(id);
    }

    public List<LabelMsg> find(PageBounds pageBounds,Map<String,Object> map){
        return labelMsgMapper.find(pageBounds,map);
    }
    public List<LabelMsg> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return labelMsgMapper.findByMap(pageBounds,map);
    }

    public void add(LabelMsg labelMsg){
        labelMsgMapper.add(labelMsg);
    }
    public Object save(Map map){
          Msg msg=new Msg();
          try{
            if(map.get("id")==null||"".equals((String)map.get("id").toString())){
               java.lang.Integer id= keyService.getIntegerId();
               map.put("id",id);
               labelMsgMapper.addByMap(map);
            }else{
               labelMsgMapper.updateByMap(map);
            }
               msg.setType(Msg.MsgType.success);
               msg.setContent("保存信息成功");
             }catch (Exception ex){
               msg.setType(Msg.MsgType.error);
               msg.setContent("保存信息失败");
            }
            return msg;
    }
    public void addBatch(List<LabelMsg> labelMsgs){
        labelMsgMapper.addBatch(labelMsgs);
    }

    public void update(LabelMsg labelMsg){
        labelMsgMapper.update(labelMsg);
    }

    public void updateByMap(Map<String,Object> map){
        labelMsgMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        labelMsgMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        labelMsgMapper.delete(id);
    }

    public void deleteBatch(java.lang.Integer[] ids){
        labelMsgMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
