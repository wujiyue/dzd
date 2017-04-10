package com.markbro.dzd.base.orgPosition.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import com.markbro.dzd.base.login.bean.LoginBean;
import com.markbro.dzd.base.orgPosition.bean.Position;
import com.markbro.dzd.base.orgPosition.dao.PositionMapper;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.common.util.ConstantUtil;
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
 * 岗位管理 service
 * Created by wujiyue on 2017-02-08 23:04:11.
 */
@Service
public class PositionService{

    @Autowired
    private TableKeyService gwKeyService;
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
    public Object save(Position position){

          Msg msg=new Msg();
          try{
            if(position.getId()==null||"".equals(position.getId().toString())){
               java.lang.String id= gwKeyService.getStringId();
					String parentid=position.getParentid();
					String pids=positionMapper.getParentidsById(parentid);
					if("null".equals(pids)||"".equals(pids)||pids==null){
						pids="0,";
					}
					pids+=id+",";
					position.setParentids(pids);
						int sort=positionMapper.getMaxSortByParentid(parentid);
						position.setSort(sort+1);
               position.setId(id);
               positionMapper.add(position);
            }else{
					String parentid=position.getParentid();
					String pids=positionMapper.getParentidsById(parentid);
					if("null".equals(pids)||"".equals(pids)||pids==null){
						pids="0,";
					}
					pids+=position.getId()+",";
					position.setParentids(pids);
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

	 public List<Position> findByParentid(PageBounds pageBounds,Map<String,Object> map){
		return positionMapper.findByParentid(pageBounds,map);
	}
	  public int checkForDelete(String ids){
		 ids=ids.replaceAll(",", "','").replaceAll("~", "','");
		return positionMapper.checkForDelete(ids);
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
		List<Position> list=null;
		List<Position> childrenlist=null;//每个区域的孩子集合
		Map<String, Object> attributes=null;//每个区域的属性
		List<Map<String, Object>> nodelist =null;//要返回的节点集合
		Map<String, Object> node=null;//节点
		if("0".equals(parentid)){

			//查当前登录用户所属的组织信息作为部门树的根节点
			//组装根节点
			rootNode.put("id","0");//根节点id总是0，这里不是组织的id
			rootNode.put("text", orgName);

			list=positionMapper.findByParentid(new PageBounds(),m);
			nodelist = new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				rootNode.put("state", "open");

				for(Position area:list){
					node=new HashMap<String, Object>();
					String id=area.getId();

					node.put("id", area.getId());
					node.put("text", area.getName()+"|"+area.getId());

					int count=positionMapper.findByParentidCount(id,orgid);
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
			list=positionMapper.findByParentid(new PageBounds(),m);
			nodelist = new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				rootNode.put("state", "open");

				for(Position area:list){
					node=new HashMap<String, Object>();
					String id=area.getId();
					node.put("id", area.getId());
					node.put("text", area.getName()+"|"+area.getId());

					int count=positionMapper.findByParentidCount(id,orgid);
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
	public Object saveSort(Map map){
		Msg msg=new Msg();
		try{
			String sort = String.valueOf(map.get("sort"));
			if(!"".equals(sort)){
				String[] sx = sort.split(",");
				for(int i=0;i<sx.length;i++) {
					String[] arr = sx[i].split("_");
					positionMapper.updateSort(arr[1], arr[2]);
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

	public List<Map<String,Object>> ztree(Map<String,Object> map){
		String parentid= (String) map.get("parentid");
		String orgId= (String) map.get("zzid");
		List<Position> list=positionMapper.findByParentid(new PageBounds(),map);
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		Map<String,Object> tmap=null;
		String id=null;
		int n=0;
		for(Position t:list){
			tmap=new HashMap<String,Object>();
			tmap= MyBatisRequestUtil.beanConvert2Map(t);
			id=t.getId();
			n=positionMapper.findByParentidCount(id,orgId);
			if(n>0){
				tmap.put("isParent",true);
			}
			result.add(tmap);
		}
		return result;
	}
     /*自定义方法*/

}
