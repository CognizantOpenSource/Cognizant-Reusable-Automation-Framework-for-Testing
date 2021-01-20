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

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

/**
 * DriverFactory which will create respective driver Object
 * 
 * @author Cognizant
 */
public class DriverFactory {

	static Logger log = Logger.getLogger(DriverFactory.class);
	private static Properties properties = Settings.getInstance();

	/**
	 * Function to return the object for WebDriver {@link WebDriver} object
	 * 
	 * @param testParameters
	 * 
	 * @return Instance of the {@link WebDriver} object
	 */
	public static WebDriver createWebDriverInstance(SeleniumTestParameters testParameters) {
		WebDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case LOCAL:
				driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
				break;

			case GRID:
				driver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser(),
						testParameters.getBrowserVersion(), testParameters.getPlatform(),
						properties.getProperty("RemoteUrl"));
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return driver;
	}

	/**
	 * Function to return the object for AppiumDriver {@link AppiumDriver} object
	 * 
	 * @param testParameters
	 * 
	 * @return Instance of the {@link AppiumDriver} object
	 */
	@SuppressWarnings("rawtypes")
	public static AppiumDriver createAppiumInstance(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case MOBILE:

				driver = AppiumDriverFactory.getAppiumDriver(testParameters);
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return driver;
	}
}