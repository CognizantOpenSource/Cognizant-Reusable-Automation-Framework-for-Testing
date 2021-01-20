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
package allocator;

import com.cognizant.framework.selenium.*;
import com.cognizant.core.DriverScript;
import com.cognizant.framework.FrameworkParameters;

/**
 * Class to facilitate parallel execution of test scripts
 * 
 * @author Cognizant
 */
class ParallelRunner implements Runnable {
	
	private final SeleniumTestParameters testParameters;
	private int testBatchStatus = 0;

	/**
	 * Constructor to initialize the details of the test case to be executed
	 * 
	 * @param testParameters
	 *            The {@link SeleniumTestParameters} object (passed from the
	 *            {@link Allocator})
	 */
	ParallelRunner(SeleniumTestParameters testParameters) {
		super();

		this.testParameters = testParameters;
	}

	/**
	 * Function to get the overall test batch status
	 * 
	 * @return The test batch status (0 = Success, 1 = Failure)
	 */
	public int getTestBatchStatus() {
		return testBatchStatus;
	}

	@Override
	public void run() {
	
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		String testReportName, executionTime, testStatus;

		if (frameworkParameters.getStopExecution()) {
			testReportName = "N/A";
			executionTime = "N/A";
			testStatus = "Aborted";
			testBatchStatus = 1; // Non-zero outcome indicates failure
		} else {
			DriverScript driverScript = new DriverScript(this.testParameters);
			driverScript.driveTestExecution();

			testReportName = driverScript.getReportName();
			executionTime = driverScript.getExecutionTime();
			testStatus = driverScript.getTestStatus();
		

			if ("failed".equalsIgnoreCase(testStatus)) {
				testBatchStatus = 1; // Non-zero outcome indicates failure
			}
		}

		ResultSummaryManager resultSummaryManager = ResultSummaryManager.getInstance();
		resultSummaryManager.updateResultSummary(testParameters, testReportName, executionTime, testStatus);
	
	}
}