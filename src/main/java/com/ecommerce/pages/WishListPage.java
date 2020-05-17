package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ecommerce.browser.DriverManager;

public class WishListPage extends BasePage {

	@FindBy(xpath = "//button[@title='Share Wishlist']")
	WebElement shareWishlistButton;

	@FindBy(xpath = "//button[@title='Add to Cart']")
	WebElement addtoCartButton;

	@FindBy(id = "email_address")
	WebElement email_address;

	@FindBy(id = "message")
	WebElement message;
	
	@FindBy(xpath = "//p[@class='wishlist-empty']")
	WebElement emptyWishlistMessage;
	

	By frameclose = By.xpath("//div[@id='closeBtn']");

	public CheckoutPage addtoCartFromWishList() throws Exception {
		//Thread.sleep(3000);
		//switchFrame("flow_close_btn_iframe", frameclose);
		
		try
		{
			//if(emptyWishlistMessage.getText().equalsIgnoreCase("You have no items in your wishlist."))
			Assert.assertEquals(emptyWishlistMessage.getText(), "You have no items in your wishlist.");
			System.out.println("wishlist not available.Add product to wishlist first");
			
		}
		catch(Exception e)
		{
			click(addtoCartButton);
			
		}
		
		//click(addtoCartButton);
		return new CheckoutPage();
	}

	public WishListPage shareWishlisttoEmail() {

		sendkeys(email_address, "sharukutty@gmail.com");
		sendkeys(message, "checking");
		click(shareWishlistButton);
		return this;
	}

}
