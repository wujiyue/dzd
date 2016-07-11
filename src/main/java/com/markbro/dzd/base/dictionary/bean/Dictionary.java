package com.markbro.dzd.base.dictionary.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import com.markbro.asoiaf.core.utils.MyBatisRequestUtil;
import java.util.Map;
/**
 * 数据字典 bean
 * Created by wujiyue on 2016-07-05 22:19:53.
 */
public class Dictionary  implements AliasModel {
	private java.lang.Integer id;//
	private java.lang.String type;//类型
	private java.lang.String value;//值
	private java.lang.String label;//内容
	private java.lang.String description;//类型的描述
	private java.lang.String createTime;//
	private java.lang.String updateTime;//
	private java.lang.String createBy;//添加者用户id
	private java.lang.Integer updateBy;//添加者用户id
	private java.lang.Integer sort;//排序
	private java.lang.Integer available;//可用标志
	private java.lang.Integer deleted;//删除标志

	public java.lang.Integer getId(){ return id ;}
	public void  setId(java.lang.Integer id){this.id=id; }
	public java.lang.String getType(){ return type ;}
	public void  setType(java.lang.String type){this.type=type; }
	public java.lang.String getValue(){ return value ;}
	public void  setValue(java.lang.String value){this.value=value; }
	public java.lang.String getLabel(){ return label ;}
	public void  setLabel(java.lang.String label){this.label=label; }
	public java.lang.String getDescription(){ return description ;}
	public void  setDescription(java.lang.String description){this.description=description; }
	public java.lang.String getCreateTime(){ return createTime ;}
	public void  setCreateTime(java.lang.String createTime){this.createTime=createTime; }
	public java.lang.String getUpdateTime(){ return updateTime ;}
	public void  setUpdateTime(java.lang.String updateTime){this.updateTime=updateTime; }
	public java.lang.String getCreateBy(){ return createBy ;}
	public void  setCreateBy(java.lang.String createBy){this.createBy=createBy; }
	public java.lang.Integer getUpdateBy(){ return updateBy ;}
	public void  setUpdateBy(java.lang.Integer updateBy){this.updateBy=updateBy; }
	public java.lang.Integer getSort(){ return sort ;}
	public void  setSort(java.lang.Integer sort){this.sort=sort; }
	public java.lang.Integer getAvailable(){ return available ;}
	public void  setAvailable(java.lang.Integer available){this.available=available; }
	public java.lang.Integer getDeleted(){ return deleted ;}
	public void  setDeleted(java.lang.Integer deleted){this.deleted=deleted; }

    public Map<String, Object> convertBeanToMap(Dictionary bean){
        Map<String, Object> map = MyBatisRequestUtil.beanConvert2Map(bean);
    	return map;
    }
	@Override
	public String toString() {
	return "Dictionary{" +
			"id=" + id+
			", type=" + type+
			", value=" + value+
			", label=" + label+
			", description=" + description+
			", createTime=" + createTime+
			", updateTime=" + updateTime+
			", createBy=" + createBy+
			", updateBy=" + updateBy+
			", sort=" + sort+
			", available=" + available+
			", deleted=" + deleted+
			 '}';
	}
}
