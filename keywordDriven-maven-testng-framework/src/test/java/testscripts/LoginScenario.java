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
package testscripts;

import org.testng.annotations.Test;

import com.cognizant.core.DriverScript;
import com.cognizant.core.TestConfigurations;
import com.cognizant.framework.selenium.SeleniumTestParameters;

public class LoginScenario extends TestConfigurations {

	@Test(dataProvider = "ChromeHeadless", dataProviderClass = TestConfigurations.class)
	public void testForInValidLogin(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for login with invalid user credentials");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

	@Test(dataProvider = "ChromeHeadless", dataProviderClass = TestConfigurations.class)
	public void testForLoginWithNewlyRegisteredUser(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for Newly Registered user");
		
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

	@Test(dataProvider = "ChromeHeadless", dataProviderClass = TestConfigurations.class)
	public void testForValidLogin(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for login with valid user credentials");
		
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

}
