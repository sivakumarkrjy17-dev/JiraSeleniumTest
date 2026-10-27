package com.MyPages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	static WebDriver driver;
	WebDriverWait wait;

//	public BasePage() {
//		
//	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitForElement(locator);
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("some error occurred while creating element " + locator.toString());
			e.printStackTrace();
		}

		return element;
	}

	public void waitForElement(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("some exception/error occurred while waiting for the element " + locator.toString());
			e.printStackTrace();
		}
	}

	public void waitForPageTitle(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}

	public static String captureScreenshotPath() {
		// Cast WebDriver to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Capture the screenshot as a temporary file
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// Define the destination path and copy the file
		String destPath = System.getProperty("user.dir") + "/screenshots/homepage.png";
		try {
			Path localScreenshotPath = Paths.get(destPath);
			boolean deleted = Files.deleteIfExists(localScreenshotPath);
			if (deleted) {
				System.out.println("Old screenshot deleted from local path.");
			}
			Files.copy(srcFile.toPath(), Paths.get(destPath));
			System.out.println("Screenshot saved to: " + destPath);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destPath;
	}

}
