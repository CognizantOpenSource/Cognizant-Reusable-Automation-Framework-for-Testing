package com.cognizant.pages;

import org.openqa.selenium.support.PageFactory;

import com.cognizant.framework.DriverManager;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MasterPageMobile {

	/**
	 * Constructor to initialize the functional library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	protected MasterPageMobile() {

		PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getAppiumDriver()), this);
	}

}
