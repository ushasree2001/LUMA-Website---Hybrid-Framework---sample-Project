package com.web.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.web.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	/*
	 * Base class contains the below initializations
	 *
	 * WebDriver Properties Logs Extent Reports DataBase ExcelReading Mail
	 *
	 */

	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() throws IOException {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/web/Config/config.properties");
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);

		dataProp = new Properties();
		File dataPropFile = new File(
				System.getProperty("user.dir") + "/src/main/java/com/web/Config/testdata.properties");
		FileInputStream dataFis = new FileInputStream(dataPropFile);
		dataProp.load(dataFis);

	}

	// step 2 - Browser configuration

	public WebDriver BrowserInitialization(String browser) {
		if (prop.getProperty("browser").equalsIgnoreCase("safari")) {
			driver = new SafariDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("CHROME")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		} else if (prop.getProperty("browser").equalsIgnoreCase("Edge")) {
			EdgeOptions edOptions = new EdgeOptions();
			edOptions.addArguments("--guest");

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(edOptions);
		}

		// step 3 - Url config
		driver.get(prop.getProperty("Url"));

		// step 4 - window actions like maximize
		driver.manage().window().maximize();

		// step 5 - implement waits & delete cookies
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));

		return driver;

	}

}
