package com.markbro.dzd.base.orgTree.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import com.markbro.dzd.base.orgTree.bean.OrgTree;
import com.markbro.dzd.base.orgTree.dao.OrgTreeMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 组织目录 Service
 * Created by wujiyue on 2016-07-14 12:01:01.
 */
@Service
public class OrgTreeService {
    @Autowired
    private OrgTreeMapper treeMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public OrgTree get(java.lang.String id){
        return treeMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return treeMapper.getMap(id);
    }
    public List<OrgTree> find(PageBounds pageBounds,Map<String,Object> map){
        return treeMapper.find(pageBounds,map);
    }
    public List<OrgTree> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return treeMapper.findByMap(pageBounds,map);
    }
    public void add(OrgTree tree){
        treeMapper.add(tree);
    }
    public Object save(OrgTree tree){
          Msg msg=new Msg();
                 try{
                     if(tree.getId()==null||"".equals(tree.getId().toString())){
                         java.lang.String id= keyService.getStringId();
                         tree.setId(id);
                         treeMapper.add(tree);
                     }else{
                         treeMapper.update(tree);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<OrgTree> trees){
        treeMapper.addBatch(trees);
    }
    public void update(OrgTree tree){
        treeMapper.update(tree);
    }
    public void updateByMap(Map<String,Object> map){
        treeMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        treeMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        treeMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        treeMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<OrgTree> findByOrgid(PageBounds pageBounds,java.lang.String orgid){
		return treeMapper.findByOrgid(pageBounds,orgid);
	}
	 public List<OrgTree> findByParentid(PageBounds pageBounds,java.lang.String parentid){
		return treeMapper.findByParentid(pageBounds,parentid);
	}
    public List<Map<String,Object>> ztree(Map<String,Object> map){
        String orgid= (String) map.get("orgid");
        String parentid= (String) map.get("parentid");
        List<OrgTree> list=treeMapper.findByParentid(new PageBounds(),parentid);
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        Map<String,Object> tmap=null;
        String id=null;
        int n=0;
        for(OrgTree t:list){
            tmap=new HashMap<String,Object>();
            tmap= MyBatisRequestUtil.beanConvert2Map(t);
            id=t.getId();
            n=treeMapper.findByParentidCount(id,orgid);
            if(n>0){
                tmap.put("isParent",true);
            }
            String type= (String) tmap.get("type");
            if("bm".equals(type)){
                tmap.put("iconSkin","bm");
            }
            if("gw".equals(type)){
                tmap.put("iconSkin","gw");
            }
            if("ry".equals(type)){
                tmap.put("iconSkin","ry");
            }
            result.add(tmap);
        }
        return result;
    }
}
