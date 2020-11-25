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
