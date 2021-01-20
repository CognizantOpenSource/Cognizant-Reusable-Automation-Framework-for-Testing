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

import org.openqa.selenium.Platform;

import io.cucumber.core.api.Scenario;

/**
 * Class to encapsulate various input parameters required for each test script
 * 
 * @author Cognizant
 */
public class SeleniumTestParameters {

	private ExecutionMode executionMode;
	private Browser browser;
	private String browserVersion;
	private Platform platform;
	private String platformVersion;
	private String deviceName;
	private MobileExecutionPlatform mobileExecutionPlatform;
	private ToolName mobileToolName;
	private String mobileOsVersion;
	private String manufacturer;
	private String modelName;
	private boolean isDeviceUdid;
	private String scenarioName;

	private boolean isMobileExecution;
	private boolean isAPIExecution;
	
	private Scenario scenario;

	public SeleniumTestParameters() {

	}

	/**
	 * Function to get the {@link ExecutionMode} for the test being executed
	 * 
	 * @return The {@link ExecutionMode} for the test being executed
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * Function to set the {@link ExecutionMode} for the test being executed
	 * 
	 * @param executionMode
	 *            The {@link ExecutionMode} for the test being executed
	 */
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	/**
	 * Function to get the {@link MobileExecutionPlatform} for the test being
	 * executed
	 * 
	 * @return The {@link MobileExecutionPlatform} for the test being executed
	 */
	public MobileExecutionPlatform getMobileExecutionPlatform() {
		return mobileExecutionPlatform;
	}

	/**
	 * Function to set the {@link MobileExecutionPlatform} for the test being
	 * executed
	 * 
	 * @param executionPlatform
	 *            The {@link MobileExecutionPlatform} for the test being
	 *            executed
	 */
	public void setMobileExecutionPlatform(MobileExecutionPlatform mobileExecutionPlatform) {
		this.mobileExecutionPlatform = mobileExecutionPlatform;
	}

	/**
	 * Function to get the {@link ToolName} for the test being executed
	 * 
	 * @return The {@link ToolName} for the test being executed
	 */
	public ToolName getMobileToolName() {
		return mobileToolName;
	}

	/**
	 * Function to set the {@link ToolName} for the test being executed
	 * 
	 * @param toolName
	 *            The {@link ToolName} for the test being executed
	 */
	public void setMobileToolName(ToolName mobileToolName) {
		this.mobileToolName = mobileToolName;
	}

	/**
	 * Function to get the {@link Browser} on which the test is to be executed
	 * 
	 * @return The {@link Browser} on which the test is to be executed
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * Function to set the {@link Browser} on which the test is to be executed
	 * 
	 * @param browser
	 *            The {@link Browser} on which the test is to be executed
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * Function to get the OS Version of device on which the test is to be
	 * executed
	 * 
	 * @return The OS Version of device Version on which the test is to be
	 *         executed
	 */
	public String getMobileOSVersion() {
		return mobileOsVersion;
	}

	/**
	 * Function to set the OS Version of device Version on which the test is to
	 * be executed
	 * 
	 * @param version
	 *            The OS Version of device Version on which the test is to be
	 *            executed
	 */
	public void setmobileOSVersion(String mobileOsVersion) {
		this.mobileOsVersion = mobileOsVersion;
	}

	/**
	 * Function to get the name of the mobile device on which the test is to be
	 * executed
	 * 
	 * @return The name of the mobile device on which the test is to be executed
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * 
	 * @param deviceName
	 *            The name of the mobile device on which the test is to be
	 *            executed
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * Function to get the name of the manufacturer Name on which the test is to
	 * be executed
	 * 
	 * @return The name of the manufacturer Name on which the test is to be
	 *         executed
	 */
	public String getManuFacturerName() {
		return manufacturer;
	}

	/**
	 * 
	 * @param manufacturer
	 *            The name of the manufacturer Name on which the test is to be
	 *            executed
	 */
	public void setManuFacturerName(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Function to get the name of the modelName on which the test is to be
	 * executed
	 * 
	 * @return The name of the modelName on which the test is to be executed
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * 
	 * @param modelName
	 *            The name of the modelName on which the test is to be executed
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * Function to get the boolean if Device is UDID
	 * 
	 * @return boolean if Device is UDID
	 */
	public boolean getIsDeviceUdid() {
		return isDeviceUdid;
	}

	/**
	 * set the boolean if the device is UDID
	 */
	public void setIsDeviceUdid(boolean isDeviceUdid) {
		this.isDeviceUdid = isDeviceUdid;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	private String seeTestPort;

	/**
	 * Function to get the See Test Port on which the test is to be executed
	 * 
	 * @return The See Test Port on which the test is to be executed
	 */
	public String getSeeTestPort() {
		return seeTestPort;
	}

	/**
	 * Function to set the See Test Port on which the test is to be executed
	 * 
	 * @param version
	 *            The See Test Port on which the test is to be executed
	 */
	public void setSeeTestPort(String seeTestPort) {
		this.seeTestPort = seeTestPort;
	}

	/**
	 * Function to get the Browser Version on which the test is to be executed
	 * 
	 * @return The Browser Version on which the test is to be executed
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * Function to set the Browser Version on which the test is to be executed
	 * 
	 * @param version
	 *            The Browser Version on which the test is to be executed
	 */
	public void setBrowserVersion(String version) {
		this.browserVersion = version;
	}

	/**
	 * Function to get the {@link Platform} on which the test is to be executed
	 * 
	 * @return The {@link Platform} on which the test is to be executed
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * Function to set the {@link Platform} on which the test is to be executed
	 * 
	 * @param platform
	 *            The {@link Platform} on which the test is to be executed
	 */
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	/**
	 * Function to get the Platform Version on which the test is to be executed
	 * 
	 * @return The Platform Version on which the test is to be executed
	 */
	public String getPlatformVersion() {
		return platformVersion;
	}

	/**
	 * Function to set the Platform Version on which the test is to be executed
	 * 
	 * @param platformVersion
	 *            The Platform Version on which the test is to be executed
	 */
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	/**
	 * Function to get the if its Mobile based execution
	 * 
	 * @return The isMobileExecution
	 */
	public boolean isMobileExecution() {
		return isMobileExecution;
	}

	/**
	 * Function to set the if its Mobile based execution
	 * 
	 * @param isMobileExecution
	 */
	public void setMobileExecution(boolean isMobileExecution) {
		this.isMobileExecution = isMobileExecution;
	}

	/**
	 * Function to get the if its API based execution
	 * 
	 * @return The isAPIExecution
	 */
	public boolean isAPIExecution() {
		return isAPIExecution;
	}

	/**
	 * Function to set the if its API based execution
	 * 
	 * @param isAPIExecution
	 */
	public void setAPIExecution(boolean isAPIExecution) {
		this.isAPIExecution = isAPIExecution;
	}

	/**
	 * @return the scenario
	 */
	public Scenario getScenario() {
		return scenario;
	}

	/**
	 * @param scenario the scenario to set
	 */
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}