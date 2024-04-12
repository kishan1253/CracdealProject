package com.cracdeal.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@id='liveResults'] //a[contains(@class,'header')]")
	private List<WebElement> searchResultLinks;
	
	
	public ProductSearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> getSearchResults()
	{
		List<WebElement> searchResultLinks=driver.findElements(By.xpath("//div[@id='liveResults'] //a[contains(@class,'header')]"));
		List<String> searchResults=new ArrayList<String>(); 
		  for(WebElement result:searchResultLinks) 
		  { 
			  searchResults.add(result.getText()); 
		  }
		  
		  return searchResults;
	}

}
