package com.markbro.dzd.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	static final Logger log = LoggerFactory.getLogger(Log.class);

	public static void write(LogItem logItem) {
		StringBuffer sb = new StringBuffer("{");
		if (logItem.getLogid().equals("")) {
			logItem.setLogid(Guid.get());
		}
		sb.append(logItem.getLogid()).append("}{").append(logItem.getYhid())
				.append("}{");
		sb.append(logItem.getClassName()).append("}{")
				.append(logItem.getMethod()).append("}{");
		sb.append(logItem.getDesc()).append("}{").append(logItem.getContent())
				.append("}{");
		sb.append(logItem.getSql()).append("}{").append(logItem.getSqlData())
				.append("}");
		String logContent = sb.toString();
		if ((logItem.getLevel().equals("")) || (logItem.getMethod().equals(""))
				|| (logItem.getDesc().equals(""))) {
			System.out.println("注意：Log.write()日志项不完整" + logContent);
		}
		if (logItem.getLevel().toLowerCase().equals("debug"))
			log.debug(logContent);
		else if (logItem.getLevel().toLowerCase().equals("info"))
			log.info(logContent);
		else if (logItem.getLevel().toLowerCase().equals("warn"))
			log.info(logContent);
		else if (logItem.getLevel().toLowerCase().equals("error"))
			log.info(logContent);
		else if (logItem.getLevel().toLowerCase().equals("fatal"))
			log.info(logContent);
		else
			System.out.println(logContent);
	}
}
