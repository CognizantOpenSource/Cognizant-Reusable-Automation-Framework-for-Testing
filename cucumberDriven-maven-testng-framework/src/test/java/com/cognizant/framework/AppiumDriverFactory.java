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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
	 * @param testParameters
	 *            Test parameters{@link SeleniumTestParameters}
	 * 
	 * @return Instance of the {@link AppiumDriver} object
	 */
	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case ANDROID:

				if (Boolean.parseBoolean(mobileProperties.getProperty("InstallApplicationInDevice"))) {
					desiredCapabilities.setCapability("app", mobileProperties.getProperty("AndroidApplicationPath"));
				}

				if (!Boolean.parseBoolean(mobileProperties.getProperty("ResetApp"))) {
					desiredCapabilities.setCapability("noReset", "true");
				}

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());

				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
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
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("iPhoneBundleID"));

				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("browserName", "Chrome");
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "iOS");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			default:

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return driver;

	}

	/**
	 * Function to return the object for RemoteWebDriver {@link RemoteWebDriver}
	 * object
	 * 
	 * @param testParameters
	 *            Test parameters{@link SeleniumTestParameters}
	 * 
	 * @return Instance of the {@link RemoteWebDriver} object
	 */
	public static RemoteWebDriver getRemoteWebDriver(SeleniumTestParameters testParameters) {

		RemoteWebDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (testParameters.getMobileExecutionPlatform()) {
			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("udid", testParameters.getDeviceName());
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("browserName", "Chrome");
				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("AppiumURL")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "iOS");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("udid", testParameters.getDeviceName());
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("AppiumURL")),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			default:

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return driver;

	}
}
