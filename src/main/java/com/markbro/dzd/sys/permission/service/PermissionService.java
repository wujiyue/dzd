package com.markbro.dzd.sys.permission.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.sys.permission.bean.Permission;
import com.markbro.dzd.sys.permission.dao.PermissionMapper;
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
 * 菜单权限 Service
 * Created by wujiyue on 2016-07-07 21:47:44.
 */
@Service
public class PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Map<String,Object> get(java.lang.String id){
        return permissionMapper.get(id);
    }
    public List<Permission> find(PageBounds pageBounds,Map<String,Object> map){
        return permissionMapper.find(pageBounds, map);
    }
    public List<Permission> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return permissionMapper.findByMap(pageBounds, map);
    }
    public void add(Permission permission){
        permissionMapper.add(permission);
    }
    public Object save(Permission permission){
          Msg msg=new Msg();
                 try{
                     if(permission.getId()==null||"".equals(permission.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         permission.setId(id);
                         String parentid=permission.getParentid();
                         String pids= permissionMapper.getParentidsById(parentid);
                         pids+=id+",";
                         permission.setParentids(pids);
                         int sort=permissionMapper.getMaxSortByParentid(parentid);
                         permission.setSort(sort+1);
                         permissionMapper.add(permission);
                     }else{
                         String parentid=permission.getParentid();
                         String pids= permissionMapper.getParentidsById(parentid);
                         pids+=permission.getId()+",";
                         permission.setParentids(pids);
                         permissionMapper.update(permission);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Permission> permissions){
        permissionMapper.addBatch(permissions);
    }
    public void update(Permission permission){
        permissionMapper.update(permission);
    }
    public void updateByMap(Map<String,Object> map){
        permissionMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        permissionMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        permissionMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        permissionMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Permission> findByParentid(PageBounds pageBounds,java.lang.String parentid){
		return permissionMapper.findByParentid(pageBounds,parentid);
	}
    public int checkForDelete(String ids){
        ids=ids.replaceAll(",", "','").replaceAll("~", "','");
        return permissionMapper.checkForDelete(ids);
    }
    public Object saveSort(Map map){
        Msg msg=new Msg();
        try{
            String sort = String.valueOf(map.get("sort"));
            if(!"".equals(sort)){
                String[] sx = sort.split(",");
                for(int i=0;i<sx.length;i++){
                    String[] arr = sx[i].split("_");
                    permissionMapper.updateMenuSort(arr[1], arr[2]);
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
    public String tree(Map<String, Object> map){
        // return areaMapper.findByParentid(pageBounds,parentid);
        String parentid= (String) map.get("parentid");
        Map<String, Object> rootNode=new HashMap<String, Object>();//根节点
        List<Permission> list=null;
        List<Permission> childrenlist=null;//每个区域的孩子集合
        Map<String, Object> attributes=null;//每个区域的属性
        List<Map<String, Object>> nodelist =null;//要返回的节点集合
        Map<String, Object> node=null;//节点
        if("0".equals(parentid)){
            list=permissionMapper.findByParentid(new PageBounds(),"0");
            nodelist = new ArrayList<Map<String,Object>>();
            //组装根节点
            rootNode.put("id", "0");
            rootNode.put("text", "权限系统");
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Permission area:list){
                    node=new HashMap<String, Object>();
                    attributes=new HashMap<String, Object>();
                    String id=area.getId();

                    node.put("id", area.getId());
                    node.put("text", area.getName());

                    attributes.put("link", area.getUrl());
                    attributes.put("code",area.getCode());
                    node.put("attributes", attributes);
                    childrenlist=permissionMapper.findByParentid(new PageBounds(),id);
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
            list=permissionMapper.findByParentid(new PageBounds(),String.valueOf(parentid));
            nodelist = new ArrayList<Map<String,Object>>();
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Permission area:list){
                    node=new HashMap<String, Object>();
                    attributes=new HashMap<String, Object>();
                    String id=area.getId();

                    node.put("id", area.getId());
                    node.put("text", area.getName());

                    attributes.put("link", area.getUrl());
                    attributes.put("code",area.getCode());
                    node.put("attributes", attributes);
                    childrenlist=permissionMapper.findByParentid(new PageBounds(),id);
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
