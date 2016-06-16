package com.markbro.dzd.base.util;


import com.markbro.dzd.common.util.ConstantUtil;
import com.markbro.dzd.common.util.ListMap2TreeUtils;
import com.markbro.dzd.common.util.PatternUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.*;

/**
 * 共用方法：用于将列表转换成树形结构
 * @author yangsl
 *
 */
public class BasicListToTree {
	/**
	 * List转树的方法
	 * column = [id, mc, parentid]
	 * 创建时间　2014-1-3
	 * 创建人　yangsl
	 * 
	 * @param m2tList
	 * @param column
	 * @return
	 */
	public static String getTreeJsonString(final List<Map<String, Object>> m2tList,
			final String[] column, final String rootName) {
		if(column.length < 4){
			return "";
		}
		Map<String, List<ListMap2TreeUtils>> arrayListMap = getArrayListMap(m2tList,column,rootName);
		return listToString(arrayListMap);
	}
	/**
	 * 生成层次形式的JSON
	 * 
	 * 创建时间  2014-2-21 上午10:12:04 
	 * 创建人 yangsl
	 * @param arrayListMap
	 * @return
	 *
	 */
	private static String listToString(Map<String, List<ListMap2TreeUtils>> arrayListMap, String... args){
		for (Map.Entry<String, List<ListMap2TreeUtils>> entry : arrayListMap.entrySet()) {
			List<ListMap2TreeUtils> smallTreeList = new ArrayList<ListMap2TreeUtils>();
			smallTreeList = entry.getValue();
			int nodeListSize = smallTreeList.size();
			for (int i = 0; i < nodeListSize; i++) {
				String findID = smallTreeList.get(i).getId();
				List<ListMap2TreeUtils> findList = arrayListMap.get(findID);
				smallTreeList.get(i).setChildren(findList);
			}
		}
		String parentKey = "";
		if(args != null && args.length > 0 && !args[0].equals("")){
			parentKey = args[0];
		}else{
			parentKey = ConstantUtil.NUM_UNONE;
		}
		List<ListMap2TreeUtils> rootNodeList = arrayListMap.get(parentKey);//ConstantUtil.NUM_UNONE
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//自动为我排除circle。
		JSONArray jsonArray = JSONArray.fromObject(rootNodeList, jsonConfig);
		return jsonArray.toString();
	}
	/**
	 * List转map的方法
	 * column = [id, text, sjid, checked, othermc....]
	 * 创建时间　2014-1-3
	 * 创建人　yangsl
	 * 
	 * @param m2tList
	 * @param column
	 * @return
	 */
	private static Map<String, List<ListMap2TreeUtils>> getArrayListMap(final List<Map<String, Object>> m2tList,
			final String[] column, final String rootName) {
		Map<String, List<ListMap2TreeUtils>> arrayListMap = new LinkedHashMap<String, List<ListMap2TreeUtils>>();
		if(!rootName.equals("")){
			List<ListMap2TreeUtils> rootlist = new ArrayList<ListMap2TreeUtils>();
			ListMap2TreeUtils root = new ListMap2TreeUtils();
			root.setId(ConstantUtil.NUM_ZERO);
			root.setText(rootName);
			root.setState(m2tList.size() > 0 ? "closed" : "open");
			root.setLeaf(true);
			root.setExpanded(true);
			rootlist.add(root);
			arrayListMap.put(ConstantUtil.NUM_UNONE, rootlist);
		}
		for (int i=m2tList.size()-1;i>=0;i--) {
			Map<String, Object> e = m2tList.get(i);
			ListMap2TreeUtils e2t = new ListMap2TreeUtils();
			e2t.setId(BasicListToTree.getString(e, column[0]));
			String text = BasicListToTree.getString(e, column[1]);
			String check = column[3].equals("") ? ConstantUtil.NUM_ZERO : BasicListToTree.getString(e, column[3]);
			e2t.setChecked(check.equals("") || check.equals(ConstantUtil.NUM_ZERO) ? false : true);
			String textTmp = "";
			if(column.length > 4){
				textTmp = BasicListToTree.getOtherText(e, column, 4);
			}
			e2t.setText(text + (textTmp.equals("") ? "" : "[" + textTmp + "]"));
			e2t.setLeaf(true);
			e2t.setExpanded(true);
			String fatherId = BasicListToTree.getString(e, column[2]);
			if (arrayListMap.get(fatherId) == null) {
				List<ListMap2TreeUtils> list = new ArrayList<ListMap2TreeUtils>();
				list.add(e2t);
				arrayListMap.put(fatherId, list);
			} else {
				List<ListMap2TreeUtils> valueList = arrayListMap.get(fatherId);
				valueList.add(e2t);
				arrayListMap.put(fatherId, valueList);
			}
		}
		return arrayListMap;
	}
	private static String getOtherText(Map<String, Object> m, String[] column, int start){
		String textTmp = "";
		for(int il=start;il<column.length;il++){
			String tmp = getString(m, column[il]);
			textTmp += textTmp.equals("") ? tmp : ConstantUtil.SYMB_MH + tmp;
		}
		return textTmp;
	}
	private static String getString(Map<String, Object> m, String key){
		String tmp = String.valueOf(m.get(key));
		return PatternUtil.isNull(tmp);
	}
	
