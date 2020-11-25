package com.cognizant.framework;

/**
 * Class to encapsulate various input parameters required for each test script
 * @author Cognizant
 */
public class TestParameters {
	private final String currentScenario;
	private final String currentTestcase;
	private String currentTestInstance;
	private String currentTestDescription;
	private String additionalDetails;
	private IterationOptions iterationMode;
	private int startIteration;
	private int endIteration;
	
	
	/**
	 * Constructor to initialize the {@link TestParameters} object
	 * @param currentScenario The current test scenario/module
	 * @param currentTestcase The current test case
	 */
	public TestParameters(String currentScenario, String currentTestcase) {
		this.currentScenario = currentScenario;
		this.currentTestcase = currentTestcase;
		
		// Set default values for all test parameters
		this.currentTestInstance = "Instance1";
		this.currentTestDescription = "";
		this.additionalDetails = "";
		this.iterationMode = IterationOptions.RUN_ALL_ITERATIONS;
		this.startIteration = 1;
		this.endIteration = 1;
	}
	
	/**
	 * Function to get the current test scenario/module
	 * @return The current test scenario/module
	 */
	public String getCurrentScenario() {
		return currentScenario;
	}
	
	/**
	 * Function to get the current test case
	 * @return The current test case
	 */
	public String getCurrentTestcase() {
		return currentTestcase;
	}
	
	/**
	 * Function to get the current test instance
	 * @return The current test instance
	 */
	public String getCurrentTestInstance() {
		return currentTestInstance;
	}
	
	/**
	 * Function to set the current test instance
	 * @param currentTestInstance The current test instance
	 */
	public void setCurrentTestInstance(String currentTestInstance) {
		this.currentTestInstance = currentTestInstance;
	}
	
	/**
	 * Function to get the description of the current test case
	 * @return The description of the current test case
	 */
	public String getCurrentTestDescription() {
		return currentTestDescription;
	}
	
	/**
	 * Function to set the description of the current test case
	 * @param currentTestDescription The description of the current test case
	 */
	public void setCurrentTestDescription(String currentTestDescription) {
		this.currentTestDescription = currentTestDescription;
	}
	
	/**
	 * Function to get additional details about the current test case/test instance
	 * @return Additional details about the current test case/test instance
	 */
	public String getAdditionalDetails() {
		return additionalDetails;
	}
	
	/**
	 * Function to set additional details about the current test case/test instance
	 * @param additionalDetails Additional details about the current test case/test instance
	 */
	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	
	/**
	 * Function to get the iteration mode
	 * @return The iteration mode
	 * @see IterationOptions
	 */
	public IterationOptions getIterationMode() {
		return iterationMode;
	}
	
	/**
	 * Function to set the iteration mode
	 * @param iterationMode The iteration mode
	 * @see IterationOptions
	 */
	public void setIterationMode(IterationOptions iterationMode) {
		this.iterationMode = iterationMode;
	}
	
	/**
	 * Function to get the start iteration
	 * @return The start iteration
	 */
	public int getStartIteration() {
		return startIteration;
	}
	
	/**
	 * Function to set the start iteration
	 * @param startIteration The start iteration (defaults to 1 if the input is less than or equal to 0)
	 */
	public void setStartIteration(int startIteration) {
		if(startIteration > 0) {
			this.startIteration = startIteration;
		}
	}
	
	/**
	 * Function to get the end iteration
	 * @return The end iteration
	 */
	public int getEndIteration() {
		return endIteration;
	}
	
	/**
	 * Function to set the end iteration
	 * @param endIteration The end iteration (defaults to 1 if the input is less than or equal to 0)
	 */
	public void setEndIteration(int endIteration) {
		if(endIteration > 0) {
			this.endIteration = endIteration;
		}
	}
}