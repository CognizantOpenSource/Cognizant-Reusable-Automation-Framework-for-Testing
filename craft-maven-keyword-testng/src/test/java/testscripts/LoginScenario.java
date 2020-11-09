package testscripts;

import org.testng.annotations.Test;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.TestConfigurations;
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
