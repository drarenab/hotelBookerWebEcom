package com.utilities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

public class EmailSender {

	public static boolean sendMail(String objet,String message,String to[], String copie[])
			throws Exception, NamingException {

		String host = "smtp.gmail.com";
		String from = "pyramide.no.replay@gmail.com";
		String password = "pyramide123123";
		int port = 587;

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");

		
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			mimeMessage.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			if (copie[0] != null) {

				InternetAddress[] toAddressCopie = new InternetAddress[copie.length];

				for (int i = 0; i < copie.length; i++) {
					toAddressCopie[i] = new InternetAddress(copie[i]);
				}
				for (int i = 0; i < toAddressCopie.length; i++) {
					mimeMessage.addRecipient(Message.RecipientType.CC, toAddressCopie[i]);

				}
			}

			mimeMessage.setSubject(objet);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart txtPart = new MimeBodyPart();
			txtPart.setContent(message, "text/html; charset=utf-8");//setText(message);
			multipart.addBodyPart(txtPart);

			mimeMessage.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			System.out.println("Send Mail Ok !");
			return true;

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return false;
	}
}
