package com.markbro.dzd.sys.cploginLog.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 登录日志 bean
 * Created by wujiyue on 2016-12-05 21:59:55.
 */
public class CploginLog  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String userid;//用户ID
	private java.lang.String yhmc;//用户名称
	private java.lang.String fjh;//
	private java.lang.String sf;//登录的省份
	private java.lang.String cpdm;//产品代码
	private java.lang.String cpmc;//产品名称
	private java.lang.String version;//版本号
	private java.lang.String logintime;//登录时间
	private java.lang.String ip;//登录IP

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getUserid(){ return userid ;}
	public void  setUserid(java.lang.String userid){this.userid=userid; }
	public java.lang.String getYhmc(){ return yhmc ;}
	public void  setYhmc(java.lang.String yhmc){this.yhmc=yhmc; }
	public java.lang.String getFjh(){ return fjh ;}
	public void  setFjh(java.lang.String fjh){this.fjh=fjh; }
	public java.lang.String getSf(){ return sf ;}
	public void  setSf(java.lang.String sf){this.sf=sf; }
	public java.lang.String getCpdm(){ return cpdm ;}
	public void  setCpdm(java.lang.String cpdm){this.cpdm=cpdm; }
	public java.lang.String getCpmc(){ return cpmc ;}
	public void  setCpmc(java.lang.String cpmc){this.cpmc=cpmc; }
	public java.lang.String getVersion(){ return version ;}
	public void  setVersion(java.lang.String version){this.version=version; }
	public java.lang.String getLogintime(){ return logintime ;}
	public void  setLogintime(java.lang.String logintime){this.logintime=logintime; }
	public java.lang.String getIp(){ return ip ;}
	public void  setIp(java.lang.String ip){this.ip=ip; }

	@Override
	public String toString() {
	return "CploginLog{" +
			"id=" + id+
			", userid=" + userid+
			", yhmc=" + yhmc+
			", fjh=" + fjh+
			", sf=" + sf+
			", cpdm=" + cpdm+
			", cpmc=" + cpmc+
			", version=" + version+
			", logintime=" + logintime+
			", ip=" + ip+
			 '}';
	}
}
