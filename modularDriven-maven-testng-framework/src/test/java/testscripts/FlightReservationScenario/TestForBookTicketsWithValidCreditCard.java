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
package testscripts.FlightReservationScenario;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cognizant.core.DriverScript;
import com.cognizant.core.TestConfigurationsLite;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.SeleniumTestParameters;

import pages.BookFlightPage;
import pages.FlightConfirmationPage;
import pages.FlightFinderPage;
import pages.SelectFlightPage;
import pages.SignOnPage;

/**
 * Test for book flight tickets and verify booking
 * 
 * @author Cognizant
 */
public class TestForBookTicketsWithValidCreditCard extends TestConfigurationsLite {
	
	
	private static final String FLIGHTS_DATA = "Flights_Data";

	@Test(dataProvider = "ChromeBrowser", dataProviderClass = TestConfigurationsLite.class)
	public void testRunner(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for book flight tickets and verify booking");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");
		
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

		driver.get(properties.getProperty("ApplicationUrl"));

		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage.loginAsValidUser();
	}

	@Override
	public void executeTest() {

		findFlights();
		selectFlights();
		bookFlights();
		verifyBooking();
		backToFlights();
	}

	@Override
	public void tearDown() {
		report.addTestLogSection("Teardown");

		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.logout();
	}

	public void findFlights() {
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.findFlights();
	}

	public void selectFlights() {
		SelectFlightPage selectFlightPage = new SelectFlightPage(scriptHelper);
		selectFlightPage.selectFlights();
	}

	public void bookFlights() {
		BookFlightPage bookFlightPage = new BookFlightPage(scriptHelper);
		bookFlightPage.bookFlights();
	}

	public void verifyBooking() {
		if (driverUtil.isTextPresent("^[\\s\\S]*Your itinerary has been booked![\\s\\S]*$")) {
			report.updateTestLog("Verify Booking", "Tickets booked successfully", Status.PASS);

			WebElement flightConfirmation = driver.findElement(FlightConfirmationPage.lblConfirmationMessage);

			String flightConfirmationNumber = flightConfirmation.getText();
			flightConfirmationNumber = flightConfirmationNumber.split("#")[1].trim();
			dataTable.putData(FLIGHTS_DATA, "FlightConfirmationNumber", flightConfirmationNumber);
			report.updateTestLog("Flight Confirmation", "The flight confirmation number is " + flightConfirmationNumber,
					Status.SCREENSHOT);
		} else {
			report.updateTestLog("Verify Booking", "Tickets booking failed", Status.FAIL);
		}
	}

	public void backToFlights() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(scriptHelper);
		flightConfirmationPage.backToFlights();
	}

}