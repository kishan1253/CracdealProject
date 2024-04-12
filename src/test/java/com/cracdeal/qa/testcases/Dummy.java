package com.cracdeal.qa.testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Dummy {
	
	@Test
	public void dummyMethod()
	{
		String str="https://www.cracdeal.com/paytmmall-offers";
		String out=str.substring(25).split("-")[0];
		System.out.println(out);
	}
	
	@Test
	public void getProducts()
	{
		XSSFWorkbook wb=null;
		String filepath=".//src//main//java//com//cracdeal//qa//testdata//TestData.xlsx";
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
		for(Object[ ] row:products)
		{
			for(Object o: row)
			{
				System.out.println(o.toString().length());
			}
		}
     }
	
	@Test
	public void generateExtentReport()
	{
		LocalDateTime s=LocalDateTime.now();
		String out= s.toString().substring(0,19).replace('-', ' ').replace(':', ' ').replaceAll(" ", "");
		System.out.println(out);
	}
	
	@Test
	public void getProperties()
	{
		System.getProperties().list(System.out);
	}
	
	@Test
	public void isDesktopSupported()
	{
		Desktop.getDesktop();
		System.out.println(Desktop.isDesktopSupported());
	}
}
