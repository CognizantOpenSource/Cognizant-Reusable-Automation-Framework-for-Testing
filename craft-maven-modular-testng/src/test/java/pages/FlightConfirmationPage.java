package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * FlightConfirmationPage class
 * 
 * @author Cognizant
 */
public class FlightConfirmationPage extends MasterPage {
	// UI Map object definitions

	// Labels
	public static final By lblConfirmationMessage = By.cssSelector("font > font > b > font");
	// By.xpath("//font/font/b/font");

	// Images
	public static final By imgFlights = By.xpath("//a/img");

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public FlightConfirmationPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Flight Confirmation")) {
			report.updateTestLog("Verify page title", "Flight Confirmation page expected, but not displayed!",
					Status.WARNING);
		}
	}

	public Boolean isTicketBooked() {
		return driverUtil.isTextPresent("^[\\s\\S]*Your itinerary has been booked![\\s\\S]*$");
	}

	public FlightConfirmationPage extractFlightConfirmationNumber() {
		WebElement flightConfirmation = driver.findElement(lblConfirmationMessage);

		String flightConfirmationNumber = flightConfirmation.getText();
		flightConfirmationNumber = flightConfirmationNumber.split("#")[1].trim();
		report.updateTestLog("Flight Confirmation", "The flight confirmation number is " + flightConfirmationNumber,
				Status.DONE);

		return this; // No need to instantiate a new object, since there is no
						// actual page navigation involved here
	}

	public FlightFinderPage backToFlights() {
		driver.findElement(imgFlights).click();
		return new FlightFinderPage(scriptHelper);
	}
}