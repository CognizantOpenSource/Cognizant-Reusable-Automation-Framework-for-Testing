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
