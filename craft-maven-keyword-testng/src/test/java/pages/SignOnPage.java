package pages;

import org.openqa.selenium.By;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * SignOnPage class
 * 
 * @author Cognizant
 */
public class SignOnPage extends MasterPage {
	// UI Map object definitions

	// Text boxes
	private final By txtUsername = By.name("userName");
	private final By txtPassword = By.name("password");

	// Buttons
	private final By btnLogin = By.name("login");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public SignOnPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Welcome") && !driver.getTitle().contains("Sign-on")) {
			report.updateTestLog("Verify page title", "Sign-on page expected, but not displayed!", Status.WARNING);
		}
	}

	public FlightFinderPage loginAsValidUser() {
		login();
		return new FlightFinderPage(scriptHelper);
	}

	private void login() {
		String userNameData = dataTable.getData("General_Data", "Username");
		String passwordData = dataTable.getData("General_Data", "Password");

		driver.findElement(txtUsername).sendKeys(userNameData);
		driver.findElement(txtPassword).sendKeys(passwordData);

		report.updateTestLog("Login",
				"Enter login credentials: " + "Username = " + userNameData + ", " + "Password = " + passwordData,
				Status.DONE);
		driver.findElement(btnLogin).click();
	}

	public SignOnPage loginAsInvalidUser() {
		login();
		return new SignOnPage(scriptHelper); // Return new object to indicate an
												// actual page navigation
	}
}