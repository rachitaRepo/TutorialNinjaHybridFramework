package com.ninja.qa.listners;


import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.projNinja.Utils.ExtentReported;

public class MyListners implements ITestListener		{		
	
	ExtentReports extendReportObj;
	ExtentTest extenttest;
	  public void onStart(ITestContext context) {
		//System.out.println("Execution of Test Started :");
		  
		  //generate ExtentReport
		   extendReportObj=ExtentReported.generateExtendReport();
		  
	}

	//name of method will invoke
	  public void onTestStart(ITestResult result) {
	String Test_Name=result.getName();
	//adding one more line to get print in extent report
	ExtentTest extenttest=extendReportObj.createTest(Test_Name);
	extenttest.log(Status.INFO, Test_Name + "Started Executing.");
	//above print in report
	System.out.println(Test_Name + "Started Executing.");
	}
	
	


	public void onTestSuccess(ITestResult result) {
		String Test_Name=result.getName();
		 extenttest=extendReportObj.createTest(Test_Name);
		 extenttest.log(Status.PASS, Test_Name + "got successfully passed.");
		 
	//	System.out.println(Test_Name + "got successfully passed.");
	}
	
	  public void onTestFailure(ITestResult result) {
		String Test_Name=result.getName();
		
		System.out.println("ScreenShot taken");
		
		WebDriver driver=null;
		//code for retriving the driver 
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchElementException |SecurityException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath=System.getProperty("user.dir")+ "\\Screenshots\\" +Test_Name+".png";
	
		try {
			org.openqa.selenium.io.FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//attach the SS to testCases
		extenttest.addScreenCaptureFromPath(destinationScreenshotPath);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, Test_Name + "got failed.");
		
		/*
		System.out.println(Test_Name + "got failed.");
		System.out.println(result.getThrowable());  // reason to fail*/
	}
	  public void onTestSkipped(ITestResult result) {
		String Test_Name=result.getName();
		
		 extenttest=extendReportObj.createTest(Test_Name);
		 extenttest.log(Status.INFO, result.getThrowable());
		 extenttest.log(Status.SKIP, Test_Name + "got skipped.");
		 
		 
		// System.out.println(result.getThrowable());
	//	System.out.println(Test_Name + "got skipped.");
		  // reason to skip
	}
	  public void onFinish(ITestContext context) {
		 extendReportObj.flush();
		System.out.println("Finished execution test of project");
	}

	
}
