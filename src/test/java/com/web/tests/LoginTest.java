package com.web.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.web.base.Base;
import com.web.pages.AccountPage;
import com.web.pages.LoginPage;
import com.web.pages.WebsiteHomePage;
import com.web.utilities.Utilities;

import graphql.Assert;

public class LoginTest extends Base {
	public WebDriver driver;

	WebsiteHomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;

	public LoginTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException {
		driver = BrowserInitialization(prop.getProperty("browser"));

		homePage = new WebsiteHomePage(driver);
		loginPage = new LoginPage(driver);
		accountPage = new AccountPage(driver);

		loginPage = homePage.clickOnSignInToLogin();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "supplyTestDataFromExcel")
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException {

		loginPage.verifyLoginWithCredentials(email, password);
		Assert.assertTrue(accountPage.verifySucessfulLogin());

	}

	@DataProvider
	public Object[][] supplyTestDataFromExcel() {

		Object[][] data = Utilities.getTestDataFromExcel("Sheet 1");
		return data;

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {

		loginPage.verifyLoginWithCredentials(Utilities.generateTimeStamp(), dataProp.getProperty("invalidPassword"));

		String actualErrorMessage = loginPage.verifyErrorMessage();
		String ExpectedErrorMessage = dataProp.getProperty("ExpectedErrorMessage");

		Assert.assertTrue(actualErrorMessage.contains(ExpectedErrorMessage));

	}

}
