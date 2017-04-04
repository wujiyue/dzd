package com.markbro.dzd.sys.permission.service;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.utils.string.StringUtil;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.sys.permission.bean.Permission;
import com.markbro.dzd.sys.permission.bean.PermissionVo;
import com.markbro.dzd.sys.permission.dao.PermissionMapper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单权限 service
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
                         if("null".equals(pids)||pids==null){
                             pids="0,";
                         }
                         pids+=id+",";
                         permission.setParentids(pids);
                         int sort=permissionMapper.getMaxSortByParentid(parentid);
                         permission.setSort(sort+1);
                         if(permission.getAvailable()==null){
                             permission.setAvailable(1);
                         }
                         permissionMapper.add(permission);
                     }else{
                         String parentid=permission.getParentid();
                         String pids= permissionMapper.getParentidsById(parentid);
                         if("null".equals(pids)||pids==null){
                             pids="0,";
                         }
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
    //角色授权功能查询三级权限列表
    public String findPermissionsForShouquan(){
        List<Permission> list=null;//一级权限
        List<Permission> list2=null;//二级权限
        List<Permission> list3=null;//三级权限
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> result2=null;
        List<Map<String,Object>> result3=null;
        //三级权限，parentid为0的是第一级
        Map<String,Object> rmap=null;
        Map<String,Object> rmap2=null;
        Map<String,Object> rmap3=null;
        list=findByParentid(new PageBounds(),"0");
        String id=null;
        String id2=null;
        String id3=null;
        for(Permission p:list){
            result2=new ArrayList<Map<String,Object>>();
            rmap=new HashMap<String,Object>();
            rmap.put("type","mk");//第一级为模块类型
            id=p.getId();
            rmap.put("id",id);
            rmap.put("name",p.getName());
           //查询parentid为id的就是二级菜单
            list2=findByParentid(new PageBounds(),id);
            for(Permission p2:list2){
                result3=new ArrayList<Map<String,Object>>();
                rmap2=new HashMap<String,Object>();
                rmap2.put("type","ym");//第二级为页面类型
                id2=p2.getId();
                rmap2.put("id",id2);
                rmap2.put("name",p2.getName());
                //查询parentid为id2的就是三级菜单
                list3=findByParentid(new PageBounds(),id2);
                for(Permission p3:list3){
                    rmap3=new HashMap<String,Object>();
                    rmap3.put("type","ff");//第三级为方法类型
                    id3=p3.getId();
                    rmap3.put("id",id3);
                    rmap3.put("name",p3.getName());
                    result3.add(rmap3);
                }
                rmap2.put("children",result3);
                result2.add(rmap2);
            }
            rmap.put("children",result2);
            result.add(rmap);
        }

        return JSONObject.toJSONString(result);
    }
    //角色授权功能查询三级权限列表  新增用到
    public List<PermissionVo> findPermissionsListForShouquan(){
        List<Permission> list=null;//一级权限
        List<Permission> list2=null;//二级权限
        List<Permission> list3=null;//三级权限
        List<PermissionVo> result=new ArrayList<PermissionVo>();
        List<PermissionVo> result2=null;
        List<PermissionVo> result3=null;
        //三级权限，parentid为0的是第一级
        PermissionVo rmap=null;
        PermissionVo rmap2=null;
        PermissionVo rmap3=null;
        list=findByParentid(new PageBounds(),"0");
        String id=null;
        String id2=null;
        String id3=null;
        for(Permission p:list){
            result2=new ArrayList<PermissionVo>();
            rmap=new PermissionVo();
            rmap.setType("mk");//第一级为模块类型
            id=p.getId();
            rmap.setId(id);
            rmap.setName(p.getName());
            //查询parentid为id的就是二级菜单
            list2=findByParentid(new PageBounds(),id);
            for(Permission p2:list2){
                result3=new ArrayList<PermissionVo>();
                rmap2=new PermissionVo();
                rmap2.setType("ym");//第二级为页面类型
                id2=p2.getId();
                rmap2.setId(id2);
                rmap2.setName(p2.getName());
                //查询parentid为id2的就是三级菜单
                list3=findByParentid(new PageBounds(),id2);
                for(Permission p3:list3){
                    rmap3=new PermissionVo();
                    rmap3.setType("ff");//第三级为方法类型
                    id3=p3.getId();
                    rmap3.setId(id3);
                    rmap3.setName(p3.getName());
                    result3.add(rmap3);
                }

                rmap2.setChildren(result3);
                result2.add(rmap2);
            }
            rmap.setChildren(result2);
            result.add(rmap);
        }

        return result;
    }

    //角色授权功能查询三级权限列表   编辑用到
    public List<PermissionVo> findPermissionsListForShouquanEdit(String jsid){
        List<String> jsqxs=permissionMapper.findPermissionsByRole(jsid);

        List<Permission> list=null;//一级权限
        List<Permission> list2=null;//二级权限
        List<Permission> list3=null;//三级权限
        List<PermissionVo> result=new ArrayList<PermissionVo>();
        List<PermissionVo> result2=null;
        List<PermissionVo> result3=null;
        //三级权限，parentid为0的是第一级
        PermissionVo rmap=null;
        PermissionVo rmap2=null;
        PermissionVo rmap3=null;
        list=findByParentid(new PageBounds(),"0");
        String id=null;
        String id2=null;
        String id3=null;
        for(Permission p:list){
            result2=new ArrayList<PermissionVo>();
            rmap=new PermissionVo();
            rmap.setType("mk");//第一级为模块类型
            id=p.getId();
            if(StringUtil.isContains(id,jsqxs)){
                rmap.setChecked("1");//该权限选中
            }
            rmap.setId(id);
            rmap.setName(p.getName());
            //查询parentid为id的就是二级菜单
            list2=findByParentid(new PageBounds(),id);
            for(Permission p2:list2){
                result3=new ArrayList<PermissionVo>();
                rmap2=new PermissionVo();
                rmap2.setType("ym");//第二级为页面类型
                id2=p2.getId();
                if(StringUtil.isContains(id2,jsqxs)){
                    rmap2.setChecked("1");//该权限选中
                }
                rmap2.setId(id2);
                rmap2.setName(p2.getName());
                //查询parentid为id2的就是三级菜单
                list3=findByParentid(new PageBounds(),id2);
                for(Permission p3:list3){
                    rmap3=new PermissionVo();
                    rmap3.setType("ff");//第三级为方法类型
                    id3=p3.getId();
                    if(StringUtil.isContains(id3,jsqxs)){
                        rmap3.setChecked("1");//该权限选中
                    }
                    rmap3.setId(id3);
                    rmap3.setName(p3.getName());
                    result3.add(rmap3);
                }

                rmap2.setChildren(result3);
                result2.add(rmap2);
            }
            rmap.setChildren(result2);
            result.add(rmap);
        }

        return result;
    }
    public Object saveRolePermissions(Map map){
        Msg msg=new Msg();
        try {
            String ids= (String) map.get("ids");
            String jsid= (String) map.get("jsid");
            String[] arr=ids.split(",");
            int len=arr.length;
            if(len>0){
                permissionMapper.deleteRolePermission(jsid);
                for(int i=0;i<len;i++){
                    permissionMapper.addRolePermission(arr[i],jsid);
                }
            }

            msg.setType(Msg.MsgType.success);
            msg.setContent("保存角色权限成功！");
        }catch (Exception e){
            msg.setType(Msg.MsgType.error);
            msg.setContent("保存角色权限失败！");
        }
        return msg;
    }

    /**
     * 用户的权限列表
     * @param yhid
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getQxByYhid(String yhid) {
        List<Map<String,Object>> qxList=permissionMapper.queryQxByYhid(yhid);

        return qxList;
    }
    /**
     * 用户的菜单列表
     * @param yhid
     * @return
     */
    public List<Map<String, Object>> getUserMenus(String yhid) {
        List<Map<String,Object>> qxList=permissionMapper.queryQxByYhid(yhid);
        //List<Map<String,Object>> one=
        List<Map<String,Object>> one= qxList.stream().filter(qx->{if(((String)qx.get("parentid")).equals("0")&&((String)qx.get("qxlx")).equals("1")) return  true;else return  false;}).sorted((qx1,qx2)-> ((Integer)qx1.get("sort")).compareTo((Integer)qx2.get("sort"))).collect(Collectors.toList());

        List<Map<String,Object>> two=null;
        List<Map<String,Object>> three=null;
        for(Map<String,Object> m:one){
            final String id=(String)m.get("id");
            two=new ArrayList<Map<String,Object>>();
            two=qxList.stream().filter(qx->{if(((String)qx.get("parentid")).equals(id)&&((String)qx.get("qxlx")).equals("1")) return  true;else return  false;}).sorted((qx1, qx2) -> ((Integer) qx1.get("sort")).compareTo((Integer) qx2.get("sort"))).collect(Collectors.toList());
            for(Map<String,Object> mm:two){
                final String id2=(String)mm.get("id");
                three=new ArrayList<Map<String,Object>>();
                three=qxList.stream().filter(qx->{if(((String)qx.get("parentid")).equals(id2)&&((String)qx.get("qxlx")).equals("1")) return  true;else return  false;}).sorted((qx1, qx2) -> ((Integer) qx1.get("sort")).compareTo((Integer) qx2.get("sort"))).collect(Collectors.toList());
                mm.put("children",three);
            }
            m.put("children",two);
        }
        return one;
    }

}
