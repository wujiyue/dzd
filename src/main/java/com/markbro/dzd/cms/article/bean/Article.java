package com.markbro.dzd.cms.article.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 文章 bean
 * Created by wujiyue on 2016-08-14 20:02:44.
 */
public class Article  implements AliasModel {
	private java.lang.String id;//主键，文章ID
	private java.lang.String userid;//作者
	private java.lang.String datascope;//数据范围
	private java.lang.String title;//文章标题
	private java.lang.String keywords;//关键词
	private java.lang.String description;//摘要
	private java.lang.Object content;//文章内容
	private java.lang.String articletypes;//文章类别
	private java.lang.String channelids;//频道栏目ID
	private java.lang.String source;//来源
	private java.lang.String link;//原始链接
	private java.lang.String static_url;//静态化后url
	private java.lang.String tags;//标签
	private java.lang.Integer hit;//点击数
	private java.lang.Integer up_vote;//点赞数
	private java.lang.Integer down_vote;//差评数
	private java.lang.Integer hot_flag;//热点标志
	private java.lang.Integer new_flag;//新增标志
	private java.lang.Integer comment_flag;//是否开启评论
	private java.lang.Integer top_flag;//置顶标志
	private java.lang.Integer favourite;//收藏数
	private java.lang.String createTime;//创建时间
	private java.lang.String updateTime;//更新时间
	private java.lang.Integer available;//状态标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getUserid(){ return userid ;}
	public void  setUserid(java.lang.String userid){this.userid=userid; }
	public java.lang.String getDatascope(){ return datascope ;}
	public void  setDatascope(java.lang.String datascope){this.datascope=datascope; }
	public java.lang.String getTitle(){ return title ;}
	public void  setTitle(java.lang.String title){this.title=title; }
	public java.lang.String getKeywords(){ return keywords ;}
	public void  setKeywords(java.lang.String keywords){this.keywords=keywords; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.Object getContent(){ return content ;}
	public void  setContent(java.lang.Object content){this.content=content; }
	public java.lang.String getArticletypes(){ return articletypes ;}
	public void  setArticletypes(java.lang.String articletypes){this.articletypes=articletypes; }
	public java.lang.String getChannelids(){ return channelids ;}
	public void  setChannelids(java.lang.String channelids){this.channelids=channelids; }
	public java.lang.String getSource(){ return source ;}
	public void  setSource(java.lang.String source){this.source=source; }
	public java.lang.String getLink(){ return link ;}
	public void  setLink(java.lang.String link){this.link=link; }
	public java.lang.String getStatic_url(){ return static_url ;}
	public void  setStatic_url(java.lang.String static_url){this.static_url=static_url; }
	public java.lang.String getTags(){ return tags ;}
	public void  setTags(java.lang.String tags){this.tags=tags; }
	public java.lang.Integer getHit(){ return hit ;}
	public void  setHit(java.lang.Integer hit){this.hit=hit; }
	public java.lang.Integer getUp_vote(){ return up_vote ;}
	public void  setUp_vote(java.lang.Integer up_vote){this.up_vote=up_vote; }
	public java.lang.Integer getDown_vote(){ return down_vote ;}
	public void  setDown_vote(java.lang.Integer down_vote){this.down_vote=down_vote; }
	public java.lang.Integer getHot_flag(){ return hot_flag ;}
	public void  setHot_flag(java.lang.Integer hot_flag){this.hot_flag=hot_flag; }
	public java.lang.Integer getNew_flag(){ return new_flag ;}
	public void  setNew_flag(java.lang.Integer new_flag){this.new_flag=new_flag; }
	public java.lang.Integer getComment_flag(){ return comment_flag ;}
	public void  setComment_flag(java.lang.Integer comment_flag){this.comment_flag=comment_flag; }
	public java.lang.Integer getTop_flag(){ return top_flag ;}
	public void  setTop_flag(java.lang.Integer top_flag){this.top_flag=top_flag; }
	public java.lang.Integer getFavourite(){ return favourite ;}
	public void  setFavourite(java.lang.Integer favourite){this.favourite=favourite; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Article{" +
			"id=" + id+
			", userid=" + userid+
			", datascope=" + datascope+
			", title=" + title+
			", keywords=" + keywords+
			", description=" + description+
			", content=" + content+
			", articletypes=" + articletypes+
			", channelids=" + channelids+
			", source=" + source+
			", link=" + link+
			", static_url=" + static_url+
			", tags=" + tags+
			", hit=" + hit+
			", up_vote=" + up_vote+
			", down_vote=" + down_vote+
			", hot_flag=" + hot_flag+
			", new_flag=" + new_flag+
			", comment_flag=" + comment_flag+
			", top_flag=" + top_flag+
			", favourite=" + favourite+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
