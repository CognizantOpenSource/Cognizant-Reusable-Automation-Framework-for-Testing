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
package com.cognizant.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.cognizant.steps" }, tags = {
		"@Completed" }, strict = true, monochrome = true, plugin = { "pretty",
				"pretty:target/cucumber-report/pretty.txt", "html:target/cucumber-report",
				"json:target/cucumber-report/cucumber.json", "junit:target/cucumber-report/cucumber-junitreport.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })

public class RunnerWebApp extends AbstractTestNGCucumberTests {

	/***
	 * Please enable parallel = true for executing scenaeios in Parallel Also
	 * number of Parallel Threads can be controlled in suite-xml file with
	 * parameter data-provider-thread-count="1"
	 */

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
