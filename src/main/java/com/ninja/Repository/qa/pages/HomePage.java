package com.ninja.Repository.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

WebDriver driver;
	
//objects declaration : making webelement private so that outside of class cant be access


	@FindBy (xpath="//span[contains(text(),'My Account')]")
	private WebElement myAccountDropdown;

	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	
	@FindBy (linkText="Register")
	private WebElement RegisterOption;
	//create constructor of the page to initolaize webdriver
	
	//Search feature on
	@FindBy (name="search")
	private WebElement SearchTextBox;
	
	
	@FindBy (xpath="//i[@class='fa fa-search']")
	private WebElement SearchButton;
	
	
	@FindBy (xpath="//img[@title='HP LP3065']")
	private WebElement SearchResult;
	
	@FindBy (xpath="//p[contains(text(),'There is no product that matches the search criteria')]")
			private WebElement NoSearchResultFoundtext;
	
	
	public  HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
		public void clickonMyAccount() {
			
			myAccountDropdown.click();
		}
		public void selectLoginOption() {
			
			loginOption.click();
		}
		public void selectRegisterOption() {
			RegisterOption.click();}
		
		public void enterSearchText(String SearchText) {
			SearchTextBox.sendKeys(SearchText);
		}
		public void clickSearch() {
			SearchButton.click();
		}
		public Boolean getStatusSearchResult() {
			Boolean displayStatus=SearchResult.isDisplayed();
			return displayStatus;
		}
		public String noResultFound() {
			
			String noResultText=NoSearchResultFoundtext.getText();
			return noResultText;
		}
}

