package com.markbro.dzd.sys.permission.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 菜单权限 bean
 * Created by wujiyue on 2016-07-07 21:47:42.
 */
public class Permission  implements AliasModel {
	private java.lang.String id;//主键
	private java.lang.String parentid;//权限父ID
	private java.lang.String parentids;//
	private java.lang.String name;//权限名称
	private java.lang.String code;//权限代码
	private java.lang.String url;//权限url
	private java.lang.String icon;//
	private java.lang.Integer sort;//
	private java.lang.String description;//权限描述
	private java.lang.String createTime;//
	private java.lang.String createBy;//
	private java.lang.String updateBy;//
	private java.lang.String updateTime;//
	private java.lang.Integer available;//状态标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getParentid(){ return parentid ;}
	public void  setParentid(java.lang.String parentid){this.parentid=parentid; }
	public java.lang.String getParentids(){ return parentids ;}
	public void  setParentids(java.lang.String parentids){this.parentids=parentids; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getCode(){ return code ;}
	public void  setCode(java.lang.String code){this.code=code; }
	public java.lang.String getUrl(){ return url ;}
	public void  setUrl(java.lang.String url){this.url=url; }
	public java.lang.String getIcon(){ return icon ;}
	public void  setIcon(java.lang.String icon){this.icon=icon; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.String updateBy){this.updateBy=updateBy; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

    public Map<String, Object> convertBeanToMap(Permission bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Permission{" +
			"id=" + id+
			", parentid=" + parentid+
			", parentids=" + parentids+
			", name=" + name+
			", code=" + code+
			", url=" + url+
			", icon=" + icon+
			", sort=" + sort+
			", description=" + description+
			", createTime=" + createTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
