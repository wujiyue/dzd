package com.markbro.dzd.cms.link.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 链接 bean
 * Created by wujiyue on 2016-08-14 22:55:32.
 */
public class Link  implements AliasModel {
	private java.lang.String id;//
	private java.lang.String mkdm;//所属模块
	private java.lang.String userid;//
	private java.lang.String datascope;//数据范围
	private java.lang.String name;//链接名称
	private java.lang.String linktypes;//链接类型（引用资源分类）
	private java.lang.String tags;//标签
	private java.lang.String href;//
	private java.lang.String target;//
	private java.lang.String img;//链接图片1
	private java.lang.String img2;//链接图片2
	private java.lang.String description;//链接内容描述
	private java.lang.Integer hit;//点击数
	private java.lang.Integer favourite;//收藏数
	private java.lang.Integer up_vote;//喜欢数
	private java.lang.Integer down_vote;//不喜欢数
	private java.lang.Integer top_flag;//置顶标志
	private java.lang.Integer new_flag;//新增标志
	private java.lang.String createTime;//
	private java.lang.String createBy;//
	private java.lang.String updateTime;//
	private java.lang.String updateBy;//
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getMkdm(){ return mkdm ;}
	public void  setMkdm(java.lang.String mkdm){this.mkdm=mkdm; }
	public java.lang.String getUserid(){ return userid ;}
	public void  setUserid(java.lang.String userid){this.userid=userid; }
	public java.lang.String getDatascope(){ return datascope ;}
	public void  setDatascope(java.lang.String datascope){this.datascope=datascope; }
	public java.lang.String getName(){ return name ;}
	public void  setName(java.lang.String name){this.name=name; }
	public java.lang.String getLinktypes(){ return linktypes ;}
	public void  setLinktypes(java.lang.String linktypes){this.linktypes=linktypes; }
	public java.lang.String getTags(){ return tags ;}
	public void  setTags(java.lang.String tags){this.tags=tags; }
	public java.lang.String getHref(){ return href ;}
	public void  setHref(java.lang.String href){this.href=href; }
	public java.lang.String getTarget(){ return target ;}
	public void  setTarget(java.lang.String target){this.target=target; }
	public java.lang.String getImg(){ return img ;}
	public void  setImg(java.lang.String img){this.img=img; }
	public java.lang.String getImg2(){ return img2 ;}
	public void  setImg2(java.lang.String img2){this.img2=img2; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.Integer getHit(){ return hit ;}
	public void  setHit(java.lang.Integer hit){this.hit=hit; }
	public java.lang.Integer getFavourite(){ return favourite ;}
	public void  setFavourite(java.lang.Integer favourite){this.favourite=favourite; }
	public java.lang.Integer getUp_vote(){ return up_vote ;}
	public void  setUp_vote(java.lang.Integer up_vote){this.up_vote=up_vote; }
	public java.lang.Integer getDown_vote(){ return down_vote ;}
	public void  setDown_vote(java.lang.Integer down_vote){this.down_vote=down_vote; }
	public java.lang.Integer getTop_flag(){ return top_flag ;}
	public void  setTop_flag(java.lang.Integer top_flag){this.top_flag=top_flag; }
	public java.lang.Integer getNew_flag(){ return new_flag ;}
	public void  setNew_flag(java.lang.Integer new_flag){this.new_flag=new_flag; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.String getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.String updateBy){this.updateBy=updateBy; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Link{" +
			"id=" + id+
			", mkdm=" + mkdm+
			", userid=" + userid+
			", datascope=" + datascope+
			", name=" + name+
			", linktypes=" + linktypes+
			", tags=" + tags+
			", href=" + href+
			", target=" + target+
			", img=" + img+
			", img2=" + img2+
			", description=" + description+
			", hit=" + hit+
			", favourite=" + favourite+
			", up_vote=" + up_vote+
			", down_vote=" + down_vote+
			", top_flag=" + top_flag+
			", new_flag=" + new_flag+
			", createTime=" + createTime+
			", createBy=" + createBy+
			", updateTime=" + updateTime+
			", updateBy=" + updateBy+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
