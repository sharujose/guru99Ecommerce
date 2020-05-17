package com.ecommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DetailsPage extends BasePage {

	@FindBy(xpath = "//span[@class='price']")
	WebElement productPrice;

	@FindBy(xpath = "//div[@class='product-name']")
	WebElement productName;
	
	
	private static Logger log = LogManager.getLogger(DetailsPage.class.getName());
	
	public String verifyPrice() {
		log.info(productName.getText() + "details page opened");
		String productprice = productPrice.getText();
		return productprice;
	}

}
