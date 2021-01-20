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