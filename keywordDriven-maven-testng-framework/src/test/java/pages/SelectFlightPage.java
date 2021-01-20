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

import org.openqa.selenium.By;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * SelectFlightPage class
 * 
 * @author Cognizant
 */
public class SelectFlightPage extends MasterPage {
	// UI Map object definitions

	// Buttons
	private final By btnContinue = By.name("reserveFlights");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public SelectFlightPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Select a Flight")) {
			report.updateTestLog("Verify page title", "Select a Flight page expected, but not displayed!",
					Status.WARNING);
		}
	}

	public BookFlightPage selectFlights() {
		report.updateTestLog("Select Flights", "Select the first available flights", Status.DONE);
		driver.findElement(btnContinue).click();
		return new BookFlightPage(scriptHelper);
	}
}