package com.markbro.dzd.base.login.bean;

import com.markbro.dzd.common.util.ConstantUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 信息
 * @author yangsl
 *
 */
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bmid;//部门id
	private String gwid;//岗位id
	private String yhid;//用户id
	private String zzid;//组织机构id
	private String xm;//显示名
	private String state;//用户状态
	private boolean isAdmin;
	private boolean mobileLogin; // 是否手机登录
	//登录用户组织信息
	private Map<String, Object> orgMap =new HashMap<String, Object>();
	//登录用户信息
	private Map<String, Object> userMap =new HashMap<String, Object>();
	private List<Map<String, String>> jsList = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> bmList = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> gwList = new ArrayList<Map<String, String>>();
	
	
	public boolean isAdmin() {
		this.isAdmin =this.yhid.equals(ConstantUtil.CON_ADMIN);
		return isAdmin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public  Map<String, Object> getOrgMap() {
		return orgMap;
	}
	public void setOrgMap( Map<String, Object> orgMap) {
		this.orgMap = orgMap;
	}
	public String getBmid() {
		return bmid;
	}
	public void setBmid(String bmid) {
		this.bmid = bmid;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getYhid() {
		return yhid;
	}
	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	public String getZzid() {
		return zzid;
	}
	public void setZzid(String zzid) {
		this.zzid = zzid;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Map<String, String>> getJsList() {
		return jsList;
	}

	public void setJsList(List<Map<String, String>> jsList) {
		this.jsList = jsList;
	}

	public List<Map<String, String>> getBmList() {
		return bmList;
	}

	public void setBmList(List<Map<String, String>> bmList) {
		this.bmList = bmList;
	}

	public List<Map<String, String>> getGwList() {
		return gwList;
	}

	public void setGwList(List<Map<String, String>> gwList) {
		this.gwList = gwList;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	public void setMobileLogin(boolean mobileLogin) {
		this.mobileLogin = mobileLogin;
	}

	public Map<String, Object> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, Object> userMap) {
		this.userMap = userMap;
	}
}
