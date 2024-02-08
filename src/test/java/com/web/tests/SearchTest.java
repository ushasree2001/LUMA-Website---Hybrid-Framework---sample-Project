package com.web.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.web.base.Base;
import com.web.pages.LoginPage;
import com.web.pages.SearchPage;
import com.web.pages.WebsiteHomePage;

public class SearchTest extends Base {
	WebsiteHomePage homePage;
	LoginPage loginPage;
	SearchPage searchPage;
	public WebDriver driver;

	public SearchTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver = BrowserInitialization(prop.getProperty("browser"));
		homePage = new WebsiteHomePage(driver);
		searchPage = new SearchPage(driver);
		loginPage = new LoginPage(driver);

		// click on sigin link in homepage to login
		loginPage = homePage.clickOnSignInToLogin();

		// login in loginpage
		loginPage.verifyLoginWithCredentials(prop.getProperty("validEmail"), prop.getProperty("validPassword"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchFunctionalityWithValidProductSearch() {

		searchPage.enterProductForSearchFied(dataProp.getProperty("validSearchFor"));

		WebElement productStatusElement = searchPage.displayStatusOfValidProduct();
		Assert.assertTrue(productStatusElement.isDisplayed(), "Valid product status is not visible.");

		// (or)
		// Assert.assertTrue(searchPage.displayStatusOfValidProduct().isDisplayed(),
		// "Valid product status is not visible.");

	}

	@Test(priority = 2)
	public void verifySearchFunctionalityWithInValidProductSearch() throws InterruptedException {

		searchPage.enterProductForSearchFied(dataProp.getProperty("invalidSearchFor"));

		System.out.println(searchPage.retrieveInvalidItemSearchMessage());
		Assert.assertEquals(searchPage.retrieveInvalidItemSearchMessage(), "Your search returned no results.");

	}

}
