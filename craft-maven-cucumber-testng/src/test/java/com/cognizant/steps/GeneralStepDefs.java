package com.cognizant.steps;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cognizant.framework.DriverManager;

import io.cucumber.java.en.Given;

public class GeneralStepDefs extends MasterSteps {

	static Logger log = Logger.getLogger(GeneralStepDefs.class);

	WebDriver driver = DriverManager.getWebDriver();

	@Given("^I am in the login page of the application$")
	public void i_am_in_login_page() {
		driver.get(properties.getProperty("ApplicationUrl"));

		attachScreenshotForWeb();

		assertTrue(driver.getTitle().contains("Welcome") || driver.getTitle().contains("Sign-on"));
	}
}