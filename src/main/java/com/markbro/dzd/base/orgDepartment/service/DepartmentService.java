package com.markbro.dzd.base.orgDepartment.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.dzd.base.login.bean.LoginBean;
import com.markbro.dzd.base.orgDepartment.bean.Department;
import com.markbro.dzd.base.orgDepartment.dao.DepartmentMapper;
import com.markbro.dzd.base.orgTree.bean.OrgTree;
import com.markbro.dzd.base.orgTree.dao.OrgTreeMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.common.util.ConstantUtil;
import com.markbro.dzd.common.util.Guid;
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
 * 部门 service
 * Created by wujiyue on 2016-07-17 11:52:55.
 */
@Service
public class DepartmentService{
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private OrgTreeMapper orgTreeMapper;
    @Autowired
    private TableKeyService bmKeyService;
     /*基础公共方法*/
    public Department get(java.lang.String id){
        return departmentMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return departmentMapper.getMap(id);
    }
    public List<Department> find(PageBounds pageBounds,Map<String,Object> map){
        return departmentMapper.find(pageBounds, map);
    }
    public List<Department> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return departmentMapper.findByMap(pageBounds, map);
    }
    public void add(Department department){
        departmentMapper.add(department);
    }
    @Transactional
    public Object save(Map<String,Object> map){

        Msg msg=new Msg();
        try{
            Department department=com.markbro.asoiaf.utils.string.MapUtils.toObject(Department.class,map);

            if(department.getId()==null||"".equals(department.getId().toString())){
                java.lang.String id= bmKeyService.getStringId();
                department.setId(id);
                String orgid= (String) map.get("orgid");
                department.setOrgid(orgid);
                String parentid=department.getParentid();
                String pids= departmentMapper.getParentidsById(parentid);
                if("null".equals(pids)||pids==null){
                    pids="0,";
                }
                pids+=id+",";
                department.setParentids(pids);
                int sort=departmentMapper.getMaxSortByParentid(parentid);
                department.setSort(sort+1);
                departmentMapper.add(department);
                //插入org_tree记录
                OrgTree tree=new OrgTree();
                tree.setOrgid(orgid);
                tree.setId(Guid.get());
                OrgTree treeTemp= orgTreeMapper.getByLxidAndType(department.getParentid(), "bm");
                tree.setParentid(treeTemp.getId());
                tree.setType("bm");
                tree.setSjbmid(treeTemp.getLxid());
                tree.setLxid(department.getId());
                tree.setName(department.getName());
                orgTreeMapper.add(tree);
            }else{
                String parentid =department.getParentid();
                String pids= departmentMapper.getParentidsById(parentid);
                if("null".equals(pids)||pids==null){
                    pids="0,";
                }
                pids+=department.getId()+",";
                department.setParentids(pids);
                departmentMapper.update(department);
            }
            msg.setType(Msg.MsgType.success);
            msg.setContent("保存信息成功");
        }catch (Exception ex){
            msg.setType(Msg.MsgType.error);
            msg.setContent("保存信息失败");
        }
        return msg;
    }
    public void addBatch(List<Department> departments){
        departmentMapper.addBatch(departments);
    }
    public void update(Department department){
        departmentMapper.update(department);
    }
    public void updateByMap(Map<String,Object> map){
        departmentMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        departmentMapper.updateByMapBatch(map);
    }
    @Transactional
    public void delete(java.lang.String id){
        Department department=departmentMapper.get(id);
        orgTreeMapper.deleteByLxidAndType(department.getId(),"bm");
        departmentMapper.delete(id);
    }
    @Transactional
    public void deleteBatch(java.lang.String[] ids){
        for(int i=0;i<ids.length;i++)
        {
            Department department=departmentMapper.get(ids[0]);
            orgTreeMapper.deleteByLxidAndType(department.getId(),"bm");
        }
        departmentMapper.deleteBatch(ids);
    }
     /*自定义方法*/

    public List<Department> findByParentid(PageBounds pageBounds,Map<String,Object> map){
        return departmentMapper.findByParentid(pageBounds,map);
    }

    public String tree(Map<String, Object> map){
        String yhid=(String) map.get("yhid");
        LoginBean loginBean=(LoginBean) EhCacheUtils.getUserInfo(ConstantUtil.CACHE_YH_USERBEAN, yhid);
        Map<String, Object> orgMap = loginBean.getOrgMap();
        String orgid= (String) orgMap.get("id");
        String orgName= (String) orgMap.get("name");

        String parentid= (String) map.get("parentid");
        Map m=new HashMap<String,Object>();
        m.put("orgid",orgid);
        m.put("parentid",parentid);

        Map<String, Object> rootNode=new HashMap<String, Object>();//根节点
        List<Department> list=null;
        List<Department> childrenlist=null;//每个区域的孩子集合
        Map<String, Object> attributes=null;//每个区域的属性
        List<Map<String, Object>> nodelist =null;//要返回的节点集合
        Map<String, Object> node=null;//节点
        if("0".equals(parentid)){

            //查当前登录用户所属的组织信息作为部门树的根节点
            //组装根节点
            rootNode.put("id","0");//根节点id总是0，这里不是组织的id
            rootNode.put("text", orgName);
            //attributes=new HashMap<String, Object>();
            // attributes.put("type","org");
            //rootNode.put("attributes", attributes);

            list=departmentMapper.findByParentid(new PageBounds(),m);
            nodelist = new ArrayList<Map<String,Object>>();
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Department area:list){
                    node=new HashMap<String, Object>();
                    String id=area.getId();

                    node.put("id", area.getId());
                    node.put("text", area.getName()+"|"+area.getId());
                    //attributes=new HashMap<String, Object>();
                    //node.put("attributes", attributes);
                    int count=departmentMapper.findByParentidCount(id,orgid);
                    if(count>0){
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
            list=departmentMapper.findByParentid(new PageBounds(),m);
            nodelist = new ArrayList<Map<String,Object>>();
            if(list!=null&&list.size()>0){
                rootNode.put("state", "open");

                for(Department area:list){
                    node=new HashMap<String, Object>();
                    String id=area.getId();
                    node.put("id", area.getId());
                    node.put("text", area.getName()+"|"+area.getId());
                    //attributes=new HashMap<String, Object>();
                    //node.put("attributes", attributes);
                    int count=departmentMapper.findByParentidCount(id,orgid);
                    if(count>0){
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
    public int checkForDelete(String ids){
        ids=ids.replaceAll(",", "','").replaceAll("~", "','");
        return departmentMapper.checkForDelete(ids);
    }
    public Object saveSort(Map map){
        Msg msg=new Msg();
        try{
            String sort = String.valueOf(map.get("sort"));
            if(!"".equals(sort)){
                String[] sx = sort.split(",");
                for(int i=0;i<sx.length;i++) {
                    String[] arr = sx[i].split("_");
                    departmentMapper.updateSort(arr[1], arr[2]);
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
