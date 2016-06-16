/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.markbro.dzd.base.util;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.markbro.asoiaf.core.utils.EhCacheUtils;
import com.markbro.asoiaf.core.utils.SpringContextHolder;
import com.markbro.dzd.base.dictionary.bean.Dictionary;
import com.markbro.dzd.base.dictionary.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 */
public class DictUtils {
	
	private static DictionaryService dictService = SpringContextHolder.getBean(DictionaryService.class);

	public static final String CACHE_DICT_MAP = "dictMap";

	/**
	 * 根据值和字典类型获得前台显示内容（Label）
	 * @param value
	 * @param type
	 * @return
	 */
	public static String getDictLabel(String value, String type){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			List<Dictionary> temp=getDictListByType(type);
			for (Dictionary dict : temp){
				if (value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "空";
	}
	/**
	 * 根据一组值和字典类型获得前台显示内容（Label）
	 * @param values
	 * @param type
	 * @return
	 */
	public static String getDictLabels(String values, String type){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();

			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type));
			}
			return StringUtils.join(valueList, ",");
		}
		return "";
	}

	/**
	 * 根据label(前台显示内容)以及字典类型获得值
	 * @param label
	 * @param type
	 * @return
	 */
	public static String getDictValue(String label, String type){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dictionary dict : getDictListByType(type)){
				if (label.equals(dict.getDescription())){
					return dict.getValue();
				}
			}
		}
		return "";
	}

	
	public static List<Dictionary> getDictListByType(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dictionary>> dictMap = (Map<String, List<Dictionary>>)EhCacheUtils.getSysInfo(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			List<Dictionary> all=dictService.find(new PageBounds(),null);
			for (Dictionary dict : all){
				List<Dictionary> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			EhCacheUtils.putSysInfo(CACHE_DICT_MAP, dictMap);
		}
		List<Dictionary> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
}
