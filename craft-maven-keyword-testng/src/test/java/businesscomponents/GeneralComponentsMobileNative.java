package businesscomponents;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

import pages.EriBankPage;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class GeneralComponentsMobileNative extends ReusableLibrary {

	EriBankPage eribankPage = new EriBankPage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public GeneralComponentsMobileNative(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void loginEriBank() {
		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");

		eribankPage.txtUsername.sendKeys(userName);
		eribankPage.txtPassword.sendKeys(password);
		eribankPage.btnLogin.click();
		report.updateTestLog("LoginEribank", "Logged in Succesfully", Status.PASS);
	}

	public void makePayment() throws InterruptedException {

		eribankPage.btnMakePayment.click();

		report.updateTestLog("Payment", "Making the Payment", Status.PASS);
	}

	public void enterPaymentDetails() {

		eribankPage.txtPhone.sendKeys(dataTable.getData("General_Data", "Phone"));
		eribankPage.txtName.sendKeys(dataTable.getData("General_Data", "Name"));
		eribankPage.txtAmount.sendKeys(dataTable.getData("General_Data", "Amount"));
		driver.hideKeyboard();
		eribankPage.btnCountrySelect.click();
		eribankPage.btnCountryValue.click();

		report.updateTestLog("Enter Details", "Entered Details Successfuly", Status.PASS);

		eribankPage.btnSendPayment.click();
		eribankPage.btnYes.click();

		report.updateTestLog("Payment", "Payment Succesfull", Status.PASS);
	}

	public void logoutEriBank() {

		eribankPage.btnLogout.click();
		report.updateTestLog("Logout", "Logged Out Succesfully", Status.PASS);
	}

}