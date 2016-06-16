package com.markbro.dzd.common.util;

import com.markbro.asoiaf.core.utils.SysPara;

import java.util.Map;

/**.
 * 生成带页码查询条件的Map对象
 * 
 * @author yangsl
 *
 */
public class PageUtil {
	private String dbType = "db_type";
	private String pageNoKey = "pageNo";
	private String pageGoKey = "pageSize";
	private int getDbType(){
		if(SysPara.compareValue(dbType, "sqlserver")){
			return 1;
		}else if(SysPara.compareValue(dbType, "mysql")){
			return 0;
		}else{
			return 2;
		}
	}
	/**
	 * 根据传入的页码，每页显示的记录数量，换算成不同数据据的分页条件，放入Map中并返回
	 * 创建时间　2014-1-9
	 * 创建人　yangsl
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> getPageMap(Map<String, Object> map, final int pageNo, final int pageSize){
		switch(getDbType()){
		case 0://mysql
		case 1://sqlserver
			int start = pageNo-1;
			if(pageNo == 1 || pageNo == 0){
				start = 0;
			}
			map.put(pageNoKey, start * pageSize);
			map.put(pageGoKey, pageSize);
			break;
		case 2://others
			if(pageNo == 0 || pageNo == 1){
				map.put(pageNoKey, 0);
				map.put(pageGoKey, pageSize);
			}else{
				map.put(pageNoKey, pageSize * (pageNo-1)+1);
				map.put(pageGoKey, pageSize * pageNo);
			}
			break;
		default:
		}
		
		/*if(getDbType() == 0){
			int start = pageNo-1;
			if(pageNo == 1 || pageNo == 0){
				start = 0;
			}
			map.put(pageNoKey, start * pageSize);
			map.put(pageGoKey, pageSize);
		}else{
			if(pageNo == 0 || pageNo == 1){
				map.put(pageNoKey, 0);
				map.put(pageGoKey, pageSize);
			}else{
				map.put(pageNoKey, pageSize * (pageNo-1)+1);
				map.put(pageGoKey, pageSize * pageNo);
			}
		}*/
		
		return map;
	}
}
