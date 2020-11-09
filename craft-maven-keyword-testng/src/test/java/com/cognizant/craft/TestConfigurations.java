package com.cognizant.craft;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.ExecutionMode;
import com.cognizant.framework.selenium.MobileExecutionPlatform;
import com.cognizant.framework.selenium.SeleniumParametersBuilders;
import com.cognizant.framework.selenium.ToolName;

public class TestConfigurations extends CRAFTTestCase {

	@DataProvider(name = "ChromeHeadless")
	public Object[][] desktopBrowsers(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME_HEADLESS).build() } };
	}

	@DataProvider(name = "NativeAndroid")
	public Object[][] mobileDevice(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").executionMode(ExecutionMode.MOBILE)
				.mobileExecutionPlatform(MobileExecutionPlatform.ANDROID).toolName(ToolName.APPIUM)
				.deviceName("1215fc22b4101e04").build() } };
	}


	@DataProvider(name = "ChromeParallel", parallel = true)
	public Object[][] desktopBrowsersParallel(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance1")
						.executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance2")
						.executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance3")
						.executionMode(ExecutionMode.LOCAL).browser(Browser.CHROME).build() } };
	}

}