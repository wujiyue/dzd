package com.markbro.dzd.base.actionlog.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 操作日志 bean
 * Created by wujiyue on 2016-07-03 21:07:54.
 */
public class Actionlog  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String yhid;//请求人Id
	private java.lang.String yhmc;//请求人
	private java.lang.String uri;//请求的uri
	private java.lang.Object params;//请求参数
	private java.lang.String method;//方法
	private java.lang.String description;//描述
	private java.lang.Object result;//执行结果
	private java.lang.String createTime;//请求时间
	private java.lang.Integer actionTime;//消耗的时间
	private java.lang.String type;//类型
	private java.lang.Integer available;//
	private java.lang.Integer deleted;//

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getYhid(){ return yhid ;}
	public void  setYhid(java.lang.String yhid){this.yhid=yhid; }
	public java.lang.String getYhmc(){ return yhmc ;}
	public void  setYhmc(java.lang.String yhmc){this.yhmc=yhmc; }
	public java.lang.String getUri(){ return uri ;}
	public void  setUri(java.lang.String uri){this.uri=uri; }
	public java.lang.Object getParams(){ return params ;}
	public void  setParams(java.lang.Object params){this.params=params; }
	public java.lang.String getMethod(){ return method ;}
	public void  setMethod(java.lang.String method){this.method=method; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.Object getResult(){ return result ;}
	public void  setResult(java.lang.Object result){this.result=result; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.Integer getActionTime(){ return actionTime ;}
	public void  setActionTime(java.lang.Integer actionTime){this.actionTime=actionTime; }
	public java.lang.String getType(){ return type ;}
	public void  setType(java.lang.String type){this.type=type; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

    public Map<String, Object> convertBeanToMap(Actionlog bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Actionlog{" +
			"id=" + id+
			", yhid=" + yhid+
			", yhmc=" + yhmc+
			", uri=" + uri+
			", params=" + params+
			", method=" + method+
			", description=" + description+
			", result=" + result+
			", createTime=" + createTime+
			", actionTime=" + actionTime+
			", type=" + type+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
