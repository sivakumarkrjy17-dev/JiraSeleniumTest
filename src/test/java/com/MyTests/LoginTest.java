package com.MyTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.ConfigReader;
import com.util.JiraPolicy;

public class LoginTest extends BaseTest {

	@JiraPolicy(logTicketReady=true)
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, "GmailXX");		
	}
	
	@JiraPolicy(logTicketReady=true)
	@Test(priority=2)
	public void verifyLoginPageErrorTest() {
		String errorText = loginPage.doErrorLogin(ConfigReader.getProperty("incorrectMailId"));
		System.out.println("error Text : "+errorText);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(errorText, "Couldn’t sign me in");
	}
}
