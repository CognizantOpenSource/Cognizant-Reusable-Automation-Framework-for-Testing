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
package com.cognizant.framework;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener, ISuiteListener {

	static String resultFolder;
	Properties properties = Settings.getInstance();
	static Logger log = Logger.getLogger(TestNGListener.class);

	@Override
	public void onStart(ISuite suite) {

		if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
			resultFolder = TimeStamp.getInstance();
			TimeStamp.getScreenShotInstanace();
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
			copyReportsFolder();
		}
	}

	private void copyReportsFolder() {

		File sourceCucumber = new File(Util.getCucumberReportPath());
		File sourceExtent = new File(Util.getExtentReportPath());
		File destination = new File(resultFolder);

		String encryptedDestPath = WhitelistingPath
				.cleanStringForFilePath(destination + Util.getFileSeparator() + "CucumberReport");
		File destCucumPath = new File(encryptedDestPath);
		destCucumPath.mkdir();

		String encryptedExtentPath = WhitelistingPath
				.cleanStringForFilePath(destination + Util.getFileSeparator() + "ExtentReport");
		File destExtentPath = new File(encryptedExtentPath);
		destExtentPath.mkdir();

		try {
			FileUtils.copyDirectory(sourceCucumber, destCucumPath);
			FileUtils.copyDirectory(sourceExtent, destExtentPath);

			// try {
			// FileUtils.cleanDirectory(sourceCucumber);
			// } catch (Exception e) {
			//
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TimeStamp.reportPathWithTimeStamp = null;
	}

	@Override
	public void onTestStart(ITestResult result) {

		SeleniumTestParameters testParameters = new SeleniumTestParameters();
		try {
			setDefaultTestParameters(result.getTestContext(), testParameters);
			DriverManager.setTestParameters(testParameters);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void setDefaultTestParameters(ITestContext testContext, SeleniumTestParameters testParameters) {

		try {
			String executionMode = testContext.getCurrentXmlTest().getLocalParameters().get("ExecutionMode");
			String toolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");

			isMobileExecution(executionMode, toolName, testParameters);
			isAPIExecution(executionMode, toolName, testParameters);

			switch (executionMode) {

			case "API":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				break;

			case "LOCAL":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(
							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
				}

				break;

			case "GRID":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(
							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
				}

				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

				} else {
					testParameters.setBrowserVersion(
							testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
				}

				if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

				} else {
					testParameters
							.setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
				}

				break;

			case "MOBILE":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
					testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
				} else {
					String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
					testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
				}

				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileExecutionPlatform(
							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters
							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
				}

				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

				} else {
					testParameters
							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
				}

				break;

			default:

				testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowerName") == null) {
					testParameters.setBrowser(
							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowerName")));
				} else {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
				}
			}

		} catch (Exception ex) {
			log.error(
					"Error at settings TestParameters , please check the TestNG XML suite File and pass valid key & values"
							+ ex.getMessage());
		}
	}

	private void isAPIExecution(String executionMode, String toolName, SeleniumTestParameters testParameters) {

		testParameters.setAPIExecution(false);
		if (executionMode.equals(ExecutionMode.API.toString())) {
			testParameters.setAPIExecution(true);
		}
	}

	private void isMobileExecution(String executionMode, String toolName, SeleniumTestParameters testParameters) {

		testParameters.setMobileExecution(false);
		if (!(toolName == null)) {
			if (toolName.equals(ToolName.APPIUM.toString())) {
				testParameters.setMobileExecution(true);
			}
		}
	}

	/***
	 * 
	 * Unused Methods
	 * 
	 */

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
