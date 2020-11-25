package com.cognizant.framework.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Settings;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class AppiumDriverFactory {

	private static Properties mobileProperties;

	private AppiumDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the object for AppiumDriver {@link AppiumDriver}
	 * object
	 * 
	 * @param executionPlatform
	 *            executionPlatform{@link MobileExecutionPlatform}
	 * @param deviceName
	 *            The deviceName
	 * @param version
	 *            The Mobile Device OS Version
	 * @param installApp
	 *            Boolean to install App
	 * @param appiumURL
	 *            URL of the Appium
	 * 
	 * @return Instance of the {@link AppiumDriver} object
	 */
	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp, String appiumURL) {

		AppiumDriver driver = null;
		mobileProperties = Settings.getMobilePropertiesInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				if (Boolean.parseBoolean(mobileProperties.getProperty("InstallApplicationInDevice"))) {
					desiredCapabilities.setCapability("app", mobileProperties.getProperty("AndroidApplicationPath"));
				}

				if (!Boolean.parseBoolean(mobileProperties.getProperty("ResetApp"))) {
					desiredCapabilities.setCapability("noReset", "true");
				}

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("udid", deviceName);
				desiredCapabilities.setCapability("platformVersion", version);

				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				try {
					driver = new AndroidDriver(new URL(appiumURL), desiredCapabilities);

				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The android driver invokation has problem, please re-check the capabilities or Start Appium");
				}
				break;

			case IOS:

				if (Boolean.parseBoolean(mobileProperties.getProperty("InstallApplicationInDevice"))) {
					desiredCapabilities.setCapability("app", mobileProperties.getProperty("iPhoneApplicationPath"));
				}

				if (!Boolean.parseBoolean(mobileProperties.getProperty("ResetApp"))) {
					desiredCapabilities.setCapability("noReset", "true");
				}

				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("platformName", "iOS");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("udid", deviceName);
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("iPhoneBundleID"));

				try {
					driver = new IOSDriver(new URL(appiumURL), desiredCapabilities);

				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The IOS driver invokation has problem, please re-check the capabilities or Start Appium");
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("udid", deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("browserName", "Chrome");
				try {
					driver = new AndroidDriver(new URL(appiumURL), desiredCapabilities);
				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The android driver invokation has problem, please check the capabilities or Start Appium");
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "iOS");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("udid", deviceName);
				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new IOSDriver(new URL(appiumURL), desiredCapabilities);

				} catch (MalformedURLException e) {
					throw new FrameworkException(
							"The IOS driver invokation has problem, please check the capabilities or Start Appium");
				}
				break;

			default:
				throw new FrameworkException("Unhandled ExecutionMode!");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FrameworkException(
					"The appium driver invocation created a problem , please check the capabilities");
		}

		return driver;

	}
}
