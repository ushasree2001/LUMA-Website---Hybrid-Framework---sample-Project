package RoughWork;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import graphql.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NaveenProject {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);

		driver.get("http://localhost:5173/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions actions = new Actions(driver);

		WebElement startdateField = driver.findElement(By.xpath("//input[@id='datepicker1']"));
		startdateField.sendKeys("01/01/2022");

		WebElement startdateLabel = driver
				.findElement(By.cssSelector("body > div:nth-child(2) > div > label:nth-child(1)"));
//		Point startdateLabelLocation = startdateLabel.getLocation();
//		int x = startdateLabelLocation.getX();
//		int y = startdateLabelLocation.getY();
//		actions.moveByOffset(x, y).click().perform();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", startdateLabel);

		WebElement enddateField = driver.findElement(By.xpath("//input[@id='datepicker2']"));
		enddateField.sendKeys("31/01/2024");
		WebElement enddateLabel = driver
				.findElement(By.cssSelector("body > div:nth-child(2) > div > label:nth-child(3)"));
//		Point enddateLabelLocation = enddateLabel.getLocation();
//		int x1 = enddateLabelLocation.getX();
//		int y1 = enddateLabelLocation.getY();
//		actions.moveByOffset(x1, y1).click().perform();
		js.executeScript("arguments[0].click();", enddateLabel);

		WebElement AcctNumberLabel = driver.findElement(By.cssSelector(
				"body > div:nth-child(2) > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1)"));
//		Point accountNumberLabelLocation = AcctNumberLabel.getLocation();
//		int x2 = accountNumberLabelLocation.getX();
//		int y2 = accountNumberLabelLocation.getY();
//		actions.moveByOffset(x2, y2).click().perform();
		js.executeScript("arguments[0].click();", AcctNumberLabel);
		Assert.assertTrue(AcctNumberLabel.isDisplayed());

		// startdateLabel.click();
		// startdateLabel.getText();

//		enddateLabel.click();
//		enddateLabel.getText();

//		WebElement AcctNumberLabel = driver
//				.findElement(By.xpath("//table//tr[1]/td[contains(text(),'Account Number')]"));

//		actions.moveToElement(startdateLabel).click().perform();
//		actions.moveToElement(enddateLabel).click().perform();
//		actions.scrollToElement(AcctNumberLabel).click().perform();

		System.out.println(AcctNumberLabel.getText());

		Thread.sleep(5000);

	}
}
