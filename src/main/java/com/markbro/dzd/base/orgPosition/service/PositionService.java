package com.markbro.dzd.base.orgPosition.service;
import com.markbro.dzd.base.orgPosition.bean.Position;
import com.markbro.dzd.base.orgPosition.dao.PositionMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Position Service
 * Created by wujiyue on 2016-06-12 22:37:39.
 */
@Service
public class PositionService{
    @Autowired
    private PositionMapper positionMapper;
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

	 public List<Position> findByOrgid(PageBounds pageBounds,java.lang.String orgid){
		return positionMapper.findByOrgid(pageBounds,orgid);
	}
}
