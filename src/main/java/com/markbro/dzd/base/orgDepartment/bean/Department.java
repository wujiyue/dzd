package com.markbro.dzd.base.orgDepartment.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 部门 bean
 * Created by wujiyue on 2016-07-09 23:04:44.
 */
public class Department  implements AliasModel {
	private java.lang.String orgid;//机构、组织id
	private java.lang.String id;//主键。部门id
	private java.lang.String parentid;//上级id
	private java.lang.String parentids;//parentids
	private java.lang.String name;//部门名称
	private java.lang.Integer sort;//排序序号
	private java.lang.String phone;//电话
	private java.lang.String fax;//传真
	private java.lang.String email;//邮箱
	private java.lang.String bz;//备注
	private java.lang.String createTime;//
	private java.lang.String updateTime;//
	private java.lang.String createBy;//
	private java.lang.String updateBy;//
	private java.lang.Integer available;//可用标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getOrgid(){ return orgid ;}
	public void  setOrgid(java.lang.String orgid){this.orgid=orgid; }
	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getParentid(){ return parentid ;}
	public void  setParentid(java.lang.String parentid){this.parentid=parentid; }
	public java.lang.String getParentids(){ return parentids ;}
	public void  setParentids(java.lang.String parentids){this.parentids=parentids; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
	public java.lang.String getPhone(){ return phone ;}
	public void  setPhone(java.lang.String phone){this.phone=phone; }
	public java.lang.String getFax(){ return fax ;}
	public void  setFax(java.lang.String fax){this.fax=fax; }
	public java.lang.String getEmail(){ return email ;}
	public void  setEmail(java.lang.String email){this.email=email; }
	public java.lang.String getBz(){ return bz ;}
	public void  setBz(java.lang.String bz){this.bz=bz; }
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

    public Map<String, Object> convertBeanToMap(Department bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Department{" +
			"orgid=" + orgid+
			", id=" + id+
			", parentid=" + parentid+
			", parentids=" + parentids+
			", name=" + name+
			", sort=" + sort+
			", phone=" + phone+
			", fax=" + fax+
			", email=" + email+
			", bz=" + bz+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
