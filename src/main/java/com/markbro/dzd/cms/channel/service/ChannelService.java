package com.markbro.dzd.cms.channel.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.channel.bean.Channel;
import com.markbro.dzd.cms.channel.dao.ChannelMapper;
import com.markbro.dzd.cms.channel_big.bean.ChannelBig;
import com.markbro.dzd.cms.channel_big.dao.ChannelBigMapper;
import com.markbro.dzd.common.util.MyBatisRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 栏目 service
 * Created by wujiyue on 2016-08-04 21:03:50.
 */
@Service
public class ChannelService{
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ChannelBigMapper channelBigMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Channel get(java.lang.String id){
        return channelMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return channelMapper.getMap(id);
    }
    public List<Channel> find(PageBounds pageBounds,Map<String,Object> map){
        return channelMapper.find(pageBounds,map);
    }
    public List<Channel> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return channelMapper.findByMap(pageBounds,map);
    }
    public void add(Channel channel){
        channelMapper.add(channel);
    }
    public Object save(Channel channel){
          Msg msg=new Msg();
                 try{
                     if(channel.getId()==null||"".equals(channel.getId().toString())){
                         java.lang.String id= keyService.getStringId();
						 String parentid=channel.getParentid();
						int sort=channelMapper.getMaxSortByParentid(parentid);
						channel.setSort(sort+1);
                         channel.setId(id);
                         channelMapper.add(channel);
                     }else{

                         channelMapper.update(channel);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Channel> channels){
        channelMapper.addBatch(channels);
    }
    public void update(Channel channel){
        channelMapper.update(channel);
    }
    public void updateByMap(Map<String,Object> map){
        channelMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        channelMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        channelMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        channelMapper.deleteBatch(ids);
    }

	 public List<Channel> findByParentid(PageBounds pageBounds,String parentid){
		return channelMapper.findByParentid(pageBounds,parentid);
	}
	  public int checkForDelete(String ids){
		 ids=ids.replaceAll(",", "','").replaceAll("~", "','");
		return channelMapper.checkForDelete(ids);
	}

	public Object saveSort(Map map){
		Msg msg=new Msg();
		try{
			String sort = String.valueOf(map.get("sort"));
			if(!"".equals(sort)){
				String[] sx = sort.split(",");
				for(int i=0;i<sx.length;i++) {
					String[] arr = sx[i].split("_");
					channelMapper.updateSort(arr[1], arr[2]);
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
        if("".equals(parentid)||"0".equals(parentid)){
            List<ChannelBig> list=channelBigMapper.find(new PageBounds(),null);
            List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
            Map<String,Object> tmap=null;
            String id=null;
            int n=0;
            for(ChannelBig t:list){
                tmap=new HashMap<String,Object>();
                tmap= MyBatisRequestUtil.beanConvert2Map(t);
                id=t.getId();
                n=channelMapper.findByParentidCount(id);
                if(n>0){
                    tmap.put("isParent",true);
                }
                result.add(tmap);
            }
            return result;
        }else{
            List<Channel> list=channelMapper.findByParentid(new PageBounds(),parentid);
            List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
            Map<String,Object> tmap=null;
            String id=null;
            int n=0;
            for(Channel t:list){
                tmap=new HashMap<String,Object>();
                tmap= MyBatisRequestUtil.beanConvert2Map(t);
                id=t.getId();
                n=channelMapper.findByParentidCount(id);
                if(n>0){
                    tmap.put("isParent",true);
                }
                result.add(tmap);
            }
            return result;
        }

	}
    public List<Map<String,Object>> tree(Map<String,Object> map){
        String parentid= (String) map.get("parentid");
        if("".equals(parentid)||"0".equals(parentid)){
            List<ChannelBig> list=channelBigMapper.find(new PageBounds(),null);
            List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
            Map<String,Object> tmap=null;
            String id=null;
            int n=list.size();

                tmap=new HashMap<String,Object>();
                tmap.put("id","root");
                tmap.put("name","栏目分类");
                if(n>0){
                    tmap.put("isParent",true);
                }
                result.add(tmap);

            return result;
        }
        //if("root".equals(parentid)){
        else{
            List<ChannelBig> list=channelBigMapper.find(new PageBounds(), null);
            List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
            Map<String,Object> tmap=null;
            String id=null;
            for(ChannelBig t:list){
                tmap=new HashMap<String,Object>();
                tmap= MyBatisRequestUtil.beanConvert2Map(t);

                result.add(tmap);
            }
            return result;
        }

    }
     /*自定义方法*/

}
