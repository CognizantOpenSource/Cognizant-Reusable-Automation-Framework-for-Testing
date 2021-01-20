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
package testscripts.LoginScenario;

import org.testng.annotations.Test;

import com.cognizant.core.DriverScript;
import com.cognizant.core.TestConfigurationsLite;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.SeleniumTestParameters;

import pages.FlightFinderPage;
import pages.SignOnPage;

/**
 * Test for login with valid user credentials
 * 
 * @author Cognizant
 */
public class TestForValidLogin extends TestConfigurationsLite {

	@Test(dataProvider = "ChromeBrowser", dataProviderClass = TestConfigurationsLite.class)
	public void testRunner(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for login with valid user credentials");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	}

	@Override
	public void setUp() {
		// report.addTestLogSection("Setup");
	}

	@Override
	public void executeTest() {

		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));

		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage.loginAsValidUser();

		// The login succeeds if the flight finder page is displayed
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
	}

	@Override
	public void tearDown() {
		report.addTestLogSection("TearDown");
		logout();
	}

	public void logout() {
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.logout();
	}
}