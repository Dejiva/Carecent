package com.codecoop.interact.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(PropertyPlaceholderConfig.class)
public class CoreAppPropertyReader {
	  @Value("${user.reg.mail.sender}") private String userRegMailSender;
	  @Value("${message.mail.sender}") private String messageMailSender;
	
	public String getUserRegMailSender() {
		return userRegMailSender;
	}
	public String getMessageMailSender() {
		return messageMailSender;
	}
}
