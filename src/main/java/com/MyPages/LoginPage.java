package com.MyPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
	
	private By emailId = By.id("identifierId");
	private By nextBtn = By.xpath("//span[text()=\"Next\"]");
	private By errorMsg = By.xpath("//span[contains(text(),\"sign you in\")]");

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getEmailId() {
		return getElement(emailId);
	}
	
	public WebElement getNextbtn() {
		return getElement(nextBtn);
	}
	
	public WebElement getErrorMsg() {
		return getElement(errorMsg);
	}
	
	public String getLoginPageTitle() {
		return getPageTitle();
	}
	
	public String doErrorLogin(String email) {
		getEmailId().sendKeys(email);
		getNextbtn().click();
		waitForElement(errorMsg);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getElement(errorMsg).getText();
	}
	
	
	

}
