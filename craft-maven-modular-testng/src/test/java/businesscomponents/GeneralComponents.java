package businesscomponents;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

import pages.FlightFinderPage;
import pages.SignOnPage;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class GeneralComponents extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public GeneralComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void invokeApplication() {
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));
		
	}

	public void loginAsValidUser() {
		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage.loginAsValidUser();

		// The login succeeds if the flight finder page is displayed
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
	}

	public void loginAsInValidUser() {
		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage = signOnPage.loginAsInvalidUser();

		// The login fails if the sign-on page is displayed again
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);
	}

	public void logout() {
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.logout();
	}
}