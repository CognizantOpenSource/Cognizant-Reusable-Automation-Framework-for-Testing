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