package com.markbro.dzd.sys.sysUser.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 系统用户 bean
 * Created by wujiyue on 2016-07-05 22:52:53.
 */
public class User  implements AliasModel {
	private java.lang.String id;//主键
	private java.lang.String orgid;//机构ID
	private java.lang.String account;//用户账户
	private java.lang.String password;//用户密码
	private java.lang.String usertype;//用户类型
	private java.lang.String nickname;//昵称
	private java.lang.String remark;//备注
	private java.lang.String gender;//性别
	private java.lang.String birthday;//生日
	private java.lang.Integer age;//年龄
	private java.lang.String realname;//真实姓名
	private java.lang.String idcard;//身份证ID
	private java.lang.String hometown;//故乡
	private java.lang.String address;//地址
	private java.lang.String qq;//QQ
	private java.lang.String email;//email
	private java.lang.Integer emailflag;//email验证标志
	private java.lang.String phone;//手机号码
	private java.lang.Integer phoneflag;//phone验证标志
	private java.lang.String createtype;//该用户创建类型
	private java.lang.String lastLoginIp;//最后登陆IP
	private java.lang.String lastLoginTime;//上次登陆时间
	private java.lang.String openid;//
	private java.lang.String createTime;//创建时间
	private java.lang.String updateTime;//更新时间
	private java.lang.Integer available;//可用标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.String getId(){ return id ;}
	public void  setId(java.lang.String id){this.id=id; }
	public java.lang.String getOrgid(){ return orgid ;}
	public void  setOrgid(java.lang.String orgid){this.orgid=orgid; }
	public java.lang.String getAccount(){ return account ;}
	public void  setAccount(java.lang.String account){this.account=account; }
	public java.lang.String getPassword(){ return password ;}
	public void  setPassword(java.lang.String password){this.password=password; }
	public java.lang.String getUsertype(){ return usertype ;}
	public void  setUsertype(java.lang.String usertype){this.usertype=usertype; }
	public java.lang.String getNickname(){ return nickname ;}
	public void  setNickname(java.lang.String nickname){this.nickname=nickname; }
	public java.lang.String getRemark(){ return remark ;}
	public void  setRemark(java.lang.String remark){this.remark=remark; }
	public java.lang.String getGender(){ return gender ;}
	public void  setGender(java.lang.String gender){this.gender=gender; }
	public java.lang.String getBirthday(){ return birthday ;}
	public void  setBirthday(java.lang.String birthday){this.birthday=birthday; }
	public java.lang.Integer getAge(){ return age ;}
	public void  setAge(java.lang.Integer age){this.age=age; }
	public java.lang.String getRealname(){ return realname ;}
	public void  setRealname(java.lang.String realname){this.realname=realname; }
	public java.lang.String getIdcard(){ return idcard ;}
	public void  setIdcard(java.lang.String idcard){this.idcard=idcard; }
	public java.lang.String getHometown(){ return hometown ;}
	public void  setHometown(java.lang.String hometown){this.hometown=hometown; }
	public java.lang.String getAddress(){ return address ;}
	public void  setAddress(java.lang.String address){this.address=address; }
	public java.lang.String getQq(){ return qq ;}
	public void  setQq(java.lang.String qq){this.qq=qq; }
	public java.lang.String getEmail(){ return email ;}
	public void  setEmail(java.lang.String email){this.email=email; }
	public java.lang.Integer getEmailflag(){ return emailflag ;}
	public void  setEmailflag(java.lang.Integer emailflag){this.emailflag=emailflag; }
	public java.lang.String getPhone(){ return phone ;}
	public void  setPhone(java.lang.String phone){this.phone=phone; }
	public java.lang.Integer getPhoneflag(){ return phoneflag ;}
	public void  setPhoneflag(java.lang.Integer phoneflag){this.phoneflag=phoneflag; }
	public java.lang.String getCreatetype(){ return createtype ;}
	public void  setCreatetype(java.lang.String createtype){this.createtype=createtype; }
	public java.lang.String getLastLoginIp(){ return lastLoginIp ;}
	public void  setLastLoginIp(java.lang.String lastLoginIp){this.lastLoginIp=lastLoginIp; }
	public java.lang.String getLastLoginTime(){ return lastLoginTime ;}
	public void  setLastLoginTime(java.lang.String lastLoginTime){this.lastLoginTime=lastLoginTime; }
	public java.lang.String getOpenid(){ return openid ;}
	public void  setOpenid(java.lang.String openid){this.openid=openid; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

    public Map<String, Object> convertBeanToMap(User bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "User{" +
			"id=" + id+
			", orgid=" + orgid+
			", account=" + account+
			", password=" + password+
			", usertype=" + usertype+
			", nickname=" + nickname+
			", remark=" + remark+
			", gender=" + gender+
			", birthday=" + birthday+
			", age=" + age+
			", realname=" + realname+
			", idcard=" + idcard+
			", hometown=" + hometown+
			", address=" + address+
			", qq=" + qq+
			", email=" + email+
			", emailflag=" + emailflag+
			", phone=" + phone+
			", phoneflag=" + phoneflag+
			", createtype=" + createtype+
			", lastLoginIp=" + lastLoginIp+
			", lastLoginTime=" + lastLoginTime+
			", openid=" + openid+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
