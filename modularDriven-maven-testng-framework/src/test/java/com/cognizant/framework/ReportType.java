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

/**
 * Interface representing a type of report available with the framework
 * 
 * @author Cognizant
 */
interface ReportType {
	/**
	 * Function to initialize the test log
	 */
	public void initializeTestLog();

	/**
	 * Function to add a heading to the test log
	 * 
	 * @param heading
	 *            The heading to be added
	 */
	public void addTestLogHeading(String heading);

	/**
	 * Function to add sub-headings to the test log (4 sub-headings present per
	 * test log row)
	 * 
	 * @param subHeading1
	 *            The first sub-heading to be added
	 * @param subHeading2
	 *            The second sub-heading to be added
	 * @param subHeading3
	 *            The third sub-heading to be added
	 * @param subHeading4
	 *            The fourth sub-heading to be added
	 */
	public void addTestLogSubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4);

	/**
	 * Function to add the overall table headings to the test log (should be
	 * called first before adding the actual content into the test log; headings
	 * and sub-headings should be added before this)
	 */
	public void addTestLogTableHeadings();

	/**
	 * Function to add a section to the test log
	 * 
	 * @param section
	 *            The section to be added
	 */
	public void addTestLogSection(String section);

	/**
	 * Function to add a sub-section to the test log (should be called only
	 * within a previously created section)
	 * 
	 * @param subSection
	 *            The sub-section to be added
	 */
	public void addTestLogSubSection(String subSection);

	/**
	 * Function to update the test log with the details of a particular test
	 * step
	 * 
	 * @param stepNumber
	 *            The current step number
	 * @param stepName
	 *            The test step name
	 * @param stepDescription
	 *            The description of what the test step does
	 * @param stepStatus
	 *            The {@link Status} of the test step
	 * @param screenshotName
	 *            The filename of the screenshot file (in case of failed step)
	 */
	public void updateTestLog(String stepNumber, String stepName, String stepDescription, Status stepStatus,
			String screenshotName);

	/**
	 * Function to create a footer to close the test log
	 * 
	 * @param executionTime
	 *            The time taken to execute the test case
	 * @param nStepsPassed
	 *            The number of test steps that passed
	 * @param nStepsFailed
	 *            The number of test steps that failed
	 */
	public void addTestLogFooter(String executionTime, int nStepsPassed, int nStepsFailed);

	/**
	 * Function to initialize the results summary
	 */
	public void initializeResultSummary();

	/**
	 * Function to add a heading to the results summary
	 * 
	 * @param heading
	 *            The heading to be added
	 */
	public void addResultSummaryHeading(String heading);

	/**
	 * Function to add sub-headings to the results summary (4 sub-headings
	 * present per results summary row)
	 * 
	 * @param subHeading1
	 *            The first sub-heading to be added
	 * @param subHeading2
	 *            The second sub-heading to be added
	 * @param subHeading3
	 *            The third sub-heading to be added
	 * @param subHeading4
	 *            The fourth sub-heading to be added
	 */
	public void addResultSummarySubHeading(String subHeading1, String subHeading2, String subHeading3,
			String subHeading4);

	/**
	 * Function to add the overall table headings to the results summary (should
	 * be called first before adding the actual content into the results
	 * summary; headings and sub-headings should be added before this)
	 */
	public void addResultSummaryTableHeadings();

	/**
	 * Function to update the results summary with the status of the test
	 * instance which was executed
	 * 
	 * @param testParameters
	 *            The {@link TestParameters} object containing the details of
	 *            the test instance which was executed
	 * @param testReportName
	 *            The name of the test report file corresponding to the test
	 *            instance
	 * @param executionTime
	 *            The time taken to execute the test instance
	 * @param testStatus
	 *            The execution status of the test instance
	 */
	public void updateResultSummary(TestParameters testParameters, String testReportName, String executionTime,
			String testStatus);

	/**
	 * Function to create a footer to close the results summary
	 * 
	 * @param totalExecutionTime
	 *            The total time taken to execute all the test cases
	 * @param nTestsPassed
	 *            The number of test cases that passed
	 * @param nTestsFailed
	 *            The number of test cases that failed
	 */
	public void addResultSummaryFooter(String totalExecutionTime, int nTestsPassed, int nTestsFailed);

	public void updateTestLog(String string, String endPoint, Object expectedValue, Object actualValue,
			Status stepStatus);

}