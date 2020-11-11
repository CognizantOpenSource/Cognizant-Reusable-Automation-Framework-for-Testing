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
