package com.markbro.dzd.common.util;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 
 * @ClassName: DatagridBean 
 * @Description: 表格主参数
 * @author mywhile 
 * @date 2015-9-6
 *  
 */
public class DatagridBean {
	/** 
	 * @Fields pagination : 显示分页		:defalut value true
	 */
	private boolean pagination = true;
	/** 
	 * @Fields rownumbers : 是否显示行号	:default value false
	 */
	private boolean rownumbers = false;
	/** 
	 * @Fields singleSelect : 是否可以只选一行	:default value false
	 */
	private boolean singleSelect = false;
	/** 
	 * @Fields width : 宽			:default value auto
	 */
	private String width = "auto";
	/** 
	 * @Fields height : 高			:default value auto
	 */
	private String height = "auto";
	/** 
	 * @Fields idField : 主键的字段	:default value guid
	 */
	private String idField = "guid";
	/** 
	 * @Fields edit : 是否可编辑	:default value false	{true, false}	
	 */
	private boolean edit = false;
	/** 
	 * @Fields initType : 数据展示方式，使用div或table :default value table; 0 or 1
	 */
	private int initType = 1;
	/** 
	 * @Fields lineFunc : 行事件
	 */
	private String lineFunc = "";
	/** 
	 * @Fields pagerows : 默认每页显示的行数
	 */
	private int pagerows = 15;
	/** 
	 * @Fields field : 要显示的数据列项
	 */
	private DatagridField[] field = new DatagridField[0];
	
	/** 
	 * @Fields rows : 要显示的数据集
	 */
	private List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	private Map<String, Object> sumrows = new HashMap<String, Object>();
	private List<String> rowsjson = new ArrayList<String>();
	private String[] sumField;
	
	private double[] sumValue;
	/** 
	 * @Fields datarows : 数据的总行数
	 */
	private int countrow = 0;
	
	private int pageAmount = 0;

	private int currentPage = 1;

	private int interval = 2;
	private int tfootcolspan = 1;
	/**
	 * @return the pagination
	 */
	public boolean isPagination() {
		return pagination;
	}
	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	/**
	 * @return the rownumbers
	 */
	public boolean isRownumbers() {
		return rownumbers;
	}
	/**
	 * @param rownumbers the rownumbers to set
	 */
	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}
	/**
	 * @return the singleSelect
	 */
	public boolean isSingleSelect() {
		return singleSelect;
	}
	/**
	 * @param singleSelect the singleSelect to set
	 */
	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}
	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}
	/**
	 * @param  width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param  height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * @return the idField
	 */
	public String getIdField() {
		return idField;
	}
	/**
	 * @param  idField to set
	 */
	public void setIdField(String idField) {
		this.idField = idField;
	}
	/**
	 * @return the edit
	 */
	public boolean isEdit() {
		return edit;
	}
	/**
	 * @param  edit to set
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	/**
	 * @return the initType
	 */
	public int getInitType() {
		return initType;
	}
	/**
	 * @param  initType to set
	 */
	public void setInitType(int initType) {
		this.initType = initType;
	}
	/**
	 * @return the lineFunc
	 */
	public String getLineFunc() {
		return lineFunc;
	}
	/**
	 * @param  lineFunc to set
	 */
	public void setLineFunc(String lineFunc) {
		this.lineFunc = lineFunc;
	}
	/**
	 * @return the field
	 */
	public DatagridField[] getField() {
		return field;
	}
	/**
	 * @param  field to set
	 */
	public void setField(DatagridField[] field) {
		this.field = field;
		this.tfootcolspan = field.length + (this.rownumbers ? 1 : 0);
	}
	/**
	 * @return the pagerows
	 */
	public int getPagerows() {
		return pagerows;
	}
	/**
	 * @param  pagerows to set
	 */
	public void setPagerows(int pagerows) {
		this.pagerows = pagerows;
	}
	/**
	 * @return the data
	 */
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	/**
	 * @param
	 */
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
		if(rows != null && rows.size() > 0){
			this.rowsjson = new ArrayList<String>();
			for(int i=0;i<rows.size();i++){
				Map<String, Object> omap = rows.get(i);
				JSONArray json = JSONArray.fromObject(omap);
				this.rowsjson.add(i, json.toString());
			}
		}
	}
	/**
	 * @return the countrow
	 */
	public int getCountrow() {
		return countrow;
	}
	/**
	 * @param countrow the countrow to set
	 */
	public void setCountrow(int countrow) {
		this.countrow = countrow;
		this.pageAmount = this.countrow / this.pagerows + (this.countrow % this.pagerows > 0 ? 1 : 0);
	}
	/**
	 * @return the pageAmount
	 */
	public int getPageAmount() {
		return pageAmount;
	}
	/**
	 * @param pageAmount the pageAmount to set
	 */
	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
	/**
	 * @return the tfootcolspan
	 */
	public int getTfootcolspan() {
		return tfootcolspan;
	}
	/**
	 * @param tfootcolspan the tfootcolspan to set
	 */
	public void setTfootcolspan(int tfootcolspan) {
		this.tfootcolspan = tfootcolspan;
	}
	/**
	 * @return the rowsjson
	 */
	public List<String> getRowsjson() {
		return rowsjson;
	}
	/**
	 * @param rowsjson the rowsjson to set
	 */
	public void setRowsjson(List<String> rowsjson) {
		this.rowsjson = rowsjson;
	}
	/**
	 * @return the sumField
	 */
	public String[] getSumField() {
		return sumField;
	}
	/**
	 * @param sumField the sumField to set
	 */
	public void setSumField(String[] sumField) {
		this.sumField = sumField;
		this.sumValue = new double[sumField.length];
	}
	/**
	 * @return the sumValue
	 */
	public double[] getSumValue() {
		return sumValue;
	}
	/**
	 * @param sumValue the sumValue to set
	 */
	public void setSumValue(double[] sumValue) {
		this.sumValue = sumValue;
	}
	/**
	 * @return the sumrows
	 */
	public Map<String, Object> getSumrows() {
		return sumrows;
	}
	/**
	 * @param sumrows the sumrows to set
	 */
	public void setSumrows(Map<String, Object> sumrows) {
		this.sumrows = sumrows;
	}
	
	
}
