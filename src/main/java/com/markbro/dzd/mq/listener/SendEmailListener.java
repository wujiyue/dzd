package com.markbro.dzd.mq.listener;


import com.markbro.dzd.mq.EmailUtil;
import com.markbro.dzd.mq.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * 发送邮件队列
 * 
 * @author ShenHuaJie
 * @version 2016年6月14日 上午11:00:53
 */
public class SendEmailListener implements MessageListener {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	public void onMessage(Message message) {
		try {
			Email email = (Email) ((ObjectMessage) message).getObject();
			logger.info("将发送邮件至：" + email.getSendTo());
			EmailUtil.sendEmail(email);
		}catch (JMSException e) {
			logger.error(e.toString());
		}
	}
}
