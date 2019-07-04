package com.gestionusuario.app;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gestionusuario.app.email.Email;
import com.gestionusuario.app.service.impl.EmailServiceImpl;


@SpringBootApplication
public class GestionUsuariosApplication {
	
	
	//private static final Logger logger = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	
	@Autowired
    private EmailServiceImpl emailService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
		
	}
	
	
    public void run(ApplicationArguments applicationArguments) throws Exception {
       

        Email mail = new Email();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("rsantos@exact.com.pe");
        mail.setSubject("Sending Simple Email with JavaMailSender Example");
        mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");

        emailService.sendSimpleMessage(mail);
        System.out.println("se envio correo");
    }
}
