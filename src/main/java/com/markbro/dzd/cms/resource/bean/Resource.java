package com.markbro.dzd.cms.resource.bean;
import com.markbro.asoiaf.core.model.AliasModel;
/**
 * 资源 bean
 * Created by wujiyue on 2016-07-27 22:30:42.
 */
public class Resource  implements AliasModel {
	private java.lang.String id;//主键
	private java.lang.String resourcetypeid;//资源分类Id
	private java.lang.String name;//资源名称
	private java.lang.String keywords;//搜索关键词
	private java.lang.String comefrom;//来源
	private java.lang.String url;//链接
	private java.lang.String urlsmall;//资源如果是图片，有可能有小图
	private java.lang.String locaurl;//资源本地存放路径
	private java.lang.String suffix;//后缀
	private java.lang.String targetid;//文章图片等资源记录的id
	private java.lang.Integer resourcestate;//资源状态
	private java.lang.String createTime;//创建时间
	private java.lang.String updateTime;//更新时间
	private java.lang.String createBy;//资源创建者的用户ID
	private java.lang.String updateBy;//资源更新者的用户ID
	private java.lang.Integer available;//可用标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getResourcetypeid(){ return resourcetypeid ;}
	public void  setResourcetypeid(java.lang.String resourcetypeid){this.resourcetypeid=resourcetypeid; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getKeywords(){ return keywords ;}
	public void  setKeywords(java.lang.String keywords){this.keywords=keywords; }

	public String getComefrom() {
		return comefrom;
	}

	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}

	public java.lang.String getUrl(){ return url ;}
	public void  setUrl(java.lang.String url){this.url=url; }
	public java.lang.String getUrlsmall(){ return urlsmall ;}
	public void  setUrlsmall(java.lang.String urlsmall){this.urlsmall=urlsmall; }
	public java.lang.String getLocaurl(){ return locaurl ;}
	public void  setLocaurl(java.lang.String locaurl){this.locaurl=locaurl; }
	public java.lang.String getSuffix(){ return suffix ;}
	public void  setSuffix(java.lang.String suffix){this.suffix=suffix; }
	public java.lang.String getTargetid(){ return targetid ;}
	public void  setTargetid(java.lang.String targetid){this.targetid=targetid; }
	public java.lang.Integer getResourcestate(){ return resourcestate ;}
	public void  setResourcestate(java.lang.Integer resourcestate){this.resourcestate=resourcestate; }
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

	@Override
	public String toString() {
	return "Resource{" +
			"id=" + id+
			", resourcetypeid=" + resourcetypeid+
			", name=" + name+
			", keywords=" + keywords+
			", comefrom=" + comefrom+
			", url=" + url+
			", urlsmall=" + urlsmall+
			", locaurl=" + locaurl+
			", suffix=" + suffix+
			", targetid=" + targetid+
			", resourcestate=" + resourcestate+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
