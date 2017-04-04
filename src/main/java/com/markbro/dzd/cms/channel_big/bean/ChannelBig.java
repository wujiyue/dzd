package com.markbro.dzd.cms.channel_big.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 栏目大类 bean
 * Created by wujiyue on 2016-08-04 21:14:28.
 */
public class ChannelBig  implements AliasModel {
	private java.lang.String id;//栏目大类ID
	private java.lang.String name;//栏目大类名称
	private java.lang.String jump_url;//栏目链接
	private java.lang.String img_url;//栏目封面图片
	private java.lang.String small_img_url;//栏目封面图片缩略图
	private java.lang.Integer sort;//排序
	private java.lang.Integer subscribe_num;//订阅人数
	private java.lang.String createTime;//创建时间
	private java.lang.String createBy;//
	private java.lang.String updateBy;//
	private java.lang.String updateTime;//更新时间
	private java.lang.Integer available;//状态标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getJump_url(){ return jump_url ;}
	public void  setJump_url(java.lang.String jump_url){this.jump_url=jump_url; }
	public java.lang.String getImg_url(){ return img_url ;}
	public void  setImg_url(java.lang.String img_url){this.img_url=img_url; }
	public java.lang.String getSmall_img_url(){ return small_img_url ;}
	public void  setSmall_img_url(java.lang.String small_img_url){this.small_img_url=small_img_url; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
	public java.lang.Integer getSubscribe_num(){ return subscribe_num ;}
	public void  setSubscribe_num(java.lang.Integer subscribe_num){this.subscribe_num=subscribe_num; }
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

	@Override
	public String toString() {
	return "ChannelBig{" +
			"id=" + id+
			", name=" + name+
			", jump_url=" + jump_url+
			", img_url=" + img_url+
			", small_img_url=" + small_img_url+
			", sort=" + sort+
			", subscribe_num=" + subscribe_num+
			", createTime=" + createTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
