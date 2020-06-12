package com.ecommerce.listeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.ecommerce.utils.TestUtils;
import com.ecommerce.reports.ExtentManager;
import com.ecommerce.reports.ExtentReport;
import com.ecommerce.reports.LogStatus;




/*
 * Listener class which is implementing ITestListener and hence we can use this to dynamically create reports, write logs.
 */
public class ListenerClass implements ITestListener{
	
	private static String TestcaseName;

	

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	@Override
	public void onTestStart(ITestResult result) {
		TestcaseName =result.getMethod().getDescription();
		setTestcaseName(TestcaseName);
		ExtentManager.setExtentTest(ExtentReport.report.startTest(TestcaseName));
		LogStatus.pass(TestcaseName+ " is started successfully");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		LogStatus.pass(result.getMethod().getDescription()+ " test case is passed");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		LogStatus.fail(result.getMethod().getDescription()+ " is failed");
		LogStatus.fail(result.getThrowable().toString());
		LogStatus.fail("Failed",TestUtils.pullScreenshotPath());
		ExtentReport.report.endTest(ExtentManager.getExtTest());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		LogStatus.skip(result.getMethod().getDescription()+ " is skipped");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	@Override
	public void onStart(ITestContext context) {
	
		
	}

	public void onFinish(ITestContext context) {
		ExtentReport.report.endTest(ExtentManager.getExtTest());
		
	}



}
