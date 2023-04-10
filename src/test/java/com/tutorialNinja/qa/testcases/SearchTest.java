package com.tutorialNinja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninja.Repository.qa.pages.HomePage;
import com.tutorialNinja.qa.base.Base;
@Listeners(com.ninja.qa.listners.MyListners.class)
public class SearchTest extends Base{
	public WebDriver driver;
	
	//to call the construtor/prop-method of super class
	public  SearchTest() {
		super();
		}
	
	@BeforeMethod
	public void commonCodeAtBeforeMethod() {
		
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		
	}
	@Test
	public void searchProduct() {
		
			HomePage homePageObj=new HomePage(driver);
			homePageObj.enterSearchText("HP");
			homePageObj.clickSearch();
			Assert.assertTrue(homePageObj.getStatusSearchResult(),"No matching product is found");
			
			
		//driver.findElement(By.name("search")).sendKeys("HP");
		//driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		
	}
//Assert.assertTrue(driver.findElement(By.xpath("//img[@title='HP LP3065']")).isDisplayed(),"No matching product is found");
		
		
	
	@Test(priority=2)
	public void searchInvalidProduct() {
		HomePage homePageObj=new HomePage(driver);
		homePageObj.enterSearchText("Honda");
		homePageObj.clickSearch();
		String expValue=homePageObj.noResultFound();
		//String expValue=driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria')]")).getText();
		System.out.println(expValue);
		String actValue="There is no product that matches the search criteria";
		Assert.assertTrue(expValue.contains(actValue), "String not found");
		
	}
	@Test(priority=3)
	public void searchWithoutProduct() {
		HomePage homePageObj=new HomePage(driver);
		homePageObj.enterSearchText("");
		homePageObj.clickSearch();
		String expValue=homePageObj.noResultFound();
		System.out.println(expValue);
		String actValue="There is no product that matches the search criteria";
		Assert.assertTrue(expValue.contains(actValue), "String not found");
		//driver.findElement(By.name("search")).sendKeys("");
		//driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		//String expValue=driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria')]")).getText();
		
	
		
		
	}
	@AfterMethod
	public void closeBrowser() {

		
		driver.quit();
	}

}