	/**
	 * 带属性的List转JSON树
	 * [id, text, sjid, checked, iconCls, attributes{v1:v2:v3}, othermc...]
	 * 创建时间  2014-2-21 上午10:12:25 
	 * 创建人 yangsl
	 * @param m2tList
	 * @param column
	 * @param rootName
	 * @return
	 *
	 */
	public static String getTreeJsonStringFull(final List<Map<String, Object>> m2tList,
			final String[] column, final String rootName) {
		if(column.length < 6){
			return "";
		}
		
		Map<String, List<ListMap2TreeUtils>> arrayListMap = getArrayListMapFull(m2tList,column,rootName);
		return listToString(arrayListMap);
	}
	/**
	 * List转map的方法
	 * column = [id, mc, parentid]
	 * 创建时间　2014-1-3
	 * 创建人　yangsl
	 * 
	 * @param m2tList
	 * @param column
	 * @return
	 */
	private static Map<String, List<ListMap2TreeUtils>> getArrayListMapFull(final List<Map<String, Object>> m2tList,
			final String[] column, final String rootName) {
		LinkedHashMap<String, List<ListMap2TreeUtils>> arrayListMap = new LinkedHashMap<String, List<ListMap2TreeUtils>>();
		if(!rootName.equals("")){
			List<ListMap2TreeUtils> rootlist = new ArrayList<ListMap2TreeUtils>();
			ListMap2TreeUtils root = new ListMap2TreeUtils();
			root.setId(ConstantUtil.NUM_ZERO);
			root.setText(rootName);
			rootlist.add(root);
			root.setState(m2tList.size() > 0 ? "closed" : "open");
			root.setLeaf(true);
			root.setExpanded(true);
			arrayListMap.put(ConstantUtil.NUM_UNONE, rootlist);
		}
		for (int i=0;i<m2tList.size();i++) {
			Map<String, Object> e = m2tList.get(i);
			ListMap2TreeUtils e2t = new ListMap2TreeUtils();
			e2t.setId(BasicListToTree.getString(e, column[0]));//id
			String text = BasicListToTree.getString(e, column[1]);//text
			String check = column[3].equals("") ? ConstantUtil.NUM_ZERO : BasicListToTree.getString(e, column[3]);//checked
			e2t.setChecked(check.equals("") || check.equals(ConstantUtil.NUM_ZERO) ? false : true);
			//other text
			String textTmp = "";
			if(column.length > 6){
				textTmp = BasicListToTree.getOtherText(e, column, 6);
			}
			e2t.setText(text + (textTmp.equals("") ? "" : "[" + textTmp + "]"));
			e2t.setAttributes(BasicListToTree.getColumnAttributes(e, column[5]));
			e2t.setLeaf(true);
			e2t.setExpanded(true);
			String fatherId = BasicListToTree.getString(e, column[2]);
			if (arrayListMap.get(fatherId) == null) {
				List<ListMap2TreeUtils> list = new ArrayList<ListMap2TreeUtils>();
				list.add(e2t);
				arrayListMap.put(fatherId, list);
			} else {
				List<ListMap2TreeUtils> valueList = arrayListMap.get(fatherId);
				valueList.add(e2t);
				arrayListMap.put(fatherId, valueList);
			}
		}
		return arrayListMap;
	}
	private static Map<String, Object> getColumnAttributes(Map<String, Object> m, String attr){
		Map<String, Object> map = new HashMap<String, Object>();
		if(PatternUtil.isNull(attr).equals("")){
			return map;
		}
		String[] val = attr.split(ConstantUtil.SYMB_MH);
		for(int i=0;i<val.length;i++){
			map.put(val[i], getString(m, val[i]));
		}
		return map;
	}
}
