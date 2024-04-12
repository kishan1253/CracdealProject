package com.cracdeal.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cracdeal.utils.ExtentReporter;
import com.cracdeal.utils.Utilities;

public class MySiteListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context)  //Invoked before running all the test methods belonging to the classes inside the <test>tag and calling all their Configuration methods.
	{
		extentReport=ExtentReporter.generateExtentReport(); //This method is from ExtentReporter class which returns object of ExtentReports which initializing onStart
	}
	@Override
	public void onTestStart(ITestResult result) {
		extentTest=extentReport.createTest(result.getName()); //this method creates extenTest
		extentTest.log(Status.INFO, result.getName()+" get started...");  //This statement is to log
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" got successfully executed..."); //This statement is to log
	}
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		try { 
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()); //this statement is to bring life to driver object inside this class
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String destinationFilePath=Utilities.getScreenShot(driver, result.getName());
	    extentTest.addScreenCaptureFromPath(destinationFilePath); //This method is to attach screenshot to extent report
	    extentTest.log(Status.FAIL, result.getThrowable());
	    extentTest.log(Status.FAIL, result.getName()+" got failed...");
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped...");
	}
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush(); //This method ensures that all the work done till now writes to output report
		File extentReportFile = new File("C:/KishanPersonal/EclipseWorkSpaces/HybridFrameWork/Cracdeal/test-output/ExtentReports/"+ExtentReporter.Filename+".html");
		//Desktop.getDesktop().browseFileDirectory(extentReportFile);  // This step automatically opens Extent Report on Finish of test execution
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
