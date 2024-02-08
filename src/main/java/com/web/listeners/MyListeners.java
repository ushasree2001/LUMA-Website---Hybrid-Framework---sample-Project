package com.web.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.web.utilities.ExtentReportsClass;
import com.web.utilities.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, "Now working with : " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test Executed Successfully : " + testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {

			e.printStackTrace();
		}

		// capture Screenshot
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);

		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable()); // will get all exceptions that caused the test to fail
		extentTest.log(Status.FAIL, "Test got Failed: " + testName);
		extentTest = extentReport.createTest(testName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, "Test got Skipped : " + testName);
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("***********Test Execution Started***************");
		try {
			extentReport = ExtentReportsClass.generateExtentReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println("***********Test Execution Finished***************");
		extentReport.flush();

		// to auto open extent report
		String PathOfExtentReports = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html";
		File file = new File(PathOfExtentReports);
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
