package com.markbro.dzd.base.filter;


import com.markbro.asoiaf.core.utils.Md5;
import com.markbro.asoiaf.core.utils.SysPara;


public class Token {
	//通过随机guid串和数据库配置的固定token串经过一次md5加密构造出一个token串
	public static String getToken(String str) throws Exception {
		String token_key = SysPara.getValue("token_key");
		return Md5.getMd5(str + token_key);
	}
	//检验客户端的token是否有效
	public static boolean checkToken(String str, String token) throws Exception {
		String token_key = SysPara.getValue("token_key");
		String md5 = Md5.getMd5(str + token_key);

		return md5.equals(token);
	}
}
