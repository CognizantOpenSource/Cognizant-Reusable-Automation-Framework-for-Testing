package testscripts.FlightReservationScenario;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.TestConfigurationsLite;
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