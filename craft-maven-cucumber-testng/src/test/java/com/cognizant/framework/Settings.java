package com.cognizant.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton class that encapsulates the user settings specified in the
 * properties file of the framework
 * 
 * @author Cognizant
 */
public class Settings {
	private static Properties properties = loadFromPropertiesFile();

	private Settings() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the singleton instance of the {@link Properties} object
	 * 
	 * @return Instance of the {@link Properties} object
	 */
	public static Properties getInstance() {
		return properties;
	}

	private static Properties loadFromPropertiesFile() {
		Properties properties = new Properties();
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"
				+ Util.getFileSeparator() + "resources";

		try {
			properties.load(new FileInputStream(relativePath + Util.getFileSeparator() + "Global Settings.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}