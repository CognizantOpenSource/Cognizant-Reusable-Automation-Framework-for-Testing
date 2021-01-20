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
package com.cognizant.framework;

import java.io.File;

/**
 * Singleton class which manages the creation of timestamped result folders for
 * every test batch execution
 * 
 * @author Cognizant
 */
public class TimeStamp {
	public static volatile String reportPathWithTimeStamp;
	public static volatile String reportPathScreenShot;

	private TimeStamp() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the timestamped result folder path
	 * 
	 * @return The timestamped result folder path
	 */
	public static String getInstance() {
		if (reportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (reportPathWithTimeStamp == null) { // Double-checked locking
					String timeStamp = "Run_" + Util.getCurrentFormattedTime("dd-MMM-yyyy hh:mm:ss a").replace(" ", "_")
							.replace(":", "-");

					reportPathWithTimeStamp = WhitelistingPath
							.cleanStringForFilePath(Util.getResultsPath() + Util.getFileSeparator() + timeStamp);

					new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		}

		return reportPathWithTimeStamp;
	}

	public static String getScreenShotInstanace() {

		if (reportPathScreenShot == null) {
			synchronized (TimeStamp.class) {
				if (reportPathScreenShot == null) { // Double-checked locking

					reportPathScreenShot = reportPathWithTimeStamp + Util.getFileSeparator() + "ScreenShots";

					new File(reportPathScreenShot).mkdirs();

				}
			}
		}
		return reportPathScreenShot;

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}