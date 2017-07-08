package mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Component;

import loger.Log4J2XmlConf;

@Component
public class MailProperties {
	private FileInputStream fis;
	private Properties prop;
	final private String MAIL_PROPERTIES_FILE_NAME = "Config\\MailProp.xml";

	public Properties getMailProp() {
		return prop;
	}

	public MailProperties() {
		Logger logger = (Logger) Log4J2XmlConf.getLogger();
		try {
			// System.out.println("Mail Properties created");
			File fl = new File(MAIL_PROPERTIES_FILE_NAME);
			if (!fl.exists()) {
				logger.fatal("File " + MAIL_PROPERTIES_FILE_NAME + " does not exists");
				System.exit(0);
			}
			// System.out.println("StationProperties file exists");
			fis = new FileInputStream(fl);
			prop = new Properties();
			prop.loadFromXML(fis);
			fis.close();

		} catch (FileNotFoundException e) {
			logger.fatal("File " + MAIL_PROPERTIES_FILE_NAME + " does not exists: " + e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			logger.info("Warning: Unable to close " + MAIL_PROPERTIES_FILE_NAME + " file. : " + e.getMessage());

		}
	}
}
