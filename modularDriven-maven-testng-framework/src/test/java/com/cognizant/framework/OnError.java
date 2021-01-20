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
 * Enumeration to represent the action to be taken in the event of an error during test execution
 * @author Cognizant
 */
public enum OnError {
	/**
	 * Proceed to the next iteration (if applicable) of the current test case
	 */
	NEXT_ITERATION,
	/**
	 * Proceed to the next test case (if applicable)
	 */
	NEXT_TESTCASE,
	/**
	 * Abort execution of all further test cases (applicable only during test batch execution using the Allocator)
	 */
	STOP;
}