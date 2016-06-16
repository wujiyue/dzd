package com.markbro.dzd.base.util;

import com.markbro.dzd.base.util.dao.BasicUtilMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 该类主要用于处理数据表中的塔式列表
 * @author yangsl
 *
 */
@Service
public class BasicTreeNode {
	@Resource
	private BasicUtilMapper basicUtilMapper;
	/**
	 * 步骤说明：
	 * 	2、表的上级字段与主键字段
	 * 	3、递归该表，给字段加入塔式内容
	 * 
	 * 创建时间  2014-3-11 下午4:21:42 
	 * 创建人 yangsl
	 *
	 */
	/*public void tableNodeColumnInit(String table, String pkName, String sjidName, String pkVal, int level){
		List<Map<String, Object>> column = basicUtilMapper.queryTableColumnBySjid(table, sjidName, pkVal);
		if(column != null && column.size() > 0){
			String sql = firstSql;
			sql = sql.replaceAll("_table_", table);
			sql = sql.replaceAll("_pkVal_", pkVal);
			sql = sql.replaceAll("_level_", level+"");
			sql = sql.replaceAll("_sjidName_", sjidName);
			basicUtilMapper.updateForSql(sql);
			int tLevel = level+1;
			for(int i=0;i<column.size();i++){
				Map<String, Object> m = column.get(i);
				String tpkVal = m.get(pkName).toString();
				tableNodeColumnInit(table, pkName, sjidName, tpkVal, tLevel);
			}
		}
	}*/
	public void tableNodeColumnInit(String table, String pkName, String sjidName, String pkVal, int level){
		List<Map<String, Object>> column = basicUtilMapper.queryTableColumnBySjid(table, sjidName, pkVal);
		if(column != null && column.size() > 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("_table_", table);
			map.put("_pkVal_", pkVal);
			map.put("_level_", level+"");
			map.put("_sjidName_", sjidName);
			map.put("_table_", table);
			
			basicUtilMapper.updateForSql(map);
			int tLevel = level+1;
			for(int i=0;i<column.size();i++){
				Map<String, Object> m = column.get(i);
				String tpkVal = m.get(pkName).toString();
				tableNodeColumnInit(table, pkName, sjidName, tpkVal, tLevel);
			}
		}
	}
	private String firstSql = "UPDATE _table_ a, ("
			+ " SELECT a.guid,IFNULL((SELECT treecode FROM _table_ WHERE guid='_pkVal_'), '') AS prex, "
			+ "  CASE WHEN LENGTH(COUNT(*))='1' THEN CONCAT('000', COUNT(*)) "
			+ "    WHEN LENGTH(COUNT(*))='2' THEN CONCAT('00', COUNT(*)) "
			+ "    WHEN LENGTH(COUNT(*))='3' THEN CONCAT('0', COUNT(*)) "
			+ "   END treecode,COUNT(*) treeorder, _level_ treelevel "
			+ " FROM (SELECT guid FROM _table_ WHERE _sjidName_='_pkVal_') a, "
			+ "      (SELECT guid FROM _table_ WHERE _sjidName_='_pkVal_') b "
			+ " WHERE a.guid>=b.guid GROUP BY a.guid) c "
			+ " SET a.treecode = CONCAT(c.prex,c.treecode),a.treeorder = c.treeorder,a.treelevel = c.treelevel WHERE a.guid = c.guid ";
	
}
