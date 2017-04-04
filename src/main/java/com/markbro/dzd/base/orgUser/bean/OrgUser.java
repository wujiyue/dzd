package com.markbro.dzd.base.orgUser.bean;

import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;

import java.util.Map;

/**
 * 系统用户 bean
 * Created by wujiyue on 2016-07-05 22:52:53.
 */
public class OrgUser implements AliasModel {
	private String id;//主键
	private String orgid;//机构ID
	private String account;//用户账户
	private String password;//用户密码
	private String usertype;//用户类型
	private String nickname;//昵称
	private String remark;//备注
	private String gender;//性别
	private String birthday;//生日
	private Integer age;//年龄
	private String realname;//真实姓名
	private String idcard;//身份证ID
	private String hometown;//故乡
	private String address;//地址
	private String qq;//QQ
	private String email;//email
	private Integer emailflag;//email验证标志
	private String phone;//手机号码
	private Integer phoneflag;//phone验证标志
	private String createtype;//该用户创建类型
	private String lastLoginIp;//最后登陆IP
	private String lastLoginTime;//上次登陆时间
	private String openid;//
	private String createTime;//创建时间
	private String updateTime;//更新时间
	private Integer available;//可用标志
	private Integer deleted;//删除标志

	public String getId(){ return id ;}
	public void  setId(String id){this.id=id; }
	public String getOrgid(){ return orgid ;}
	public void  setOrgid(String orgid){this.orgid=orgid; }
	public String getAccount(){ return account ;}
	public void  setAccount(String account){this.account=account; }
	public String getPassword(){ return password ;}
	public void  setPassword(String password){this.password=password; }
	public String getUsertype(){ return usertype ;}
	public void  setUsertype(String usertype){this.usertype=usertype; }
	public String getNickname(){ return nickname ;}
	public void  setNickname(String nickname){this.nickname=nickname; }
	public String getRemark(){ return remark ;}
	public void  setRemark(String remark){this.remark=remark; }
	public String getGender(){ return gender ;}
	public void  setGender(String gender){this.gender=gender; }
	public String getBirthday(){ return birthday ;}
	public void  setBirthday(String birthday){this.birthday=birthday; }
	public Integer getAge(){ return age ;}
	public void  setAge(Integer age){this.age=age; }
	public String getRealname(){ return realname ;}
	public void  setRealname(String realname){this.realname=realname; }
	public String getIdcard(){ return idcard ;}
	public void  setIdcard(String idcard){this.idcard=idcard; }
	public String getHometown(){ return hometown ;}
	public void  setHometown(String hometown){this.hometown=hometown; }
	public String getAddress(){ return address ;}
	public void  setAddress(String address){this.address=address; }
	public String getQq(){ return qq ;}
	public void  setQq(String qq){this.qq=qq; }
	public String getEmail(){ return email ;}
	public void  setEmail(String email){this.email=email; }
	public Integer getEmailflag(){ return emailflag ;}
	public void  setEmailflag(Integer emailflag){this.emailflag=emailflag; }
	public String getPhone(){ return phone ;}
	public void  setPhone(String phone){this.phone=phone; }
	public Integer getPhoneflag(){ return phoneflag ;}
	public void  setPhoneflag(Integer phoneflag){this.phoneflag=phoneflag; }
	public String getCreatetype(){ return createtype ;}
	public void  setCreatetype(String createtype){this.createtype=createtype; }
	public String getLastLoginIp(){ return lastLoginIp ;}
	public void  setLastLoginIp(String lastLoginIp){this.lastLoginIp=lastLoginIp; }
	public String getLastLoginTime(){ return lastLoginTime ;}
	public void  setLastLoginTime(String lastLoginTime){this.lastLoginTime=lastLoginTime; }
	public String getOpenid(){ return openid ;}
	public void  setOpenid(String openid){this.openid=openid; }
	public String getCreateTime(){ return createTime ;}
	public void  setCreateTime(String createTime){this.createTime=createTime; }
	public String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(String updateTime){this.updateTime=updateTime; }
	public Integer getAvailable(){ return available ;}
	public void  setAvailable(Integer available){this.available=available; }
	public Integer getDeleted(){ return deleted ;}
	public void  setDeleted(Integer deleted){this.deleted=deleted; }

    public Map<String, Object> convertBeanToMap(OrgUser bean){
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
