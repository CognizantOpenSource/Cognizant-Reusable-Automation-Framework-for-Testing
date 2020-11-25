package com.cognizant.framework.selenium;

/**
 * Enumeration to represent the browser to be used for execution
 * 
 * @author Cognizant
 */
public enum Browser {
	CHROME("chrome"), FIREFOX("firefox"), GHOST_DRIVER("phantomjs"), INTERNET_EXPLORER("internet explorer"),
	SAFARI("safari"), CHROME_HEADLESS("chrome_headless"), EDGE("edge");

	private String value;

	Browser(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}