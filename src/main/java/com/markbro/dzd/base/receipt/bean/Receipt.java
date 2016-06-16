package com.markbro.dzd.base.receipt.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * Receipt bean
 * Created by wujiyue on 2016-03-03 23:01:37.
 */
public class Receipt  implements AliasModel {
	private java.lang.String id;//主键
	private java.lang.String orgid;//组织机构id
	private java.lang.Integer djfl;//单据分类0通用 1：采购 2：销售 3：库存 4：账目
	private java.lang.String djmc;//单据名称
	private java.lang.String djlb;//单据类别
	private java.lang.String zdy;//自定义前缀
	private java.lang.String qz1;//前缀1
	private java.lang.Integer lscd;//流水长度
	private java.lang.Integer glsz;//归零设置
	private java.lang.Integer qsz;//流水起始值
	private java.lang.Integer djsy;//是否适用所有单据
	private java.lang.String fgf;//分隔符
	private java.lang.String dqls;//现在流水号
	private java.lang.String createTime;//
	private java.lang.String updateTime;//
	private java.lang.String createBy;//
	private java.lang.String updateBy;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getOrgid(){ return orgid ;}
	public void  setOrgid(java.lang.String orgid){this.orgid=orgid; }
	public java.lang.Integer getDjfl(){ return djfl ;}
	public void  setDjfl(java.lang.Integer djfl){this.djfl=djfl; }
	public java.lang.String getDjmc(){ return djmc ;}
	public void  setDjmc(java.lang.String djmc){this.djmc=djmc; }
	public java.lang.String getDjlb(){ return djlb ;}
	public void  setDjlb(java.lang.String djlb){this.djlb=djlb; }
	public java.lang.String getZdy(){ return zdy ;}
	public void  setZdy(java.lang.String zdy){this.zdy=zdy; }
	public java.lang.String getQz1(){ return qz1 ;}
	public void  setQz1(java.lang.String qz1){this.qz1=qz1; }
	public java.lang.Integer getLscd(){ return lscd ;}
	public void  setLscd(java.lang.Integer lscd){this.lscd=lscd; }
	public java.lang.Integer getGlsz(){ return glsz ;}
	public void  setGlsz(java.lang.Integer glsz){this.glsz=glsz; }
	public java.lang.Integer getQsz(){ return qsz ;}
	public void  setQsz(java.lang.Integer qsz){this.qsz=qsz; }
	public java.lang.Integer getDjsy(){ return djsy ;}
	public void  setDjsy(java.lang.Integer djsy){this.djsy=djsy; }
	public java.lang.String getFgf(){ return fgf ;}
	public void  setFgf(java.lang.String fgf){this.fgf=fgf; }
	public java.lang.String getDqls(){ return dqls ;}
	public void  setDqls(java.lang.String dqls){this.dqls=dqls; }
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

    public Map<String, Object> convertBeanToMap(Receipt bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Receipt{" +
			"id=" + id+
			", orgid=" + orgid+
			", djfl=" + djfl+
			", djmc=" + djmc+
			", djlb=" + djlb+
			", zdy=" + zdy+
			", qz1=" + qz1+
			", lscd=" + lscd+
			", glsz=" + glsz+
			", qsz=" + qsz+
			", djsy=" + djsy+
			", fgf=" + fgf+
			", dqls=" + dqls+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
