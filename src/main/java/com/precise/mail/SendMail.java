package com.precise.mail;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import com.precise.controller.LoginController;

@Component

public class SendMail {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(SendMail.class);

	Message msg;
	private Session session;
	String Subject = "";
	Properties p = new Properties();
	//final String fromMail = "preciseexpense2017@gmail.com";
	//final String password = "precise2017";
	final String fromMail = "pgpplacecom@iima.ac.in";
	
	//final String password = "placecom18@sv";
	
	public SendMail() {
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.EnableSSL.enable", "true");
		p.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.socketFactory.port", "465");
	}


	public String sendMail(String body, String email, String subject) {
		logger.info("sendMail() function to send mail"+email);
		Subject = subject;
		String msgsent = "";
		String toEmail=email;
	//	System.out.println("toEmail::"+toEmail);
		session = Session.getInstance(p, new SimpleMailAuthenticator(fromMail, getMailPwd(fromMail)));
		try {
			msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromMail));
			msg.setContent(body, "text/html");
			Transport.send(msg);
			msgsent = "Message Sent";
		} catch (Exception e) {
			msgsent = "Invalid Addresses";
			e.printStackTrace();

		}
		logger.info("sendMail() msgsent::" + msgsent);
		return msgsent;

	}
	
	public String sendMailHR(String body, String email, String subject) {
		logger.info("sendMailHR() function to send mail :: "+email);
		Subject = subject;
		String msgsent = "";
		String toEmail=email;
		//System.out.println("toEmail::"+toEmail);
	//	session = Session.getInstance(p, new SimpleMailAuthenticator("pgpplacements@iima.ac.in","placements@123"));
		session = Session.getInstance(p, new SimpleMailAuthenticator("pgpplacements@iima.ac.in",getMailPwd("pgpplacements@iima.ac.in")));
		try {
			msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromMail));
			msg.setContent(body, "text/html");
			Transport.send(msg);
			msgsent = "Message Sent";
		} catch (Exception e) {
			msgsent = "Invalid Addresses";
			e.printStackTrace();

		}
		logger.info("sendMailHR() msgsent::" + msgsent);
		return msgsent;

	}
	
	public String sendMailShortList(String body, String email,String rmemail, String subject) {
		logger.info("sendMailShortList() function to send mail"+email);
		Subject = subject;
		String msgsent = "";
		String toEmail=email;
		String ccEmailid=rmemail;
	//	System.out.println("toEmail::"+toEmail);
	//	session = Session.getInstance(p, new SimpleMailAuthenticator("shortlist@iima.ac.in","shortlist@12"));
		session = Session.getInstance(p, new SimpleMailAuthenticator("shortlist@iima.ac.in",getMailPwd("shortlist@iima.ac.in")));
		
		try {
			msg = new MimeMessage(session);
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(toEmail));
			if(!ccEmailid.equals(""))
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmailid));
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromMail));
			msg.setContent(body, "text/html");
			Transport.send(msg);
			msgsent = "Message Sent";
		} catch (Exception e) {
			msgsent = "Invalid Addresses";
			logger.info("Email Not Send mail id ::"+toEmail);
			e.printStackTrace();

		}
		logger.info("sendMailShortList() msgsent::" + msgsent);
		return msgsent;

	}
	
	public String sendMailHRShortList(String body, String email,String rmemail, String subject) {
		logger.info("sendMailHRShortList() function to send mail"+email);
		Subject = subject;
		String msgsent = "";
		String toEmail=email;
		String ccEmailid=rmemail;
	//	System.out.println("toEmail::"+toEmail);
		session = Session.getInstance(p, new SimpleMailAuthenticator("pgpplacements@iima.ac.in",getMailPwd("pgpplacements@iima.ac.in")));
	//	session = Session.getInstance(p, new SimpleMailAuthenticator("pgpplacements@iima.ac.in","placements@123"));
		try {
			msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmailid));
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromMail));
			msg.setContent(body, "text/html");
			Transport.send(msg);
			msgsent = "Message Sent";
		} catch (Exception e) {
			msgsent = "Invalid Addresses";
			e.printStackTrace();

		}
		logger.info("sendMailHRShortList msgsent::" + msgsent);
		return msgsent;

	}
	
	public String sendMailForMessage(String body, String email, String subject) {
		//System.out.println("inside send mail function to send mail"+email);
		Subject = subject;
		String msgsent = "";
		String[] toEmail=email.split(",");
		logger.info("sendMailForMessage toEmail::"+toEmail);
		session = Session.getInstance(p, new SimpleMailAuthenticator(fromMail, getMailPwd(fromMail)));
		try {
			System.out.println("toEmailLength::"+toEmail.length);
			for(int i=0;i<toEmail.length;i++){
				System.out.println("inside for loop::");
				msg = new MimeMessage(session);
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail[i].trim()));
				//msg.addRecipient(Message.RecipientType.TO, new InternetAddress("komaljain120@gmail.com"));
				msg.setSubject(subject);
				msg.setFrom(new InternetAddress(fromMail));
				msg.setContent(body, "text/html");
				Transport.send(msg);
				msgsent = "MessageSent";
			}
		} catch (Exception e) {
			logger.error("Mail Not Send Invalid addresss");
			msgsent = "Invalid Addresses";
			e.printStackTrace();

		}
		logger.info("sendMailForMessage msgsent::" + msgsent);
		return msgsent;

	}
	
	public void sendMimePart(String fileName,String filePath,String subject,String msgBody,String emailAddresses){
	
		try {
			//String mailTo = "kapilgyanvihar@gmail.com,ayushigupta.12july@gmail.com";
			session = Session.getInstance(p, new SimpleMailAuthenticator(fromMail, getMailPwd(fromMail)));
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.setRecipients(Message.RecipientType.BCC,	InternetAddress.parse(emailAddresses));
			message.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			//messageBodyPart.setText(msgBody);
			messageBodyPart.setContent(msgBody,"text/html");

			MimeBodyPart messageBodyFile=null;
			if(!fileName.equals("")){
				try{
					messageBodyFile = new MimeBodyPart();
					DataSource source = new FileDataSource(filePath);
					messageBodyFile.setDataHandler(new DataHandler(source));
					if(!fileName.equals(""))
						messageBodyFile.setFileName(fileName);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			// 5) create Multipart object and add MimeBodyPart objects to this
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			if(!fileName.equals("")){
				multipart.addBodyPart(messageBodyFile);
			}
			message.setContent(multipart);
			Transport.send(message);
			
			logger.info("sendMimePart() mail sent.");
		} catch (Exception ex) {
			logger.error("sendMimePart() Mails not sent ::"+ex.getMessage());
			ex.printStackTrace();
		} 
	}
	
	
	public void sendAnnouncement(String fileName,String filePath,String subject,String msgBody,String emailAddresses){
		/*String fromMailId = "pgpplacecom@iima.ac.in";
		String passwordVal = "pgpplacecom4131";*/
		try {
			String name = "";
			logger.info("sendAnnouncement() emailAddresses :: "+emailAddresses);
			//String[] emailAdd=emailAddresses.split(",");
			//String mailTo = "kapilgyanvihar@gmail.com,ayushigupta.12july@gmail.com";
			session = Session.getInstance(p, new SimpleMailAuthenticator(fromMail, getMailPwd(fromMail)));
			
			/*for(String emailID:emailAdd){
				if(!emailID.equals("")){
				name = getNames(emailID.trim());
				if(name.equals("") || name==null){
					name = emailID.split("@")[0];
				}
				System.out.println("name : "+name);*/
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromMail));
				//message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(emailAddresses));
				message.setRecipients(Message.RecipientType.BCC,	InternetAddress.parse(emailAddresses));
				//message.setSubject("PLACECOM"+"_"+subject);
				message.setSubject(subject);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(msgBody,"text/html");
	
				MimeBodyPart messageBodyFile=null;
				if(!fileName.equals("")){
					try{
						messageBodyFile = new MimeBodyPart();
						DataSource source = new FileDataSource(filePath);
						messageBodyFile.setDataHandler(new DataHandler(source));
						if(!fileName.equals(""))
							messageBodyFile.setFileName(fileName);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				// 5) create Multipart object and add MimeBodyPart objects to this
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				if(!fileName.equals("")){
					multipart.addBodyPart(messageBodyFile);
				}
				message.setContent(multipart);
				Transport.send(message);
				
				logger.info("sendAnnouncement() mail sent.");
			}
	
			/*}*/
	 catch (Exception ex) {
			logger.error("sendAnnouncement() Mails not sent ::"+ex.getMessage());
			ex.printStackTrace();
		} 
	}
	
	public String getNames(String emailAddresses){
		String name="";
		//System.out.println("emailAddresses ::"+emailAddresses);
		String getNames="SELECT USERNAME FROM tbl_trn_IIMStd WHERE IsActive =1 and EmailId ='"+emailAddresses+"'";
		System.out.println("sqlQuery::"+getNames);
		List<Map<String,Object>> rows=jdbcTemplate.queryForList(getNames);
		for(Map<String,Object> row:rows){
			name = (String) row.get("USERNAME");
		//	System.out.println("username:::::"+row.get("USERNAME"));
		}
		logger.info("getNames() emailAddresses ::"+emailAddresses+" name ::"+name);
		return name;
	}
	
	public String getMailPwd(String emailAddresses){
		String email_pwd="";
		String query="SELECT Password FROM tbl_mst_MailingEmailId WHERE IsActive =1 and EmailID ='"+emailAddresses+"'";
		System.out.println("sqlQuery::"+query);
		List<Map<String,Object>> rows=jdbcTemplate.queryForList(query);
		for(Map<String,Object> row:rows){
			email_pwd = (String) row.get("Password");
		}
		logger.info("getMailPwd() emailAddresses ::"+emailAddresses+" email_pwd ::"+email_pwd);
		return email_pwd;
	}
	
}
