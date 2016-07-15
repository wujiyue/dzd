package com.markbro.dzd.base.orgTree.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 组织目录 bean
 * Created by wujiyue on 2016-07-14 12:00:59.
 */
public class OrgTree implements AliasModel {
	private java.lang.String orgid;//组织id
	private java.lang.String id;//机构角色id、主键
	private java.lang.String parentid;//上级id
	private java.lang.String type;//树中该节点的类型，bm部门，gw岗位，ry人员
	private java.lang.String sjbmid;//上级部门id
	private java.lang.String lxid;//人、部门、岗位对应的真实id
	private java.lang.String name;//角色名称
	private java.lang.String py;//
	private java.lang.Integer sort;//排序序号
	private java.lang.String bz;//备注
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
	public java.lang.String getParentid(){ return parentid ;}
	public void  setParentid(java.lang.String parentid){this.parentid=parentid; }
	public java.lang.String getType(){ return type ;}
	public void  setType(java.lang.String type){this.type=type; }
	public java.lang.String getSjbmid(){ return sjbmid ;}
	public void  setSjbmid(java.lang.String sjbmid){this.sjbmid=sjbmid; }
	public java.lang.String getLxid(){ return lxid ;}
	public void  setLxid(java.lang.String lxid){this.lxid=lxid; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getPy(){ return py ;}
	public void  setPy(java.lang.String py){this.py=py; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
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

    public Map<String, Object> convertBeanToMap(OrgTree bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Tree{" +
			"orgid=" + orgid+
			", id=" + id+
			", parentid=" + parentid+
			", type=" + type+
			", sjbmid=" + sjbmid+
			", lxid=" + lxid+
			", name=" + name+
			", py=" + py+
			", sort=" + sort+
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
