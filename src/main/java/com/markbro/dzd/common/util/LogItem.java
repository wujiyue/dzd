package com.markbro.dzd.common.util;

public class LogItem {
	private String yhid = "";

	private String logid = "";

	private String level = "";

	private String className = "";

	private String method = "";

	private String desc = "";

	private String sql = "";

	private String sqlData = "";

	private String content = "";

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSqlData() {
		return this.sqlData;
	}

	public void setSqlData(String sqlData) {
		this.sqlData = sqlData;
	}

	public String getYhid() {
		return this.yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
