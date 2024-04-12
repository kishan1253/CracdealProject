package com.cracdeal.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cracdeal.base.Base;
import com.cracdeal.qa.pages.HomePage;
import com.cracdeal.qa.pages.ProductSearchPage;
import com.cracdeal.utils.Utilities;

public class ProductSearchTests extends Base{
	
	public WebDriver driver;
	HomePage homePage;
	ProductSearchPage productSearchPage;
	
	@BeforeMethod
	public void setUp()
	{
		loadProperties();
		driver=initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
		
	}
	@DataProvider
	public Object[ ][ ] supplyProductData()
	{
		Object[ ][ ] data=Utilities.getProductsDataFromExcel("TestData");
		return data;
	}
	@Test(priority=1,dataProvider="supplyProductData")
	public void validate_Product_Search(String productName)
	{
		  if(!productName.trim().equals("ProductName"))
		  {
		  homePage.sendSearchProduct(productName.trim());
		  productSearchPage=homePage.clickOnSearchButton();
		  }
		  else
		  {
			  System.out.println("Not test data");
		  }
		  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='liveResults'] //a[contains(@class,'header')]")));
		  Assert.assertEquals(productSearchPage.getSearchResults().contains(productName), true);
		 
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
