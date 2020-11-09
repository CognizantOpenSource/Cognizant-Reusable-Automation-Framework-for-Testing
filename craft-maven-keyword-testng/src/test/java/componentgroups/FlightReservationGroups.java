package componentgroups;

import com.cognizant.craft.*;

import businesscomponents.*;


/**
 * Class for storing component groups related to the flight reservation functionality
 * @author Cognizant
 */
public class FlightReservationGroups extends ReusableLibrary {
	/**
	 * Constructor to initialize the component group library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public FlightReservationGroups(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	public void findAndBookFlights() {
		FlightReservationComponents flightReservationComponents =
											new FlightReservationComponents(scriptHelper);
		
		flightReservationComponents.findFlights();
		flightReservationComponents.selectFlights();
		flightReservationComponents.bookFlights();
		flightReservationComponents.verifyBooking();
		flightReservationComponents.backToFlights();
	}
}