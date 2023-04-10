package com.projNinja.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class Utilities {
	
	//final variable cant be change, it is same throgh class... we will use this in our base class
	
	public static final int Implicit_Wait_Time=10;
	public static final int Page_Load_Time=5;
	
	//making method static so that it can be directly called by its class
	
	// why time stamp ,, so that email if is not repeated again.. and many time login is not locked the app
	
	public static String generateTimeStamp() {
		Date date=new Date() ;
		String timeStamp=date.toString().replace(" ", "_").replace(":", "_");
		return "rachita" + timeStamp + "@gmail.com";
	
	
}
	
	public static Object[][] getTestDatafromExcel(String sheetname) {
		
		File excelFile= new File(System.getProperty("user.dir") +"\\src\\main\\java\\com\\magicBox\\qa\\testData\\TestDataFile.xlsx");
		XSSFWorkbook   workbook=null;
		try {
		FileInputStream fisExcel=new FileInputStream(excelFile);
		workbook=new XSSFWorkbook(fisExcel);
			
		}catch (Throwable   e) {
			e.printStackTrace();
		}
		XSSFSheet sheet=workbook.getSheet(sheetname);
		int rows=sheet.getLastRowNum();
		
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object [][] data=new Object [rows][cols];
		for (int i=0;i<rows;i++) {
			XSSFRow row=sheet.getRow(i+1);
			
			for (int j=0; j<cols; j++) {
				XSSFCell cell=row.getCell(j);
				org.apache.poi.ss.usermodel.CellType cellType= cell.getCellType();
				
				
				switch (cellType) {
				
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int) cell.getNumericCellValue())  ;
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				}
				 
				
			}
		}
		return data;
	}
}

//in utility we keep the  unchaged data through out the class. we make variable.method. static so that it canbe
//diretly call by class
