package com.markbro.dzd.base.orgPosition.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.orgPosition.bean.Position;
import com.markbro.dzd.base.orgPosition.dao.PositionMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 岗位 Service
 * Created by wujiyue on 2016-07-10 10:31:56.
 */
@Service
public class PositionService{
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private TableKeyService gwKeyService;
     /*基础公共方法*/
    public Position get(java.lang.String id){
        return positionMapper.get(id);
    }
    public List<Position> find(PageBounds pageBounds,Map<String,Object> map){
        return positionMapper.find(pageBounds,map);
    }
    public List<Position> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return positionMapper.findByMap(pageBounds,map);
    }
    public void add(Position position){
        positionMapper.add(position);
    }
    public Object save(Position position){
          Msg msg=new Msg();
                 try{
                     if(position.getId()==null||"".equals(position.getId().toString())){
                         java.lang.String id= gwKeyService.getStringId();
                         position.setId(id);
                         positionMapper.add(position);
                     }else{
                         positionMapper.update(position);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Position> positions){
        positionMapper.addBatch(positions);
    }
    public void update(Position position){
        positionMapper.update(position);
    }
    public void updateByMap(Map<String,Object> map){
        positionMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        positionMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        positionMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        positionMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Position> findByParentid(PageBounds pageBounds,java.lang.String parentid){
		return positionMapper.findByParentid(pageBounds,parentid);
	}
}
