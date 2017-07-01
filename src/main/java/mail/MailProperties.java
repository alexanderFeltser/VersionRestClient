package mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class MailProperties {
	private FileInputStream fis;
	private Properties prop;
	final private String MAIL_PROPERTIES_FILE_NAME = "Config\\MailProp.xml";

	public Properties getMailProp() {
		return prop;
	}

	public MailProperties() {
		try {
			// System.out.println("Mail Properties created");
			File fl = new File(MAIL_PROPERTIES_FILE_NAME);
			if (!fl.exists()) {
				System.out.println("File " + MAIL_PROPERTIES_FILE_NAME + "does not exists");
				System.exit(0);
			}
			// System.out.println("StationProperties file exists");
			fis = new FileInputStream(fl);
			prop = new Properties();
			prop.loadFromXML(fis);
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("File " + MAIL_PROPERTIES_FILE_NAME + "does not exists");
			System.out.println(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Warning: Unable to close " + MAIL_PROPERTIES_FILE_NAME + " file.");
			System.out.println(e.getMessage());
		}
	}
}
