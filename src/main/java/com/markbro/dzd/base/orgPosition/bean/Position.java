package com.markbro.dzd.base.orgPosition.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * Position bean
 * Created by wujiyue on 2016-06-12 22:37:38.
 */
public class Position  implements AliasModel {
	private java.lang.String orgid;//机构组织id
	private java.lang.String id;//主键
	private java.lang.String parentid;//上级id
	private java.lang.String parentids;//parentids
	private java.lang.String name;//岗位名称
	private java.lang.String description;//岗位描述
	private java.lang.Integer sort;//排序
	private java.lang.String createTime;//创建时间
	private java.lang.String updateTime;//更新时间
	private java.lang.String createBy;//创建人
	private java.lang.String updateBy;//更新人
	private java.lang.Integer available;//可用状态
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
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
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

    public Map<String, Object> convertBeanToMap(Position bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Position{" +
			"orgid=" + orgid+
			", id=" + id+
			", parentid=" + parentid+
			", parentids=" + parentids+
			", name=" + name+
			", description=" + description+
			", sort=" + sort+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
