package testscripts.LoginScenario;

import org.testng.annotations.Test;

import com.cognizant.core.DriverScript;
import com.cognizant.core.TestConfigurationsLite;
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.SeleniumTestParameters;

import pages.FlightFinderPage;
import pages.SignOnPage;
import pages.UserRegistrationConfirmationPage;
import pages.UserRegistrationPage;

/**
 * Test for login with newly registered user
 * 
 * @author Cognizant
 */
public class TestForLoginWithNewlyRegisteredUser extends TestConfigurationsLite {

	private static final String GENERAL_DATA = "General_Data";

	@Test(dataProvider = "ChromeBrowser", dataProviderClass = TestConfigurationsLite.class)
	public void testRunner(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for book flight tickets and verify booking");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));

	}

	@Override
	public void executeTest() {
		registerUser();

		String userName = dataTable.getData(GENERAL_DATA, "Username");

		if (driverUtil.isTextPresent("^[\\s\\S]*Dear " + dataTable.getExpectedResult("FirstName") + " "
				+ dataTable.getExpectedResult("LastName") + "[\\s\\S]*$")) {
			report.updateTestLog("Verify Registration", "User " + userName + " registered successfully", Status.PASS);
		} else {
			throw new FrameworkException("Verify Registration", "User " + userName + " registration failed");
		}

		clickSignIn();

		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage = signOnPage.loginAsInvalidUser();

		// The login fails if the sign-on page is displayed again
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);

	}

	@Override
	public void tearDown() {
		report.addTestLogSection("TearDown");
		logout();
	}

	public void registerUser() {
		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		UserRegistrationPage userRegistrationPage = signOnPage.clickRegister();
		userRegistrationPage.registerUser();
	}

	public void clickSignIn() {
		UserRegistrationConfirmationPage userRegistrationConfirmationPage = new UserRegistrationConfirmationPage(
				scriptHelper);
		userRegistrationConfirmationPage.clickSignIn();
	}

	public void logout() {
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.logout();
	}
}