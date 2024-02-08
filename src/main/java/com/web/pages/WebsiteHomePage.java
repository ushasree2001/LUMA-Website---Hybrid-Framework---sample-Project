package com.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebsiteHomePage {
	public WebDriver driver;

	@FindBy(xpath = "//header//li[@class='authorization-link']/a")
	private WebElement SignInLink;

	@FindBy(xpath = "//a[contains(text(),'Create an Account')]")
	private WebElement CreateAnAccountLink;

	public WebsiteHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage clickOnSignInToLogin() {
		SignInLink.click();
		return new LoginPage(driver);
	}

	public RegisterPage clickOnCreateAnAccountToRegister() {
		CreateAnAccountLink.click();
		return new RegisterPage(driver);
	}

}
