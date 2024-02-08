package com.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	// validate we are in account creation page
	@FindBy(xpath = "//span[contains(text(),'Create New Customer Account')]")
	private WebElement createAccountPageMessage;

	// Personal Information

	@FindBy(id = "firstname")
	private WebElement firstNameField;

	@FindBy(xpath = "//div[@id='firstname-error']")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement lastNameField;

	@FindBy(xpath = "//div[@id='lastname-error']")
	private WebElement lastNameWarning;

	// Sign-in Information

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailField;

	@FindBy(xpath = "//div[@id='email_address-error']")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@name='password' and @id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//div[@id='password-error']")
	private WebElement passwordWarning;

	@FindBy(id = "password-strength-meter-label")
	private WebElement passwordRulesMessage;

	@FindBy(xpath = "//input[@name='password_confirmation' and @id='password-confirmation']")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//div[@id='password-confirmation-error']")
	private WebElement passwordConfirmationWarning;

	@FindBy(xpath = "//button/span[contains(text(),'Create an Account')]")
	private WebElement submitButton;

	@FindBy(xpath = "//div[contains(text(),'There is already an account with this email address.')]")
	private WebElement duplicateEmailWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String retrieveFirstNameWarningMessage() {

		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrieveLastNameWarningMessage() {

		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retrieveEmailWarningMessage() {

		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}

	public String retrieveDuplicateEmailWarningMessage() {

		String duplicateEmailWarningText = duplicateEmailWarning.getText();
		return duplicateEmailWarningText;
	}

	public String retrievePasswordWarningMessage() {

		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}

	public String retrievePasswordRulesMessage() {

		String passwordRulesText = passwordRulesMessage.getText();
		return passwordRulesText;
	}

	public String retrievePasswordConfirmationMessage() {

		String passwordConfirmationText = passwordConfirmationWarning.getText();
		return passwordConfirmationText;
	}

	public AccountPage verifyRegisterAnNewAccountWithValidDetails(String firstName, String lastName, String email,
			String password, String confirmPassword) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		submitButton.click();
		return new AccountPage(driver);
	}

	public void VerifyRegisterAnNewAccountWithoutProvidingDetails() {
		submitButton.click();
	}

	public void displayStatusOfWarningMessages() {

	}
}
