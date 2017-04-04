package com.markbro.dzd.cms.product.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 产品 bean
 * Created by wujiyue on 2016-09-17 18:05:18.
 */
public class Product  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String cpdm;//产品代码
	private java.lang.String cpmc;//产品名称
	private java.lang.String cpsm;//产品说明
	private java.lang.String zcfs;//注册方式
	private java.lang.String zcmy;//注册密钥
	private java.lang.Integer sort;//排序
	private java.lang.String createBy;//
	private java.lang.String createTime;//
	private java.lang.Integer available;//是否启用
	private java.lang.Integer deleted;//

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getCpdm(){ return cpdm ;}
	public void  setCpdm(java.lang.String cpdm){this.cpdm=cpdm; }
	public java.lang.String getCpmc(){ return cpmc ;}
	public void  setCpmc(java.lang.String cpmc){this.cpmc=cpmc; }
	public java.lang.String getCpsm(){ return cpsm ;}
	public void  setCpsm(java.lang.String cpsm){this.cpsm=cpsm; }
	public java.lang.String getZcfs(){ return zcfs ;}
	public void  setZcfs(java.lang.String zcfs){this.zcfs=zcfs; }
	public java.lang.String getZcmy(){ return zcmy ;}
	public void  setZcmy(java.lang.String zcmy){this.zcmy=zcmy; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
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
	return "Product{" +
			"id=" + id+
			", cpdm=" + cpdm+
			", cpmc=" + cpmc+
			", cpsm=" + cpsm+
			", zcfs=" + zcfs+
			", zcmy=" + zcmy+
			", sort=" + sort+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
