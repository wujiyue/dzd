package com.markbro.dzd.base.sysPara.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 系统参数 bean
 * Created by wujiyue on 2016-07-03 19:25:35.
 */
public class Para  implements AliasModel {
	private java.lang.String id;//
	private java.lang.Integer mk_dm;//
	private java.lang.String csdm;//参数代码
	private java.lang.String csz;//
	private java.lang.String cssm;//参数说明
	private java.lang.String createTime;//
	private java.lang.Integer deleted;//

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.Integer getMk_dm(){ return mk_dm ;}
	public void  setMk_dm(java.lang.Integer mk_dm){this.mk_dm=mk_dm; }
	public java.lang.String getCsdm(){ return csdm ;}
	public void  setCsdm(java.lang.String csdm){this.csdm=csdm; }
	public java.lang.String getCsz(){ return csz ;}
	public void  setCsz(java.lang.String csz){this.csz=csz; }
	public java.lang.String getCssm(){ return cssm ;}
	public void  setCssm(java.lang.String cssm){this.cssm=cssm; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

    public Map<String, Object> convertBeanToMap(Para bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Para{" +
			"id=" + id+
			", mk_dm=" + mk_dm+
			", csdm=" + csdm+
			", csz=" + csz+
			", cssm=" + cssm+
			", createTime=" + createTime+
			", deleted=" + deleted+
			 '}';
	}
}
