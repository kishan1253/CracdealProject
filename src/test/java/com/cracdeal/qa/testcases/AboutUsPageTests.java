package com.cracdeal.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cracdeal.base.Base;
import com.cracdeal.qa.pages.AboutUsPage;
import com.cracdeal.qa.pages.HomePage;

public class AboutUsPageTests extends Base {
	
    public WebDriver driver;
    HomePage homePage;
    AboutUsPage aboutUsPage;

	@BeforeMethod
	public void setUp()
	{
		loadProperties();
		driver=initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
	}
	
	@Test(priority=1)
	public void validate_AboutUs_Content()
	{
		aboutUsPage=homePage.clickOnAboutUsLink();
		Assert.assertEquals(aboutUsPage.getparagraph1Text(), dataprop.getProperty("aboutUsPagep1text"));
		Assert.assertEquals(aboutUsPage.getparagraph2Text(), dataprop.getProperty("aboutUsPagep2text"));
	}
	@Test(priority=2)
	public void validate_Adding_Comment()
	{
		aboutUsPage=homePage.clickOnAboutUsLink();
		aboutUsPage.clickOnAddCommentButton();
		aboutUsPage.sendTextToTitle(dataprop.getProperty("aboutUsPage_title"));
		aboutUsPage.sendTextToDescription(dataprop.getProperty("aboutUsPage_description"));
		aboutUsPage.clickOnSubmitButton();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginModal']/div[@class='header']")));
		Assert.assertEquals(aboutUsPage.displayOfsignInPrompt(), true);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
