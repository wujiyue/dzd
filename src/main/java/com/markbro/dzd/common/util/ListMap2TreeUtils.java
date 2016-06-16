package com.markbro.dzd.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.*;

/**
 * 使用说明：将List<Map>转换为Tree<List<Map>>
 * @author mywhile
 *
 */
public class ListMap2TreeUtils {

	private String id;		//主键
	private String text;	//名称
	private String iconCls;		//图标
	private boolean leaf;	//是否叶子
	private boolean expanded;	//是否展开
	private int sort;		//序号
	private boolean checked;	//是否选中
	private String state = "";
	private Map<String, Object> attributes;
	private List<ListMap2TreeUtils> children;	//子节点

	public ListMap2TreeUtils() {
		super();
	}
	/*
	public ListMap2TreeUtils(String id, String text, String iconCls, boolean leaf,
			boolean expanded, Map<String, Object> attributes, List<ListMap2TreeUtils> children) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.leaf = leaf;
		this.expanded = expanded;
		this.children = children;
		this.attributes = attributes;
	}
	*/
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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<ListMap2TreeUtils> getChildren() {
		return children;
	}

	public void setChildren(List<ListMap2TreeUtils> children) {
		this.children = children;
	}
	
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
			final String[] column, final String rootName, final String parentid) {
		if(column.length < 6){
			return "";
		}
		
		Map<String, List<ListMap2TreeUtils>> arrayListMap = getArrayListMapFull(m2tList,column,rootName);
		return listToString(arrayListMap, parentid);
	}
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
			root.setState("closed");
			rootlist.add(root);
			arrayListMap.put(ConstantUtil.NUM_UNONE, rootlist);
		}
		for (int i=m2tList.size()-1;i>=0;i--) {
			Map<String, Object> e = m2tList.get(i);
			ListMap2TreeUtils e2t = new ListMap2TreeUtils();
			e2t.setId(e2t.getString(e, column[0]));
			String text = e2t.getString(e, column[1]);
			String check = column[3].equals("") ? ConstantUtil.NUM_ZERO : e2t.getString(e, column[3]);
			e2t.setChecked(check.equals("") || check.equals(ConstantUtil.NUM_ZERO) ? false : true);
			String textTmp = "";
			if(column.length > 4){
				textTmp = e2t.getOtherText(e, column, 4);
			}
			e2t.setText(text + (textTmp.equals("") ? "" : "[" + textTmp + "]"));
			String fatherId = e2t.getString(e, column[2]);
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
		String parentId = ConstantUtil.NUM_ZERO;
		boolean r0 = false;
		if(m2tList != null && m2tList.size() > 0){
			Map<String, Object> ePid = m2tList.get(0);
			String fatherId = PatternUtil.isNull(String.valueOf(ePid.get(column[2])));
			if(fatherId.equals(ConstantUtil.NUM_ZERO)){
				r0 = true;
			}else{
				parentId = fatherId;
			}
		}
		if(!rootName.equals("")){
			List<ListMap2TreeUtils> rootlist = new ArrayList<ListMap2TreeUtils>();
			ListMap2TreeUtils root = new ListMap2TreeUtils();
			if(r0){
				root.setId(ConstantUtil.NUM_ZERO);
			}else{
				root.setId(parentId);
			}
			root.setText(rootName);
			rootlist.add(root);
			arrayListMap.put(ConstantUtil.NUM_UNONE, rootlist);
		}
		for (int i=0;i<m2tList.size();i++) {
			Map<String, Object> e = m2tList.get(i);
			ListMap2TreeUtils e2t = new ListMap2TreeUtils();
			e2t.setId(e2t.getString(e, column[0]));//id
			String text = e2t.getString(e, column[1]);//text
			String check = column[3].equals("") ? ConstantUtil.NUM_ZERO : e2t.getString(e, column[3]);//checked
			e2t.setChecked(check.equals("") || check.equals(ConstantUtil.NUM_ZERO) ? false : true);
			//other text
			String textTmp = "";
			if(column.length > 6){
				textTmp = e2t.getOtherText(e, column, 6);
			}
			e2t.setText(text + (textTmp.equals("") ? "" : "[" + textTmp + "]"));
			e2t.setAttributes(e2t.getColumnAttributes(e, column[5]));
			String lxdm = e2t.getString(e, "lx_dm");
			if(rootName.equals("")){
				if(lxdm.equals("204")){
					e2t.setIconCls("icon-user");
					e2t.setState("");
				}else{
					e2t.setState("closed");
				}
				if(lxdm.equals("202")){
					e2t.setIconCls("icon-group");
					String childcount = e2t.getString(e, "lx_dm");
					if(childcount!=null&&Integer.parseInt(childcount)==0){
						e2t.setState("");
					}
				}
			}
			String fatherId = e2t.getString(e, column[2]);
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
	/**
	 * 附加显示的信息
	 * 
	 * 创建时间  2014-2-21 上午10:22:29 
	 * 创建人 yangsl
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
