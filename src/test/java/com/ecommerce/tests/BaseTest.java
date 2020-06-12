package com.ecommerce.tests;

import java.awt.Desktop;
import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ecommerce.constants.Constants;
import com.ecommerce.reports.ExtentReport;
import com.ecommerce.browser.Driver;
import com.ecommerce.browser.DriverManager;

public class BaseTest {


	@BeforeMethod
	public void setUp() {
		try {
			Driver.initialize();
			//Driver.loadUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	@AfterMethod
	public void wrapUp() {
		DriverManager.getDriver().quit();
	}
	

	@BeforeSuite
	public void beforeSuite() throws Exception {
		ExtentReport.initialize();
		
	}

	@AfterSuite
	public void afterSuite() throws Exception {

		ExtentReport.report.flush();
		File htmlFile = new File(Constants.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());
		
	}
}
