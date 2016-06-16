package com.markbro.dzd.common.util;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* 项目名称：ydxs   
* 类名称：PatternUtil   
* 类描述：模式匹配类
* 创建人：hujianren 
* 创建时间：Dec 11, 2013 10:44:02 AM    
* 修改备注：  
* @version
 */
public class PatternUtil {

	/**
	 * 验证字符串是否是email
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证是否是手机号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCellphone(String str) {
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证是否是QQ号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isQQ(String str) {
		Pattern pattern = Pattern.compile("[1-9]{5,10}");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是浮点数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains(".")) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断是否是json结构
	 */
	public static boolean isJson(String value) {
		try {
			new JSONObject(value);
		} catch (JSONException e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是xml结构
	 */
	public static boolean isXML(String value) {
		try {
			DocumentHelper.parseText(value);
		} catch (DocumentException e) {
			return false;
		}
		return true;
	}
	/**
	 * 字符串是否为空:返回字符串
	 * 创建时间　2013-5-16
	 * 创建人　yangsl
	 * 
	 * @param value
	 * @return
	 */
	public static String isNull(String value){
		if(value == null || value.isEmpty() || value.equals("[]") || value.equalsIgnoreCase("null")){
			return "";
		}else{
			return value;
		}
	}
	/**
	 * 为空则返回新值
	 * 创建时间　2014-1-9
	 * 创建人　yangsl
	 * 
	 * @param value
	 * @param nVal
	 * @return
	 */
	public static String isNull(String value, String nVal){
		if(value == null || value.isEmpty() || value.equalsIgnoreCase("null")){
			return isNull(nVal);
		}else{
			return value;
		}
	}
	/**
	 * 当前日期
	 * 创建时间　2013-5-16
	 * 创建人　yangsl
	 * 
	 * @param interval
	 * @return
	 */
	public static String getDate(int interval){
		Calendar c=Calendar.getInstance();//今天的时间
		c.add(Calendar.MONTH, interval);//今天的时间月份-1支持1月的上月
		java.util.Date date=c.getTime();
		SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd");
		String formateDate = df.format(date);
		return formateDate;
	}
}
