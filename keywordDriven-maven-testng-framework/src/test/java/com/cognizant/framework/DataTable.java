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

import java.util.Map;

/**
 * Class to encapsulate the datatable related functions of the framework
 * 
 * @author Cognizant
 */
public class DataTable {
	private final String datatablePath, datatableName;
	private String dataReferenceIdentifier = "#";

	private String currentTestcase;
	private int currentIteration = 0, currentSubIteration = 0;
	/**
	 * Constructor to initialize the {@link DataTable} object
	 * 
	 * @param datatablePath The path where the datatable is stored
	 * @param datatableName The name of the datatable file
	 */
	public DataTable(String datatablePath, String datatableName) {
		this.datatablePath = datatablePath;
		this.datatableName = datatableName;
	}

	/**
	 * Function to set the data reference identifier character
	 * 
	 * @param dataReferenceIdentifier The data reference identifier character
	 */
	public void setDataReferenceIdentifier(String dataReferenceIdentifier) {
		if (dataReferenceIdentifier.length() != 1) {
			throw new FrameworkException("The data reference identifier must be a single character!");
		}

		this.dataReferenceIdentifier = dataReferenceIdentifier;
	}

	/**
	 * Function to set the variables required to uniquely identify the exact row of
	 * data under consideration
	 * 
	 * @param currentTestcase     The ID of the current test case
	 * @param currentIteration    The Iteration being executed currently
	 * @param currentSubIteration The Sub-Iteration being executed currently
	 */
	public void setCurrentRow(String currentTestcase, int currentIteration, int currentSubIteration) {
		this.currentTestcase = currentTestcase;
		this.currentIteration = currentIteration;
		this.currentSubIteration = currentSubIteration;
	}

	private void checkPreRequisites() {
		if (currentTestcase == null) {
			throw new FrameworkException("DataTable.currentTestCase is not set!");
		}
		if (currentIteration == 0) {
			throw new FrameworkException("DataTable.currentIteration is not set!");
		}

		if (currentSubIteration == 0) {
			throw new FrameworkException("DataTable.currentSubIteration is not set!");
		}
	}

	/**
	 * Function to return the test data value corresponding to the sheet name and
	 * field name passed
	 * 
	 * @param datasheetName The name of the sheet in which the data is present
	 * @param fieldName     The name of the field whose value is required
	 * @return The test data present in the field name specified
	 * @see #putData(String, String, String)
	 * @see #getExpectedResult(String)
	 */
	public String getData(String datasheetName, String fieldName) {
		checkPreRequisites();

		ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath, datatableName);
		testDataAccess.setDatasheetName(datasheetName);

		int rowNum = testDataAccess.getRowNum(currentTestcase, 0, 1); // Start
																		// at
																		// row
																		// 1,
																		// skipping
																		// the
																		// header
																		// row
		if (rowNum == -1) {
			throw new FrameworkException("The test case \"" + currentTestcase + "\""
					+ "is not found in the test data sheet \"" + datasheetName + "\"!");
		}
		rowNum = testDataAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestcase + "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		rowNum = testDataAccess.getRowNum(Integer.toString(currentSubIteration), 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestcase
					+ "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		String dataValue = testDataAccess.getValue(rowNum, fieldName);

		if (dataValue.startsWith(dataReferenceIdentifier)) {
			dataValue = getCommonData(fieldName, dataValue);
		}

