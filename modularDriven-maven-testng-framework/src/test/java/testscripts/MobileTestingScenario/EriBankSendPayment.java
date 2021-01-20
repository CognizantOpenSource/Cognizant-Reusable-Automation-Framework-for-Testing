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
package testscripts.MobileTestingScenario;

import org.testng.annotations.Test;

import com.cognizant.core.DriverScript;
import com.cognizant.core.TestConfigurationsLite;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.SeleniumTestParameters;

import pages.EriBankPage;

public class EriBankSendPayment extends TestConfigurationsLite {

	EriBankPage eribankPage = new EriBankPage(scriptHelper);

	@Test(dataProvider = "ChromeBrowser", dataProviderClass = TestConfigurationsLite.class)
	public void seleniumPracticeTest(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for Sample Selenium Test");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	}

	@Override
	public void setUp() {
		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");

		eribankPage.txtUsername.sendKeys(userName);
		eribankPage.txtPassword.sendKeys(password);
		eribankPage.btnLogin.click();
		report.updateTestLog("LoginEribank", "Logged in Succesfully", Status.PASS);
	}

	@Override
	public void executeTest() {

		makePayment();
		enterPaymentDetails();
	}

	@Override
	public void tearDown() {
		logoutEriBank();
	}

	public void makePayment() {

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
