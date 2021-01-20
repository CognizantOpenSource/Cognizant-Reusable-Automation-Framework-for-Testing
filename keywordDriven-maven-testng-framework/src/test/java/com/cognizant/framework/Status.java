
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