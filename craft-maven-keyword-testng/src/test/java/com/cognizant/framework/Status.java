package com.cognizant.framework;


/**
 * Enumeration to represent the status of the current test step
 * @author Cognizant
 */
public enum Status {
	/**
	 * Indicates that the outcome of a verification was not successful
	 */
	FAIL,
	/**
	 * Indicates a warning message
	 */
	WARNING,
	/**
	 * Indicates that the outcome of a verification was successful
	 */
	PASS,
	/**
	 * Indicates a step that is logged into the results for informational purposes,
	 * along with an attached screenshot for reference
	 */
	SCREENSHOT,
	/**
	 * Indicates a message that is logged into the results for informational purposes
	 */
	DONE,
	/**
	 * Indicates a debug-level message, typically used by automation developers
	 * to troubleshoot any errors that may occur
	 */
	DEBUG;
}