package com.cognizant.framework;

import java.io.File;
import java.util.Properties;

/**
 * Singleton class which manages the creation of timestamped result folders for
 * every test batch execution
 * 
 * @author Cognizant
 */
public class TimeStamp {
	private static volatile String reportPathWithTimeStamp;

	private TimeStamp() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the timestamped result folder path
	 * 
	 * @return The timestamped result folder path
	 */
	public static String getInstance() {
		if (reportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (reportPathWithTimeStamp == null) { // Double-checked locking
					FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

					if (frameworkParameters.getRelativePath() == null) {
						throw new FrameworkException("FrameworkParameters.relativePath is not set!");
					}
					if (frameworkParameters.getRunConfiguration() == null) {
						throw new FrameworkException("FrameworkParameters.runConfiguration is not set!");
					}

					Properties properties = Settings.getInstance();
					String timeStamp = "Run_" + Util.getCurrentFormattedTime(properties.getProperty("DateFormatString"))
							.replace(" ", "_").replace(":", "-");

					reportPathWithTimeStamp = frameworkParameters.getRelativePath() + Util.getFileSeparator()
							+ "Results" + Util.getFileSeparator() + frameworkParameters.getRunConfiguration()
							+ Util.getFileSeparator() + timeStamp;
					reportPathWithTimeStamp = WhitelistingPath.cleanStringForFilePath(reportPathWithTimeStamp);

					new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		}

		return reportPathWithTimeStamp;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}