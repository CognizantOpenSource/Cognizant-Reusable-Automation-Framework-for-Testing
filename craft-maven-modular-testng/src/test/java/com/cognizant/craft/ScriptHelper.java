package com.cognizant.craft;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cognizant.framework.CraftDataTable;
import com.cognizant.framework.selenium.CraftDriver;
import com.cognizant.framework.selenium.SeleniumReport;
import com.cognizant.framework.selenium.WebDriverUtil;

/**
 * Wrapper class for common framework objects, to be used across the entire test
 * case and dependent libraries
 * 
 * @author Cognizant
 */
public class ScriptHelper {

	private final CraftDataTable dataTable;
	private final SeleniumReport report;
	private CraftDriver craftDriver;
	private WebDriverUtil driverUtil;
	private Map<String, String> reusableHandle;

	/**
	 * Constructor to initialize all the objects wrapped by the {@link ScriptHelper}
	 * class
	 * 
	 * @param dataTable      The {@link CraftDataTable} object
	 * @param report         The {@link SeleniumReport} object
	 * @param driver         The {@link WebDriver} object
	 * @param driverUtil     The {@link WebDriverUtil} object
	 * @param reusableHandle
	 * @param extentTest
	 * @param apiDriver
	 */

	public ScriptHelper(CraftDataTable dataTable, SeleniumReport report, CraftDriver craftDriver,
			WebDriverUtil driverUtil, Map<String, String> reusableHandle) {
		this.dataTable = dataTable;
		this.report = report;
		this.craftDriver = craftDriver;
		this.driverUtil = driverUtil;
		this.reusableHandle = reusableHandle;
	}

	/**
	 * Function to get the {@link CraftDataTable} object
	 * 
	 * @return The {@link CraftDataTable} object
	 */
	public CraftDataTable getDataTable() {
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
	 * Function to get the {@link CraftDriver} object
	 * 
	 * @return The {@link CraftDriver} object
	 */
	public CraftDriver getcraftDriver() {
		return craftDriver;
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