package com.markbro.dzd.cms.labelMsg.bean;

import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;

/**
 * 便签管理 bean
 * Created by wujiyue on 2017-02-05 22:21:01 .
 */
public class LabelMsg  implements AliasModel {

	private java.lang.Integer id;//主键
	private java.lang.String orgid;//组织id
	private java.lang.String bmid;//部门id
	private java.lang.String gwid;//岗位id
	private java.lang.String yhid;//用户id
	private java.lang.String label_msg_type;//便签类型
	private java.lang.String title;//便签标题
	private java.lang.String content;//便签内容
	private java.lang.String feedback;//
	private java.lang.String createBy;//创建人
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
	public java.lang.String getLabel_msg_type(){ return label_msg_type ;}
	public void  setLabel_msg_type(java.lang.String label_msg_type){this.label_msg_type=label_msg_type; }
	public java.lang.String getTitle(){ return title ;}
	public void  setTitle(java.lang.String title){this.title=title; }
	public java.lang.String getContent(){ return content ;}
	public void  setContent(java.lang.String content){this.content=content; }
	public java.lang.String getFeedback(){ return feedback ;}
	public void  setFeedback(java.lang.String feedback){this.feedback=feedback; }
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
	return "LabelMsg{" +
			"id=" + id+
			", orgid=" + orgid+
			", bmid=" + bmid+
			", gwid=" + gwid+
			", yhid=" + yhid+
			", label_msg_type=" + label_msg_type+
			", title=" + title+
			", content=" + content+
			", feedback=" + feedback+
			", createBy=" + createBy+
			", createTime=" + createTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
