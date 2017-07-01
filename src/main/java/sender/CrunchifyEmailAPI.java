package sender;

import java.util.Properties;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

//@Service("mailSender")
@Component
public class CrunchifyEmailAPI {

	// @Autowired
	private MailSender crunchifymail; // MailSender interface defines a strategy
										// for sending simple mails

	public void crunchifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
		crunchifymail = new JavaMailSenderImpl();

		((JavaMailSenderImpl) crunchifymail).setJavaMailProperties(setMailProperties());
		((JavaMailSenderImpl) crunchifymail).setHost("smtp.gmail.com");
		// ((JavaMailSenderImpl) crunchifymail).setPort("465");
		// ((JavaMailSenderImpl) crunchifymail).setProtocol("smtp");

		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(fromAddress);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		crunchifymail.send(crunchifyMsg);
	}

	Properties setMailProperties() {
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.starttls.required", "true");
		mailProperties.put("mail.smtp.socketFactory.port", "465");
		mailProperties.put("mail.smtp.debug", "true");
		mailProperties.put("mail.smtp.socketFactory.class", "false");
		mailProperties.put("mail.smtp.socketFactory.fallback", "false");
		return mailProperties;

	}

}