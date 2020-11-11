package businesscomponents;

import com.cognizant.craft.*;
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;

import pages.*;

/**
 * Class for storing business components related to the user registration
 * functionality
 * 
 * @author Cognizant
 */
public class UserRegistrationComponents extends ReusableLibrary {
	private static final String GENERAL_DATA = "General_Data";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public UserRegistrationComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
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


	public void verifyRegistration() {
		String userName = dataTable.getData(GENERAL_DATA, "Username");

		if (driverUtil.isTextPresent("^[\\s\\S]*Dear " + dataTable.getExpectedResult("FirstName") + " "
				+ dataTable.getExpectedResult("LastName") + "[\\s\\S]*$")) {
			report.updateTestLog("Verify Registration", "User " + userName + " registered successfully", Status.PASS);
		} else {
			throw new FrameworkException("Verify Registration", "User " + userName + " registration failed");
		}
	}

}