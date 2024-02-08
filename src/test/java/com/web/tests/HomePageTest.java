package com.web.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.web.base.Base;
import com.web.pages.LoginPage;
import com.web.pages.RegisterPage;
import com.web.pages.WebsiteHomePage;

public class HomePageTest extends Base {

	public WebDriver driver;
	WebsiteHomePage homePage;
	LoginPage loginPage;
	RegisterPage registerPage;

	public HomePageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = BrowserInitialization(prop.getProperty("browser"));
		homePage = new WebsiteHomePage(driver);
		loginPage = new LoginPage(driver);
		registerPage = new RegisterPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void clickOnCreateAnAccountLinkForNewRegister() {

		homePage.clickOnCreateAnAccountToRegister();
	}

	@Test
	public void clickOnSigInLinkForLogin() {

		homePage.clickOnSignInToLogin();
	}

}
