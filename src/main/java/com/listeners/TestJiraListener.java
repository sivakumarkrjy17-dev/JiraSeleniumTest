package com.listeners;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.MyPages.BasePage;

import com.util.JiraPolicy;
import com.util.JiraServiceProvider;

public class TestJiraListener implements ITestListener {

//	public TestJiraListener() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	public TestJiraListener(WebDriver driver) {
//		super(driver);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {

		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isTicketReady = jiraPolicy.logTicketReady();
		if (isTicketReady) {

			// raise jira ticket:
			System.out.println("is ticket ready for JIRA: " + isTicketReady);

			String pwdToken = "ATATT3xFfGF0iTnDnNDU1zaFVdiOYY1fFBaL2a2CQxLaH0A_-k5bUY1Gl7_yfP8tTRRUw9xETznznNO8T50YAmVDSbphIehpPCtIGYDpG90E40lvm4CKEaBhL5fmL0LGD2mM6tRAPtj2Cd8O5h_bBVnqk8VlcRP5eHdHYV_adg0uxmqBdLxJnsw=71858DF9";
			JiraServiceProvider jiraSp = new JiraServiceProvider("https://jiramyacc.atlassian.net/",
					"jiramyacc@gmail.com", pwdToken, "SCRUM");
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
					+ "got failed due to some assertion or exception";
			String issueDescription = result.getThrowable().getMessage() + "\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			jiraSp.createJiraTicket("Bug", issueSummary, issueDescription, BasePage.captureScreenshotPath());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
