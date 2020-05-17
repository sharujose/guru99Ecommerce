package com.ecommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewAccountPage extends BasePage {
	
	
	@FindBy(id = "firstname")
	WebElement firstname;

	@FindBy(id = "lastname")
	WebElement lastname;

	@FindBy(id = "email_address")
	WebElement email_address;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "confirmation")
	WebElement confirmation;
	
	@FindBy(xpath = "//button[@title='Register']")
	WebElement registerButton;
	
	@FindBy(xpath = "//p[@class='welcome-msg']")
	WebElement welcomeMessage;
	
	
	public String register(String fname,String lname,String email,String pass,String confirmPass)
	{
		sendkeys(firstname,fname);
		sendkeys(lastname,lname);
		sendkeys(email_address,email);
		sendkeys(password,pass);
		sendkeys(confirmation,confirmPass);
		click(registerButton);
		return welcomeMessage.getText();
		
	}
	
	
	
}
