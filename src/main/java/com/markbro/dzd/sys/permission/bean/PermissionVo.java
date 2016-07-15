package com.markbro.dzd.sys.permission.bean;

import com.markbro.asoiaf.core.model.AliasModel;

import java.util.List;

/**
 * 菜单权限 bean
 * Created by wujiyue on 2016-07-07 21:47:42.
 */
public class PermissionVo implements AliasModel {
	private String id;//主键
	private String name;//权限名称
	private String type;//权限代码
	private String checked;//1选中 0没选中
	private List<PermissionVo> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public List<PermissionVo> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionVo> children) {
		this.children = children;
	}
}
