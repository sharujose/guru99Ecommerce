package com.ecommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLoginPage extends BasePage{
	
	
	@FindBy(xpath = "//span[contains(text(),'Create an Account')]")
	WebElement createNewAccount;
	
	@FindBy(xpath = "//input[@title='Email Address']")
	WebElement emailId;
	@FindBy(xpath = "//input[@title='Password']")
	WebElement password;
	@FindBy(xpath = "//button[@title='Login']")
	WebElement login;
	
	public CreateNewAccountPage createNewUser() {
		click(createNewAccount);
		return new CreateNewAccountPage();
	}
	
	
	public DashBoardPage login(String email,String passwordtext) {
		sendkeys(emailId,email);
		sendkeys(password,passwordtext);
		click(login);
		return new DashBoardPage();
	}
	
	

}
