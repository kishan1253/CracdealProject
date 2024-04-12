package com.cracdeal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static String Filename;
	
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport = new ExtentReports();
		LocalDateTime ldt=LocalDateTime.now();
		Filename=ldt.toString().substring(0,19).replace('-', ' ').replace(':', ' ').replaceAll(" ", "");
		File extentReportFile = new File(".//test-output//ExtentReports//"+Filename+".html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
		
		spark.config().setTheme(Theme.DARK);   //This statement sets Dark theme for the report
		spark.config().setReportName("Cracdeal Project Extent Report");  //this statement sets name for the report
		spark.config().setDocumentTitle("CracdealAutomationSuiteReport");  //this statement sets webpage title for the report
	    spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");  //this statement sets time stamp format for the report
		
		extentReport.attachReporter(spark);  //this step is to attach reporter to extentReport
		
		Properties config=new Properties();
		File f=new File(".//src//main//java//com//cracdeal//config//config.properties");
		try {
			FileInputStream fis = new FileInputStream(f);
			config.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", config.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", config.getProperty("browserName"));
		
		//System.getProperties().list(System.out);   // This step lists/prints out all System properties like os.name, user.country, user.name, java.version etc...
		
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}

}
