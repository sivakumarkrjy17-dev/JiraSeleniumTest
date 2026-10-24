package com.MyPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
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

}
