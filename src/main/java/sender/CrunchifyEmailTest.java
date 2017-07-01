package sender;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CrunchifyEmailTest {

	@SuppressWarnings("resource")
	public static void main1(String args[]) {

		// Spring Bean file you specified in /src/main/resources folder
		String emailConfFile = "mailProp.xml";// "crunchify-bean.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(emailConfFile);

		// @Service("crunchifyEmail") <-- same annotation you specified in
		// CrunchifyEmailAPI.java
		CrunchifyEmailAPI crunchifyEmailAPI = (CrunchifyEmailAPI) context.getBean("mailSender");
		String toAddr = "alexanderfster@gmail.com";
		String fromAddr = "alexanderfster@gmail.com";

		// email subject
		String subject = "Hey.. This email sent by Sash's Spring MVC Tutorial";

		// email body
		String body = "There you go.. You got an email.. Let's understand details on how Spring MVC works -- By Crunchify Admin";
		crunchifyEmailAPI.crunchifyReadyToSendEmail(toAddr, fromAddr, subject, body);
	}
}