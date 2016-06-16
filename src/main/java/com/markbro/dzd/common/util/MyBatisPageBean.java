package com.markbro.dzd.common.util;

import java.util.List;
import java.util.Map;
/** 
* 项目名称：ydxs   
* 类名称：MyBatisPageBean   
* 类描述：页面查询辅助类
* 创建人：hujianren 
* 创建时间：Dec 26, 2013 6:00:10 PM    
* 修改备注：  
* @version
 */

/**
 * mybatis 分页工具类
 * @author mywhile
 */
public class MyBatisPageBean extends PageBean {
	/**
		sql		要执行的sql语句
		countSql		复杂sql需要指定计算总记录数的语句
		currentPage		当前页码
		pageGoto		要转到的页数
		pageSize		每页大小
		Map namedParameters		参数值，供npjt使用
		page_allPage		返回到页面，总页数
		page_cur		返回到页面，当前页
		page_allCount		返回到页面，总记录数
		String orderBy		排序字段
	*/
	private int page;
	private int countRow;
	private List<Map<String, Object>> pageList;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCountRow() {
		return countRow;
	}
	public void setCountRow(int countRow) {
		this.countRow = countRow;
		int pSize = Integer.parseInt(this.getPageSize());
		int cp = countRow % pSize;
		page = countRow / pSize;
		page += cp > 0 ? 1 : 0;
		this.setPage_allPage(page);
		this.setCurrentPage(this.getPageGoto());
		this.setPage_cur(Integer.parseInt(this.getPageGoto()));
		this.setPage_allCount(countRow);
	}
	public List<Map<String, Object>> getPageList() {
		return pageList;
	}
	public void setPageList(List<Map<String, Object>> pageList) {
		this.pageList = pageList;
	}
}
