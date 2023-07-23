package com.example.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Emailsenderservice {
	
	
	@Autowired
	private JavaMailSender jms;
	
	public void sendmessage(String toemail,String subject,String body )
	{
	   SimpleMailMessage message=new SimpleMailMessage();
           
	   message.setFrom("princetechieboy@gmail.com");
	   message.setTo(toemail);
	   message.setText(body);
	   message.setSubject(subject);
	   jms.send(message);
	   System.out.println("success");
	   
	   
         }
}