package com.ninja.Repository.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
//object
	WebDriver driver;
	@FindBy (name="firstname")
	private WebElement firstName;
	
	@FindBy (name="lastname")
	private WebElement lastName;
	
	@FindBy (name="email")
	private WebElement emailAddress;
	
	@FindBy (name="telephone")
	private WebElement telephone;
	@FindBy (name="password")
	private WebElement password;
	@FindBy (name="confirm")
	private WebElement confirmPassword;
	
	@FindBy (name="agree")
	private WebElement agreeCheckBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement SucessText;
	
	
	public  RegisterPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//actions
	
	public void enterFname(String fname) {
		firstName.sendKeys(fname);
	}
	public void enterLname(String Lname) {
		lastName.sendKeys(Lname);
	}
	public void enterEmail(String Email) {
		emailAddress.sendKeys(Email);
	}
	public void enterTelephone(String Telephone) {
		telephone.sendKeys(Telephone);
	}
	public void enterPassword(String Password) {
		password.sendKeys(Password);
	}
	public void enterConfirmPassword(String ConfirmPassword) {
		confirmPassword.sendKeys(ConfirmPassword);
	}
	public void agreeBtn() {
		agreeCheckBox.click();
	}
	public void continueClick() {
		continueBtn.click();
	}
	public String actualSucceessText() {
		String Text=SucessText.getText();
		return Text;
	}
	
	}
