package com.cognizant.core;

import java.util.Map;

import com.cognizant.framework.DataTable;
import com.cognizant.framework.selenium.CustomDriver;
import com.cognizant.framework.selenium.SeleniumReport;
import com.cognizant.framework.selenium.WebDriverUtil;

/**
 * Wrapper class for common framework objects, to be used across the entire test
 * case and dependent libraries
 * 
 * @author Cognizant
 */
public class ScriptHelper {

	private final DataTable dataTable;
	private final SeleniumReport report;
	private CustomDriver customDriver;
	private WebDriverUtil driverUtil;
	private Map<String, String> reusableHandle;

	/**
	 * Constructor to initialize all the objects wrapped by the {@link ScriptHelper}
	 * class
	 * 
	 * @param dataTable      The {@link DataTable} object
	 * @param report         The {@link SeleniumReport} object
	 * @param customDriver         The {@link CustomDriver} object
	 * @param driverUtil     The {@link WebDriverUtil} object
	 * @param reusableHandle
	 */

	public ScriptHelper(DataTable dataTable, SeleniumReport report, CustomDriver customDriver,
			WebDriverUtil driverUtil, Map<String, String> reusableHandle) {
		this.dataTable = dataTable;
		this.report = report;
		this.customDriver = customDriver;
		this.driverUtil = driverUtil;
		this.reusableHandle = reusableHandle;
	}

	/**
	 * Function to get the {@link DataTable} object
	 * 
	 * @return The {@link DataTable} object
	 */
	public DataTable getDataTable() {
		return dataTable;
	}

	/**
	 * Function to get the {@link SeleniumReport} object
	 * 
	 * @return The {@link SeleniumReport} object
	 */
	public SeleniumReport getReport() {
		return report;
	}

	/**
	 * Function to get the {@link CustomDriver} object
	 * 
	 * @return The {@link CustomDriver} object
	 */
	public CustomDriver getcustomDriver() {
		return customDriver;
	}

	/**
	 * Function to get the {@link WebDriverUtil} object
	 * 
	 * @return The {@link WebDriverUtil} object
	 */
	public WebDriverUtil getDriverUtil() {
		return driverUtil;
	}


	/**
	 * Function to get the {@link Map<String, String>} object
	 * 
	 * @return The {@link Map<String, String>} object
	 */
	public Map<String, String> getReusablehandle() {
		return reusableHandle;
	}

}