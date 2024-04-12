package com.cracdeal.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cracdeal.base.Base;
import com.cracdeal.qa.pages.ContactUsPage;
import com.cracdeal.qa.pages.HomePage;

public class ContactUsPageTests extends Base {
	
    public WebDriver driver;
    HomePage homePage;
    ContactUsPage contactUsPage;

	@BeforeMethod
	public void setUp()
	{
		loadProperties();
		driver=initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);	
	}
	
	@Test(priority=1)
	public void validate_ContactUs_Content()
	{
		contactUsPage=homePage.clickOnContactUsLink();
		Assert.assertEquals(contactUsPage.getParagraph2Text(), dataprop.getProperty("p2text"));
		Assert.assertEquals(contactUsPage.getParagraph3Text(), dataprop.getProperty("p3text"));
	}
	@Test(priority=2)
	public void validate_Submit_Contact_Form()
	{
		contactUsPage=homePage.clickOnContactUsLink();
		contactUsPage.fillForm(dataprop.getProperty("name"), dataprop.getProperty("email"), 
				dataprop.getProperty("subject"), dataprop.getProperty("message"));
		contactUsPage.clickOnSubmit();
		Assert.assertEquals(contactUsPage.getErrorMessageText(), "Failed while verifying captcha");
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}