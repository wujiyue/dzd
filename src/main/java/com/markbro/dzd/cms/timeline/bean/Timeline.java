package com.markbro.dzd.cms.timeline.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 时光轴 bean
 * Created by wujiyue on 2016-12-06 22:31:54.
 */
public class Timeline  implements AliasModel {
	private java.lang.Integer id;//主键
	private java.lang.String orgid;//组织id
	private java.lang.String bmid;//部门id
	private java.lang.String gwid;//
	private java.lang.String yhid;//
	private java.lang.String timeline_type;//记录类型
	private java.lang.String content_type;//内容类型
	private java.lang.String content;//内容
	private java.lang.String createBy;//来源
	private java.lang.String createTime;//创建时间
	private java.lang.Integer available;//标志
	private java.lang.Integer deleted;//

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getOrgid(){ return orgid ;}
	public void  setOrgid(java.lang.String orgid){this.orgid=orgid; }
	public java.lang.String getBmid(){ return bmid ;}
	public void  setBmid(java.lang.String bmid){this.bmid=bmid; }
	public java.lang.String getGwid(){ return gwid ;}
	public void  setGwid(java.lang.String gwid){this.gwid=gwid; }
	public java.lang.String getYhid(){ return yhid ;}
	public void  setYhid(java.lang.String yhid){this.yhid=yhid; }
	public java.lang.String getTimeline_type(){ return timeline_type ;}
	public void  setTimeline_type(java.lang.String timeline_type){this.timeline_type=timeline_type; }
	public java.lang.String getContent_type(){ return content_type ;}
	public void  setContent_type(java.lang.String content_type){this.content_type=content_type; }
	public java.lang.String getContent(){ return content ;}
	public void  setContent(java.lang.String content){this.content=content; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

	@Override
	public String toString() {
	return "Timeline{" +
			"id=" + id+
			", orgid=" + orgid+
			", bmid=" + bmid+
			", gwid=" + gwid+
			", yhid=" + yhid+
			", timeline_type=" + timeline_type+
			", content_type=" + content_type+
			", content=" + content+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
