package com.contact.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.contact.entity.MailInformation;

public class MailSender {
	
	public void sendMail(MailInformation mailInformation) {
		
		final String userName = "kelimeezberle30@gmail.com";
        final String password = "054627736zx";
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                      new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(userName, password);
               }
        }); 
        try {

               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(mailInformation.getSender()));
               message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mailInformation.getRecipientMail()));
               message.setSubject(mailInformation.getSubject());
               message.setText(mailInformation.getText());
               Transport.send(message);
        } catch (Exception ex) {
               ex.printStackTrace();
               System.out.println("Hata oluþtu.");
        }
	}

}
