package com.cracdeal.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='ui grid'] //p[2]")
	private WebElement paragraph2;
	
	@FindBy(xpath="//div[@class='ui grid'] //p[3]")
	private WebElement paragraph3;
	
	@FindBy(xpath="//input[@id='name']")
	private WebElement name;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='subject']")
	private WebElement subject;
	
	@FindBy(xpath="//textarea[@id='body']")
	private WebElement message;
	
	@FindBy(xpath="//form[@id='cuForm'] //button[text()='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath="//div[contains(@class,'negative')]/div")
	private WebElement errorMessage;
	
	public String getParagraph2Text()
	{
		return paragraph2.getText();
	}
	
	public String getParagraph3Text()
	{
		return paragraph3.getText();
	}
	
	public void fillForm(String s1, String s2, String s3, String s4)
	{
		name.sendKeys(s1);
		email.sendKeys(s2);
		subject.sendKeys(s3);
		message.sendKeys(s4);
	}
	public void clickOnSubmit()
	{
		submitButton.click();
	}
	
	public String getErrorMessageText()
	{
		return errorMessage.getText();
	}
	
	public ContactUsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
