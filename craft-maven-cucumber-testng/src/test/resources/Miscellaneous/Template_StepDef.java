package com.cognizant.steps;

import org.openqa.selenium.WebDriver;

import com.cognizant.framework.DriverManager;

public class Template_StepDef extends MasterSteps {

	/***
	 * Use Below for Web Application Automation
	 * 
	 */

	WebDriver driver = DriverManager.getWebDriver();

	/***
	 * Use Below for Mobile Application Automation
	 * 
	 */

	// AppiumDriver driver = DriverManager.getAppiumDriver();

}
