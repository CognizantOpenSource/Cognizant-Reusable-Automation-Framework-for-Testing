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
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;

import pages.SignOnPage;
import pages.UserRegistrationConfirmationPage;
import pages.UserRegistrationPage;

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