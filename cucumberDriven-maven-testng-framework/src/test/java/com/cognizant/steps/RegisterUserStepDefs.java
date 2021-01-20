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

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.framework.DriverManager;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterUserStepDefs extends MasterSteps {

	static Logger log = Logger.getLogger(RegisterUserStepDefs.class);

	WebDriver driver = DriverManager.getWebDriver();

	@When("^I register a new user with the following details:$")
	public void registerUser(DataTable userData) {
		Map<String, String> newUser = userData.asMap(String.class, String.class);

		driver.findElement(By.linkText("REGISTER")).click();
		driver.findElement(By.name("firstName")).sendKeys(newUser.get("FirstName"));
		driver.findElement(By.name("lastName")).sendKeys(newUser.get("LastName"));
		driver.findElement(By.name("phone")).sendKeys(newUser.get("Phone"));
		driver.findElement(By.name("userName")).sendKeys(newUser.get("Email"));
		driver.findElement(By.name("address1")).sendKeys(newUser.get("Address"));
		driver.findElement(By.name("city")).sendKeys(newUser.get("City"));
		driver.findElement(By.name("state")).sendKeys(newUser.get("State"));
		driver.findElement(By.name("postalCode")).sendKeys(newUser.get("PostalCode"));
		driver.findElement(By.name("email")).sendKeys(newUser.get("Username"));
		driver.findElement(By.name("password")).sendKeys(newUser.get("Password"));
		driver.findElement(By.name("confirmPassword")).sendKeys(newUser.get("Password"));

		attachScreenshotForWeb();

		driver.findElement(By.name("register")).click();
	}

	@Then("^I should get a confirmation on successful registration$")
	public void i_should_get_registration_confirmation() {
		attachScreenshotForWeb();

		// assertTrue(driverUtil.isTextPresent("^[\\s\\S]*Thank you for
		// registering.[\\s\\S]*$"));
	}
}