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
package com.cognizant.framework.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Report;
import com.cognizant.framework.ReportSettings;
import com.cognizant.framework.ReportTheme;

/**
 * Class which extends the {@link Report} class with a Selenium specific
 * override for taking screenshots
 * 
 * @author Cognizant
 */
public class SeleniumReport extends Report {

	private CustomDriver driver;
	private final SeleniumTestParameters testParameters;

	/**
	 * Constructor to initialize the Report object
	 * 
	 * @param reportSettings The {@link ReportSettings} object
	 * @param reportTheme    The {@link ReportTheme} object
	 * @param testParameters
	 */
	public SeleniumReport(ReportSettings reportSettings, ReportTheme reportTheme,
			SeleniumTestParameters testParameters) {
		super(reportSettings, reportTheme, testParameters);
		this.testParameters = testParameters;
	}

	/**
	 * Function to set the {@link CustomDriver} object
	 * 
	 * @param driver The {@link CustomDriver} object
	 */

	public void setDriver(CustomDriver driver) {
		this.driver = driver;
	}

	@Override
	protected void takeScreenshot(String screenshotPath) {
		if (driver == null) {
			throw new FrameworkException("Report.driver is not initialized!");
		}
		File scrFile = null;
		switch (testParameters.getExecutionMode()) {

		case LOCAL:
		case GRID:
		case MOBILE:

			try {
				if ("RemoteWebDriver".equals(driver.getWebDriver().getClass().getSimpleName())) {
					Capabilities capabilities = ((RemoteWebDriver) driver.getWebDriver()).getCapabilities();
					if ("htmlunit".equals(capabilities.getBrowserName())) {
						return; // Screenshots not supported in headless mode
					}
					WebDriver augmentedDriver = new Augmenter().augment(driver.getWebDriver());
					scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				} else {
					scrFile = ((TakesScreenshot) driver.getWebDriver()).getScreenshotAs(OutputType.FILE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new FrameworkException("Error while capturing the screenshot");
			}

			break;

		}

		if (!(scrFile == null)) {
			try {
				FileUtils.copyFile(scrFile, new File(screenshotPath), true);
			} catch (IOException e) {
				e.printStackTrace();
				throw new FrameworkException("Error while writing screenshot to file");
			}
		}

	}

}