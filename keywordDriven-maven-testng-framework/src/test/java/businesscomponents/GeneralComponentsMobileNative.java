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
package businesscomponents;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;
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