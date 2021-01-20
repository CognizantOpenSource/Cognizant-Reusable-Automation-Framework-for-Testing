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
package pages;

import org.openqa.selenium.By;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ScriptHelper;
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
	private final By txtLogin = By.name("password");

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
		driver.findElement(txtLogin).sendKeys(passwordData);

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