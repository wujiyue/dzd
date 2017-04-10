package com.markbro.dzd.base.area.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 区域 bean
 * Created by wujiyue on 2016-07-17 01:33:46.
 */
public class Area  implements AliasModel {
	private java.lang.String id;//
	private java.lang.String parentid;//父级编号
	private java.lang.String parentids;//所有父级编号
	private java.lang.String code;//区域编码
	private java.lang.String name;//区域名称
	private java.lang.Integer sort;//排序
	private java.lang.String areatype;//区域类型
	private java.lang.Integer createBy;//
	private java.lang.String createTime;//
	private java.lang.Integer updateBy;//
	private java.lang.String updateTime;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getParentid(){ return parentid ;}
	public void  setParentid(java.lang.String parentid){this.parentid=parentid; }
	public java.lang.String getParentids(){ return parentids ;}
	public void  setParentids(java.lang.String parentids){this.parentids=parentids; }
	public java.lang.String getCode(){ return code ;}
	public void  setCode(java.lang.String code){this.code=code; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
	public java.lang.String getAreatype(){ return areatype ;}
	public void  setAreatype(java.lang.String areatype){this.areatype=areatype; }
	public java.lang.Integer getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.Integer createBy){this.createBy=createBy; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.Integer getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.Integer updateBy){this.updateBy=updateBy; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Area{" +
			"id=" + id+
			", parentid=" + parentid+
			", parentids=" + parentids+
			", code=" + code+
			", name=" + name+
			", sort=" + sort+
			", areatype=" + areatype+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", updateBy=" + updateBy+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
