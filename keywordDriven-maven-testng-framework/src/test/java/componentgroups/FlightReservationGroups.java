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
package componentgroups;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;

import businesscomponents.FlightReservationComponents;


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