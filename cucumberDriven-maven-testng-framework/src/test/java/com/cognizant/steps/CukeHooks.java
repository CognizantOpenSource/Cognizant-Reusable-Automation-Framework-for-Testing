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
package com.cognizant.steps;

import java.io.IOException;

import com.cognizant.framework.DriverManager;
import com.cognizant.framework.TestHarness;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CukeHooks extends MasterSteps{
	
	private TestHarness testHarness;

	@Before
	public void setUp(Scenario scenario) {
	
		testHarness = new TestHarness();
		DriverManager.getTestParameters().setScenario(scenario);
		testHarness.invokeDriver(scenario);	
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
		
		testHarness.closeRespestiveDriver(scenario);
	}

}
