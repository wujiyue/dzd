package com.markbro.dzd.base.sysPara.service;
import com.markbro.dzd.base.sysPara.bean.Para;
import com.markbro.dzd.base.sysPara.dao.ParaMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import com.markbro.asoiaf.core.model.Msg;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
/**
 * 系统参数 service
 * Created by wujiyue on 2016-07-03 19:25:36.
 */
@Service
public class ParaService{
    @Autowired
    private ParaMapper paraMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Para get(java.lang.String id){
        return paraMapper.get(id);
    }
    public List<Para> find(PageBounds pageBounds,Map<String,Object> map){
        return paraMapper.find(pageBounds,map);
    }
    public List<Para> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return paraMapper.findByMap(pageBounds,map);
    }
    public void add(Para para){
        paraMapper.add(para);
    }
    public Object save(Para para){
          Msg msg=new Msg();
                 try{
                     if(para.getId()==null||"".equals(para.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         para.setId(id);
                         paraMapper.add(para);
                     }else{
                         paraMapper.update(para);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Para> paras){
        paraMapper.addBatch(paras);
    }
    public void update(Para para){
        paraMapper.update(para);
    }
    public void updateByMap(Map<String,Object> map){
        paraMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        paraMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        paraMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        paraMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Para> findByMk_dm(PageBounds pageBounds,java.lang.Integer mk_dm){
		return paraMapper.findByMk_dm(pageBounds,mk_dm);
	}
}
