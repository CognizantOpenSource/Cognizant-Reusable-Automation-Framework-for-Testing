package pages;

import org.openqa.selenium.WebElement;

import com.cognizant.craft.ScriptHelper;

import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * UI Map for EriBank
 */
public class EriBankPage extends MasterPageMobile {

	// text Fields
	@AndroidFindBy(id = "com.experitest.ExperiBank:id/usernameTextField")
	public WebElement txtUsername;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/passwordTextField")
	public WebElement txtPassword;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/phoneTextField")
	public WebElement txtPhone;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/nameTextField")
	public WebElement txtName;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/amountTextField")
	public WebElement txtAmount;

	// Buttons
	@AndroidFindBy(id = "com.experitest.ExperiBank:id/loginButton")
	public WebElement btnLogin;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/makePaymentButton")
	public WebElement btnMakePayment;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/countryButton")
	public WebElement btnCountrySelect;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Norway']")
	public WebElement btnCountryValue;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/sendPaymentButton")
	public WebElement btnSendPayment;

	@AndroidFindBy(id = "android:id/button1")
	public WebElement btnYes;

	@AndroidFindBy(id = "com.experitest.ExperiBank:id/logoutButton")
	public WebElement btnLogout;

	public EriBankPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

}