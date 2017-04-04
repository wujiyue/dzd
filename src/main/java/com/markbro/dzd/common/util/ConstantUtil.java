package com.markbro.dzd.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常量与常用方法
 * @author yangsl
 *
 */
public class ConstantUtil {
	public static final String PIC_ADRR="/UnigoService/unigo/mobile/file?id=";

	public static final String XML_CONTENTTYPE = "text/xml;charset=UTF-8";
	public static final String XML_APP_CONTYPE = "application/xml;charset=UTF-8";
	
	public static final String HTML_CONTENTTYPE = "text/html;charset=UTF-8";
	
	public static final String JSON_CONTENTTYPE = "text/json;charset=UTF-8";
	public static final String JSON_APP_CONTYPE = "text/javascript;charset=UTF-8";
	
	public static final String CHARCODE = "UTF-8";
	
	public static final String POST_JSON_VIEW = "jsonView";
	
	
	public static final String ID_KEY = "id";
	public static final String GUID_KEY = "guid";
	public static final String ZZID_KEY = "zzid";
	public static final String YHID_KEY = "yhid";
	public static final String BMID_KEY = "bmid";
	public static final String ORG_ZZID_KEY = "org_zzid";
	public static final String ORG_BMID_KEY = "org_bmid";
	public static final String ORG_GWID_KEY = "org_gwid";
	public static final String ORG_YHID_KEY = "org_yhid";
	public static final String ORG_SJID_KEY = "org_sjid";
	
	public static final String BM_KEY = "bm";
	public static final String GW_KEY = "gw";
	public static final String YH_KEY = "yh";
	public static final String GWID_KEY = "gwid";
	public static final String ZTDM_KEY = "zt_dm";
	public static final String CJSJ_KEY = "cjsj";
	public static final String NULL_KEY = "null";
	public static final String XM_KEY = "xm";
	public static final String DM_KEY = "dm";
	public static final String MC_KEY = "mc";
	public static final String IP_KEY = "ip";
	public static final String XH_KEY = "xh";
	public static final String LXID_KEY = "lxid";
	public static final String EXTENDSVALUE ="kzz";
	public static final String KZ_KEY = "kz";
	public static final String KZDM_KEY = "kz_dm";
	
	public static final String OTHER_LX_DM = "200";
	public static final String DEPT_LX_DM = "201";
	public static final String POSITION_LX_DM = "201";
	public static final String USER_LX_DM = "204";
	
	public static final String USER_DM_303 = "303";
	public static final String USER_DM_302 = "302";
	
	public static final String PAGE_GOTO_KEY = "page_goto";
	public static final String PAGE_COUNT_KEY = "page_count";
	public static final String PAGE_PAGE_KEY = "page";
	public static final String PAGE_ROWS_KEY = "rows";
	public static final String PAGE_SHOW_TOTAL = "total";
	public static final String PAGE_SHOW_ROWS = "10";
	
	public static final String TABLE_LIST_KEY = "table_list";
	public static final String TABLE_LIST_KEY2 = "table_list2";
	
	public static final String NUM_ZERO = "0";
	public static final String NUM_ONE = "1";
	public static final String NUM_TWO = "2";
	public static final String NUM_UNONE = "-1";
	public static final String NUM_TEN = "10";
	
	public static final boolean TRUE_FLAG = true; 
	public static final String TRUE_FLAG_STR = "true"; 
	public static final boolean FALSE_FLAG = false;
	public static final String FALSE_FLAG_STR = "false"; 
	
	public static final String SYMB_KGE = "";
	public static final String SYMB_DYH = "'";
	public static final String SYMB_DOUH = ",";
	public static final String SYMB_DYH_LDOU = ",'";
	public static final String SYMB_DYH_RDOU = "',";
	public static final String SYMB_DYH_DDOU = "','";
	public static final String SYMB_DYH_YKHYH="') ";
	public static final String SYMB_DYH_FHAO = ";";
	public static final String SYMB_LINE = "_";
	public static final String SYMB_BL = "~";
	public static final String SYMB_MH = ":";
	public static final String SYMB_SHUXIAN = "|";
	public static final String SYMB_FENHAO = ";";
	public static final String SYMB_XIEXIAN = "/";
	public static final String SYMB_SHUXIAN_ZHANYI = "\\|";
	public static final String SYMB_BAIFENHAO = "%";
	
