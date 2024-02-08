package com.web.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsClass {

	public static ExtentReports generateExtentReport() throws IOException {

		ExtentReports extentReports = new ExtentReports();
		File ExtentReportFile = new File(
				System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ExtentReportFile);

		sparkReporter.config().setDocumentTitle("Extent Report : Usha 1st Project");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Extent Report - Luma Project Reports");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy");
		sparkReporter.config().getReporter();

		extentReports.attachReporter(sparkReporter);

		Properties configExtentprop = new Properties();
		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/web/Config/config.properties");
		FileInputStream fis = new FileInputStream(file);
		configExtentprop.load(fis);

		extentReports.setSystemInfo("Application URL", configExtentprop.getProperty("Url"));
		extentReports.setSystemInfo("Browser Name", configExtentprop.getProperty("browser"));
		extentReports.setSystemInfo("Operating System", System.getProperty("os.version"));
		extentReports.setSystemInfo("Java-Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("UserName", System.getProperty("user.name"));

		return extentReports;
	}
}
