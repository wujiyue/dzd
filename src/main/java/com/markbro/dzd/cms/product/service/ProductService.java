package com.markbro.dzd.cms.product.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.cms.product.bean.Product;
import com.markbro.dzd.cms.product.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 产品 service
 * Created by wujiyue on 2016-09-17 18:05:19.
 */
@Service
public class ProductService{
    @Autowired
    private ProductMapper productMapper;
     /*基础公共方法*/
    public Product get(java.lang.Integer id){
        return productMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return productMapper.getMap(id);
    }
    public List<Product> find(PageBounds pageBounds,Map<String,Object> map){
        return productMapper.find(pageBounds,map);
    }
    public List<Product> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return productMapper.findByMap(pageBounds,map);
    }
    public void add(Product product){
        productMapper.add(product);
    }
    public Object save(Product product){
          Msg msg=new Msg();
                 try{
                     if(product.getId()==null||"".equals(product.getId().toString())){
						int sort=productMapper.getMaxSort();
						product.setSort(sort+1);
                        productMapper.add(product);
                     }else{
                         productMapper.update(product);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Product> products){
        productMapper.addBatch(products);
    }
    public void update(Product product){
        productMapper.update(product);
    }
    public void updateByMap(Map<String,Object> map){
        productMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        productMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        productMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        productMapper.deleteBatch(ids);
    }


	public Object saveSort(Map map){
		Msg msg=new Msg();
		try{
			String sort = String.valueOf(map.get("sort"));
			if(!"".equals(sort)){
				String[] sx = sort.split(",");
				for(int i=0;i<sx.length;i++) {
					String[] arr = sx[i].split("_");
					productMapper.updateSort(arr[1], arr[2]);
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
     public List<Map<String,Object>> select(Map<String,Object> map){

         List<Product> list=productMapper.find(new PageBounds(),map);
         List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
         Map<String,Object> tmap=null;
         String id=null;
         for(Product t:list){
             tmap=new HashMap<String,Object>();
             tmap.put("dm",t.getCpdm());
             tmap.put("mc",t.getCpmc());
             result.add(tmap);
         }
         return result;
     }
}
