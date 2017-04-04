package com.markbro.dzd.gen.config.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 配置 bean
 * Created by wujiyue on 2016-11-23 16:38:40.
 */
public class Config  implements AliasModel {
	private java.lang.Integer id;//编号
	private java.lang.String section;//所属类型
	private java.lang.String name;//配置名称
	private java.lang.String value;//配置值
	private java.lang.String description;//说明
	private java.lang.String createBy;//创建者
	private java.lang.String createTime;//创建时间
	private java.lang.String updateBy;//更新者
	private java.lang.String updateTime;//更新时间
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//删除标记（0：正常；1：删除）

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getSection(){ return section ;}
	public void  setSection(java.lang.String section){this.section=section; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getValue(){ return value ;}
	public void  setValue(java.lang.String value){this.value=value; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.String updateBy){this.updateBy=updateBy; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Config{" +
			"id=" + id+
			", section=" + section+
			", name=" + name+
			", value=" + value+
			", description=" + description+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", updateBy=" + updateBy+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
