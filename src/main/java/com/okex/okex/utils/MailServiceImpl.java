package com.okex.okex.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author : huangkai @date : 2021/5/7 : 10:28 上午
 */
@Component
public class MailServiceImpl  {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	
	public void sendSimpleMail(String to, String subject, String content) throws MailException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from); // 邮件发送者
		message.setTo(to); // 邮件接受者
		message.setSubject(subject); // 主题
		message.setText(content); // 内容
		
		mailSender.send(message);
	}
}