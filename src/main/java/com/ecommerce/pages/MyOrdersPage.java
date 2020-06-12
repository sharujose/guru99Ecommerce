package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class MyOrdersPage extends BasePage {

	@FindBy(xpath = "//a[@class='link-print']")
	WebElement print;
	@FindBy(xpath = "//select[@title='Results per page']")
	WebElement orderlistDD;
	

	public String getOrderStatus(String orderno) {
		
		/*
		 * try { explicitwait(orderlistDD); } catch(Exception e) {
		 * selectByVisibleText(orderlistDD,"50");
		 * 
		 * }
		 */
		selectByVisibleText(orderlistDD,"                    50                ");
		WebElement orderStatus = DriverManager.getDriver().findElement(By.xpath(
				"//table[@id='my-orders-table']//td[contains(text(),'" + orderno + "')]/../td[@class='status']"));
		return orderStatus.getText();
	}

	public MyOrdersPage viewandPrintOrder(String orderno) {
		
		try
		{
			explicitwait(orderlistDD);
		}
		catch(Exception e)
		{
			selectByVisibleText(orderlistDD,"                    50                ");
			
		}
		WebElement viewOrderLink = DriverManager.getDriver()
				.findElement(By.xpath("//table[@id='my-orders-table']//td[contains(text(),'" + orderno
						+ "')]/../td[@class='a-center view last']/span/a[contains(text(),'View Order')]"));
		click(viewOrderLink);
		click(print);
		
		return this;
	}
	
	public CheckoutPage reOrder(String orderno) {
		
		try
		{
			explicitwait(orderlistDD);
			selectByVisibleText(orderlistDD,"                    50                ");
		}
		catch(Exception e)
		{
			System.out.println("not more than 10 items");
			
		}
		WebElement reOrderLink = DriverManager.getDriver()
				.findElement(By.xpath("//table[@id='my-orders-table']//td[contains(text(),'" + orderno
						+ "')]/../td[@class='a-center view last']/span/a[contains(text(),'Reorder')]"));
		
		click(reOrderLink);
		return new CheckoutPage();
		
	}
	
	

}
