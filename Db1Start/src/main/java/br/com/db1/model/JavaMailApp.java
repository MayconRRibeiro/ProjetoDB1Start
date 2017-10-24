package br.com.db1.model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("-----------", "---------------");
			}
		});
		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hiago_luvizutto@hotmail.com")); 
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(""));
			message.setSubject("Enviando email com JavaMail");
			message.setText("SYSOUT TECH É SHOW!!!!");
			Transport.send(message);
			System.out.println("Feito!!!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}