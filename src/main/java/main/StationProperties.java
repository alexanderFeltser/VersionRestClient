package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class StationProperties {
	private FileInputStream fis;
	private Properties prop;
	final private String STATION_PROPERTIES_FILE_NAME = "Config\\StationProp.xml";

	public Properties getDbProp() {
		return prop;
	}

	public StationProperties() {
		try {

			File fl = new File(STATION_PROPERTIES_FILE_NAME);
			if (!fl.exists()) {
				System.out.println("File " + STATION_PROPERTIES_FILE_NAME + "does not exists");
				System.exit(0);
			}
			// System.out.println("StationProperties file exists");
			fis = new FileInputStream(fl);
			prop = new Properties();
			prop.loadFromXML(fis);
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("File " + STATION_PROPERTIES_FILE_NAME + "does not exists");
			System.out.println(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Warning: Unable to close " + STATION_PROPERTIES_FILE_NAME + " file.");
			System.out.println(e.getMessage());
		}
	}
}
