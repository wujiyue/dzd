package com.markbro.dzd.common.util;

import java.util.*;
import java.util.Map.Entry;


public class JspJsonData {
	private HashMap<String, Object> dataMap = new HashMap<String, Object>();

	private HashMap<String, List<Map<String, Object>>> selectMap = new HashMap<String, List<Map<String, Object>>>();

	private HashMap<String, HashMap<String, Object>> gridMap = new HashMap<String, HashMap<String, Object>>();

	private HashMap<String, Object> formMap = new HashMap<String, Object>();

	private List<String> tableIdList = new ArrayList<String>();

	//private String dbtype = "";

	public JspJsonData() {
		this.dataMap.put("result", Boolean.valueOf(true));
		this.dataMap.put("msg", "");
	}

	public HashMap<String, Object> getData() {
		this.dataMap.put("selectData", this.selectMap);
		this.dataMap.put("formData", this.formMap);
		this.dataMap.put("gridData", this.gridMap);
		this.dataMap.put("tableHover", this.tableIdList);
		return this.dataMap;
	}

	public void setSelect(String selectId, List<Map<String, Object>> list) {
		try {
			this.selectMap.put(selectId, convertList(list));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setGrid(String tableId, List<Map<String, Object>> list, PageBean page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("data", convertList(list));
			HashMap<String, Object> mapPageInfo = null;
			if (page != null) {
				mapPageInfo = new HashMap<String, Object>();
				mapPageInfo.put("page_cur", Integer.valueOf(page.getPage_cur()));
				mapPageInfo.put("page_allCount",Integer.valueOf(page.getPage_allCount()));
				mapPageInfo.put("page_allPage", Integer.valueOf(page.getPage_allPage()));
			}
			map.put("page", mapPageInfo);
			this.gridMap.put(tableId, map);
			this.tableIdList.add(tableId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> convertList(List<Map<String, Object>> _list) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> _map = null;
		for (int i = 0; i < _list.size(); i++) {
			Map<String, Object> map =  _list.get(i);
			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			_map = new HashMap<String, Object>();
			while (it.hasNext()) {
				Entry m = (Entry) it.next();
				if ((m.getValue() != null)
						&& ((m.getValue() instanceof String)))
					_map.put(m.getKey().toString().toLowerCase(),
							((String) m.getValue()).replace("\\", "\\\\"));
				else
					_map.put(m.getKey().toString().toLowerCase(), m.getValue());
			}
			list.add(_map);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Object> convertMap(Map<String, Object> _map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		try {
			Iterator<Entry<String, Object>> it = _map.entrySet().iterator();
			while (it.hasNext()) {
				Entry m = (Entry) it.next();
				if ((m.getValue() != null)
						&& ((m.getValue() instanceof String)))
					map1.put(m.getKey().toString().toLowerCase(),
							((String) m.getValue()).replace("\\", "\\\\"));
				else
					map1.put(m.getKey().toString().toLowerCase(), m.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map1;
	}

	public void setForm(Map<String, Object> map) {
		try {
			this.formMap.putAll(convertMap(map));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setResult(boolean result, String msg) {
		this.dataMap.put("result", Boolean.valueOf(result));
		this.dataMap.put("msg", msg);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setExtend(String extId, Object data) {
		if ((data instanceof List))
			try {
				this.dataMap.put(extId, convertList((List) data));
			} catch (Exception localException) {
			}
		else
			this.dataMap.put(extId, data);
	}
}
