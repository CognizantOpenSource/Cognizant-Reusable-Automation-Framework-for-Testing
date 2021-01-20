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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;
import com.cognizant.framework.Status;

import pages.SeleniumPracticePage;

public class SeleniumPracticeComponents extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper The {@link ScriptHelper} object passed from the
	 *                     {@link DriverScript}
	 */
	public SeleniumPracticeComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void launchSeleniumUrl() {

		driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
		report.updateTestLog("Launch URL", "Application launched Successfully", Status.DONE);
	}

	public void verifyHome() {

		driverUtil.waitUntilElementLocated(SeleniumPracticePage.homeLink, 30);
		report.updateTestLog("Home Page", "Navigated to Home Page", Status.PASS);
	}

	public void enterDetails() {
		
		String firstName = dataTable.getData("General_Data", "FirstName");
		String lastName = dataTable.getData("General_Data", "LastName");
		
		driver.findElement(SeleniumPracticePage.txtFirstName).sendKeys(firstName);
		driver.findElement(SeleniumPracticePage.txtLastName).sendKeys(lastName);
		
		driver.findElement(SeleniumPracticePage.btnGender).click();
		
		driver.findElement(SeleniumPracticePage.btnExp).click();
		
		driver.findElement(SeleniumPracticePage.btnProf).click();
		
		driver.findElement(SeleniumPracticePage.btnToolUFT).click();
		driver.findElement(SeleniumPracticePage.btnToolProtractor).click();
		driver.findElement(SeleniumPracticePage.btnToolSelenium).click();
		
		WebElement ele = driver.findElement(SeleniumPracticePage.selectContinents);
		Select select = new Select(ele);
		select.selectByVisibleText(dataTable.getData("General_Data", "Country"));
		
		report.updateTestLog("Details", "Entered Details successfully", Status.PASS);
	}

	public void submitDetails() {
		
		driver.findElement(By.id("submit")).click();
		report.updateTestLog("Submit", "Details Submitted", Status.PASS);
	}
}
