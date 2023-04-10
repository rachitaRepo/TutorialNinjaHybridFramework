package com.tutorialNinja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.projNinja.Utils.Utilities;


public class Base {
	WebDriver driver;
	public Properties prop, dataprop;   // public so that can be access inside the child class
	
	//Creating construtor so that it can initilize the propfile in child class
	public  Base() {
	//	Properties prop=new Properties();
			prop=new Properties();
			File propFile=new File(System.getProperty("user.dir") +"\\src\\main\\java\\com\\projNinja\\qa\\Config\\config.properties");
			
				try{
				FileInputStream fip=new FileInputStream(propFile);
			prop.load(fip);
			}catch(Exception e) {
				e.printStackTrace();
			}
				
			dataprop=new Properties();
			File datapropFile=new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\magicBox\\qa\\testData\\TestData.properties");
			try {
			FileInputStream fiptest=new FileInputStream(datapropFile);
			dataprop.load(fiptest);}
			catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	//taking broswer parameter from prop file
	//creating method of websriver to initilaize and return webdriver
	public WebDriver initializeBrowserAndOpenApplication(String browserName){
	
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		///** To Execute test in many browser		
				
				if (browserName.equalsIgnoreCase("chrome")) {
					
					driver=new ChromeDriver();
					
				}else if(browserName.equalsIgnoreCase("firefox")) {
					
					driver=new FirefoxDriver();
					
				}else if (browserName.equalsIgnoreCase("edge")) {
					driver=new EdgeDriver();
					
				}else if (browserName.equalsIgnoreCase("safari")) {
					driver=new SafariDriver();
				}
		//**********************************************************************
				driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				//removing hardcoding..replace nos with below veriable called by class
				
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_Wait_Time));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Load_Time));
	
				
		//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
				
				driver.get("http://tutorialsninja.com/demo/");
				driver.get(prop.getProperty("url"));
				
				return driver;

}}

	