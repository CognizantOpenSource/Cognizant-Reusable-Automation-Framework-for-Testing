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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.framework.DriverManager;
import com.cognizant.pages.AutomationEntryPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AutomationEntrySteDefs extends MasterSteps {

	WebDriver driver = DriverManager.getWebDriver();

	@Given("I Launch the Automation Entry Application")
	public void i_Launch_the_Automation_Entry_Application() {

		driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
		waitFor(3000);
		attachScreenshotForWeb();
	}

	@Given("Verify if home Page is displayed")
	public void verify_if_home_Page_is_displayed() {
		waitUntilElementLocated(AutomationEntryPage.homeLink, 30);
		attachScreenshotForWeb();
	}

	@Then("Enter the Details(.*) (.*) (.*)$")
	public void enter_the_Details_John_Harry(String firstName, String lastName, String country) {

		driver.findElement(AutomationEntryPage.txtFirstName).sendKeys(firstName);
		driver.findElement(AutomationEntryPage.txtLastName).sendKeys(lastName);

		driver.findElement(AutomationEntryPage.btnGender).click();

		driver.findElement(AutomationEntryPage.btnExp).click();

		driver.findElement(AutomationEntryPage.btnProf).click();

		driver.findElement(AutomationEntryPage.btnToolUFT).click();
		driver.findElement(AutomationEntryPage.btnToolProtractor).click();
		driver.findElement(AutomationEntryPage.btnToolSelenium).click();

		WebElement ele = driver.findElement(AutomationEntryPage.selectContinents);
		Select select = new Select(ele);
		select.selectByVisibleText(country);
		attachScreenshotForWeb();
	}

	@Then("Submit the Details")
	public void submit_the_Details() {
		driver.findElement(By.id("submit")).click();
		attachScreenshotForWeb();
	}

	/**
	 * Function to wait until the specified element is located
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
	}

}
