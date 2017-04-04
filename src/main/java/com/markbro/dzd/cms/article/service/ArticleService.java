package com.markbro.dzd.cms.article.service;
import com.markbro.dzd.cms.article.bean.Article;
import com.markbro.dzd.cms.article.dao.ArticleMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import com.markbro.asoiaf.core.model.Msg;
import java.util.List;
import java.util.ArrayList;
import com.markbro.dzd.common.util.MyBatisRequestUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
/**
 * 文章 service
 * Created by wujiyue on 2016-08-14 20:02:46.
 */
@Service
public class ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TableKeyService keyService;
     /*基础公共方法*/
    public Article get(java.lang.String id){
        return articleMapper.get(id);
    }
    public Map<String,Object> getMap(java.lang.String id){
        return articleMapper.getMap(id);
    }
    public List<Article> find(PageBounds pageBounds,Map<String,Object> map){
        return articleMapper.find(pageBounds,map);
    }
    public List<Article> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return articleMapper.findByMap(pageBounds,map);
    }
    public void add(Article article){
        articleMapper.add(article);
    }
    public Object save(Article article){
          Msg msg=new Msg();
                 try{
                     if(article.getId()==null||"".equals(article.getId().toString())){
                         java.lang.String id= keyService.getStringId();


                         article.setId(id);
                         articleMapper.add(article);
                     }else{

                         articleMapper.update(article);
                     }
                     msg.setType(Msg.MsgType.success);
                     msg.setContent("保存信息成功");
                 }catch (Exception ex){
                     msg.setType(Msg.MsgType.error);
                     msg.setContent("保存信息失败");
                 }
                return msg;
    }
    public void addBatch(List<Article> articles){
        articleMapper.addBatch(articles);
    }
    public void update(Article article){
        articleMapper.update(article);
    }
    public void updateByMap(Map<String,Object> map){
        articleMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        articleMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.String id){
        articleMapper.delete(id);
    }
    public void deleteBatch(java.lang.String[] ids){
        articleMapper.deleteBatch(ids);
    }



     /*自定义方法*/

}
