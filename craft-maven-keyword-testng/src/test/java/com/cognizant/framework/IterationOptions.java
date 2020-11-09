package com.cognizant.framework;

/**
 * Enumeration to represent the various options for iteration mode
 * @author Cognizant
 */
public enum IterationOptions {
	/**
	 * Run all iterations specified in the test data sheet
	 */
	RUN_ALL_ITERATIONS,
	/**
	 * Run only the first iteration specified in the test data sheet
	 */
	RUN_ONE_ITERATION_ONLY,
	/**
	 * Run the range of iterations specified by StartIteration and EndIteration
	 */
	RUN_RANGE_OF_ITERATIONS;
}