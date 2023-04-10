package com.tutorialNinja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninja.Repository.qa.pages.HomePage;
import com.ninja.Repository.qa.pages.RegisterPage;
import com.projNinja.Utils.Utilities;
import com.tutorialNinja.qa.base.Base;
@Listeners(com.ninja.qa.listners.MyListners.class)

public class RegistrationTest extends Base{

	public WebDriver driver;

//to call the construtor/prop-method of super class
	public  RegistrationTest() {
	super();
	}
	
	
	
	@BeforeMethod
	public void atBeforeMethod_SelectRegisterLink() {
		
		// as this method has webdriver return type so need to store like below
		driver =initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
				
		HomePage homePage=new HomePage(driver);
		homePage.clickonMyAccount();
		homePage.selectRegisterOption();
	//	driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		System.out.println("My Account Clicked");
		//driver.findElement(By.linkText("Register")).click();
		System.out.println("Register is clicked");
	
	}

	@Test(priority=1)
	
	public void verifyRegisteringAccountbyProvidingOnlyMandatoryfields() {
				
		//Click on Register  .. removing hardcode value and taking from testDatapro file
		System.out.println("Start Test");
		RegisterPage registerPageObj=new RegisterPage(driver);
		registerPageObj.enterFname(dataprop.getProperty("firstName"));
		registerPageObj.enterLname(dataprop.getProperty("lastName"));
		registerPageObj.enterEmail(Utilities.generateTimeStamp());
		registerPageObj.enterTelephone("8800445566");
		registerPageObj.enterPassword("12345");
		registerPageObj.enterConfirmPassword("12345");
		registerPageObj.agreeBtn();
		registerPageObj.continueClick();
		//String ActSucessText=driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();
	//	String ActSucessText=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		String ActSucessText=registerPageObj.actualSucceessText();
		System.out.println("ActTextIs= " + ActSucessText);
		String ExpSuccessMsg=dataprop.getProperty("accountSuccessfullyCreatedMsg");
		System.out.println("ExpTextIs=" + ExpSuccessMsg);
		
		Assert.assertEquals(ActSucessText, ExpSuccessMsg,"Registration is not successful");
		System.out.println("***After Mandatory method Execution****");
		driver.quit();
		
		//no need of below code
		/*driver.findElement(By.name("firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.name("lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.name("email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.name("telephone")).sendKeys(dataprop.getProperty("telephoneNo"));
		driver.findElement(By.name("password")).sendKeys("12345");
		driver.findElement(By.name("confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();*/
		
		//to verify accout is created successfully
		
	//	String ActSucessText=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		
		
		//Assert.assertEquals(ActSucessText, "Your Account Has Been Created!","Registration is not successful");
		
		
	}
	@Test(priority=2)
	
	  public void verifyRegisteringAccountWithAllfields() {
	  
	  //Click on Register
	  
	  driver.findElement(By.name("firstname")).sendKeys(dataprop.getProperty("firstName"));
	  
	  driver.findElement(By.name("lastname")).sendKeys(dataprop.getProperty("lastName"));
	  driver.findElement(By.name("email")).sendKeys(Utilities.generateTimeStamp());
	  driver.findElement(By.name("telephone")).sendKeys(dataprop.getProperty("telephoneNo"));
	  driver.findElement(By.name("password")).sendKeys("12345");
	 driver.findElement(By.name("confirm")).sendKeys("12345");
	 
	  driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click
	  (); driver.findElement(By.name("agree")).click();
	  driver.findElement(By.xpath("//input[@value='Continue']")).click();
	  
	  //to verify accout is created successfully
	  
	  String
	  ActSucessText=driver.findElement(By.xpath("//div[@id='content']/h1")).getText	 ();
	  
	  System.out.println(ActSucessText); 
	  String ExpSuccessMsg=dataprop.getProperty("accountSuccessfullyCreatedMsg");
		System.out.println(ExpSuccessMsg);
		//Assert.assertEquals(ActSucessText, "Your Account Has Been Created!","Registration is not successful");
		Assert.assertEquals(ActSucessText, ExpSuccessMsg,"Registration is not successful");
	 
	  System.out.println("Your Account Created msg got for all fields");
	}
	 
	
	@AfterMethod
	public void closeBrowser() {

		
		driver.quit();
	}
	}


