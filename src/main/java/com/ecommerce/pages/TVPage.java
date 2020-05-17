package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ecommerce.browser.DriverManager;

public class TVPage extends BasePage {

	
	
	public DashBoardPage addProducttoWishList(String productName) {

		WebElement addtoWishList= DriverManager.getDriver().findElement(By.xpath("//a[@title='" + productName
				+ "']//following::div[@class='actions']//child::a[contains(text(),'Add to Wishlist')]"));

		click(addtoWishList);

		return new DashBoardPage();

	}
	
	
	
}
