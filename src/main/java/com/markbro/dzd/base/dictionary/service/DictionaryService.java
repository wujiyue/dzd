package com.markbro.dzd.base.dictionary.service;
import com.markbro.dzd.base.dictionary.bean.Dictionary;
import com.markbro.dzd.base.dictionary.dao.DictionaryMapper;
import org.springframework.stereotype.Service;
import com.markbro.asoiaf.core.service.CrudService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * Dictionary Service
 * Created by wujiyue on 2016-03-06 22:45:03.
 */
@Service
public class DictionaryService{
    @Autowired
    private DictionaryMapper dictionaryMapper;
     /*基础公共方法*/
    public Dictionary get(java.lang.Integer id){
        return dictionaryMapper.get(id);
    }
    public List<Dictionary> find(PageBounds pageBounds,Map<String,Object> map){
        return dictionaryMapper.find(pageBounds,map);
    }
    public List<Dictionary> findByMap(PageBounds pageBounds,Map<String,Object> map){
        return dictionaryMapper.findByMap(pageBounds,map);
    }
    public void add(Dictionary dictionary){
        dictionaryMapper.add(dictionary);
    }
    public void addBatch(List<Dictionary> dictionarys){
        dictionaryMapper.addBatch(dictionarys);
    }
    public void update(Dictionary dictionary){
        dictionaryMapper.update(dictionary);
    }
    public void updateByMap(Map<String,Object> map){
        dictionaryMapper.updateByMap(map);
    }
    public void updateByMapBatch(Map<String,Object> map){
        dictionaryMapper.updateByMapBatch(map);
    }
    public void delete(java.lang.Integer id){
        dictionaryMapper.delete(id);
    }
    public void deleteBatch(java.lang.Integer[] ids){
        dictionaryMapper.deleteBatch(ids);
    }
     /*自定义方法*/

	 public List<Dictionary> findByType(PageBounds pageBounds,java.lang.String type){
		return dictionaryMapper.findByType(pageBounds,type);
	}
}
