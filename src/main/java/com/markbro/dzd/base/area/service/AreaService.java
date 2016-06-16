package com.markbro.dzd.base.area.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.area.bean.Area;
import com.markbro.dzd.base.area.dao.AreaMapper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Area Service
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Service
public class AreaService{
    @Autowired
    private AreaMapper areaMapper;
     /*基础公共方法*/
    public Area get(java.lang.Integer id){
        return areaMapper.get(id);
    }
    public List<Area> find(PageBounds pageBounds,Map<String,Object> map){
        return areaMapper.find(pageBounds,map);
    }
    public List<Area> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return areaMapper.findByMap(pageBounds,map);
    }
    @Transactional
    public void add(Area area){
        areaMapper.add(area);
    }
    @Transactional
    public void addBatch(List<Area> areas){

        areaMapper.addBatch(areas);
    }
    @Transactional
    public void update(Area area){
        areaMapper.update(area);
    }
    @Transactional
    public void updateByMap(Map<String,Object> map){
        areaMapper.updateByMap(map);
    }
    @Transactional
    public void updateByMapBatch(Map<String,Object> map){
        areaMapper.updateByMapBatch(map);
    }
    @Transactional
    public void delete(java.lang.Integer id){
        areaMapper.delete(id);
    }
    @Transactional
    public void deleteBatch(java.lang.Integer[] ids){
        areaMapper.deleteBatch(ids);
    }
     /*自定义方法*/
    @Transactional
     public void test(){
         Area a=  new Area();
         areaMapper.add(a);
         throw new RuntimeException("test ex!");
     }
	 public List findByParentid(PageBounds pageBounds,java.lang.Integer parentid){
		return areaMapper.findByParentid(pageBounds,parentid);
	}

    public String tree(Map<String, Object> map){
       // return areaMapper.findByParentid(pageBounds,parentid);
        String parentid= (String) map.get("parentid");
        Map<String, Object> rootNode=new HashMap<String, Object>();//根节点
        List<Area> list=null;
        List<Area> childrenlist=null;//每个区域的孩子集合
        Map<String, Object> attributes=null;//每个区域的属性
        List<Map<String, Object>> nodelist =null;//要返回的节点集合
        Map<String, Object> node=null;//节点
        if("0".equals(parentid)){
            list=areaMapper.findByParentid(new PageBounds(),0);
            nodelist = new ArrayList<Map<String,Object>>();
            //组装根节点
            rootNode.put("id", "0");
            rootNode.put("text", "区域目录");
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Area area:list){
                    node=new HashMap<String, Object>();
                    attributes=new HashMap<String, Object>();
                    Integer id=area.getId();

                    node.put("id", area.getId());
                    node.put("text", area.getName());

                    attributes.put("areatype", area.getAreatype());
                    attributes.put("code",area.getCode());
                    node.put("attributes", attributes);
                    childrenlist=areaMapper.findByParentid(new PageBounds(),id);
                    if(childrenlist!=null&childrenlist.size()>0){
                        node.put("state", "closed");
                    }
                    nodelist.add(node);
                }
                rootNode.put("children", nodelist);
            }
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//自动为我排除circle。
            JSONArray jsonArray = JSONArray.fromObject(rootNode, jsonConfig);
            return jsonArray.toString();
        }else{
            list=areaMapper.findByParentid(new PageBounds(),Integer.valueOf(parentid));
            nodelist = new ArrayList<Map<String,Object>>();
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Area area:list){
                    node=new HashMap<String, Object>();
                    attributes=new HashMap<String, Object>();
                    Integer id=area.getId();

                    node.put("id", area.getId());
                    node.put("text", area.getName());

                    attributes.put("areatype", area.getAreatype());
                    attributes.put("code",area.getCode());
                    node.put("attributes", attributes);
                    childrenlist=areaMapper.findByParentid(new PageBounds(),id);
                    if(childrenlist!=null&childrenlist.size()>0){
                        node.put("state", "closed");
                    }
                    nodelist.add(node);
                }
            }
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//自动为我排除circle。
            JSONArray jsonArray = JSONArray.fromObject(nodelist, jsonConfig);
            return jsonArray.toString();
        }
    }
}
