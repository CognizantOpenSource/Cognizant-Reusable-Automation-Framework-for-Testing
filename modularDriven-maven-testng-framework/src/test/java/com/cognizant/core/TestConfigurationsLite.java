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
package com.cognizant.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.ExecutionMode;
import com.cognizant.framework.selenium.MobileExecutionPlatform;
import com.cognizant.framework.selenium.SeleniumParametersBuilders;
import com.cognizant.framework.selenium.ToolName;

public class TestConfigurationsLite extends ModularTestCase {

	private int responseStatus;
	private HttpURLConnection httpURLConnect;

	@DataProvider(name = "ChromeBrowser")
	public Object[][] desktopBrowsers(Method currentMethod) {

		String[] currentPackageSplit = currentMethod.getDeclaringClass().getPackage().toString().split("testscripts.");
		currentScenario = currentPackageSplit[1];
		currentTestcase = currentMethod.getDeclaringClass().getSimpleName();

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() } };
	}

	@DataProvider(name = "WebAndriod")
	public Object[][] mobileDevice(Method currentMethod) {
		String[] currentPackageSplit = currentMethod.getDeclaringClass().getPackage().toString().split("testscripts.");
		currentScenario = currentPackageSplit[1];
		currentTestcase = currentMethod.getDeclaringClass().getSimpleName();

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance1")
						.executionMode(ExecutionMode.MOBILE).mobileExecutionPlatform(MobileExecutionPlatform.ANDROID)
						.toolName(ToolName.APPIUM).deviceName("4d005cb2c4938197").build() } };
	}

	@DataProvider(name = "ChromeParallel", parallel = true)
	public Object[][] desktopBrowsersParallel(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance1")
						.executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance2")
						.executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance3")
						.executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() } };
	}

	/**
	 * Function to check the All Broken Links available in the Page
	 * 
	 */
	protected void validateAllLinksInPage() {

		String url;
		int responseCode;

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				continue;
			}

			try {
				httpURLConnect = (HttpURLConnection) (new URL(url).openConnection());

				httpURLConnect.setRequestMethod("HEAD");

				httpURLConnect.connect();

				responseCode = httpURLConnect.getResponseCode();

				if (responseCode >= 400) {
					report.updateTestLog(url, "Response code : " + responseStatus + " - BROKEN", Status.WARNING);
				} else {
					report.updateTestLog(url, "Response code : " + responseStatus + " - OK", Status.DONE);
				}

			} catch (MalformedURLException e) {
				report.updateTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);

			} catch (IOException e) {
				report.updateTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);
			}
		}

	}

	/**
	 * Function to check the All Broken Image Links available in the Page
	 * 
	 */
	protected void validateAllImageLinksInPage() {

		String url;
		int responseCode;

		List<WebElement> links = driver.findElements(By.tagName("img"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				continue;
			}

			try {
				httpURLConnect = (HttpURLConnection) (new URL(url).openConnection());

				httpURLConnect.setRequestMethod("HEAD");

				httpURLConnect.connect();

				responseCode = httpURLConnect.getResponseCode();

				if (responseCode >= 400) {
					report.updateTestLog(url, "Response code : " + responseStatus + " - BROKEN", Status.WARNING);
				} else {
					report.updateTestLog(url, "Response code : " + responseStatus + " - OK", Status.DONE);
				}

			} catch (MalformedURLException e) {
				report.updateTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);

			} catch (IOException e) {
				report.updateTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);
			}
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

	
}