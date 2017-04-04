package com.markbro.dzd.cms.send_email.bean;

import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;

/**
 * 发送邮件 bean
 * Created by wujiyue on 2016-12-23 21:26:15 .
 */
public class SendEmail  implements AliasModel {

	private java.lang.String id;//
	private java.lang.String emailtype;//邮件类型
	private java.lang.String userid;//用户ID
	private java.lang.String toemail;//
	private java.lang.String fromemail;//
	private java.lang.String title;//标题
	private java.lang.Object content;//内容
	private java.lang.String sendflag;//是否发送
	private java.lang.String feedback;//是否反馈
	private java.lang.String createTime;//创建时间
	private java.lang.String sendTime;//发送时间


	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getEmailtype(){ return emailtype ;}
	public void  setEmailtype(java.lang.String emailtype){this.emailtype=emailtype; }
	public java.lang.String getUserid(){ return userid ;}
	public void  setUserid(java.lang.String userid){this.userid=userid; }
	public java.lang.String getToemail(){ return toemail ;}
	public void  setToemail(java.lang.String toemail){this.toemail=toemail; }
	public java.lang.String getFromemail(){ return fromemail ;}
	public void  setFromemail(java.lang.String fromemail){this.fromemail=fromemail; }
	public java.lang.String getTitle(){ return title ;}
	public void  setTitle(java.lang.String title){this.title=title; }
	public java.lang.Object getContent(){ return content ;}
	public void  setContent(java.lang.Object content){this.content=content; }
	public java.lang.String getSendflag(){ return sendflag ;}
	public void  setSendflag(java.lang.String sendflag){this.sendflag=sendflag; }
	public java.lang.String getFeedback(){ return feedback ;}
	public void  setFeedback(java.lang.String feedback){this.feedback=feedback; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getSendTime(){ return sendTime ;}
	public void  setSendTime(java.lang.String sendTime){this.sendTime=sendTime; }


	@Override
	public String toString() {
	return "SendEmail{" +
			"id=" + id+
			", emailtype=" + emailtype+
			", userid=" + userid+
			", toemail=" + toemail+
			", fromemail=" + fromemail+
			", title=" + title+
			", content=" + content+
			", sendflag=" + sendflag+
			", feedback=" + feedback+
			", createTime=" + createTime+
			", sendTime=" + sendTime+
			 '}';
	}
}
