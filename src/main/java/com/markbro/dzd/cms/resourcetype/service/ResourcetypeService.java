package com.markbro.dzd.cms.resourcetype.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.resourcetype.bean.Resourcetype;
import com.markbro.dzd.cms.resourcetype.dao.ResourcetypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 资源分类 service
 * Created by wujiyue on 2016-07-21 21:21:06.
 */
@Service
public class ResourcetypeService{
    @Autowired
    private ResourcetypeMapper resourcetypeMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Resourcetype get(java.lang.String id){
        return resourcetypeMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return resourcetypeMapper.getMap(id);
    }
    public List<Resourcetype> find(PageBounds pageBounds,Map<String,Object> map){
        return resourcetypeMapper.find(pageBounds,map);
    }
    public List<Resourcetype> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return resourcetypeMapper.findByMap(pageBounds,map);
    }
    public void add(Resourcetype resourcetype){
        resourcetypeMapper.add(resourcetype);
    }
    public Object save(Resourcetype resourcetype){
          Msg msg=new Msg();
                 try{
                     if(resourcetype.getId()==null||"".equals(resourcetype.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         resourcetype.setId(id);
                         String parentid=resourcetype.getParentid();
                         String pids= resourcetypeMapper.getParentidsById(parentid);
                         if("null".equals(pids)||pids==null){
                             pids="0,";
                         }
                         pids+=id+",";
                         resourcetype.setParentids(pids);
                         int sort=resourcetypeMapper.getMaxSortByParentid(parentid);
                         resourcetype.setSort(sort+1);
                         resourcetypeMapper.add(resourcetype);
                     }else{
                         String parentid =resourcetype.getParentid();
                         String pids= resourcetypeMapper.getParentidsById(parentid);
                         if("null".equals(pids)||pids==null){
                             pids="0,";
                         }
                         pids+=resourcetype.getId()+",";
                         resourcetype.setParentids(pids);
                         resourcetypeMapper.update(resourcetype);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Resourcetype> resourcetypes){
        resourcetypeMapper.addBatch(resourcetypes);
    }
    public void update(Resourcetype resourcetype){
        resourcetypeMapper.update(resourcetype);
    }
    public void updateByMap(Map<String,Object> map){
        resourcetypeMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        resourcetypeMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        resourcetypeMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        resourcetypeMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Resourcetype> findByParentid(PageBounds pageBounds,java.lang.String parentid){
		return resourcetypeMapper.findByParentid(pageBounds,parentid);
	}

    public List<Map<String,Object>> ztree(Map<String,Object> map){
        String parentid= (String) map.get("parentid");
        List<Resourcetype> list=resourcetypeMapper.findByParentid(new PageBounds(),parentid);
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        Map<String,Object> tmap=null;
        String id=null;
        int n=0;
        for(Resourcetype t:list){
            tmap=new HashMap<String,Object>();
            tmap= MyBatisRequestUtil.beanConvert2Map(t);
            id=t.getId();
            n=resourcetypeMapper.findByParentidCount(id);
           if(n>0){
                tmap.put("isParent",true);
            }
           /*  String type= (String) tmap.get("type");
            if("bm".equals(type)){
                tmap.put("iconSkin","bm");
            }
            if("gw".equals(type)){
                tmap.put("iconSkin","gw");
            }
            if("ry".equals(type)){
                tmap.put("iconSkin","ry");
            }*/
            result.add(tmap);
        }
        return result;
    }

    public int checkForDelete(String ids){
        ids=ids.replaceAll(",", "','").replaceAll("~", "','");
        return resourcetypeMapper.checkForDelete(ids);
    }
    @Transactional
    public Object saveSort(Map map){
        Msg msg=new Msg();
        try{
            String sort = String.valueOf(map.get("sort"));
            if(!"".equals(sort)){
                String[] sx = sort.split(",");
                for(int i=0;i<sx.length;i++) {
                    String[] arr = sx[i].split("_");
                    resourcetypeMapper.updateSort(arr[1], arr[2]);
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
    public List<Map<String,Object>> treeSelect(Map<String,Object> map){

        List<Map<String,Object>> list=resourcetypeMapper.treeSelect();
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        Map<String,Object> tmap=null;
        String id=null;
        int n=0;
        for(Map<String,Object> t:list){
            tmap=new HashMap<String,Object>();
            n=String.valueOf(t.get("parentids")).split(",").length-1;
            tmap.put("dm",String.valueOf(t.get("id")));
            tmap.put("mc",getPrependStr(n)+String.valueOf(t.get("name")));
            result.add(tmap);
        }
        return result;
    }
    private  String getPrependStr(int n){
        String s="|---";
        if(n==1){
            return s;
        }else if(n>1){
            for(int i=0;i<n-1;i++){
                s+="|----";
            }
            return s;
        }else{
            return "";
        }

    }
}
