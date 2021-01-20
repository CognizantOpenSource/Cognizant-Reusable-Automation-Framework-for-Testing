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

/**
 * Singleton class that encapsulates Framework level global parameters
 * 
 * @author Cognizant
 */
public class FrameworkParameters {
	private String relativePath;
	private String runConfiguration;
	private boolean stopExecution = false;


	private static final FrameworkParameters FRAMEWORK_PARAMETERS = new FrameworkParameters();

	private FrameworkParameters() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the singleton instance of the
	 * {@link FrameworkParameters} object
	 * 
	 * @return Instance of the {@link FrameworkParameters} object
	 */
	public static FrameworkParameters getInstance() {
		return FRAMEWORK_PARAMETERS;
	}

	/**
	 * Function to get the absolute path of the framework (to be used as a
	 * relative path)
	 * 
	 * @return The absolute path of the framework
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * Function to set the absolute path of the framework (to be used as a
	 * relative path)
	 * 
	 * @param relativePath
	 *            The absolute path of the framework
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/**
	 * Function to get the run configuration to be executed
	 * 
	 * @return The run configuration
	 */
	public String getRunConfiguration() {
		return runConfiguration;
	}

	/**
	 * Function to set the run configuration to be executed
	 * 
	 * @param runConfiguration
	 *            The run configuration
	 */
	public void setRunConfiguration(String runConfiguration) {
		this.runConfiguration = runConfiguration;
	}

	/**
	 * Function to get a boolean value indicating whether to stop the overall
	 * test batch execution
	 * 
	 * @return The stopExecution boolean value
	 */
	public boolean getStopExecution() {
		return stopExecution;
	}

	/**
	 * Function to set a boolean value indicating whether to stop the overall
	 * test batch execution
	 * 
	 * @param stopExecution
	 *            Boolean value indicating whether to stop the overall test
	 *            batch execution
	 */
	public void setStopExecution(boolean stopExecution) {
		this.stopExecution = stopExecution;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}