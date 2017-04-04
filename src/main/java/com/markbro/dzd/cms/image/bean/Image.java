package com.markbro.dzd.cms.image.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 图片 bean
 * Created by wujiyue on 2016-08-12 21:03:05.
 */
public class Image  implements AliasModel {
	private java.lang.String id;//
	private java.lang.String yhid;//
	private java.lang.String datascope;//数据访问类型
	private java.lang.String name;//图片名称
	private java.lang.String url;//
	private java.lang.String url_small;//
	private java.lang.Integer size;//大小
	private java.lang.String suffixes;//后缀
	private java.lang.String keywords;//关键词
	private java.lang.String resourcetypeid;//资源类型
	private java.lang.String createBy;//
	private java.lang.String createTime;//
	private java.lang.String updateBy;//
	private java.lang.String updateTime;//
	private java.lang.Integer available;//状态
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getYhid(){ return yhid ;}
	public void  setYhid(java.lang.String yhid){this.yhid=yhid; }
	public java.lang.String getDatascope(){ return datascope ;}
	public void  setDatascope(java.lang.String datascope){this.datascope=datascope; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getUrl(){ return url ;}
	public void  setUrl(java.lang.String url){this.url=url; }
	public java.lang.String getUrl_small(){ return url_small ;}
	public void  setUrl_small(java.lang.String url_small){this.url_small=url_small; }
	public java.lang.Integer getSize(){ return size ;}
	public void  setSize(java.lang.Integer size){this.size=size; }
	public java.lang.String getSuffixes(){ return suffixes ;}
	public void  setSuffixes(java.lang.String suffixes){this.suffixes=suffixes; }
	public java.lang.String getKeywords(){ return keywords ;}
	public void  setKeywords(java.lang.String keywords){this.keywords=keywords; }
	public java.lang.String getResourcetypeid(){ return resourcetypeid ;}
	public void  setResourcetypeid(java.lang.String resourcetypeid){this.resourcetypeid=resourcetypeid; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.String updateBy){this.updateBy=updateBy; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Image{" +
			"id=" + id+
			", yhid=" + yhid+
			", datascope=" + datascope+
			", name=" + name+
			", url=" + url+
			", url_small=" + url_small+
			", size=" + size+
			", suffixes=" + suffixes+
			", keywords=" + keywords+
			", resourcetypeid=" + resourcetypeid+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", updateBy=" + updateBy+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
