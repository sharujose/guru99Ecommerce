package com.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class DashBoardPage extends BasePage{
	
	
	@FindBy(xpath = "//button[@title='Share Wishlist']")
	WebElement shareWishlistButton;
	
	@FindBy(xpath = "//ul[@class='messages']//span")
	WebElement dashboardmessage;
	
	By dashboardMenu = By.xpath("//div[@class='block-content']/ul/li/a");
	
	public WishListPage shareWishlist() {
		click(shareWishlistButton);
		return new WishListPage();
	}
	
	
	public   DashBoardPage selectDashBoardMenu(String value) {
		List<WebElement> menuList = DriverManager.getDriver().findElements(dashboardMenu);
		for(int i=0;i<menuList.size();i++)
		{
			if(menuList.get(i).getText().equalsIgnoreCase(value))
			{
				click(menuList.get(i));
				break;
				
			}
				
		}
		
		return this;
	}
	
	
	
	public String dashboardMessage() {
		String dashmessage = dashboardmessage.getText();
		return dashmessage;
	}
	
	

}
