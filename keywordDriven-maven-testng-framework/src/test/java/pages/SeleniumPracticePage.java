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

public class SeleniumPracticePage {
	
	public static final By homeLink = By.linkText("Techlistic");
	
	public static final By txtFirstName = By.name("firstname");
	public static final By txtLastName = By.name("lastname");
	
	public static final By btnGender = By.id("sex-0");
	public static final By btnExp = By.id("exp-6");
	public static final By btnProf = By.id("profession-0");
	
	public static final By btnToolUFT = By.id("tool-0");
	public static final By btnToolProtractor = By.id("tool-1");
	public static final By btnToolSelenium =By.id("tool-2");
	
	public static final By selectContinents =By.id("continents");
	public static final By btnSubmit =By.id("submit");

}
