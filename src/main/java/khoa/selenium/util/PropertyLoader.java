package khoa.selenium.util;

import java.io.IOException;
import java.util.Properties;

/*
 * Class that extracts properties from the prop file.
 *
 */
public enum PropertyLoader {
	INSTANCE;

	public static String loadProperty(String name) {
		String value = "";
		if (name != null) {
			value = INSTANCE.applicationProperties.getProperty(name);
		}
		return value;
	}

	PropertyLoader() {
		applicationProperties = new Properties();
		try {
			applicationProperties.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
		} catch (IOException e) {
			applicationProperties = null;
			e.printStackTrace();
		}
	}
	private static final String PROP_FILE = "/application.properties";
	private Properties applicationProperties ;
}