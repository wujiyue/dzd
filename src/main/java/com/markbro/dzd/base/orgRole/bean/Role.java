package com.markbro.dzd.base.orgRole.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * Role bean
 * Created by wujiyue on 2016-06-12 22:35:17.
 */
public class Role  implements AliasModel {
	private java.lang.String orgid;//机构、组织id
	private java.lang.String id;//机构角色id、主键
	private java.lang.String name;//角色名称
	private java.lang.String description;//描述
	private java.lang.String datascope;//数据范围
	private java.lang.String createTime;//创建时间
	private java.lang.String updateTime;//更新时间
	private java.lang.String createBy;//创建人
	private java.lang.String updateBy;//更新人
	private java.lang.Integer available;//可用状态
	private java.lang.Integer deleted;//删除标志。1删除。0未删除。

	public java.lang.String getOrgid(){ return orgid ;}
	public void  setOrgid(java.lang.String orgid){this.orgid=orgid; }
	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.String getDatascope(){ return datascope ;}
	public void  setDatascope(java.lang.String datascope){this.datascope=datascope; }
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

    public Map<String, Object> convertBeanToMap(Role bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Role{" +
			"orgid=" + orgid+
			", id=" + id+
			", name=" + name+
			", description=" + description+
			", datascope=" + datascope+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
