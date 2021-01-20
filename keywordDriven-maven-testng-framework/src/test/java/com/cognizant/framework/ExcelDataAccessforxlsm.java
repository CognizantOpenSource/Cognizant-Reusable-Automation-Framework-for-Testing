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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataAccessforxlsm {

	private final String filePath, fileName;

	private String datasheetName;

	/**
	 * Function to get the Excel sheet name
	 * 
	 * @return The Excel sheet name
	 */
	public String getDatasheetName() {
		return datasheetName;
	}

	/**
	 * Function to set the Excel sheet name
	 * 
	 * @param datasheetName
	 *            The Excel sheet name
	 */
	public void setDatasheetName(String datasheetName) {
		this.datasheetName = datasheetName;
	}

	/**
	 * Constructor to initialize the excel data filepath and filename
	 * 
	 * @param filePath
	 *            The absolute path where the excel data file is stored
	 * @param fileName
	 *            The name of the excel data file (without the extension). Note
	 *            that .xlsx files are not supported, only .xls files are
	 *            supported
	 */
	public ExcelDataAccessforxlsm(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	private void checkPreRequisites() {
		if (datasheetName == null) {
			throw new FrameworkException("ExcelDataAccess.datasheetName is not set!");
		}
	}

	private XSSFWorkbook openFileForReading() {

		String encryptedAbsoluteFilePath = WhitelistingPath
				.cleanStringForFilePath(filePath + Util.getFileSeparator() + fileName + ".xlsm");
		String absoluteFilePath = encryptedAbsoluteFilePath;

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(absoluteFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
		}

		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(fileInputStream);
			// fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"Error while opening the specified Excel workbook \"" + absoluteFilePath + "\"");
		}

		return workbook;
	}

	private XSSFSheet getWorkSheet(XSSFWorkbook workbook) {
		XSSFSheet worksheet = workbook.getSheet(datasheetName);
		if (worksheet == null) {
			throw new FrameworkException("The specified sheet \"" + datasheetName + "\""
					+ "does not exist within the workbook \"" + fileName + ".xls\"");
		}

		return worksheet;
	}

	/**
	 * Function to get the last row number within the worksheet
	 * 
	 * @return The last row number within the worksheet
	 */
	public int getLastRowNum() {
		checkPreRequisites();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);

		return worksheet.getLastRowNum();
	}

	/**
	 * Function to search for a specified key within a column, and return the
	 * corresponding row number
	 * 
	 * @param key
	 *            The value being searched for
	 * @param columnNum
	 *            The column number in which the key should be searched
	 * @param startRowNum
	 *            The row number from which the search should start
	 * @return The row number in which the specified key is found (-1 if the key
	 *         is not found)
	 */
	public int getRowNum(String key, int columnNum, int startRowNum) {
		checkPreRequisites();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		String currentValue;
		for (int currentRowNum = startRowNum; currentRowNum <= worksheet.getLastRowNum(); currentRowNum++) {

			XSSFRow row = worksheet.getRow(currentRowNum);
			XSSFCell cell = row.getCell(columnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(key)) {
				return currentRowNum;
			}
		}

		return -1;
	}

	/**
	 * Function to get the value in the cell identified by the specified row
	 * number and column header
	 * 
	 * @param rowNum
	 *            The row number of the cell
	 * @param columnHeader
	 *            The column header of the cell
	 * @return The value present in the cell
	 */
	public String getValue(int rowNum, String columnHeader) {
		checkPreRequisites();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow row = worksheet.getRow(0); // 0 because header is always in the
											// first row
		int columnNum = -1;
		String currentValue;
		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {

			XSSFCell cell = row.getCell(currentColumnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		if (columnNum == -1) {
			throw new FrameworkException("The specified column header \"" + columnHeader + "\""
					+ "is not found in the sheet \"" + datasheetName + "\"!");
		} else {
			row = worksheet.getRow(rowNum);
			XSSFCell cell = row.getCell(columnNum);
			return getCellValueAsString(cell, formulaEvaluator);
		}
	}

	
	private String getCellValueAsString(XSSFCell cell, FormulaEvaluator formulaEvaluator) {
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return "";
		} else {
			if (formulaEvaluator.evaluate(cell).getCellType() == CellType.ERROR) {
				throw new FrameworkException(
						"Error in formula within this cell! " + "Error code: " + cell.getErrorCellValue());
			}

			DataFormatter dataFormatter = new DataFormatter();
			return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell(cell));
		}
	}

	public List<Map<String, String>> getValues(String[] keys) {
		checkPreRequisites();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow hrow = worksheet.getRow(0);
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= this.getLastRowNum(); i++) {
			Map<String, String> valueMap = new HashMap<String, String>();
			XSSFRow row = worksheet.getRow(i);
			for (int j = 0; j < keys.length; j++) {
				String value = getValue(hrow, row, keys[j], formulaEvaluator);
				valueMap.put(keys[j], value);
			}
			values.add(valueMap);
		}

		return values;
	}

	public Map<String, String> getValuesForSpecificRow(String[] keys, int rowNum) {
		checkPreRequisites();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow hrow = worksheet.getRow(0);

		Map<String, String> valueMap = new HashMap<String, String>();
		XSSFRow row = worksheet.getRow(rowNum);
		for (int j = 0; j < keys.length; j++) {
			String value = getValue(hrow, row, keys[j], formulaEvaluator, keys.length);
			valueMap.put(keys[j], value);
		}

		return valueMap;
	}

	private String getValue(XSSFRow hrow, XSSFRow row, String columnHeader, FormulaEvaluator formulaEvaluator) {
		int columnNum = -1;
		String currentValue;

		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {
			XSSFCell cell = hrow.getCell(currentColumnNum);

			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		if (columnNum == -1) {
			throw new FrameworkException("The specified column header \"" + columnHeader + "\""
					+ "is not found in the sheet \"" + datasheetName + "\"!");
		} else {

			XSSFCell cell = row.getCell(columnNum);
			return getCellValueAsString(cell, formulaEvaluator);
		}
	}

	private String getValue(XSSFRow hrow, XSSFRow row, String columnHeader, FormulaEvaluator formulaEvaluator,
			int length) {
		int columnNum = -1;
		String currentValue;

		for (int currentColumnNum = 0; currentColumnNum < length; currentColumnNum++) {
			XSSFCell cell = hrow.getCell(currentColumnNum);

			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		if (columnNum == -1) {
			throw new FrameworkException("The specified column header \"" + columnHeader + "\""
					+ "is not found in the sheet \"" + datasheetName + "\"!");
		} else {

			XSSFCell cell = row.getCell(columnNum);
			return getCellValueAsString(cell, formulaEvaluator);
		}
	}

}
