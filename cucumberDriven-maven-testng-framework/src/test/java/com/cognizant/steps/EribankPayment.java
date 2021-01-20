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
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EribankPayment extends MasterSteps {

	static Logger log = Logger.getLogger(EribankPayment.class);
	@SuppressWarnings("rawtypes")
	AppiumDriver driver = DriverManager.getAppiumDriver();
	EriBankPage eribankPage = new EriBankPage();

	@When("^I click Make Payment$")
	public void i_click_Make_Payment() throws Throwable {
		eribankPage.btnMakePayment.click();
	}

	@When("^I enter Phone number as \"([^\"]*)\" name as \"([^\"]*)\" Amount as \"([^\"]*)\"$")
	public void i_enter_Phone_number_as_name_as_Amount_as(String phone, String name, String amount) throws Throwable {
		eribankPage.txtPhone.sendKeys(phone);
		eribankPage.txtName.sendKeys(name);
		eribankPage.txtAmount.sendKeys(amount);
	}

	@Then("^I select country as \"([^\"]*)\"$")
	public void i_select_country_as(String country) throws Throwable {
		driver.hideKeyboard();
		eribankPage.btnCountrySelect.click();
		eribankPage.btnCountryValue.click();
	}

	@Then("^click Send Payment$")
	public void click_Send_Payment() throws Throwable {
		eribankPage.btnSendPayment.click();
		eribankPage.btnYes.click();
		driver.navigate().back();
	}
}
