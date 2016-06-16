package com.markbro.dzd.common.util;




import com.markbro.asoiaf.core.utils.EhCacheUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Map;

public class RequestUtil {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> getMap(HttpServletRequest req) {
		Map mapReq = new LogHashMap();
		Enumeration enu = req.getParameterNames();
		while (enu.hasMoreElements()) {
			String paramName = (String) enu.nextElement();
			String[] values = req.getParameterValues(paramName);
			String value = "";
			String  valueTmp=null;
			for (int i = 0; i < values.length; i++) {
				value = value + (i == 0 ? "" : "~") + values[i];
			}
			mapReq.put(paramName, value);
		}
		String yhid = (String) req.getAttribute("yhid");//当前请求的yhid
		mapReq.put("yhid", req.getAttribute("yhid"));
		if (yhid != null) {
			if (yhid.equals("admin")) {//当前用户是admin用户，把bmid、gwid、zzid设置为0
				mapReq.put("bmid", "0");
				mapReq.put("gwid", "0");
				mapReq.put("zzid", "0");
			} else {//非admin用户
				if (!mapReq.containsKey("bmid")) {//请求中没bmid
					//根据yhid从缓存中取得bmid、zzid
					mapReq.put("bmid", (String) EhCacheUtils.getUserInfo(yhid, "bmid"));
					mapReq.put("zzid", EhCacheUtils.getUserInfo(
							(String) req.getAttribute("yhid"), "zzid"));
					mapReq.put("orgid", EhCacheUtils.getUserInfo(
							(String) req.getAttribute("yhid"), "orgid"));
				} else {
					mapReq.put(
							"zzid",
							EhCacheUtils.getUserInfo((String) req.getAttribute("yhid")
									+ "bm" + mapReq.get("bmid"), "zzid"));
				}
				if (!mapReq.containsKey("gwid")) {
					mapReq.put("gwid", EhCacheUtils.getUserInfo(yhid, "gwid"));
				}
			}
		}
		String dateColumn = req.getParameter("sys_name_datetime");
		if ((dateColumn == null) || ("".equals(dateColumn))) {
			return mapReq;
		}
		String[] column = dateColumn.split(",");
		String value = "";
		for (String col : column) {
			value = String.valueOf(mapReq.get(col));
			if ("".equals(value))
				mapReq.put(col, null);
			else if (value.matches("\\d{4}-\\d{2}-\\d{2}"))
				mapReq.put(col, Date.valueOf(value));
			else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
				mapReq.put(col, Timestamp.valueOf(value));
			}
		}
		return mapReq;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> getMapByMobile(HttpServletRequest req) {
		Map mapReq = new LogHashMap();
		Enumeration enu = req.getParameterNames();
		while (enu.hasMoreElements()) {
			String paramName = (String) enu.nextElement();
			String[] values = req.getParameterValues(paramName);
			String value = "";
			for (int i = 0; i < values.length; i++) {
				value = value + (i == 0 ? "" : "~") + values[i];
			}
			mapReq.put(paramName, value);
		}
		String dateColumn = req.getParameter("sys_name_datetime");
		if ((dateColumn == null) || ("".equals(dateColumn))) {
			return mapReq;
		}
		String[] column = dateColumn.split(",");
		String value = "";
		for (String col : column) {
			value = String.valueOf(mapReq.get(col));
			if ("".equals(value))
				mapReq.put(col, null);
			else if (value.matches("\\d{4}-\\d{2}-\\d{2}"))
				mapReq.put(col, Date.valueOf(value));
			else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
				mapReq.put(col, Timestamp.valueOf(value));
			}
		}
		return mapReq;
	}
}
