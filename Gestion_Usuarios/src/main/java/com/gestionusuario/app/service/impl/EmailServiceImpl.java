package com.gestionusuario.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.email.Email;

@Service
public class EmailServiceImpl {
	
	@Autowired
	private JavaMailSender emailSender;
	
	 public void sendSimpleMessage(final Email email){
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setSubject(email.getSubject());
	        message.setText(email.getContent());
	        message.setTo(email.getTo());
	        message.setFrom(email.getFrom());

	        emailSender.send(message);
	    }

}
