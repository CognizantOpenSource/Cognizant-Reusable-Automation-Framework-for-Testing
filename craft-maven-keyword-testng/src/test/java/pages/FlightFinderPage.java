package pages;

import org.openqa.selenium.By;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * FlightFinderPage class
 * 
 * @author Cognizant
 */
public class FlightFinderPage extends MasterPage {
	// UI Map object definitions

	// Combo boxes
	private final By cmbPassengerCount = By.name("passCount");
	private final By cmbDepartFrom = By.name("fromPort");
	private final By cmbDepartMonth = By.name("fromMonth");
	private final By cmbDepartDate = By.name("fromDay");
	private final By cmbArriveAt = By.name("toPort");
	private final By cmbArriveMonth = By.name("toMonth");
	private final By cmbArriveDate = By.name("toDay");
	private final By cmbAirline = By.name("airline");

	// Buttons
	private final By btnContinue = By.name("findFlights");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public FlightFinderPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Find a Flight")) {
			report.updateTestLog("Verify page title", "Find a Flight page expected, but not displayed!",
					Status.WARNING);
		}
	}

	public SelectFlightPage findFlights() {
		driver.findElement(cmbPassengerCount).sendKeys((dataTable.getData("Passenger_Data", "PassengerCount")));
		driver.findElement(cmbDepartFrom).sendKeys((dataTable.getData("Flights_Data", "FromPort")));
		driver.findElement(cmbDepartMonth).sendKeys((dataTable.getData("Flights_Data", "FromMonth")));
		driver.findElement(cmbDepartDate).sendKeys((dataTable.getData("Flights_Data", "FromDay")));
		driver.findElement(cmbArriveAt).sendKeys((dataTable.getData("Flights_Data", "ToPort")));
		driver.findElement(cmbArriveMonth).sendKeys((dataTable.getData("Flights_Data", "ToMonth")));
		driver.findElement(cmbArriveDate).sendKeys((dataTable.getData("Flights_Data", "ToDay")));
		driver.findElement(cmbAirline).sendKeys((dataTable.getData("Flights_Data", "Airline")));

		report.updateTestLog("Find Flights", "Search for flights using given test data", Status.DONE);
		driver.findElement(btnContinue).click();
		return new SelectFlightPage(scriptHelper);
	}
}