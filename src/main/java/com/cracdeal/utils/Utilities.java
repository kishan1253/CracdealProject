package com.cracdeal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static LocalDate ld;
	public static final int implicitWaitTime=5;
	public static final int pageLoadTimeOut=30;
	
	public static String month()
	{
		ld=LocalDate.now(ZoneId.of("Asia/Kolkata"));
		String month=ld.getMonth().toString().charAt(0)+ld.getMonth().toString().substring(1).toLowerCase();
		return month;
	}
	public static String date()
	{
		ld=LocalDate.now(ZoneId.of("Asia/Kolkata"));
		HashMap<Integer,String> ordinal=new HashMap<Integer,String>();
		ordinal.put(1, "st");ordinal.put(2, "nd");ordinal.put(3, "rd");ordinal.put(4, "th");ordinal.put(5, "th");ordinal.put(6, "th");ordinal.put(7, "th");ordinal.put(8, "th");ordinal.put(9, "th");ordinal.put(10, "th");
		ordinal.put(11, "th");ordinal.put(12, "th");ordinal.put(13, "th");ordinal.put(14, "th");ordinal.put(15, "th");ordinal.put(16, "th");ordinal.put(17, "th");ordinal.put(18, "th");ordinal.put(19, "th");ordinal.put(20, "th");
		ordinal.put(21, "st");ordinal.put(22, "nd");ordinal.put(23, "rd");ordinal.put(24, "th");ordinal.put(25, "th");ordinal.put(26, "th");ordinal.put(27, "th");ordinal.put(28, "th");ordinal.put(29, "th");ordinal.put(30, "th");
		ordinal.put(31, "st");
		String date=ld.getDayOfMonth()+ordinal.get(ld.getDayOfMonth());
		return date;
	}
	public static int year()
	{
		ld=LocalDate.now(ZoneId.of("Asia/Kolkata"));
		int year=ld.getYear();
		return year;
	}
	
	public static List<String> menusInPrimaryHeader()
	{
		List<String> expectedPrimaryMenuItems=new ArrayList<String>();
		expectedPrimaryMenuItems.add("Home");
		expectedPrimaryMenuItems.add("Electronics");
		expectedPrimaryMenuItems.add("Men");
		expectedPrimaryMenuItems.add("Women");
		expectedPrimaryMenuItems.add("Kids");
		expectedPrimaryMenuItems.add("Categories");
		expectedPrimaryMenuItems.add("Stores");
		expectedPrimaryMenuItems.add("Mobiles");
		expectedPrimaryMenuItems.add("Blog");
		expectedPrimaryMenuItems.add("Old Home");
		return expectedPrimaryMenuItems;
	}
	public static List<String> menusInSecondaryHeader()
	{
		List<String> expectedSecondaryMenuItems=new ArrayList<String>();
		expectedSecondaryMenuItems.add("Amazon Offers");
		expectedSecondaryMenuItems.add("Flipkart Offers");
		expectedSecondaryMenuItems.add("Myntra Offers");
		expectedSecondaryMenuItems.add("FirstCry Offers");
		expectedSecondaryMenuItems.add("Swiggy Coupons");
		expectedSecondaryMenuItems.add("Zomato Coupons");
		expectedSecondaryMenuItems.add("BookMyShow Coupons");
		expectedSecondaryMenuItems.add("MakeMyTrip Coupons");
		return expectedSecondaryMenuItems;
	}
	public static List<String> differentSectionsOfHomepage()
	{
		List<String> expectedSections=new ArrayList<String>();
		expectedSections.add("Cracdeal Collections");
		expectedSections.add("Handpicked Deals");
		expectedSections.add("Mobiles Offers");
		expectedSections.add("TV Offers");
		expectedSections.add("Air Conditioner Offers");
		expectedSections.add("Washing Machine Offers");
		expectedSections.add("Refrigerator Offers");
		expectedSections.add("Computers & Laptop Offers");
		expectedSections.add("Top Mobile Brands");
		return expectedSections;
	}
	
	public static List<String> favouriteStores()
	{
		List<String> favouriteStores=new ArrayList<String>();
		favouriteStores.add("amazon");
		favouriteStores.add("flipkart");
		favouriteStores.add("myntra");
		favouriteStores.add("clovia");
		favouriteStores.add("paytmmall");
		favouriteStores.add("swiggy");
		favouriteStores.add("limeroad");
		favouriteStores.add("firstcry");
		favouriteStores.add("zomato");
		favouriteStores.add("nnnow");
		favouriteStores.add("bookmyshow");
		favouriteStores.add("makemytrip");
		return favouriteStores;
	}
	
	public static Object[][] getProductsDataFromExcel(String workBookName)
	{
		XSSFWorkbook wb=null;
		String filepath=".//src//main//java//com//cracdeal//qa//testdata//"+workBookName+".xlsx";
		try {
			   File f=new File(filepath);
		       FileInputStream fis = new FileInputStream(f);
		       wb=new XSSFWorkbook(fis);
		    }
		catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    XSSFSheet sheet=wb.getSheet("ProductsToSearch");
			int rows=sheet.getLastRowNum();
			int columns=sheet.getRow(1).getLastCellNum();
			
			Object[][] products=new Object[rows+1][columns];
			
			for(int i=0;i<=rows;i++)
				{
				XSSFRow row=sheet.getRow(i);  //Row class object gets created for every row iteration
				for(int j=0;j<columns;j++)
				{
				XSSFCell cell=row.getCell(j); //Cell object gets created for every cell in the row
				CellType celltype=cell.getCellType(); //this method returns celltype which is used to determine how to print the value
				switch(celltype)
				{
				  case STRING: products[i][j]=cell.getStringCellValue()+"\t";break;
				  case NUMERIC: products[i][j]=cell.getNumericCellValue()+"\t";break;
				  case BOOLEAN: products[i][j]=cell.getBooleanCellValue()+"\t";break;
				  default: System.out.println("No data present");
				}
				}
				}
		return products;
    }
	
	public static String getScreenShot(WebDriver driver, String testName)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		LocalDateTime ld=LocalDateTime.now();
		String fileName=ld.toString().substring(0,19).replace('-', ' ').replace(':', ' ').replace("T","_").replaceAll(" ", "");
	    String destinationFilePath=".//ScreenShots//"+testName+"_"+fileName+".png";
		File destination=new File(destinationFilePath);
	    try {
			FileHandler.copy(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return destinationFilePath;
	}
}
