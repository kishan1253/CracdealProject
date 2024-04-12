package com.cracdeal.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutUsPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='ui secondary segment']/p[1]")
	private WebElement paragraph1Text;
	
	@FindBy(xpath="//div[@class='ui secondary segment']/p[2]")
	private WebElement paragraph2Text;
	
	@FindBy(linkText="Add Comment")
	private WebElement addCommentButton;
	
	@FindBy(id="title")
	private WebElement titile;
	
	@FindBy(id="description")
	private WebElement description;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath="//div[@id='loginModal']/div[@class='header']")
	private WebElement signInPrompt;
	
	public AboutUsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getparagraph1Text()
	{
		return paragraph1Text.getText();
	}
	
	public String getparagraph2Text()
	{
		return paragraph2Text.getText();
	}
	
	public void clickOnAddCommentButton()
	{
		addCommentButton.click();
	}
	
	public void sendTextToTitle(String s)
	{
		titile.sendKeys(s);
	}
	
	public void sendTextToDescription(String s)
	{
		description.sendKeys(s);
	}
	
	public void clickOnSubmitButton()
	{
		submitButton.click();
	}
	
	public boolean displayOfsignInPrompt()
	{
		return signInPrompt.isDisplayed();
	}
}
