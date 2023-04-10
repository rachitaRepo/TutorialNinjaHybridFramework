package com.ninja.Repository.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
    @FindBy(xpath="//input[@id='input-email']")
    private WebElement emailAddressField;
    
    @FindBy(id="input-password")
    private WebElement passAddressField;
    
    @FindBy(xpath="//input[@value='Login']")
    private WebElement loginBtn;

    @FindBy (xpath="//div[@class='alert alert-danger alert-dismissible']")
  private WebElement EmailPasswordNoMatchWarningMsg;
    
    
    public LoginPage(WebDriver driver) {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
		
       }
    //Actions
    public void enterEmailId(String emailText){
    	emailAddressField.sendKeys(emailText);
    	
    }	
  public void enterPassword(String passwordText) {
	  passAddressField.sendKeys(passwordText);
  }
    	
  public void submitBtnClick() {
	  loginBtn.click();
  }
  public String retriverEmailPasswordNoMatchWarningMessage() {
  
	 String ActWarningMsg= EmailPasswordNoMatchWarningMsg.getText();
	 System.out.println("act war mess****" + ActWarningMsg);
	return ActWarningMsg;
  

  
}}
