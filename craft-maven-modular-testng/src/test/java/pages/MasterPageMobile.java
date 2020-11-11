package pages;

import org.openqa.selenium.support.PageFactory;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MasterPageMobile extends ReusableLibrary {

	/**
	 * Constructor to initialize the functional library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	protected MasterPageMobile(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(new AppiumFieldDecorator(driver.getAppiumDriver()), this);
	}

}
