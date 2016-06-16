package com.markbro.dzd.base.util;

import java.util.*;


public class JspJsonData {
	private HashMap dataMap = new HashMap();

	private HashMap<String, List> selectMap = new HashMap();

	private HashMap<String, HashMap> gridMap = new HashMap();

	private HashMap<String, Object> formMap = new HashMap();

	private List tableIdList = new ArrayList();

	private String dbtype = "";

	public JspJsonData() {
		this.dataMap.put("result", Boolean.valueOf(true));
		this.dataMap.put("msg", "");
	}

	public HashMap getData() {
		this.dataMap.put("selectData", this.selectMap);
		this.dataMap.put("formData", this.formMap);
		this.dataMap.put("gridData", this.gridMap);
		this.dataMap.put("tableHover", this.tableIdList);
		return this.dataMap;
	}

	public void setSelect(String selectId, List list) {
		try {
			this.selectMap.put(selectId, convertList(list));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*public void setGrid(String tableId, List list, PageBean page) {
		HashMap map = new HashMap();
		try {
			map.put("data", convertList(list));
			HashMap mapPageInfo = null;
			if (page != null) {
				mapPageInfo = new HashMap();
				mapPageInfo
						.put("page_cur", Integer.valueOf(page.getPage_cur()));
				mapPageInfo.put("page_allCount",
						Integer.valueOf(page.getPage_allCount()));
				mapPageInfo.put("page_allPage",
						Integer.valueOf(page.getPage_allPage()));
			}
			map.put("page", mapPageInfo);
			this.gridMap.put(tableId, map);
			this.tableIdList.add(tableId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/

	public List convertList(List _list) {
		List list = new ArrayList();
		Map _map = null;
		for (int i = 0; i < _list.size(); i++) {
			Map map = (Map) _list.get(i);
			Iterator it = map.entrySet().iterator();
			_map = new HashMap();
			while (it.hasNext()) {
				Map.Entry m = (Map.Entry) it.next();
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

	public Map convertMap(Map _map) {
		Map map1 = new HashMap();
		try {
			Iterator it = _map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry m = (Map.Entry) it.next();
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

	public void setForm(Map map) {
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
