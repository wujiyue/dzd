package com.markbro.dzd.cms.onlineupdate.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 在线升级 bean
 * Created by wujiyue on 2016-12-02 21:19:13.
 */
public class Onlineupdate  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String updateflag;//
	private java.lang.String mustflag;//
	private java.lang.String fileaddr;//
	private java.lang.Double version;//
	private java.lang.Integer cplb;//
	private java.lang.String bz;//

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getUpdateflag(){ return updateflag ;}
	public void  setUpdateflag(java.lang.String updateflag){this.updateflag=updateflag; }
	public java.lang.String getMustflag(){ return mustflag ;}
	public void  setMustflag(java.lang.String mustflag){this.mustflag=mustflag; }
	public java.lang.String getFileaddr(){ return fileaddr ;}
	public void  setFileaddr(java.lang.String fileaddr){this.fileaddr=fileaddr; }
	public java.lang.Double getVersion(){ return version ;}
	public void  setVersion(java.lang.Double version){this.version=version; }
	public java.lang.Integer getCplb(){ return cplb ;}
	public void  setCplb(java.lang.Integer cplb){this.cplb=cplb; }
	public java.lang.String getBz(){ return bz ;}
	public void  setBz(java.lang.String bz){this.bz=bz; }

	@Override
	public String toString() {
	return "Onlineupdate{" +
			"id=" + id+
			", updateflag=" + updateflag+
			", mustflag=" + mustflag+
			", fileaddr=" + fileaddr+
			", version=" + version+
			", cplb=" + cplb+
			", bz=" + bz+
			 '}';
	}
}
