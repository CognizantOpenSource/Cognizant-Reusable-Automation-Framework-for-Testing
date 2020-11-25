package com.cognizant.steps;

import org.apache.log4j.Logger;

import com.cognizant.framework.DriverManager;
import com.cognizant.pages.EriBankPage;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EribankLogin extends MasterSteps {

	static Logger log = Logger.getLogger(EribankLogin.class);
	@SuppressWarnings("rawtypes")
	AppiumDriver driver = DriverManager.getAppiumDriver();
	EriBankPage eribankPage = new EriBankPage();

	@Given("^I launch eribank$")
	public void i_launch_eribank() throws Throwable {
		if (eribankPage.txtUsername.isDisplayed()) {
			addStepLog("Launched Sucessfully");
		}
		attachScreenshotForMobile();
	}

	@When("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_enter_and(String userName, String password) throws Throwable {
		eribankPage.txtUsername.sendKeys(userName);
		eribankPage.txtPassword.sendKeys(password);
		attachScreenshotForMobile();
	}

	@Then("^I click LOGIN$")
	public void i_click_LOGIN() throws Throwable {
		eribankPage.btnLogin.click();
		attachScreenshotForMobile();
	}

	@Then("^I click LOGOUT$")
	public void i_click_LOGOUT() throws Throwable {
		eribankPage.btnLogout.click();
		attachScreenshotForMobile();
	}
}
