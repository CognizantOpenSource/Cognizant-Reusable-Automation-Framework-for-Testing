package testscripts.LoginScenario;

import org.testng.annotations.Test;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.TestConfigurationsLite;
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