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
import java.text.SimpleDateFormat;

/**
 * Class to encapsulate the reporting settings in the framework
 * 
 * @author Cognizant
 */
public class ReportSettings {
	
	private final String reportPath;
	private final String reportName;

	private String projectName;
	private int logLevel;
	private String dateFormatString;

	private boolean generateHtmlReports;

	private boolean takeScreenshotFailedStep;
	private boolean takeScreenshotPassedStep;

	private boolean linkScreenshotsToTestLog;
	private boolean linkTestLogsToSummary;

	private boolean isMobileExecution;
	private boolean isWebAutomation;

	/**
	 * Constructor to initialize the report settings
	 * 
	 * @param reportPath
	 *            The report path
	 * @param reportName
	 *            The report name
	 */
	public ReportSettings(String reportPath, String reportName) {
		reportPath = WhitelistingPath.cleanStringForFilePath(reportPath);
		boolean reportPathExists = new File(reportPath).isDirectory();
		if (!reportPathExists) {
			throw new FrameworkException("The given report path does not exist!");
		}
		this.reportPath = reportPath;
		this.reportName = reportName;

		// Set default values for all the report settings
		projectName = "";
		logLevel = 4;
		generateHtmlReports = true;
		takeScreenshotFailedStep = true;
		takeScreenshotPassedStep = false;
		linkScreenshotsToTestLog = true;
		linkTestLogsToSummary = true;
		dateFormatString = "dd-MMM-yyyy hh:mm:ss a";
		isMobileExecution = false;
	}

	/**
	 * Function to get the absolute path where the report is to be stored
	 * 
	 * @return The report path
	 */
	public String getReportPath() {
		return reportPath;
	}

	/**
	 * Function to get the name of the report
	 * 
	 * @return The report name
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * Function to get the name of the project being automated
	 * 
	 * @return The project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Function to set the name of the project being automated
	 * 
	 * @param projectName
	 *            The project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Function to get the logging level of the reports. Log levels range
	 * between 0 to 5, with 0 being minimal reporting and 5 being highly
	 * detailed reporting
	 * 
	 * @return The log level
	 */
	public int getLogLevel() {
		return logLevel;
	}

	/**
	 * Function to set the logging level of the reports. Log levels range
	 * between 0 to 5, with 0 being minimal reporting and 5 being highly
	 * detailed reporting
	 * 
	 * @param logLevel
	 *            The log level
	 */
	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;

		if (logLevel < 0) {
			this.logLevel = 0;
		}

		if (logLevel > 5) {
			this.logLevel = 5;
		}
	}

	/**
	 * Function to get a string indicating the format for the date/time to be
	 * used within the report
	 * 
	 * @return The date/time formatting string
	 * @see SimpleDateFormat
	 */
	public String getDateFormatString() {
		return dateFormatString;
	}

	/**
	 * Function to set a string indicating the format for the date/time to be
	 * used within the report
	 * 
	 * @param dateFormatString
	 *            The date/time formatting string
	 * @see SimpleDateFormat
	 */
	public void setDateFormatString(String dateFormatString) {
		this.dateFormatString = dateFormatString;
	}


	/**
	 * Function to get a Boolean value indicating whether HTML reports should be
	 * generated
	 * 
	 * @return Boolean value indicating whether HTML reports should be generated
	 */
	public boolean shouldGenerateHtmlReports() {
		return generateHtmlReports;
	}

	/**
	 * Function to set a Boolean value indicating whether HTML reports should be
	 * generated
	 * 
	 * @param generateHtmlReports
	 *            Boolean value indicating whether HTML reports should be
	 *            generated
	 */
	public void setGenerateHtmlReports(boolean generateHtmlReports) {
		this.generateHtmlReports = generateHtmlReports;
	}


	/**
	 * Function to get a Boolean value indicating whether a screenshot should be
	 * captured for failed steps
	 * 
	 * @return Boolean value indicating whether a screenshot should be captured
	 *         for failed steps
	 */
	public boolean shouldTakeScreenshotFailedStep() {
		return takeScreenshotFailedStep;
	}

	/**
	 * Function to set a Boolean value indicating whether a screenshot should be
	 * captured for failed steps
	 * 
	 * @param takeScreenshotFailedStep
	 *            Boolean value indicating whether a screenshot should be
	 *            captured for failed steps
	 */
	public void setTakeScreenshotFailedStep(boolean takeScreenshotFailedStep) {
		this.takeScreenshotFailedStep = takeScreenshotFailedStep;
	}

	/**
	 * Function to get a Boolean value indicating whether a screenshot should be
	 * captured for passed steps
	 * 
	 * @return Boolean value indicating whether a screenshot should be captured
	 *         for passed steps
	 */
	public boolean shouldTakeScreenshotPassedStep() {
		return takeScreenshotPassedStep;
	}

	/**
	 * Function to set a Boolean value indicating whether a screenshot should be
	 * captured for passed steps
	 * 
	 * @param takeScreenshotPassedStep
	 *            Boolean value indicating whether a screenshot should be
	 *            captured for passed steps
	 */
	public void setTakeScreenshotPassedStep(boolean takeScreenshotPassedStep) {
		this.takeScreenshotPassedStep = takeScreenshotPassedStep;
	}

	/**
	 * Function to get a Boolean value indicating whether any screenshot taken
	 * must be linked to the corresponding step within the test log
	 * 
	 * @return Boolean value indicating whether any screenshot taken must be
	 *         linked to the corresponding step within the test log
	 */
	public boolean shouldLinkScreenshotsToTestLog() {
		return linkScreenshotsToTestLog;
	}

	/**
	 * Function to set a Boolean value indicating whether any screenshot taken
	 * must be linked to the corresponding step within the test log
	 * 
	 * @param linkScreenshotsToTestLog
	 *            Boolean value indicating whether any screenshot taken must be
	 *            linked to the corresponding step within the test log
	 */
	public void setLinkScreenshotsToTestLog(boolean linkScreenshotsToTestLog) {
		this.linkScreenshotsToTestLog = linkScreenshotsToTestLog;
	}

	/**
	 * Function to get a Boolean value indicating whether the individual test
	 * logs must be linked to the result summary
	 * 
	 * @return Boolean value indicating whether the individual test logs must be
	 *         linked to the result summary
	 */
	public boolean shouldLinkTestLogsToSummary() {
		return linkTestLogsToSummary;
	}

	/**
	 * Function to set a Boolean value indicating whether the individual test
	 * logs must be linked to the result summary
	 * 
	 * @param linkTestLogsToSummary
	 *            Boolean value indicating whether the individual test logs must
	 *            be linked to the result summary
	 */
	public void setLinkTestLogsToSummary(boolean linkTestLogsToSummary) {
		this.linkTestLogsToSummary = linkTestLogsToSummary;
	}

	
	public void setisMobileExecution(boolean isMobileExecution) {
		this.isMobileExecution = isMobileExecution;
	}

	public void setisWebAutomation(boolean isWebAutomation) {
		this.isWebAutomation = isWebAutomation;
	}

	public boolean getisWebAutomation() {
		return isWebAutomation;
	}

	public String getWidth() {
		String width = "";
		if (this.isMobileExecution) {
			width = "250px";
		} else {
			width = "400px";
		}
		return width;
	}

	public String getHeight() {
		String height = "";
		if (this.isMobileExecution) {
			height = "450px";
		} else {
			height = "300px";
		}
		return height;
	}

	public String getColumnCount() {
		return "6";
	}
	
}