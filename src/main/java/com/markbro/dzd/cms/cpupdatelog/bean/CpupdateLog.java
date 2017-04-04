package com.markbro.dzd.cms.cpupdatelog.bean;
import com.markbro.asoiaf.core.model.AliasModel;
/**
 * 更新日志 bean
 * Created by wujiyue on 2016-12-04 19:49:43.
 */
public class CpupdateLog  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String cpdm;//产品代码
	private java.lang.String cpmc;//产品名称
	private java.lang.String version;//版本号
	private java.lang.String gxnr;//升级内容
	private java.lang.String fbsj;//发布时间
	private java.lang.String lrr;//发布人员
	private java.lang.String createTime;//
	private java.lang.String createBy;//
	private java.lang.String updateTime;//
	private java.lang.String updateBy;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getCpdm(){ return cpdm ;}
	public void  setCpdm(java.lang.String cpdm){this.cpdm=cpdm; }
	public java.lang.String getVersion(){ return version ;}
	public void  setVersion(java.lang.String version){this.version=version; }
	public java.lang.Object getGxnr(){ return gxnr ;}
	public void  setGxnr(java.lang.String gxnr){this.gxnr=gxnr; }
	public java.lang.String getFbsj(){ return fbsj ;}
	public void  setFbsj(java.lang.String fbsj){this.fbsj=fbsj; }
	public java.lang.String getLrr(){ return lrr ;}
	public void  setLrr(java.lang.String lrr){this.lrr=lrr; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.String getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.String updateBy){this.updateBy=updateBy; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "CpupdateLog{" +
			"id=" + id+
			", cpdm=" + cpdm+
			", version=" + version+
			", gxnr=" + gxnr+
			", fbsj=" + fbsj+
			", lrr=" + lrr+
			", createTime=" + createTime+
			", createBy=" + createBy+
			", updateTime=" + updateTime+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
