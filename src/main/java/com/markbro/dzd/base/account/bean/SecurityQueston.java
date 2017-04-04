package com.markbro.dzd.base.account.bean;
import com.markbro.asoiaf.core.model.AliasModel;
import java.util.Map;
/**
 * 安全问题 bean
 * Created by wujiyue on 2016-08-22 23:47:01.
 */
public class SecurityQueston  implements AliasModel {
	private String id;//ID
	private String question;//安全问题

	public String getId(){ return id ;}
	public void  setId(String id){this.id=id; }
	public String getQuestion(){ return question ;}
	public void  setQuestion(String question){this.question=question; }

	@Override
	public String toString() {
	return "SecurityQueston{" +
			"id=" + id+
			", question=" + question+
			 '}';
	}
}
