package com.markbro.dzd.base.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * @author peiwenlong
 * json工具类
 */
public class JsonUtil {
	/**
	 * @author peiwenlong
	 * @description json转Map
	 * @param jsonStr
	 * @return Map<String,Object>
	 * @createTime 2014-10-11上午11:22:56
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr){
	    Map<String, Object> map = new HashMap<String, Object>();
	    //最外层解析  
	    JSONObject json = JSONObject.fromObject(jsonStr);
	    for(Object k : json.keySet()){
	        Object v = json.get(k);
	        //如果内层还是数组的话，继续解析  
	        if(v instanceof JSONArray){
	            List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	            Iterator<JSONObject> it = ((JSONArray)v).iterator();
	            while(it.hasNext()){  
	                JSONObject json2 = it.next();
	                list.add(parseJSON2Map(json2.toString()));  
	            }  
	            map.put(k.toString(), list);  
	        } else {  
	            map.put(k.toString(), v);  
	        }  
	    }  
	    return map;  
	} 
}
