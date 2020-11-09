package pages;

import org.openqa.selenium.By;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * UserRegistrationPage class
 * 
 * @author Cognizant
 */
public class UserRegistrationPage extends MasterPage {
	// UI Map object definitions

	// Text boxes
	private final By txtFirstName = By.name("firstName");
	private final By txtLastName = By.name("lastName");
	private final By txtPhone = By.name("phone");
	private final By txtEmail = By.name("userName");
	private final By txtAddressLine1 = By.name("address1");
	private final By txtCity = By.name("city");
	private final By txtState = By.name("state");
	private final By txtPostalCode = By.name("postalCode");
	private final By txtUsername = By.name("email");
	private final By txtPassword = By.name("password");
	private final By txtConfirmPassword = By.name("confirmPassword");

	// Buttons
	private final By btnRegister = By.name("register");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public UserRegistrationPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Register")) {
			report.updateTestLog("Verify page title", "User Registration page expected, but not displayed!",
					Status.WARNING);
		}
	}

	public UserRegistrationConfirmationPage registerUser() {
		report.updateTestLog("Enter user details", "Enter new user details for registration", Status.DONE);
		driver.findElement(txtFirstName).sendKeys(dataTable.getData("RegisterUser_Data", "FirstName"));
		driver.findElement(txtLastName).sendKeys(dataTable.getData("RegisterUser_Data", "LastName"));
		driver.findElement(txtPhone).sendKeys(dataTable.getData("RegisterUser_Data", "Phone"));
		driver.findElement(txtEmail).sendKeys(dataTable.getData("RegisterUser_Data", "Email"));
		driver.findElement(txtAddressLine1).sendKeys(dataTable.getData("RegisterUser_Data", "Address"));
		driver.findElement(txtCity).sendKeys(dataTable.getData("RegisterUser_Data", "City"));
		driver.findElement(txtState).sendKeys(dataTable.getData("RegisterUser_Data", "State"));
		driver.findElement(txtPostalCode).sendKeys(dataTable.getData("RegisterUser_Data", "PostalCode"));
		driver.findElement(txtUsername).sendKeys(dataTable.getData("General_Data", "Username"));
		String passwordData = dataTable.getData("General_Data", "Password");
		driver.findElement(txtPassword).sendKeys(passwordData);
		driver.findElement(txtConfirmPassword).sendKeys(passwordData);
		report.updateTestLog("Registration", "Enter user details for registration", Status.DONE);
		driver.findElement(btnRegister).click();

		return new UserRegistrationConfirmationPage(scriptHelper);
	}
}