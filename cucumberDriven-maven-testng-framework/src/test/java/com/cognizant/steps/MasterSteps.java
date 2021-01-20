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

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cognizant.framework.DriverManager;
import com.cognizant.framework.Settings;
import com.cognizant.framework.Util;

public class MasterSteps {

	protected Properties properties = Settings.getInstance();

	// Reusable Functions to Take ScreenShots

	/**
	 * Function to take screenshot & attach it to Cucumber Default Reports &
	 * Extent for Web Application with WebDriver
	 * 
	 */

	protected void attachScreenshotForWeb() {

		try {
			DriverManager.getTestParameters().getScenario().embed(Util.takeScreenshot(DriverManager.getWebDriver()),
					"image/png");
			ExtentCucumberAdapter
					.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(DriverManager.getWebDriver()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function to take screenshot & attach it to Cucumber Default Reports &
	 * Extent for Mobile Native Application with AppiumDriver
	 * 
	 */
	protected void attachScreenshotForMobile() {

		try {
			DriverManager.getTestParameters().getScenario().embed(Util.takeScreenshot(DriverManager.getAppiumDriver()),
					"image/png");
			ExtentCucumberAdapter
					.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(DriverManager.getAppiumDriver()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Selenium Reusable Methods

	/**
	 * Function to pause the execution for the specified time period
	 * 
	 * @param milliSeconds
	 *            The wait time in milliseconds
	 */
	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to write step Log into the Extent Reports
	 * 
	 * @param stepDescription
	 *            - Step Description
	 */
	protected void addStepLog(String message) {
		ExtentCucumberAdapter.addTestStepLog(message);
	}

	/**
	 * Function to wait until the page loads completely
	 * 
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilPageLoaded(long timeOutInSeconds) {
		WebElement oldPage = DriverManager.getWebDriver().findElement(By.tagName("html"));

		(new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds))
				.until(ExpectedConditions.stalenessOf(oldPage));

	}

	/**
	 * Function to wait until the page readyState equals 'complete'
	 * 
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilPageReadyStateComplete(long timeOutInSeconds) {
		ExpectedCondition<Boolean> pageReadyStateComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		(new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds)).until(pageReadyStateComplete);
	}

	/**
	 * Function to wait until the specified element is located
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds))
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Function to wait until the specified element is visible
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementVisible(By by, long timeOutInSeconds) {
		(new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds))
				.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Function to wait until the specified element is enabled
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementEnabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds))
				.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * Function to wait until the specified element is disabled
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementDisabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds))
				.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
	}

	/**
	 * Function to select the specified value from a listbox
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the listbox
	 * @param item
	 *            The value to be selected within the listbox
	 */
	public void selectListItem(By by, String item) {
		Select dropDownList = new Select(DriverManager.getWebDriver().findElement(by));
		dropDownList.selectByVisibleText(item);
	}

	/**
	 * Function to do a mouseover on top of the specified element
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 */
	public void mouseOver(By by) {
		Actions actions = new Actions(DriverManager.getWebDriver());
		actions.moveToElement(DriverManager.getWebDriver().findElement(by)).build().perform();
	}

	/**
	 * Function to verify whether the specified object exists within the current
	 * page
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @return Boolean value indicating whether the specified object exists
	 */
	public Boolean objectExists(By by) {
		return !DriverManager.getWebDriver().findElements(by).isEmpty();
	}

	/**
	 * Function to verify whether the specified text is present within the
	 * current page
	 * 
	 * @param textPattern
	 *            The text to be verified
	 * @return Boolean value indicating whether the specified test is present
	 */
	public Boolean isTextPresent(String textPattern) {
		return DriverManager.getWebDriver().findElement(By.cssSelector("BODY")).getText().matches(textPattern);
	}

	/**
	 * Function to check if an alert is present on the current page
	 * 
	 * @param timeOutInSeconds
	 *            The number of seconds to wait while checking for the alert
	 * @return Boolean value indicating whether an alert is present
	 */
	public Boolean isAlertPresent(long timeOutInSeconds) {
		try {
			new WebDriverWait(DriverManager.getWebDriver(), timeOutInSeconds)
					.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

}
