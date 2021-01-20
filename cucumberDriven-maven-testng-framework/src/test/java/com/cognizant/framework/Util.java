/*
 *  © [2020] Cognizant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognizant.framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to encapsulate utility functions of the framework
 * 
 * @author Cognizant
 */
public class Util {

	private Util() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to get the separator string to be used for directories and files
	 * based on the current OS
	 * 
	 * @return The file separator string
	 */
	public static String getFileSeparator() {
		return File.separator;
	}

	/**
	 * Function to get the Absolute Path
	 * 
	 * @return The AbsolutePath in String
	 */
	public static String getAbsolutePath() {
		String encryptedPath = WhitelistingPath.cleanStringForFilePath(System.getProperty("user.dir"));
		String relativePath = new File(encryptedPath).getAbsolutePath();
		return relativePath;
	}

	/**
	 * Function to get the Result Path
	 * 
	 * @return The ResultPath in String
	 */
	public static String getResultsPath() {
		String encryptedResultPath = WhitelistingPath
				.cleanStringForFilePath(Util.getAbsolutePath() + Util.getFileSeparator() + "Results");
		File path = new File(encryptedResultPath);
		if (!path.isDirectory()) {
			path.mkdirs();
		}

		return path.toString();
	}

	/**
	 * Function to get the Target Path
	 * 
	 * @return The TargetPath in String
	 */
	public static String getCucumberReportPath() {

		String encryptedCucumberPath = WhitelistingPath.cleanStringForFilePath(Util.getAbsolutePath()
				+ Util.getFileSeparator() + "target" + Util.getFileSeparator() + "cucumber-report");
		File targetPath = new File(encryptedCucumberPath);

		return targetPath.toString();
	}

	/**
	 * Function to get the Extent Report Path
	 * 
	 * @return The Extent Report Path in String
	 */
	public static String getExtentReportPath() {

		String encryptedTargetPath = WhitelistingPath.cleanStringForFilePath(Util.getAbsolutePath()
				+ Util.getFileSeparator() + "test-output" + Util.getFileSeparator() + "HtmlReport");
		File targetPath = new File(encryptedTargetPath);

		return targetPath.toString();
	}

	/**
	 * Function to take screenShot for WebDriver {@link WebDriver}
	 * 
	 * @return The Byte[]
	 */
	public static byte[] takeScreenshot(WebDriver driver) {
		if (driver == null) {
			throw new RuntimeException("Report.driver is not initialized!");
		}

		if (driver.getClass().getSimpleName().equals("HtmlUnitDriver") || driver.getClass().getGenericSuperclass()
				.toString().equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
			return null; // Screenshots not supported in headless mode
		}

		if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
			Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
			if (capabilities.getBrowserName().equals("htmlunit")) {
				return null; // Screenshots not supported in headless mode
			}
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
		} else {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	}

	/**
	 * Function to take screenShot for WebDriver {@link WebDriver}
	 * 
	 * @return The screenshotFile Path as String
	 */
	public static String takeScreenshotFile(WebDriver driver) {

		String screenShotPath = null;
		try {
			if (driver == null) {
				throw new RuntimeException("Report.driver is not initialized!");
			}

			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

			screenShotPath = copyFile(scrFile).toString();
		} catch (Exception e) {

		}
		return screenShotPath;
	}

	private static File copyFile(File scrFile) {

		File screenShotPath = null;

		if (TimeStamp.reportPathScreenShot == null) {

			String encryptedPath = WhitelistingPath
					.cleanStringForFilePath(Util.getAbsolutePath() + Util.getFileSeparator() + "Screenshots");
			File newPath = new File(encryptedPath);
			if (!newPath.isDirectory()) {
				newPath.mkdir();
			}
			String encyptedscreenshot = WhitelistingPath.cleanStringForFilePath(
					newPath + Util.getFileSeparator() + RandomStringUtils.randomAlphanumeric(16) + ".png");
			screenShotPath = new File(encyptedscreenshot);

		} else {
			String encyptedscreenshot = WhitelistingPath.cleanStringForFilePath(
					TimeStamp.reportPathScreenShot + Util.getFileSeparator()
					+ RandomStringUtils.randomAlphanumeric(16) + ".png");
			screenShotPath = new File(encyptedscreenshot);
		}

		try {
			FileUtils.copyFile(scrFile, new File(screenShotPath.toString()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenShotPath;
	}

	/**
	 * Function to return the current time
	 * 
	 * @return The current time
	 * @see #getCurrentFormattedTime(String)
	 */
	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * Function to return the current time, formatted as per the DateFormatString
	 * setting
	 * 
	 * @param dateFormatString The date format string to be applied
	 * @return The current time, formatted as per the date format string specified
	 * @see #getCurrentTime()
	 * @see #getFormattedTime(Date, String)
	 */
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * Function to format the given time variable as specified by the
	 * DateFormatString setting
	 * 
	 * @param time             The date/time variable to be formatted
	 * @param dateFormatString The date format string to be applied
	 * @return The specified date/time, formatted as per the date format string
	 *         specified
	 * @see #getCurrentFormattedTime(String)
	 */
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}

	/**
	 * Function to get the time difference between 2 {@link Date} variables in
	 * minutes/seconds format
	 * 
	 * @param startTime The start time
	 * @param endTime   The end time
	 * @return The time difference in terms of hours, minutes and seconds
	 */
	public static String getTimeDifference(Date startTime, Date endTime) {
		long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000; // to
																						// convert
																						// from
																						// milliseconds
																						// to
																						// seconds
		long timeDifferenceMinutes = timeDifferenceSeconds / 60;

		String timeDifferenceDetailed;
		if (timeDifferenceMinutes >= 60) {
			long timeDifferenceHours = timeDifferenceMinutes / 60;

			timeDifferenceDetailed = Long.toString(timeDifferenceHours) + " hour(s), "
					+ Long.toString(timeDifferenceMinutes % 60) + " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		} else {
			timeDifferenceDetailed = Long.toString(timeDifferenceMinutes) + " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		}

		return timeDifferenceDetailed;
	}
}