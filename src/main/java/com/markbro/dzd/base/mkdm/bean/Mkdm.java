package com.markbro.dzd.base.mkdm.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 模块代码 bean
 * Created by wujiyue on 2016-07-17 11:08:12.
 */
public class Mkdm  implements AliasModel {
	private java.lang.String id;//
	private java.lang.String mkdm;//模块代码
	private java.lang.Object description;//模块描述
	private java.lang.String createBy;//
	private java.lang.String createTime;//
	private java.lang.String updateBy;//
	private java.lang.String updateTime;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getMkdm(){ return mkdm ;}
	public void  setMkdm(java.lang.String mkdm){this.mkdm=mkdm; }
	public java.lang.Object getDescription(){ return description ;}
	public void  setDescription(java.lang.Object description){this.description=description; }
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
	return "Mkdm{" +
			"id=" + id+
			", mkdm=" + mkdm+
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
