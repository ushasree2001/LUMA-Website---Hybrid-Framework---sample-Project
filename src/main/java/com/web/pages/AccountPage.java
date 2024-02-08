package com.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;

	// validate successful account creation message
	@FindBy(xpath = "//div[contains(text(),'Thank you for registering')]")
	private WebElement AccountSuccessMessage;

	@FindBy(xpath = "//span[@class='base']")
	private WebElement AccountTitle;

	@FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
	private WebElement LoginSucessAccountVerify;

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String retrieveAccountSuccessMessageText() {

		String successMessage = AccountSuccessMessage.getText();
		return successMessage;
	}

	public String AccountTitleText() {

		String accountTitle = AccountTitle.getText();
		return accountTitle;
	}

	public boolean verifySucessfulLogin() {
		boolean displayStatus = LoginSucessAccountVerify.isDisplayed();
		return displayStatus;

	}
}
