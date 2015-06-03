package com.codecoop.interact.core.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	private static final Logger LOG = Logger.getLogger(MailService.class);
	@Autowired
	private MailSender mailSender;
	
	/**
	 * This method will send compose and send the message 
	 * */
	public void sendMail(String subject, String body, String reciver, String from) 
	{
		
		SimpleMailMessage message = new SimpleMailMessage(); 
		//	        String[] toArray =  new String[]{"thisisram@gmail.com", "jagadeesh.subha@gmail.com"};
		//	        message.setTo(toArray);
		message.setFrom(from);
		message.setTo(reciver);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

}