		return dataValue;
	}

	private String getCommonData(String fieldName, String dataValue) {
		ExcelDataAccess commonDataAccess = new ExcelDataAccess(datatablePath, "Common Testdata");
		commonDataAccess.setDatasheetName("Common_Testdata");

		String dataReferenceId = dataValue.split(dataReferenceIdentifier)[1];

		int rowNum = commonDataAccess.getRowNum(dataReferenceId, 0, 1); // Start
																		// at
																		// row
																		// 1,
																		// skipping
																		// the
																		// header
																		// row
		if (rowNum == -1) {
			throw new FrameworkException("The common test data row identified by \"" + dataReferenceId + "\""
					+ "is not found in the common test data sheet!");
		}

		return commonDataAccess.getValue(rowNum, fieldName);
	}

	/**
	 * Function to output intermediate data (output values) into the specified sheet
	 * 
	 * @param datasheetName The name of the sheet into which the data is to be
	 *                      written
	 * @param fieldName     The name of the field into which the data is to be
	 *                      written
	 * @param dataValue     The value to be written into the field specified
	 * @see #getData(String, String)
	 */
	public void putData(String datasheetName, String fieldName, String dataValue) {
		checkPreRequisites();

		ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath, datatableName);
		testDataAccess.setDatasheetName(datasheetName);

		int rowNum = testDataAccess.getRowNum(currentTestcase, 0, 1); // Start
																		// at
																		// row
																		// 1,
																		// skipping
																		// the
																		// header
																		// row
		if (rowNum == -1) {
			throw new FrameworkException("The test case \"" + currentTestcase + "\""
					+ "is not found in the test data sheet \"" + datasheetName + "\"!");
		}
		rowNum = testDataAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestcase + "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		rowNum = testDataAccess.getRowNum(Integer.toString(currentSubIteration), 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestcase
					+ "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		synchronized (DataTable.class) {
			testDataAccess.setValue(rowNum, fieldName, dataValue);
		}
	}

	/**
	 * Function to get the expected result corresponding to the field name passed
	 * 
	 * @param fieldName The name of the field which contains the expected results
	 * @return The expected result present in the field name specified
	 * @see #getData(String, String)
	 */
	public String getExpectedResult(String fieldName) {
		checkPreRequisites();

		ExcelDataAccess expectedResultsAccess = new ExcelDataAccess(datatablePath, datatableName);
		expectedResultsAccess.setDatasheetName("Parametrized_Checkpoints");

		int rowNum = expectedResultsAccess.getRowNum(currentTestcase, 0, 1); // Start
																				// at
																				// row
																				// 1,
																				// skipping
																				// the
																				// header
																				// row
		if (rowNum == -1) {
			throw new FrameworkException("The test case \"" + currentTestcase + "\""
					+ "is not found in the parametrized checkpoints sheet!");
		}
		rowNum = expectedResultsAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestcase + "\"" + "is not found in the parametrized checkpoints sheet!");
		}

		rowNum = expectedResultsAccess.getRowNum(Integer.toString(currentSubIteration), 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestcase
					+ "\"" + "is not found in the parametrized checkpoints sheet!");
		}

		return expectedResultsAccess.getValue(rowNum, fieldName);
	}

	/**
	 * Function to return the test data value corresponding to the sheet name and
	 * field name passed
	 * 
	 * @param datasheetName The name of the sheet in which the data is present
	 * @param fieldName     The name of the field whose value is required
	 * @param subIteration  The SubIteration which is required to access the
	 *                      respective data
	 * @return The test data present in the field name specified
	 * @see #putData(String, String, String)
	 * @see #getExpectedResult(String)
	 */
	public String getDataWithSubIteration(String datasheetName, String fieldName, String subIteration) {
		checkPreRequisites();

		ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath, datatableName);
		testDataAccess.setDatasheetName(datasheetName);

		int rowNum = testDataAccess.getRowNum(currentTestcase, 0, 1); // Start
																		// at
																		// row
																		// 1,
																		// skipping
																		// the
																		// header
																		// row
		if (rowNum == -1) {
			throw new FrameworkException("The test case \"" + currentTestcase + "\""
					+ "is not found in the test data sheet \"" + datasheetName + "\"!");
		}
		rowNum = testDataAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestcase + "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		rowNum = testDataAccess.getRowNum(subIteration, 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestcase
					+ "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		String dataValue = testDataAccess.getValue(rowNum, fieldName);

		if (dataValue.startsWith(dataReferenceIdentifier)) {
			dataValue = getCommonData(fieldName, dataValue);
		}

		return dataValue;
	}

	/**
	 * Function to return the test data value corresponding to the sheet name and
	 * field name passed
	 * 
	 * @param datasheetName The name of the sheet in which the data is present
	 * @param keys          The name of the fields whose values are required
	 * @return The Map of Column Names with values
	 */
	public Map<String, String> getData(String datasheetName, String[] keys) {
		checkPreRequisites();

		ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath, datatableName);
		testDataAccess.setDatasheetName(datasheetName);

		int rowNum = testDataAccess.getRowNum(currentTestcase, 0, 1); // Start
																		// at
																		// row
																		// 1,
																		// skipping
																		// the
																		// header
																		// row
		if (rowNum == -1) {
			throw new FrameworkException("The test case \"" + currentTestcase + "\""
					+ "is not found in the test data sheet \"" + datasheetName + "\"!");
		}
		rowNum = testDataAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestcase + "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		rowNum = testDataAccess.getRowNum(Integer.toString(currentSubIteration), 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestcase
					+ "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		Map<String, String> values = testDataAccess.getValuesForSpecificRow(keys, rowNum);

		return values;
	}

	/**
	 * Function to return the test data value corresponding to the sheet name and
	 * field name passed
	 * 
	 * @param testcase      Case ID The name of Test Case ID
	 * @param datasheetName The name of the sheet in which the data is present
	 * @param fieldName     The name of the field whose value is required
	 * @param subIteration  The SubIteration which is required to access the
	 *                      respective data
	 * @return The test data present in the field name specified
	 * @see #putData(String, String, String)
	 * @see #getExpectedResult(String)
	 */
	public String getDataWithSubIterationTDID(String testcase, String datasheetName, String fieldName,
			String subIteration) {
		checkPreRequisites();

		ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath, datatableName);
		testDataAccess.setDatasheetName(datasheetName);

		int rowNum = testDataAccess.getRowNum(testcase, 0, 1); // Start
																// at
																// row
																// 1,
																// skipping
																// the
																// header
																// row
		if (rowNum == -1) {
			throw new FrameworkException("The test case \"" + currentTestcase + "\""
					+ "is not found in the test data sheet \"" + datasheetName + "\"!");
		}
		rowNum = testDataAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestcase + "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		rowNum = testDataAccess.getRowNum(subIteration, 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestcase
					+ "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		String dataValue = testDataAccess.getValue(rowNum, fieldName);

		if (dataValue.startsWith(dataReferenceIdentifier)) {
			dataValue = getCommonData(fieldName, dataValue);
		}

		return dataValue;
	}
}