	public static final String MSG_SAVE = "保存成功";
	public static final String MSG_ADD = "添加数据";
	public static final String MSG_DEL = "删除数据";
	public static final String MSG_UPD = "修改数据";
	public static final String MSG_QUR = "查询数据";
	public static final String[] MSG_TRUE_FALSE = {"成功","失败"};
	public static final String MSG_ERROR = "操作出现异常：";
	public static final String MSG_METHOD = "method:";
	
	
	public static final String CON_ADMIN = "admin";
	public static final String CON_ADMIN_MC = "系统管理员";
	
	public static final String CODE_UNKNOWN = "unknown";
	public static final String CODE_WARNING = "warning";
	public static final String CODE_FAILING = "failing";
	public static final String CODE_SUCCESS = "success";
	public static final String CODE_ERROR = "error";
	public static final String CODE_RESULT = "result";
	
	
	public static final String CACHE_YH_USERBEAN = "userbean";
	public static final String CACHE_YH_JS_LIST = "jslist";
	public static final String CACHE_YH_BM_LIST = "bmlist";
	public static final String CACHE_YH_GW_LIST = "gwlist";
	public static final String CACHE_YH_ORG = "org";
	public static final String CACHE_YH_GW_SEL = "gw_sel";
	public static final String CACHE_YH_URL = "url";
	public static final String CACHE_YH_METHOD = "method";
	public static final String CACHE_YH_SYS_BTN_AUTH = "sys_btn_auth";
	public static final String CACHE_YH_DLMC = "dlmc";
	public static final String CACHE_YH_ISMANAGER = "isManager";
	
	public static final String CACHE_GLOBAL_METHOD = "method";
	public static final String CACHE_GLOBAL_URL = "url";
	public static final String CACHE_GLOBAL_ISGROUP = "isgroup";
	public static final String CACHE_GLOBAL_ITOKEN = "invalid_token";
	public static final String CACHE_LOGIN_TIME = "login_time";
	public static final String CACHE_ACTIVE_TIME = "active_time";
	public static final String CACHE_TOKEN = "token";
	
	public static final String MD5_DEFAULT_PASS = "e10adc3949ba59abbe56e057f20f883e";
	/**
	 * 当前日期
	 * 创建时间　2013-12-25
	 * 创建人　yangsl
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curTime = format.format(date);
		return curTime;
	}
	
	/**
	 * 登录的IP地址
	 * 创建时间　2014-2-8
	 * 创建人　yangsl
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request) {
		String str = request.getHeader("x-forwarded-for");
		if ((str == null) || (str.length() == 0) || (ConstantUtil.CODE_UNKNOWN.equalsIgnoreCase(str))){
			str = request.getHeader("Proxy-Client-IP");
		}
		if ((str == null) || (str.length() == 0) || (ConstantUtil.CODE_UNKNOWN.equalsIgnoreCase(str))){
			str = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((str == null) || (str.length() == 0) || (ConstantUtil.CODE_UNKNOWN.equalsIgnoreCase(str))){
			str = request.getRemoteAddr();
		}
		return str;
	}
	/**
	 * 取Cookie中的对应值
	 * 创建时间　2014-2-8
	 * 创建人　yangsl
	 * 
	 * @param request
	 * @param parmStr
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String parmStr) {
		Cookie[] arrayOfCookie = request.getCookies();
		String str = "";
		if (arrayOfCookie != null){
			for (int i = 0; i < arrayOfCookie.length; i++) {
				if (!arrayOfCookie[i].getName().equals(parmStr)){
					continue;
				}
				str = arrayOfCookie[i].getValue();
				break;
			}
		}
		return str;
	}
	/**
	 * 在request中取字符流
	 * 
	 * 创建时间  2014-3-27 下午2:35:05 
	 * 创建人 yangsl
	 * @param request
	 * @return
	 * @throws java.io.IOException
	 *
	 */
	public static String getRequestStream(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding(ConstantUtil.CHARCODE);
		InputStream iStream = request.getInputStream();
		String inStr = XmlUtil.readInputStreamToStr(iStream);
		return inStr;
	}

}
