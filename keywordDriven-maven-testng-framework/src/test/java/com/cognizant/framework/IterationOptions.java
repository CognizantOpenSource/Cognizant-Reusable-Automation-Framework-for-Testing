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