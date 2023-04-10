package com.projNinja.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReported {

	public static ExtentReports generateExtendReport() {
		
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir") + "\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		//set the theme of report
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("MagicBox Automation Execution Report ");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp=new Properties();
		File configFile =new File(System.getProperty("user.dir") +"src\\main\\java\\com\\projNinja\\qa\\Config\\config.properties");
		try {
		FileInputStream fip=new FileInputStream(configFile);
			
		configProp.load(fip);
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("valid user", configProp.getProperty("validUser"));
		extentReport.setSystemInfo("valid Password", configProp.getProperty("validPass"));
		
		//take from System property file 
		extentReport.setSystemInfo("OS Name :", System.getProperty("os.name"));
		extentReport.setSystemInfo("OS Version :", System.getProperty("os.version"));
		extentReport.setSystemInfo("Java Version :", System.getProperty("java.version"));
		extentReport.setSystemInfo("User Name :", System.getProperty("user.name"));
		
	return extentReport;
		
	}catch(Throwable e) {
		e.printStackTrace();
	}
	
		return extentReport;
		
	}}
