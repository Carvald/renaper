package ar.com.bbva.arq.renaper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("deprecation")
public class MailService {
	
	

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendSimpleMessage(
		      String to, String subject, String text) {
		      
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setFrom("erroresorquestadorclientesapi@bbva.com");
		        message.setTo(to); 
		        message.setSubject("informe de error de orquestación"); 
		        message.setText(text);
		        javaMailSender.send(message);
		    }


}
