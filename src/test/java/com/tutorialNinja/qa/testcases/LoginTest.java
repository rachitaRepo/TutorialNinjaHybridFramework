package com.tutorialNinja.qa.testcases;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninja.Repository.qa.pages.HomePage;
import com.ninja.Repository.qa.pages.LoginPage;
import com.ninja.Repository.qa.pages.MyAccountPage;
import com.projNinja.Utils.Utilities;
import com.tutorialNinja.qa.base.Base;

@Listeners(com.ninja.qa.listners.MyListners.class)

public class LoginTest extends Base{

	public WebDriver driver;
	//to call the construtor/prop-method of super class
	public LoginTest() {
		super();
		
	}
	
	@BeforeMethod
	public void commonCodeAtBeforeMethod() {
		//loadpropertyfile();
		
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		
	//Calling Page class object/method by creating class object
		HomePage homePage=new HomePage(driver);
		homePage.clickonMyAccount();
		System.out.println("My Account Clicked");
		homePage.selectLoginOption();
		System.out.println("Login is selected");
		System.out.println("*************OK********************************");
		
		//Inplace of below code we using above page object repository
		
		/*driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		
		   // for //a tag anchor tag hyper link :  link text
		driver.findElement(By.linkText("Login")).click();    // for //a tag anchor tag hyper link :  link text
		
		*/
	}

	//login with valid credential test from excel and dataprovider

@Test (priority=1, dataProvider = "validCredentialSupplier") // add dependency
	
	public void verifyLoginwithvalidCredentialfromExcel(String User, String Password) {
		//WebDriver driver=new ChromeDriver();
		//Returning Customer
	
	//creating obkect of the Login page
	LoginPage loginPage=new LoginPage(driver);
	
	loginPage.enterEmailId(User);
	loginPage.enterPassword(Password);
	loginPage.submitBtnClick();
	
	//No need of below line after page factory declaration
		/*driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(User);
		driver.findElement(By.id("input-password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();*/
		
		//verify TEXT IS PRESENT IN MY ACCOUNT PAGE
		
		//if condition failed after isDisplayed then msg will display in report that "Edit your account is not present"
	
	MyAccountPage myAccountPage=new MyAccountPage(driver);
	
	Assert.assertTrue(myAccountPage.getDisplayStatusofEditMyAccount(),"Edit your account is not present");
	
	//	Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account is not present");
				driver.quit();
		System.out.println("Edit your account information IS PRESENT");
	}

	//Dataprovider to read data from excel in Utils

	@DataProvider(name="validCredentialSupplier")
		public Object[][] supplyTestDatafromExcel(){
			Object[][] data=Utilities.getTestDatafromExcel("Login");
			return data;
	
			}
	
	@Test (priority=2) // add dependency
	
	public void verifyLoginwithvalidCredential() {
		//WebDriver driver=new ChromeDriver();
		//Returning Customer
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validUser"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		//verify TEXT IS PRESENT IN MY ACCOUNT PAGE
		
		//if condition failed after isDisplayed then msg will display in report that "Edit your account is not present"
		
		//Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Edit your account information')]")).isDisplayed(),"Edit your account is not present");

		
Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account is not present");
				driver.quit();
		System.out.println("Edit your account information IS PRESENT");
	}
	

	@Test(priority=3) 
	public void verifyLoginWithInvalidCredential() {
	
		//generate time stamp--kab run hua test
		Date date=new Date();
		System.out.println(date);
		//replacing date [blank spaces with _ underscore]
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
			
		//Returning Customer // appending timestamp in email id to avoid duplicacy in login
		
		String User=Utilities.generateTimeStamp();
		System.out.println("User=" + User);
		System.out.println("***********************");
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailId(Utilities.generateTimeStamp());
		//driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimeStamp());
	
		String pass="pass_" +Utilities.generateTimeStamp();
		System.out.println("Password=" + pass);
		System.out.println("***********************");
		loginPage.enterPassword(pass);
	//	driver.findElement(By.id("input-password")).sendKeys(pass);
		loginPage.submitBtnClick();
		//.findElement(By.xpath("//input[@value='Login']")).click();
		
		
		//find out the div text/Inner text under div tag ...to verify the warning msg
		
		String ActualWarningMessage=loginPage.retriverEmailPasswordNoMatchWarningMessage();
		String ExpectedWarningMessage=dataprop.getProperty("ExpWarMsg");
				//	String ActWarMsg=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
	//	String ExpWarMsg1="Warning: No match for E-Mail Address and/or Password."; //copy from application
		//to display  text , in report if match does not happen
	
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Expected Warning msg is not preesnt");
		
		
	}
	
	//creating method for timestamp  to see at what time ...aise scenario me jab test fail hoga many time login
	//attempt se ..due to security reason acct locked... so hamata automa na suffer ho isslye time stamp pass
	
	
	@AfterMethod
	public void closeBrowser() {

		
		driver.quit();
	}
}

