package mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.project.email" }, basePackageClasses = MailTest.class)
public class MailTest {
	@Autowired
	private ApplicationContext ctx;

	public static void main(String args[]) {

		SpringApplication.run(MailTest.class, args);
		ApplicationContext ctx = MyApplicationContextHolder.getApplicationContext();

		// System.out.println("Context :" + ctx.toString());
		MailAPI myEmailAPI = ctx.getBean(MailAPI.class);
		String toAddr = "alexanderfster@gmail.com";
		String fromAddr = "alexanderfster@gmail.com";

		// email subject
		String subject = "Hey.. This email sent by Sash's Spring MVC Tutorial";

		// email body
		String body = "There you go.. You got an email.. Let's understand details on how Spring MVC works -- By Crunchify Admin";
		myEmailAPI.sendEmail(toAddr, fromAddr, subject, body);
	}

}
