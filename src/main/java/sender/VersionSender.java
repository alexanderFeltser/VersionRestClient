package sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//import AutotopClient.rest_client.MyVersionCreator;
import entities.AutotopVersion;
import entities.Url;
import mail.MailAPI;
import main.StationProperties;

@Component
public class VersionSender {
	@Autowired
	private StationProperties stationProperties;

	@Autowired
	private MailAPI emailAPI;
	private static Logger logger = LogManager.getLogger();
	// @Autowired
	// private RestTemplate restTemplate1;// = new RestTemplate();

	private final String MAIL_PROPERTIES_FILE_NAME = "..\\Config\\mailProp.xml";

	// private String serverUrl = "http://localhost:8080//gotversion";
	public VersionSender() {

	}

	public boolean sendVersion(String s) {
		RestTemplate restTemplate = new RestTemplate();

		String url = stationProperties.getDbProp().getProperty("url");
		AutotopVersion request = new AutotopVersion(stationProperties.getDbProp().getProperty("stationName"), s,
				"User");
		Url retUrl = null;

		try {
			retUrl = restTemplate.postForObject(url, request, Url.class);
		} catch (RestClientException e) {
			logger.fatal("Error sending version to server " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		// System.out.println(retUrl.getUrl());
		if (retUrl.getUrl().equals("/gotversion")) {
			logger.info("Version sended  to server " + retUrl.getUrl());
			return true;
		} else {
			logger.info("Version not sended  to server " + retUrl.getUrl());
			return false;
		}
	}

	public void sendEmail(String s) {
		if (emailAPI == null) {
			logger.info("Email Service does't exists unable to send mail");
			return;
		}

		// Spring Bean file you specified in folder

		// ConfigurableApplicationContext context = new
		// ClassPathXmlApplicationContext(MAIL_PROPERTIES_FILE_NAME);
		// emailAPI = context.getBean(CrunchifyEmailAPI.class);

		// @Service("crunchifyEmail") <-- same annotation you specified in
		// CrunchifyEmailAPI.java

		String toAddr = "alexanderfster@gmail.com";
		String fromAddr = "alexanderfster@gmail.com";

		// email subject
		String subject = "Hey.. This email sent by Sash's Spring MVC Tutorial";

		// email body
		String body = s;// "There you go.. You got an email.. Let's understand
						// details on how Spring MVC works -- By Crunchify
						// Admin";
		emailAPI.sendEmail(toAddr, fromAddr, subject, body);

	}

	public static void main(String[] args) {
		VersionSender vSender = new VersionSender();

		// VersionInfoCreator versionCreator = new MyVersionCreator();

		// System.out.println(versionCreator.getVersionString());
		if (vSender.sendVersion("version"/* versionCreator.getVersionString() */)) {
			System.out.println("Version does't send");
		}
		;
	}
}
