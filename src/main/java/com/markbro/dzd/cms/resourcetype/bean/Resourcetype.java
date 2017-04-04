package com.markbro.dzd.cms.resourcetype.bean;
import com.markbro.asoiaf.core.model.AliasModel;
/**
 * 资源分类 bean
 * Created by wujiyue on 2016-07-21 21:21:05.
 */
public class Resourcetype  implements AliasModel {
	private java.lang.String id;//主键
	private java.lang.String name;//资源分类名称
	private java.lang.String parentid;//资源父ID
	private java.lang.String parentids;//
	private java.lang.Integer sort;//排序
	private java.lang.String code;//资源代码
	private java.lang.String description;//资源分类描述
	private java.lang.String createTime;//
	private java.lang.String updateTime;//
	private java.lang.String createBy;//
	private java.lang.String updateBy;//
	private java.lang.Integer available;//可用标志
	private java.lang.Integer deleted;//删除标志

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getParentid(){ return parentid ;}
	public void  setParentid(java.lang.String parentid){this.parentid=parentid; }
	public java.lang.String getParentids(){ return parentids ;}
	public void  setParentids(java.lang.String parentids){this.parentids=parentids; }
	public java.lang.String getCode(){ return code ;}
	public void  setCode(java.lang.String code){this.code=code; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.String updateBy){this.updateBy=updateBy; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Resourcetype{" +
			"id=" + id+
			", name=" + name+
			", parentid=" + parentid+
			", parentids=" + parentids+
			", code=" + code+
			", sort=" + sort+
			", description=" + description+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
