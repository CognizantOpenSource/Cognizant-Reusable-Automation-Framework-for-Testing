package businesscomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
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
