package com.web.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.web.base.Base;
import com.web.pages.AccountPage;
import com.web.pages.RegisterPage;
import com.web.pages.WebsiteHomePage;
import com.web.utilities.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	WebsiteHomePage homePage;
	AccountPage accountSuccessPage;

	public RegisterTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = BrowserInitialization(prop.getProperty("browser"));
		registerPage = new RegisterPage(driver);
		homePage = new WebsiteHomePage(driver);
		accountSuccessPage = new AccountPage(driver);

		registerPage = homePage.clickOnCreateAnAccountToRegister();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void registerAccountByProvidingAllFields() {
		// Register Acct Page

		registerPage.verifyRegisterAnNewAccountWithValidDetails(dataProp.getProperty("FirstName"),
				dataProp.getProperty("LastName"), Utilities.generateTimeStamp(), dataProp.getProperty("Password"),
				dataProp.getProperty("ConfirmPassword"));

		// RegisterAccount success page - my account title validation

		String actualAccountTitle = accountSuccessPage.AccountTitleText();
		String ExpectedAccountTitle = dataProp.getProperty("ExpectedAccountTitle");
		Assert.assertEquals(actualAccountTitle, ExpectedAccountTitle);

		// RegisterAccount Success page - validate the success message

		String actualAccountSuccessMessage = accountSuccessPage.retrieveAccountSuccessMessageText();
		String ExpectedAccountSuccessMessage = dataProp.getProperty("ExpectedAccountSuccessMessage");
		Assert.assertEquals(actualAccountSuccessMessage, ExpectedAccountSuccessMessage);

	}

	@Test
	public void verifyRegisterAccountWithoutProvidingAllFields() {

		registerPage.VerifyRegisterAnNewAccountWithoutProvidingDetails();

		// method 1:
		// firstName
		String actualFirstNameWarningMessage = registerPage.retrieveFirstNameWarningMessage();
		String ExpectedFirstNameWarningMessage = dataProp.getProperty("ExpectedFirstNameWarningMessage");
		Assert.assertEquals(actualFirstNameWarningMessage, ExpectedFirstNameWarningMessage);

		// method 2:
		// last name
		Assert.assertEquals(registerPage.retrieveLastNameWarningMessage(),
				dataProp.getProperty("ExpectedLastNameWarningMessage"));

		// email
		Assert.assertEquals(registerPage.retrieveEmailWarningMessage(),
				dataProp.getProperty("ExpectedEmailWarningMessage"));

		// password
		Assert.assertEquals(registerPage.retrievePasswordWarningMessage(),
				dataProp.getProperty("ExpectedPasswordWarningMessage"));

		// confirm password
		Assert.assertEquals(registerPage.retrievePasswordConfirmationMessage(),
				dataProp.getProperty("ExpectedConfirmPasswordWarningMessage"));

	}

}
