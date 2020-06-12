package com.ecommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class HomePage extends BasePage {

	@FindBy(xpath = "//div[@class='page-title']")
	WebElement homepageTitle;

	@FindBy(xpath = "//a[@class='level0 ' and contains(text(),'Mobile')]")
	WebElement mobilelink;
	
	@FindBy(xpath = "//a[@class='level0 ' and contains(text(),'TV')]")
	WebElement tvLink;
	

	@FindBy(xpath = "//span[@class='label' and contains(text(),'Account')]")
	WebElement accountLink;

	@FindBy(xpath = "//div[@class='links' ]//ul/li/a[@title='My Account']")
	WebElement myAccountLink;

	@FindBy(xpath = "//div[@class='links' ]//ul/li/a[@title='Log In']")
	WebElement login;
	
	@FindBy(xpath = "//p[@class='welcome-msg']")
	WebElement welcomeMessage;
	
	@FindBy(xpath = "//div[@class='links' ]//ul/li/a[@title='Log Out']")
	WebElement logout;
	
	
	
	public String getTitle() {
		System.out.println(DriverManager.getDriver().getTitle());
		return DriverManager.getDriver().getTitle();
	}

	public String homepageTitle() {

		String titleText = homepageTitle.getText();
		System.out.println("Homepage title is "+titleText);
		return titleText;

	}

	public MobilePage mobileLink() {
		click(mobilelink);

		return new MobilePage();

	}

	
	public TVPage tvLink() {
		click(tvLink);

		return new TVPage();

	}
	
	public String welcomemessage()
	{
		
		explicitwait(welcomeMessage);
		return welcomeMessage.getText();
		
	}
	public HomePage AccountDD() {
		click(accountLink);
		return this;
	}
	
	public AccountLoginPage createNewAccount() {
		click(myAccountLink);
		return new AccountLoginPage();
	}
	
	public AccountLoginPage loginlink() {
		click(login);
		return new AccountLoginPage();
	}
	
	public HomePage logout() {
		click(logout);
		return this;
	}

}
