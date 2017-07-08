package loger;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class Log4J2XmlConf {
	private static Logger logger;
	private final static String LOG_CONFIGURATION_FILE_NAME = "Config/log4j2-spring.xml";

	public static void seLogConfiguration() {
		LoggerContext context = (LoggerContext) LogManager.getContext(false);

		File file = new File("Config/log4j2-spring.xml");
		if (!file.exists()) {
			System.out.println("File " + LOG_CONFIGURATION_FILE_NAME + "does't exists");
		}

		context.setConfigLocation(file.toURI());
	}

	public static Logger getLogger() {
		if (logger == null) {
			seLogConfiguration();
			logger = LogManager.getLogger();
		}
		return logger;
	}

	public void performSomeTask() {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		logger.fatal("This is a fatal message");
	}

}