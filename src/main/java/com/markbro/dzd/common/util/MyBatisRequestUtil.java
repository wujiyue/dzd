package com.markbro.dzd.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* 项目名称：ydxs   
* 类名称：MyBatisRequestUtil   
* 类描述：mybatis请求辅助类
* 创建人：hujianren 
* 创建时间：Dec 26, 2013 6:01:03 PM    
* 修改备注：  
* @version
 */
@SuppressWarnings("unchecked")
public class MyBatisRequestUtil extends RequestUtil  {
	
	private static final String METHODGET = "get"; 
	/**
	 * 转换Map的内容到Bean
	 * 创建时间　2013-2-5
	 * 创建人　yangsl
	 * @param <T>
	 * @param map
	 * @param obj
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	public static <T> T convertToBean(Map<?, ?> map, T obj)
				throws NoSuchMethodException,InstantiationException,
					IllegalAccessException, InvocationTargetException {
		Class<?> classType = obj.getClass();
		Field[] fields = classType.getDeclaredFields();// 得到对象中的字段
		// 每次循环时，重新实例化一个与传过来的对象类型一样的对象
		T oCopy = (T) classType.getConstructor(new Class[] {}).newInstance(
				new Object[] {});
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fName = fields[i].getName();
			Object value = null;
			if (field.getType().equals(String.class)) {
				value = String.valueOf(map.get(fName));
				if (value == null || value.equals("null")) {
					value = "";
				}
			} else if (field.getType().equals(Integer.class)) {
				value = String.valueOf(map.get(fName));
				if(fName.equals(ConstantUtil.ZTDM_KEY) || fName.equals("ztdm")){
					value = PatternUtil.isNull(String.valueOf(value), ConstantUtil.NUM_ONE);
				}else{
					value = PatternUtil.isNull(String.valueOf(value), ConstantUtil.NUM_ZERO);
				}
				value = Integer.valueOf(String.valueOf(value));
			} else if (field.getType().equals(java.util.Date.class)) {
				value = String.valueOf(map.get(fName));
				value = PatternUtil.isNull(String.valueOf(value), ConstantUtil.getCurrentTime());
				value = Timestamp.valueOf(String.valueOf(value));
			} else if (field.getType().equals(BigDecimal.class)) {
				value = String.valueOf(map.get(fName));
				value = PatternUtil.isNull(String.valueOf(value), "0");
				value = BigDecimal.valueOf(Double.parseDouble(String.valueOf(value)));
            } else if (field.getType().equals(Double.class)) {
            	value = String.valueOf(map.get(fName));
                value = Double.parseDouble(String.valueOf(value));
            } else if (field.getType().equals(Float.class)) {
            	value = String.valueOf(map.get(fName));
                value = Float.parseFloat(String.valueOf(value));
			} else {
				value = map.get(fName);
			}
			// 获得属性的首字母并转换为大写，与setXXX对应
			String firstLetter = fName.substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter + fName.substring(1);
			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });
			setMethod.invoke(oCopy, new Object[] { value });// 调用对象的setXXX方法
		}
		return oCopy;
	}
	
	public static <T> Map<String, Object> convertBeanToMap(T bean) 
		throws NoSuchMethodException, InstantiationException, 
			IllegalAccessException, InvocationTargetException {
		Class<?> classBean = bean.getClass();
		Field[] fields = classBean.getDeclaredFields();// 得到对象中的字段
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fName = field.getName();
			Object value = null;
			String firstLetter = fName.substring(0, 1).toUpperCase();
			String getMethodName = METHODGET + firstLetter + fName.substring(1);
			Method getMethod = classBean.getMethod(getMethodName);
			value = getMethod.invoke(bean);
			map.put(fName, value);
		}
		return map;
	}
	/**
	 * bean 对象转Map
	 * 创建时间　2014-1-15
	 * 创建人　yangsl
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanConvert2Map(Object bean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = bean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith(METHODGET)) {
					String field = method.getName();
					field = field.substring(field.indexOf(METHODGET) + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(bean, (Object[]) null);
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
