package com.markbro.dzd.common.util;

import com.markbro.asoiaf.core.utils.SysPara;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Guid {
	public static String get() {
		StringBuffer now = new StringBuffer(new SimpleDateFormat(
				"yyyyMMddHHmmssSSS").format(new Date()));
		int n = (int) (Math.random() * 90000.0D + 10000.0D);
		String xzqh = "";
		try {
			xzqh = SysPara.getValue("sys_xzqh");
		} catch (Exception ex) {
			xzqh = "";
		}
		return ("".equals(xzqh) ? "" : new StringBuilder(String.valueOf(xzqh))
				.append(":").toString()) + now.append(n).toString();
	}
}
