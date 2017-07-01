package mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

//@Service("mailSender")
@Component
public class MailAPI {

	// @Autowired
	private MailSender mailSender; // MailSender interface defines a strategy
	@Autowired // for sending simple mails
	private MailProperties mailProperties;

	public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
		mailSender = new JavaMailSenderImpl();

		((JavaMailSenderImpl) mailSender).setJavaMailProperties(setMailProperties());
		((JavaMailSenderImpl) mailSender).setHost(mailProperties.getMailProp().getProperty("host"));
		((JavaMailSenderImpl) mailSender).setProtocol(mailProperties.getMailProp().getProperty("protocol"));
		((JavaMailSenderImpl) mailSender).setUsername(mailProperties.getMailProp().getProperty("username"));
		((JavaMailSenderImpl) mailSender).setPassword(mailProperties.getMailProp().getProperty("pass"));
		((JavaMailSenderImpl) mailSender).setPort(Integer.parseInt(mailProperties.getMailProp().getProperty("port")));

		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(fromAddress);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		mailSender.send(crunchifyMsg);
	}

	Properties setMailProperties() {
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.starttls.required", "true");
		mailProperties.put("mail.smtp.debug", "true");
		return mailProperties;

	}

}