package com.cracdeal.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cracdeal.base.Base;
import com.cracdeal.qa.pages.HomePage;
import com.cracdeal.utils.Utilities;

public class HomePageTests extends Base{
	
	public WebDriver driver;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp()
	{
		loadProperties();
		driver=initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
	}
	
	@Test(priority=1)
	public void validate_Title()
	{
		String title=dataprop.getProperty("pageTitlePart1")+" | "+ Utilities.month() +" "+ Utilities.date()+", "+Utilities.year()+" | "+dataprop.getProperty("pageTitlePart2");
		Assert.assertEquals(driver.getTitle(),title);
	}
	@Test(priority=2)
	public void validate_Url()
	{
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("url"));
	}
	@Test(priority=3)
	public void validate_PageSource_Is_NotEmpty()
	{
		Assert.assertEquals(driver.getPageSource().isEmpty(),false);
	}
	@Test(priority=4)
	public void validate_Menus_In_PrimaryHeader()
	{
		Assert.assertEquals(Utilities.menusInPrimaryHeader().equals(homePage.getactualPrimaryMenuItems()), true);
	}
	@Test(priority=5)
	public void validate_Menus_In_SecondaryHeader()
	{
		Assert.assertEquals(Utilities.menusInSecondaryHeader().equals(homePage.getactualSecondaryMenuItems()), true);
	}
	@Test(priority=6)
	public void validate_Presence_Of_Live_Search()
	{
		Assert.assertEquals(homePage.getAttributeOfActualPrompt("placeholder"), dataprop.getProperty("expectedprompt"));
		Assert.assertEquals(homePage.validateSearchBoxisEnabled(), true);
	}
	@Test(priority=7)
	public void validate_Different_Sections_of_HomePage()
	{
	    Assert.assertEquals(Utilities.differentSectionsOfHomepage().equals(homePage.getactualSections()), true);
	}
	@Test(priority=8)
	public void validate_Availability_of_FavouriteStores()
	{
		Assert.assertEquals(Utilities.favouriteStores().equals(homePage.getavailableStores()), true);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
