package com.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(xpath = "//header//li[@class='authorization-link']/a")
	private WebElement SignInLink;

	@FindBy(className = "base")
	private WebElement LoginPageTitle;

	@FindBy(name = "login[username]")
	private WebElement emailField;

	@FindBy(name = "login[password]")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@class='action login primary']/span")
	private WebElement SignInBtn;

	@FindBy(xpath = "//div[@role='alert']/div/div")
	private WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickOnSignInLink() {
		SignInLink.click();
	}

	public String verifyErrorMessage() {
		String Error = errorMessage.getText();
		return Error;
	}

	public AccountPage verifyLoginWithCredentials(String email, String password) throws InterruptedException {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		SignInBtn.click();

		Thread.sleep(2000);

		return new AccountPage(driver);

	}

}
