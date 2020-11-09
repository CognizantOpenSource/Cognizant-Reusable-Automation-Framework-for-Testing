package pages;

import org.openqa.selenium.By;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * UserRegistrationConfirmationPage class
 * 
 * @author Cognizant
 */
public class UserRegistrationConfirmationPage extends MasterPage {
	// UI Map object definitions

	// Links
	private final By lnkSignIn = By.linkText("sign-in");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public UserRegistrationConfirmationPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Register")) {
			report.updateTestLog("Verify page title",
					"User Registration Confirmation page expected, but not displayed!", Status.WARNING);
		}
	}

	public Boolean isUserRegistered() {
		return driverUtil.isTextPresent("^[\\s\\S]*Dear " + dataTable.getExpectedResult("FirstName") + " "
				+ dataTable.getExpectedResult("LastName") + "[\\s\\S]*$");
	}

	public SignOnPage clickSignIn() {
		report.updateTestLog("Click sign-in", "Click the sign-in link", Status.DONE);
		driver.findElement(lnkSignIn).click();
		return new SignOnPage(scriptHelper);
	}
}