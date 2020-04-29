package com.ecommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class HomePage extends BasePage {
	
	@FindBy(xpath="//div[@class='page-title']")
	WebElement homepageTitle;
	
	
	@FindBy(xpath="//a[@class='level0 ']")
	WebElement mobilelink;
	
	
	public String getTitle()
	{
		System.out.println(DriverManager.getDriver().getTitle());
		return DriverManager.getDriver().getTitle();
	}
	
	public String homepageTitle()
	{
		
		String titleText = homepageTitle.getText();
		System.out.println(titleText);
		return titleText;
		
	}
	
	public MobilePage mobileLink()
	{
		click(mobilelink);
		
		return new MobilePage();
		
	}
	

}
