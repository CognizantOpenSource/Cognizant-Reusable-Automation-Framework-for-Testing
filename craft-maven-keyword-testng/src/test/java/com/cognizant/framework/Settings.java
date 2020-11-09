package com.cognizant.framework;

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
	private static Properties mobilePropertics = loadFromPropertiesFileForMobile();

	private Settings() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the singleton instance of the Global Properties
	 * {@link Properties} object
	 * 
	 * @return Instance of the {@link Properties} object
	 */
	public static Properties getInstance() {
		return properties;
	}

	/**
	 * Function to return the singleton instance of the Mobile Properties
	 * {@link Properties} object
	 * 
	 * @return Instance of the {@link Properties} object
	 */
	public static Properties getMobilePropertiesInstance() {
		return mobilePropertics;
	}

	private static Properties loadFromPropertiesFile() {
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

		if (frameworkParameters.getRelativePath() == null) {
			throw new FrameworkException("FrameworkParameters.relativePath is not set!");
		}

		Properties properties = new Properties();

		try {
			String encryptedGlobalPropertiesPath = WhitelistingPath
					.cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src"
							+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources"
							+ Util.getFileSeparator() + "Global Settings.properties");
			properties.load(new FileInputStream(encryptedGlobalPropertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("FileNotFoundException while loading the Global Settings file");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("IOException while loading the Global Settings file");
		}

		return properties;
	}

	private static Properties loadFromPropertiesFileForMobile() {
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

		if (frameworkParameters.getRelativePath() == null) {
			throw new FrameworkException("FrameworkParameters.relativePath is not set!");
		}

		Properties properties = new Properties();

		try {
			String encryptedMobilePropertiesPath = WhitelistingPath
					.cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src"
							+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources"
							+ Util.getFileSeparator() + "Mobile Automation Settings.properties");
			properties.load(new FileInputStream(encryptedMobilePropertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("FileNotFoundException while loading the Mobile Automation Settings file");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("IOException while loading the Mobile Automation Settings file");
		}

		return properties;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}