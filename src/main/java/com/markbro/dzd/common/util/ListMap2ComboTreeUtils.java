package com.markbro.dzd.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用说明：将List<Map>转换为Tree<List<Map>>
 * @author haop
 *
 */
public class ListMap2ComboTreeUtils {

	private String id;		//主键
	private String text;	//名称
	private String state = "";
	private List<ListMap2ComboTreeUtils> children;	//子节点

	public ListMap2ComboTreeUtils() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<ListMap2ComboTreeUtils> getChildren() {
		return children;
	}

	public void setChildren(List<ListMap2ComboTreeUtils> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * List转树的方法
	 * column = [id, mc, parentid]
	 * 创建时间　2014-3-22
	 * 创建人　haop
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
		Map<String, List<ListMap2ComboTreeUtils>> arrayListMap = getArrayListMap(m2tList,column,rootName);
		return listToString(arrayListMap);
	}
	
	/*
	 * List转ComboTree
	 */
	public static String getComboTreeTreeJsonString(final List<Map<String, Object>> m2tList, 
			final String[] column, final String rootName) {
		if(column.length < 4){
			return "";
		}
		Map<String, List<ListMap2ComboTreeUtils>> arrayListMap = getArrayListMap(m2tList,column,rootName);
		return listToString(arrayListMap);
	}
	/**
	 * 生成层次形式的JSON
	 * 
	 * 创建时间  2014-3-22
	 * 创建人 haop
	 * @param arrayListMap
	 * @return
	 *
	 */
	private static String listToString(Map<String, List<ListMap2ComboTreeUtils>> arrayListMap, String... args){
		for (Map.Entry<String, List<ListMap2ComboTreeUtils>> entry : arrayListMap.entrySet()) {
			List<ListMap2ComboTreeUtils> smallTreeList = new ArrayList<ListMap2ComboTreeUtils>();
			smallTreeList = entry.getValue();
			int nodeListSize = smallTreeList.size();
			for (int i = 0; i < nodeListSize; i++) {
				String findID = smallTreeList.get(i).getId();
				List<ListMap2ComboTreeUtils> findList = arrayListMap.get(findID);
				smallTreeList.get(i).setChildren(findList);
			}
		}
		String parentKey = "";
		if(args != null && args.length > 0 && !args[0].equals("")){
			parentKey = args[0];
		}else{
			parentKey = ConstantUtil.NUM_UNONE;
		}
		List<ListMap2ComboTreeUtils> rootNodeList = arrayListMap.get(parentKey);//ConstantUtil.NUM_UNONE
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//自动为我排除circle。
		JSONArray jsonArray = JSONArray.fromObject(rootNodeList, jsonConfig);
		return jsonArray.toString();
	}
	/**
	 * List转map的方法
	 * column = [id, text, sjid, checked, othermc....]
	 * 创建时间　2014-3-22
	 * 创建人　haop
	 * 
	 * @param m2tList
	 * @param column
	 * @return
	 */
	private static Map<String, List<ListMap2ComboTreeUtils>> getArrayListMap(final List<Map<String, Object>> m2tList, 
			final String[] column, final String rootName) {
		Map<String, List<ListMap2ComboTreeUtils>> arrayListMap = new LinkedHashMap<String, List<ListMap2ComboTreeUtils>>();
		if(!rootName.equals("")){
			List<ListMap2ComboTreeUtils> rootlist = new ArrayList<ListMap2ComboTreeUtils>();
			ListMap2ComboTreeUtils root = new ListMap2ComboTreeUtils();
			root.setId(ConstantUtil.NUM_ZERO);
			root.setText(rootName);
			root.setState("closed");
			rootlist.add(root);
			arrayListMap.put(ConstantUtil.NUM_UNONE, rootlist);
		}
		for (int i=m2tList.size()-1;i>=0;i--) {
			Map<String, Object> e = m2tList.get(i);
			ListMap2ComboTreeUtils e2t = new ListMap2ComboTreeUtils();
			e2t.setId(e2t.getString(e, column[0]));
			String text = e2t.getString(e, column[1]);
			//String check = column[3].equals("") ? ConstantUtil.NUM_ZERO : e2t.getString(e, column[3]);
			//e2t.setChecked(check.equals("") || check.equals(ConstantUtil.NUM_ZERO) ? false : true);
			String textTmp = "";
			if(column.length > 4){
				textTmp = e2t.getOtherText(e, column, 4);
			}
			e2t.setText(text + (textTmp.equals("") ? "" : "[" + textTmp + "]"));
			String fatherId = e2t.getString(e, column[2]);
			if (arrayListMap.get(fatherId) == null) {
				List<ListMap2ComboTreeUtils> list = new ArrayList<ListMap2ComboTreeUtils>();
				list.add(e2t);
				arrayListMap.put(fatherId, list);
			} else {
				List<ListMap2ComboTreeUtils> valueList = arrayListMap.get(fatherId);
				valueList.add(e2t);
				arrayListMap.put(fatherId, valueList);
			}
		}
		return arrayListMap;
	}
	
/*
	private Map<String, Object> getColumnAttributes(Map<String, Object> m, String attr){
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
*/
	/**
	 * 附加显示的信息
	 * 
	 * 创建时间  2014-3-22
	 * 创建人 haop
	 * @param m
	 * @param column
	 * @param start
	 * @return
	 *
	 */
	private String getOtherText(Map<String, Object> m, String[] column, int start){
		String textTmp = "";
		for(int il=start;il<column.length;il++){
			String tmp = getString(m, column[il]);
			textTmp += textTmp.equals("") ? tmp : ConstantUtil.SYMB_MH + tmp;
		}
		return textTmp;
	}
	private String getString(Map<String, Object> m, String key){
		String tmp = String.valueOf(m.get(key));
		return PatternUtil.isNull(tmp);
	}
}
