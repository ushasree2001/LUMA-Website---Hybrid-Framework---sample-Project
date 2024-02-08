package com.web.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	// All the methods inside utilities class are made static such that we can
	// access them using class name directly in other classes

	// waits
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	// TimeStamp to generate different invalid email
	public static String generateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "testing" + timeStamp + "@gmail.com";
	}

	// to get data from excel
	public static Object[][] getTestDataFromExcel(String sheetName) {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/Excels/LumaLoginInfoExcel.xlsx");

		FileInputStream fis;
		XSSFWorkbook workbook = null;

		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int noOfRows = sheet.getPhysicalNumberOfRows();
		int noOfCols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[noOfRows][noOfCols];

		for (int i = 0; i < noOfRows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			if (row != null) {
				for (int j = 0; j < noOfCols; j++) {
					XSSFCell cell = row.getCell(j);

					if (cell != null) {
						CellType cellType = cell.getCellType(); // to get the type of cell - string or int or etc

						switch (cellType) {
						case STRING:
							data[i][j] = cell.getStringCellValue();
							break;
						case NUMERIC:
							data[i][j] = Integer.toString((int) cell.getNumericCellValue());
							break;
						case BOOLEAN:
							data[i][j] = cell.getBooleanCellValue();
							break;
						}
					}
				}
			}
		}
		return data;

	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "/Screenshots/" + testName + ".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
}
