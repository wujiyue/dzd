package com.markbro.dzd.common.util;

import java.util.HashMap;

@SuppressWarnings({ "serial", "rawtypes" })
public class LogHashMap extends HashMap {
	public String toString() {
		String logString = "|||" + super.toString();
		return logString;
	}
}
