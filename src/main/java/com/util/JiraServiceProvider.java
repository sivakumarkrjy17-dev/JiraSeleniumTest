package com.util;

import java.io.File;
import java.util.Arrays;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;

	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary, String description, String screenshotPath) {

		try {
			FluentCreate fleuntCreate = jira.createIssue(project, issueType);
			fleuntCreate.field(Field.SUMMARY, summary);
			fleuntCreate.field(Field.DESCRIPTION, description);
//			fleuntCreate.field(Field.ATTACHMENT, Arrays.asList(screenshotPath));
			Issue newIssue = fleuntCreate.execute();
			
			// 3. Upload the screenshot to the newly created issue
			File screenshotFile = new File(screenshotPath);
			if (screenshotFile.exists()) {
			    newIssue.addAttachment(screenshotFile);
			    System.out.println("Screenshot successfully attached to issue: " + newIssue.getKey());
			} else {
			    System.err.println("Screenshot file not found at path: " + screenshotPath);
			}
			System.out.println("new issue created in jira with ID: " + newIssue);

		} catch (JiraException e) {
			e.printStackTrace();
		}

	}

}
