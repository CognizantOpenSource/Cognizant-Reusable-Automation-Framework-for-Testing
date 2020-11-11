package com.cognizant.framework;


/**
 * Exception class for the framework
 * @author Cognizant
 */
@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {
	/**
	 * The step name to be specified for the exception
	 */
	public String errorName = "Error";
	
	
	/**
	 * Constructor to initialize the exception from the framework
	 * @param errorDescription The Exception message to be thrown
	 */
	public FrameworkException(String errorDescription) {
		super(errorDescription);
	}
	
	/**
	 * Constructor to initialize the exception from the framework
	 * @param errorName The step name for the error
	 * @param errorDescription The Exception message to be thrown
	 */
	public FrameworkException(String errorName, String errorDescription) {
		super(errorDescription);
		this.errorName = errorName;
	}

	public String getErrorName() {
		return errorName;
	}
}