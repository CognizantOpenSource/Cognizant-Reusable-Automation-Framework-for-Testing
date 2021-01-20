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

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.cucumber.core.api.Scenario;

public class TestHarness {

	/**
	 * Constructor to initialize the {@link TestHarness} object
	 */
	public TestHarness() {

	}

	@SuppressWarnings("rawtypes")
	public void invokeDriver(Scenario scenario) {

		WebDriver driver;
		AppiumDriver appiumDriver;

		SeleniumTestParameters currentTestParameters = DriverManager.getTestParameters();

		switch (currentTestParameters.getExecutionMode()) {

		case API:
			break;
		case LOCAL:
		case GRID:
			driver = DriverFactory.createWebDriverInstance(currentTestParameters);
			DriverManager.setWebDriver(driver);
			break;

		case MOBILE:
			appiumDriver = DriverFactory.createAppiumInstance(currentTestParameters);
			DriverManager.setAppiumDriver(appiumDriver);
			break;

		default:
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void closeRespestiveDriver(Scenario scenario) {

		SeleniumTestParameters testParameters = DriverManager.getTestParameters();
		if (testParameters.isMobileExecution()) {
			AppiumDriver driver = DriverManager.getAppiumDriver();
			if (driver != null) {
				driver.quit();

			}
		} else if (!testParameters.isAPIExecution()) {
			WebDriver driver = DriverManager.getWebDriver();
			if (driver != null) {
				driver.quit();
			}
		}

	}

}
