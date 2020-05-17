package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class MyOrdersPage extends BasePage {

	@FindBy(xpath = "//a[@class='link-print']")
	WebElement print;

	public String getOrderStatus(String orderno) {
		WebElement orderStatus = DriverManager.getDriver().findElement(By.xpath(
				"//table[@id='my-orders-table']//td[contains(text(),'" + orderno + "')]/../td[@class='status']"));
		return orderStatus.getText();
	}

	public MyOrdersPage viewandPrintOrder(String orderno) {
		WebElement viewOrderLink = DriverManager.getDriver()
				.findElement(By.xpath("//table[@id='my-orders-table']//td[contains(text(),'" + orderno
						+ "')]/../td[@class='a-center view last']/span/a[contains(text(),'View Order')]"));
		click(viewOrderLink);
		click(print);
		
		return this;
	}
	
	public CheckoutPage reOrder(String orderno) {
		WebElement reOrderLink = DriverManager.getDriver()
				.findElement(By.xpath("//table[@id='my-orders-table']//td[contains(text(),'" + orderno
						+ "')]/../td[@class='a-center view last']/span/a[contains(text(),'Reorder')]"));
		
		click(reOrderLink);
		return new CheckoutPage();
		
	}
	
	

}
