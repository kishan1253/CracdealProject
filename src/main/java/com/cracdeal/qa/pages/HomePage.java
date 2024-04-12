package com.cracdeal.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Elements
	
	@FindBy(xpath="//nav[@class='primary-navigation clearfix fleft']  //ul[@class='st-menu wpc-menu'] //span")
	private List<WebElement> primaryHeaderMenus;
	
	@FindBy(xpath="//div[@id='stores-subhead']/div/a")
	private List<WebElement> secondaryHeaderMenus;
	
	@FindBy(xpath="//input[@id='searchStr2']")
	private WebElement searchBox;
	
	@FindBy(xpath="//span[@class='section-heading home-deals']")
	private List<WebElement> differentSections;
	
	@FindBy(xpath="//div[@id='homeStoreList']/a")
	private List<WebElement> availableStoreLinks;
	
	@FindBy(linkText="Contact Us")
	private WebElement contactUsLink;
	
	@FindBy(linkText="About Us")
	private WebElement aboutUsLink;
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement searchButton;

	//Constructor to write PageFactory init elements method
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//The above statement can also be written as below
		//PageFactory.initElements(driver, HomePage.class);
	}
	
	//Actions on above Elements
	
	public List<String> getactualPrimaryMenuItems()
	{
		List<String> actualPrimaryMenuItems=new ArrayList<String>();
		for(WebElement menuItem: primaryHeaderMenus)
		{
			actualPrimaryMenuItems.add(menuItem.getText());
		}
		
		return actualPrimaryMenuItems;
	}
	
	public List<String> getactualSecondaryMenuItems()
	{
		List<String> actualSecondaryMenuItems=new ArrayList<String>();
		for(WebElement menuItem: secondaryHeaderMenus)
		{
			actualSecondaryMenuItems.add(menuItem.getText());
		}
		return actualSecondaryMenuItems;
	}
	
	public String getAttributeOfActualPrompt(String attribute)
	{
		return searchBox.getAttribute(attribute);
	}
	public boolean validateSearchBoxisEnabled()
	{
		return searchBox.isEnabled();
	}
	
	public List<String> getactualSections()
	{
		List<String> actualSections=new ArrayList<String>();
		for(WebElement section: differentSections)
		{
			actualSections.add(section.getText());
		}
		return actualSections;
	}
	public List<String> getavailableStores()
	{
		List<String> availableStores=new ArrayList<String>();
		for(WebElement store: availableStoreLinks)
		{
			availableStores.add(store.getAttribute("href").substring(25).split("-")[0]);
		}
		return availableStores;
	}
	
	public ContactUsPage clickOnContactUsLink()
	{
		contactUsLink.click();
		return new ContactUsPage(driver);
	}
	
	public AboutUsPage clickOnAboutUsLink()
	{
		aboutUsLink.click();
		return new AboutUsPage(driver);
	}
	
	public void sendSearchProduct(String s)
	{
		searchBox.sendKeys(s);
	}
	
	public ProductSearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new ProductSearchPage(driver);
	}
	
}
