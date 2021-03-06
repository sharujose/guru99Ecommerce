package com.ecommerce.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.ecommerce.browser.Driver;
import com.ecommerce.browser.DriverManager;
import com.ecommerce.constants.Constants;

//import projectpages.TestLog4J;

public class BasePage {
	
	private static Logger log = LogManager.getLogger(BasePage.class.getName());

	public BasePage() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	
	

	public static void click(WebElement element)
	{
		explicitwait(element);
		element.click();
		log.info("Clicking is successfull on "+ element);
	}

	public static void explicitwait(WebElement element) {
		// TODO Auto-generated method stub

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	
	public static void explicitwaitPresence(By by) {
		// TODO Auto-generated method stub

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static void click(By by)
	{
		explicitwait(DriverManager.getDriver().findElement(by));
		click(DriverManager.getDriver().findElement(by));
	}



	public static void sendkeys(WebElement element, String text)  {
		explicitwait(element);
		element.sendKeys(text);
		//LogStatus.pass(text + " is entered in to the "+ element);
		
		log.info(text + " is entered in to the "+ element);
	}

	public static void sendkeys(By by, String text)  {
		sendkeys(DriverManager.getDriver().findElement(by), text);
	}

	
	

	public void switchFrame(String frameid,By frameclose) {
		//frame("flow_close_btn_iframe");
		DriverManager.getDriver().switchTo().frame(frameid);
		log.info("switched to frame"+frameid);
		click(frameclose);
		DriverManager.getDriver().switchTo().defaultContent();
		log.info("switched to main window");
	}

	public static void selectByValue(WebElement element,String text) {
		new Select(element).selectByValue(text);
	}
	
	public static void selectByVisibleText(WebElement element,String text) {
		explicitwait(element);
		new Select(element).selectByVisibleText(text);
		log.info(text + " selected from dropdown "+ element);
	}


}
