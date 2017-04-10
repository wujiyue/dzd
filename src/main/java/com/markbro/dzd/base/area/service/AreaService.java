package com.markbro.dzd.base.area.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.area.bean.Area;
import com.markbro.dzd.base.area.dao.AreaMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 区域 service
 * Created by wujiyue on 2016-07-17 01:33:47.
 */
@Service
public class AreaService{
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Area get(java.lang.String id){
        return areaMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return areaMapper.getMap(id);
    }
    public List<Area> find(PageBounds pageBounds,Map<String,Object> map){
        return areaMapper.find(pageBounds,map);
    }
    public List<Area> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return areaMapper.findByMap(pageBounds,map);
    }
    public void add(Area area){
        areaMapper.add(area);
    }
    public Object save(Area area){
          Msg msg=new Msg();
                 try{
                     if(area.getId()==null||"".equals(area.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         area.setId(id);
                         String parentid=area.getParentid();
                         String pids= areaMapper.getParentidsById(parentid);
                         if("null".equals(pids)||"".equals(pids)||pids==null){
                             pids="0,";
                         }
                         pids+=id+",";
                         area.setParentids(pids);
                         int sort=areaMapper.getMaxSortByParentid(parentid);
                         area.setSort(sort+1);
                         areaMapper.add(area);
                     }else{
                         String parentid=area.getParentid();
                         String pids= areaMapper.getParentidsById(parentid);
                         if("null".equals(pids)||"".equals(pids)||pids==null){
                             pids="0,";
                         }
                         pids+=area.getId()+",";
                         area.setParentids(pids);
                         areaMapper.update(area);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存区域成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存区域失败");
                 }
                return msg;
    }
    public void addBatch(List<Area> areas){
        areaMapper.addBatch(areas);
    }
    public void update(Area area){
        areaMapper.update(area);
    }
    public void updateByMap(Map<String,Object> map){
        areaMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        areaMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        areaMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        areaMapper.deleteBatch(ids);
    }
     /*自定义方法*/
     public int checkForDelete(String ids){
         ids=ids.replaceAll(",", "','").replaceAll("~", "','");
         return areaMapper.checkForDelete(ids);
     }
	 public List<Area> findByParentid(PageBounds pageBounds,java.lang.String parentid){
		return areaMapper.findByParentid(pageBounds,parentid);
	}

    public String tree(Map<String, Object> map){
        String parentid= (String) map.get("parentid");
        Map<String, Object> rootNode=new HashMap<String, Object>();//根节点
        List<Area> list=null;
        List<Area> childrenlist=null;//每个区域的孩子集合
        Map<String, Object> attributes=null;//每个区域的属性
        List<Map<String, Object>> nodelist =null;//要返回的节点集合
        Map<String, Object> node=null;//节点
        if("0".equals(parentid)){
            list=areaMapper.findByParentid(new PageBounds(),"0");
            nodelist = new ArrayList<Map<String,Object>>();
            //组装根节点
            rootNode.put("id", "0");
            rootNode.put("text", "区域目录");
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Area area:list){
                    node=new HashMap<String, Object>();
                    attributes=new HashMap<String, Object>();
                    String id=area.getId();

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
            list=areaMapper.findByParentid(new PageBounds(),String.valueOf(parentid));
            nodelist = new ArrayList<Map<String,Object>>();
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Area area:list){
                    node=new HashMap<String, Object>();
                    attributes=new HashMap<String, Object>();
                    String id=area.getId();

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
    public Object saveSort(Map map){
        Msg msg=new Msg();
        try{
            String sort = String.valueOf(map.get("sort"));
            if(!"".equals(sort)){
                String[] sx = sort.split(",");
                for(int i=0;i<sx.length;i++){
                    String[] arr = sx[i].split("_");
                    areaMapper.updateSort(arr[1], arr[2]);
                }
            }
            msg.setType(Msg.MsgType.success);
            msg.setContent("排序成功！");
        }catch (Exception e){
            msg.setType(Msg.MsgType.error);
            msg.setContent("排序失败！");
        }
        return msg;
    }
}
