package com.markbro.dzd.common.util;


public class PageBean {
//	private String sql = "";
//
//	private String countSql = "";
//
//	private Map namedParameters;//
//	private String orderBy = "";
//
	private String currentPage = "";
	private String pageGoto = "";
	private String pageSize = "10";

	private int page_allPage = 0;

	private int page_cur = 1;

	private int page_allCount = 0;

	public String getPageGoto() {
		return this.pageGoto;
	}

	public void setPageGoto(String pageGoto) {
		this.pageGoto = pageGoto;
	}

	public String getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
//
//	public String getSql() {
//		return this.sql;
//	}
//
//	public void setSql(String sql) {
//		this.sql = sql;
//	}
//
//	public String getCountSql() {
//		return this.countSql;
//	}
//
//	public void setCountSql(String countSql) {
//		this.countSql = countSql;
//	}
//
	public String getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
//
//	public Map getNamedParameters() {
//		return this.namedParameters;
//	}
//
//	public void setNamedParameters(Map namedParameters) {
//		this.namedParameters = namedParameters;
//	}
//
	public int getPage_allCount() {
		return this.page_allCount;
	}

	public void setPage_allCount(int page_allCount) {
		this.page_allCount = page_allCount;
	}

	public int getPage_allPage() {
		return this.page_allPage;
	}

	public void setPage_allPage(int page_allPage) {
		this.page_allPage = page_allPage;
	}

	public int getPage_cur() {
		return this.page_cur;
	}

	public void setPage_cur(int page_cur) {
		this.page_cur = page_cur;
	}
//
//	public String getOrderBy() {
//		return this.orderBy;
//	}
//
//	public void setOrderBy(String orderBy) {
//		this.orderBy = orderBy;
//	}
}