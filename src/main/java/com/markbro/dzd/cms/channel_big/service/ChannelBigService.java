package com.markbro.dzd.cms.channel_big.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.cms.channel_big.bean.ChannelBig;
import com.markbro.dzd.cms.channel_big.dao.ChannelBigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 栏目大类 service
 * Created by wujiyue on 2016-08-04 21:14:30.
 */
@Service
public class ChannelBigService{
    @Autowired
    private ChannelBigMapper channelBigMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public ChannelBig get(java.lang.String id){
        return channelBigMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return channelBigMapper.getMap(id);
    }
    public List<ChannelBig> find(PageBounds pageBounds,Map<String,Object> map){
        return channelBigMapper.find(pageBounds,map);
    }
    public List<ChannelBig> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return channelBigMapper.findByMap(pageBounds,map);
    }
    public void add(ChannelBig channelBig){
        channelBigMapper.add(channelBig);
    }
    public Object save(ChannelBig channelBig){
          Msg msg=new Msg();
                 try{
                     if(channelBig.getId()==null||"".equals(channelBig.getId().toString())){
                         java.lang.String id= keyService.getStringId();

					    int sort=channelBigMapper.getMaxSort();
					    channelBig.setSort(sort+1);
                         channelBig.setId(id);
                         channelBigMapper.add(channelBig);
                     }else{

                         channelBigMapper.update(channelBig);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<ChannelBig> channelBigs){
        channelBigMapper.addBatch(channelBigs);
    }
    public void update(ChannelBig channelBig){
        channelBigMapper.update(channelBig);
    }
    public void updateByMap(Map<String,Object> map){
        channelBigMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        channelBigMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        channelBigMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        channelBigMapper.deleteBatch(ids);
    }


	public Object saveSort(Map map){
		Msg msg=new Msg();
		try{
			String sort = String.valueOf(map.get("sort"));
			if(!"".equals(sort)){
				String[] sx = sort.split(",");
				for(int i=0;i<sx.length;i++) {
					String[] arr = sx[i].split("_");
					channelBigMapper.updateSort(arr[1], arr[2]);
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

     /*自定义方法*/

}
