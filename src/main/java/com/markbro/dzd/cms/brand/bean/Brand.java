package com.markbro.dzd.cms.brand.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 品牌 bean
 * Created by wujiyue on 2016-09-17 17:58:13.
 */
public class Brand  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String ppdm;//品牌代码
	private java.lang.String ppmc;//品牌名称
	private java.lang.String ppfl;//品牌类别
	private java.lang.String createBy;//
	private java.lang.String createTime;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getPpdm(){ return ppdm ;}
	public void  setPpdm(java.lang.String ppdm){this.ppdm=ppdm; }
	public java.lang.String getPpmc(){ return ppmc ;}
	public void  setPpmc(java.lang.String ppmc){this.ppmc=ppmc; }
	public java.lang.String getPpfl(){ return ppfl ;}
	public void  setPpfl(java.lang.String ppfl){this.ppfl=ppfl; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Brand{" +
			"id=" + id+
			", ppdm=" + ppdm+
			", ppmc=" + ppmc+
			", ppfl=" + ppfl+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
