package com.ecommerce.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.ecommerce.browser.Driver;
import com.ecommerce.browser.DriverManager;

public class BaseTest {


	@BeforeMethod
	public void setUp() {
		try {
			Driver.initialize();
			Driver.loadUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	@AfterMethod
	public void wrapUp() {
		//DriverManager.getDriver().close();
	}
}
