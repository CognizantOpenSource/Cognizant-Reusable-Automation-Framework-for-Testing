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

import org.openqa.selenium.WebElement;

import com.cognizant.core.ScriptHelper;

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