package main;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import AutotopClient.rest_client.MyVersionCreator;
import interfaces.VersionInfoCreator;
import mail.MailAPI;
import mail.MyApplicationContextHolder;
import sender.VersionSender;

//"sender", "interfaces", "entities"
@Component
@SpringBootApplication
@ComponentScan(basePackages = { "sender", "interfaces", "entities", "main", "mail",
		"org.springframework.web.client" }, basePackageClasses = Main.class)
public class Main {
	@Autowired
	private VersionSender vSender;
	@Autowired
	private MailAPI mailSender;

	// @Autowired
	// private RestTemplate restTemplate;

	public Main() {

	}

	public void run() {
		VersionInfoCreator vrscreator = new MyVersionCreator();
		if (vSender.sendVersion(vrscreator.getVersionString())) {
			mailSender.sendEmail("alexanderfster@gmail.com", "alexanderfster@gmail.com", "Version   sended",
					vrscreator.getVersionString());

			// mailSender.sendEmail(setLikeMail(vrscreator.getServerName()),
			// setLikeMail(vrscreator.getServerName()),
			// "Version sended", vrscreator.getVersionString());
		} else {

			mailSender.sendEmail("alexanderfster@gmail.com", setLikeMail(vrscreator.getServerName()),
					"Version   NOT SENDED", vrscreator.getVersionString());
		}

	}

	public String setLikeMail(String s) {
		return (s.replaceAll("\\s+", "") + "@menora.co.il");
	}

	public static void printCreateedBeans(ApplicationContext ctx) {
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("  >>>>    Beans created: ");
		System.out.println(" ");
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		ApplicationContext ctx = MyApplicationContextHolder.getApplicationContext();
		// printCreateedBeans(ctx);
		// RestTemplate restTemplate = ctx.getBean(RestTemplate.class);

		// System.out.println("RestTemplate :" + restTemplate.toString());
		Main m = ctx.getBean(Main.class);
		m.run();

		// CrunchifyEmailAPI crunchifyEmailAPI =
		// ctx.getBean(CrunchifyEmailAPI.class);
	}
}
