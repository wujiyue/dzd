package com.markbro.dzd.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 行级参数
 * @author yangsl
 *
 */
public class DatagridField {
	/** 
	 * @Fields field : 要显示的字段
	 */
	private String field;
	/** 
	 * @Fields title : 标题
	 */
	private String title;
	/** 
	 * @Fields checkbox : 是否复选框	:default value false;	true, false
	 */
	private boolean checkbox = false;
	/** 
	 * @Fields width : 列宽度	:default value 50
	 */
	private int width = 50;
	/** 
	 * @Fields widthcss : 该属性自动生成；用于指定列宽
	 */
	private String widthcss = "width:50px;";
	//private String formatter;	//格式化字符串
	/** 
	 * @Fields align : 文字位置；左0、中1、右2 	:default value 0
	 */
	private int align = 0;
	/** 
	 * @Fields aligncss : 位置,自动生成
	 */
	private String aligncss = "text-align:left";
	/** 
	 * @Fields ALIGNVALUE : 常量
	 */
	private final static String[] ALIGNVALUE = {"left", "ceter", "right"};
	/** 
	 * @Fields edit : 表示该列是否要编辑	:default value false
	 */
	private boolean isedit = false;
	/** 
	 * @Fields edittype : 编辑框类型	:default value 0 ;{0:text,1:password,2:select,3:radio,4:checkbox}
	 */
	private int edittype = 0;	//
	/** 
	 * @Fields editFunc : 编辑事件,用于当用户点击编辑框或编辑框值变的情况处理
	 */
	private String editFunc;
	/** 
	 * @Fields options : 编辑框使用，下拉列表、复选、单选框的值
	 */
	private List<Map<String, Object>> options = new ArrayList<Map<String, Object>>();
	/**
	 * @Fields linkFunc : 点击事件
	 */
	private String linkFunc;
	private String[] linkFuncArgs;//事件参数
	private String linkStyle;
	
	private double sumvalue;
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p>  
	 */
	public DatagridField() {
		
	}
	public DatagridField(String field, String title, boolean checkbox){
		this.field = field;
		this.title = title;
		if(checkbox)
			this.checkbox = checkbox;
	}
	/** 
	 * <p>Title: </p> 
	 * <p>Description: 根据基本初始</p> 
	 * @param field
	 * @param title
	 * @param checkbox
	 * @param width
	 * @param align
	 * @param isedit
	 */
	public DatagridField(String field, String title, boolean checkbox, int width, int align, boolean isedit){
		this.field = field;
		this.title = title;
		if(checkbox)
			this.checkbox = checkbox;
		if(width > 0){
			this.width = width;
			this.widthcss = "width:"+width+"px;";
		}
		if(align >= 0){
			this.align = align;
			this.aligncss = "text-align:"+ALIGNVALUE[align]+";";
		}
		this.isedit = isedit;
	}
	public DatagridField(String field, String title, int width, int align, boolean isedit){
		this.field = field;
		this.title = title;
		if(width > 0){
			this.width = width;
			this.widthcss = "width:"+width+"px;";
		}
		if(align >= 0){
			this.align = align;
			this.aligncss = "text-align:"+ALIGNVALUE[align]+";";
		}
		this.isedit = isedit;
	}
	public DatagridField(final DatagridField dbean, int nums) {
		this.field = dbean.field;
		this.title = dbean.title;
		this.checkbox = dbean.checkbox;
		this.width = dbean.width;
		this.widthcss = dbean.widthcss;
		this.align = dbean.align;
		this.aligncss = dbean.aligncss;
		this.isedit = dbean.isedit;
		this.edittype = dbean.edittype;
	}
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the checkbox
	 */
	public boolean isCheckbox() {
		return checkbox;
	}
	/**
	 * @param checkbox the checkbox to set
	 */
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the widthcss
	 */
	public String getWidthcss() {
		return widthcss;
	}
	/**
	 * @param widthcss the widthcss to set
	 */
	public void setWidthcss(String widthcss) {
		this.widthcss = widthcss;
	}
	/**
	 * @return the align
	 */
	public int getAlign() {
		return align;
	}
	/**
	 * @param align the align to set
	 */
	public void setAlign(int align) {
		this.align = align;
	}
	/**
	 * @return the aligncss
	 */
	public String getAligncss() {
		return aligncss;
	}
	/**
	 * @param aligncss the aligncss to set
	 */
	public void setAligncss(String aligncss) {
		this.aligncss = aligncss;
	}
	/**
	 * @return the isedit
	 */
	public boolean isIsedit() {
		return isedit;
	}
	/**
	 * @param isedit the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}
	/**
	 * @return the edittype
	 */
	public int getEdittype() {
		return edittype;
	}
	/**
	 * @param edittype the edittype to set
	 */
	public void setEdittype(int edittype) {
		this.edittype = edittype;
	}
	/**
	 * @return the editFunc
	 */
	public String getEditFunc() {
		return editFunc;
	}
	/**
	 * @param editFunc the editFunc to set
	 */
	public void setEditFunc(String editFunc) {
		this.editFunc = editFunc;
	}
	/**
	 * @return the options
	 */
	public List<Map<String, Object>> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<Map<String, Object>> options) {
		this.options = options;
	}
	/**
	 * @return the linkFunc
	 */
	public String getLinkFunc() {
		return linkFunc;
	}
	/**
	 * @param linkFunc the linkFunc to set
	 */
	public void setLinkFunc(String linkFunc) {
		this.linkFunc = linkFunc;
		this.linkStyle = "cursor:hand;color:blue;";
	}
	/**
	 * @return the linkFuncArgs
	 */
	public String[] getLinkFuncArgs() {
		return linkFuncArgs;
	}
	/**
	 * @param linkFuncArgs the linkFuncArgs to set
	 */
	public void setLinkFuncArgs(String[] linkFuncArgs) {
		this.linkFuncArgs = linkFuncArgs;
	}
	/**
	 * @return the linkStyle
	 */
	public String getLinkStyle() {
		return linkStyle;
	}
	/**
	 * @param linkStyle the linkStyle to set
	 */
	public void setLinkStyle(String linkStyle) {
		this.linkStyle = linkStyle;
	}
	/**
	 * @return the sumvalue
	 */
	public double getSumvalue() {
		return sumvalue;
	}
	/**
	 * @param sumvalue the sumvalue to set
	 */
	public void setSumvalue(double sumvalue) {
		this.sumvalue = sumvalue;
	}
	
	
}
