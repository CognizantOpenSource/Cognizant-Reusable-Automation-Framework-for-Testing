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

import org.openqa.selenium.support.PageFactory;

import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MasterPageMobile extends ReusableLibrary {

	/**
	 * Constructor to initialize the functional library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *
	 */
	protected MasterPageMobile(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(new AppiumFieldDecorator(driver.getAppiumDriver()), this);
	}

}
