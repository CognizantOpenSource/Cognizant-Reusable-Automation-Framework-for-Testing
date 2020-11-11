package pages;

import org.openqa.selenium.By;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * BookFlightPage class
 * 
 * @author Cognizant
 */
public class BookFlightPage extends MasterPage {
	// UI Map object definitions
	private String firstName = "passFirst";
	private String lastName = "passLast";
	private final By txtCardNo = By.name("creditnumber");

	// Combo boxes
	private final By cmbCreditCard = By.name("creditCard");

	// Buttons
	private final By btnSecurePurchase = By.name("buyFlights");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public BookFlightPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Book a Flight")) {
			report.updateTestLog("Verify page title", "Book a Flight page expected, but not displayed!",
					Status.WARNING);
		}
	}

	public FlightConfirmationPage bookFlights() {
		String[] passengerFirstNames = dataTable.getData("Passenger_Data", "PassengerFirstNames").split(",");
		String[] passengerLastNames = dataTable.getData("Passenger_Data", "PassengerLastNames").split(",");
		int passengerCount = Integer.parseInt(dataTable.getData("Passenger_Data", "PassengerCount"));

		for (int i = 0; i < passengerCount; i++) {
			driver.findElement(By.name(firstName + i)).sendKeys(passengerFirstNames[i]);
			driver.findElement(By.name(lastName + i)).sendKeys(passengerLastNames[i]);
		}

		driver.findElement(cmbCreditCard).sendKeys(dataTable.getData("Passenger_Data", "CreditCard"));
		driver.findElement(txtCardNo).sendKeys(dataTable.getData("Passenger_Data", "CreditNumber"));
		driver.findElement(btnSecurePurchase).click();
		report.updateTestLog("Book Tickets", "Enter passenger details and book tickets", Status.DONE);

		return new FlightConfirmationPage(scriptHelper);
	}
}