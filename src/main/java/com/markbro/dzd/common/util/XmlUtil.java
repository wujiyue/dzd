package com.markbro.dzd.common.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Xml处理工具类
 * @author mywhile
 *
 */
@SuppressWarnings({"rawtypes", "unchecked", "deprecation" })
public class XmlUtil {
	/**
	 * 取得一个节点的值
	 * 创建时间　2013-3-20
	 * 创建人　yangsl
	 * 
	 * @param xml
	 * @param name
	 * @return
	 */
	public static String queryXmlElementValue(String xml, String name){
		String res = "";
		try {
            Document doc = DocumentHelper.parseText(xml);
			Element element = doc.getRootElement();
			Iterator itElem = element.elementIterator();
			while(itElem.hasNext()){
				Element sonElem = (Element) itElem.next();
				if (sonElem.getName().equals(name)) {
					res = sonElem.getText();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return res;
	}
	/**
	 * XML字符串转JSON字符串，
	 * 创建时间　2013-3-20
	 * 创建人　yangsl
	 * 
	 * @param xml
	 * @return
	 */
	public static String xml2JsonString(String xml){
		XMLSerializer xmls = new XMLSerializer();
		JSON json = xmls.read(xml.toString());
		return json.toString();
	}
	/**
	 * XML字符串转List<Map>
	 * 创建时间　2013-3-20
	 * 创建人　yangsl
	 * 
	 * @param xml
	 * @return
	 */
	public static List<Map> xml2List(String xml){
		XMLSerializer xmls = new XMLSerializer();
		JSON json = xmls.read(xml.toString());
		List<Map> list = JSONArray.toList(JSONArray.fromObject(json), Map.class);
		return list;
	}
	/**
	 * JSON字符串转List<Map>
	 * 创建时间　2013-3-21
	 * 创建人　yangsl
	 * 
	 * @param str
	 * @return
	 */
	public static List<Map> json2List(String str){
		JSON json = JSONSerializer.toJSON(str);
		List<Map> list = JSONArray.toList(JSONArray.fromObject(json), Map.class);
		return list;
	}
	/**
	 * 读取InputStream中的字符串
	 * 创建时间　2013-3-20
	 * 创建人　yangsl
	 * 
	 * @param iStream
	 * @return
	 */
	public static String readInputStreamToStr(InputStream iStream) {
		if (iStream != null) {
			StringBuffer stringBuffer = new StringBuffer();
			String line = "";
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(iStream, "utf-8"));
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line).append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					iStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return stringBuffer.toString();
		}
		return "";
	}
	/**
	 * 根据流内容返回字符串：Android接口使用
	 * 创建时间　2013-12-26
	 * 创建人　yangsl
	 * 
	 * @param iStream
	 * @return
	 */
	public static String readInputStreamToStrForAndroid(InputStream iStream) {
		if (iStream != null) {
			StringBuffer stringBuffer = new StringBuffer();
			String line = "";
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(iStream, "utf-8"));
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line).append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return stringBuffer.toString();
		}
		return "";
	}
}
