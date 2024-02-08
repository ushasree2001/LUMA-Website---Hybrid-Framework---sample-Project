package com.web.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

	WebDriver driver;

	@FindBy(id = "search")
	private WebElement searchField;

	@FindBy(xpath = "//button[@type='submit' and @title='Search']")
	private WebElement searchIconBtn;

	@FindBy(linkText = "Bella Tank")
	private WebElement validItemSearch;

	@FindBy(xpath = "//div[@class='message notice']/div")
	private WebElement inValidItemSearchMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterProductForSearchFied(String ProductName) {
		searchField.sendKeys(ProductName);
		searchIconBtn.click();
	}

	public WebElement displayStatusOfValidProduct() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement validProductDisplayStatus = wait.until(ExpectedConditions.visibilityOf(validItemSearch));
		return validProductDisplayStatus;
	}

	public String retrieveInvalidItemSearchMessage() {
		String noProductMessage = inValidItemSearchMessage.getText();
		return noProductMessage;
	}
}
