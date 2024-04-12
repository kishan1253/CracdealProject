package com.cracdeal.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cracdeal.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public void loadProperties()
	{
		prop=new Properties();
		File f1=new File(".//src//main//java//com//cracdeal//config//config.properties");
		dataprop=new Properties();
		File f2=new File(".//src//main//java//com//cracdeal//qa//testdata//testdata.properties");
		try {
			FileInputStream fis1 = new FileInputStream(f1);
			prop.load(fis1);
			FileInputStream fis2 = new FileInputStream(f2);
			dataprop.load(fis2);
		    }
		catch (Throwable e) {
				e.printStackTrace();
			}
	}
	
	public WebDriver initializeBrowserAndOpenURL(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome")) //Always use .equals method when comparing 2 strings instead of ==
		{
		driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
		driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
		driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageLoadTimeOut));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
