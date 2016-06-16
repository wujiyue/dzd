package com.markbro.dzd.base.orgOrganization.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * Organization bean
 * Created by wujiyue on 2016-06-12 22:38:52.
 */
public class Organization  implements AliasModel {
	private java.lang.String id;//主键。机构、组织id
	private java.lang.String name;//机构名称
	private java.lang.String orgtypeid;//机构类型id
	private java.lang.String orgtypename;//机构类型名称
	private java.lang.String phone;//电话
	private java.lang.String fax;//传真
	private java.lang.String description;//机构描述
	private java.lang.String createtype;//创建类型
	private java.lang.String createTime;//
	private java.lang.String updateTime;//
	private java.lang.String createBy;//
	private java.lang.String updateBy;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getOrgtypeid(){ return orgtypeid ;}
	public void  setOrgtypeid(java.lang.String orgtypeid){this.orgtypeid=orgtypeid; }
	public java.lang.String getOrgtypename(){ return orgtypename ;}
	public void  setOrgtypename(java.lang.String orgtypename){this.orgtypename=orgtypename; }
	public java.lang.String getPhone(){ return phone ;}
	public void  setPhone(java.lang.String phone){this.phone=phone; }
	public java.lang.String getFax(){ return fax ;}
	public void  setFax(java.lang.String fax){this.fax=fax; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.String getCreatetype(){ return createtype ;}
	public void  setCreatetype(java.lang.String createtype){this.createtype=createtype; }
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

    public Map<String, Object> convertBeanToMap(Organization bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Organization{" +
			"id=" + id+
			", name=" + name+
			", orgtypeid=" + orgtypeid+
			", orgtypename=" + orgtypename+
			", phone=" + phone+
			", fax=" + fax+
			", description=" + description+
			", createtype=" + createtype+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
