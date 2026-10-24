package com.MyTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.MyPages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginPage;
	
	@BeforeMethod
	@Parameters(value = { "browser" })
	public void setupTest(String browser) {
		
		if(browser.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		driver.get("https://mail.google.com/mail/");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPage = new LoginPage(this.driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
