package com.cognizant.framework.selenium;

/**
 * Enumeration to represent the mode of execution
 * 
 * @author Cognizant
 */
public enum ExecutionMode {
	
	/**
	 * Execute on the local machine
	 */
	LOCAL,
	/**
	 * Execute on a selenium grid
	 */
	GRID,
	/**
	 * Execute on a mobile device using Appium
	 */
	MOBILE;

}