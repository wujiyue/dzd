package com.markbro.dzd.base.orgTree.service;
import com.markbro.dzd.base.orgTree.bean.Tree;
import com.markbro.dzd.base.orgTree.dao.TreeMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Tree Service
 * Created by wujiyue on 2016-06-12 22:41:02.
 */
@Service
public class TreeService{
    @Autowired
    private TreeMapper treeMapper;
     /*基础公共方法*/
    public Tree get(java.lang.String id){
        return treeMapper.get(id);
    }
    public List<Tree> find(PageBounds pageBounds,Map<String,Object> map){
        return treeMapper.find(pageBounds,map);
    }
    public List<Tree> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return treeMapper.findByMap(pageBounds,map);
    }
    public void add(Tree tree){
        treeMapper.add(tree);
    }
    public void addBatch(List<Tree> trees){
        treeMapper.addBatch(trees);
    }
    public void update(Tree tree){
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

	 public List<Tree> findByOrgid(PageBounds pageBounds,java.lang.String orgid){
		return treeMapper.findByOrgid(pageBounds,orgid);
	}
}
