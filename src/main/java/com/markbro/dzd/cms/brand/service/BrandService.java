package com.markbro.dzd.cms.brand.service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.cms.brand.bean.Brand;
import com.markbro.dzd.cms.brand.dao.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 品牌 service
 * Created by wujiyue on 2016-09-17 17:58:15.
 */
@Service
public class BrandService{
    @Autowired
    private BrandMapper brandMapper;
     /*基础公共方法*/
    public Brand get(java.lang.Integer id){
        return brandMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.Integer id){
        return brandMapper.getMap(id);
    }
    public List<Brand> find(PageBounds pageBounds,Map<String,Object> map){
        return brandMapper.find(pageBounds,map);
    }
    public List<Brand> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return brandMapper.findByMap(pageBounds,map);
    }
    public void add(Brand brand){
        brandMapper.add(brand);
    }
    public Object save(Brand brand){
          Msg msg=new Msg();
                 try{
                     if(brand.getId()==null||"".equals(brand.getId().toString())){
                         brandMapper.add(brand);
                     }else{
                         brandMapper.update(brand);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Brand> brands){
        brandMapper.addBatch(brands);
    }
    public void update(Brand brand){
        brandMapper.update(brand);
    }
    public void updateByMap(Map<String,Object> map){
        brandMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        brandMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        brandMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        brandMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
