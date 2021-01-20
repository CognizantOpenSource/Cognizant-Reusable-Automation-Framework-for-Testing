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
package com.cognizant.framework.selenium;

import org.openqa.selenium.Platform;

public class SeleniumParametersBuilders {

	private final SeleniumTestParameters testParameters;

	/**
	 * Constructor to initialize the {@link SeleniumParametersBuilders} object
	 * 
	 * @param currentScenario
	 *            The current Scenario
	 * @param currentTestcase
	 *            The current Test case
	 */
	public SeleniumParametersBuilders(String currentScenario, String currentTestcase) {
		this.testParameters = new SeleniumTestParameters(currentScenario, currentTestcase);
	}

	/**
	 * Function to set the test instance
	 * 
	 * @param testInstance
	 *            The test instance
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders testInstance(String testInstance) {
		this.testParameters.setCurrentTestInstance(testInstance);
		return this;
	}

	/**
	 * Function to set the {@link ExecutionMode}
	 * 
	 * @param executionMode
	 *            The {@link ExecutionMode}
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders executionMode(ExecutionMode executionMode) {
		this.testParameters.setExecutionMode(executionMode);
		return this;
	}

	/**
	 * Function to set the {@link Browser}
	 * 
	 * @param browser
	 *            The {@link Browser}
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders browser(Browser browser) {
		this.testParameters.setBrowser(browser);
		return this;
	}

	/**
	 * Function to set the browser version
	 * 
	 * @param browserVersion
	 *            The browser version
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders browserVersion(String browserVersion) {
		this.testParameters.setBrowserVersion(browserVersion);
		return this;
	}

	/**
	 * Function to set the {@link Platform}
	 * 
	 * @param platform
	 *            The {@link Platform}
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders platform(Platform platform) {
		this.testParameters.setPlatform(platform);
		return this;
	}

	/**
	 * Function to set the device name
	 * 
	 * @param deviceName
	 *            The device name
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders deviceName(String deviceName) {
		this.testParameters.setDeviceName(deviceName);
		return this;
	}

	/**
	 * Function to set the Mobile Execution Platform
	 * 
	 * @param mobileExecutionPlatform
	 *            The device name
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders mobileExecutionPlatform(MobileExecutionPlatform mobileExecutionPlatform) {
		this.testParameters.setMobileExecutionPlatform(mobileExecutionPlatform);
		return this;
	}

	/**
	 * Function to set the ToolName
	 * 
	 * @param toolName
	 *            The Tool Name
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders toolName(ToolName toolName) {
		this.testParameters.setMobileToolName(toolName);
		return this;
	}

	/**
	 * Function to set the device name
	 * 
	 * @param mobileOsVersion
	 *            The Mobile OS version
	 * @return The current {@link SeleniumParametersBuilders} object
	 */
	public SeleniumParametersBuilders mobileOsVersion(String mobileOsVersion) {
		this.testParameters.setmobileOSVersion(mobileOsVersion);
		return this;
	}

	/**
	 * Function to build the {@link SeleniumTestParameters} object
	 * 
	 * @return The {@link SeleniumTestParameters} object
	 */
	public SeleniumTestParameters build() {
		return this.testParameters;
	}

}
