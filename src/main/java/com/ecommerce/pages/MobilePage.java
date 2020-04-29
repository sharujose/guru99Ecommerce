package com.ecommerce.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class MobilePage extends BasePage {

	@FindBy(xpath = "//div[@class='page-title category-title']")
	WebElement mobilepageTitle;

	@FindBy(xpath = "//select[@title='Sort By']")
	WebElement sortDropDown;

	public String searchresults = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li/div/h2/a";

	public void getPrice(String productName) {

		String price = DriverManager.getDriver()
				.findElement(
						By.xpath("//div[@class='product-info']/h2/a[@title='" + productName + "']//following::span[2]"))
				.getText();
		System.out.println(price);

	}

	public boolean isProductListSorted() {

		List<WebElement> list = DriverManager.getDriver().findElements(By.xpath(searchresults));

		// List<String> mobilelist = new ArrayList<String>();
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {

			if (list.get(i).getText().compareTo(list.get(i + 1).getText()) > 1) {
				isSorted = false;
				break;
			}

			// System.out.println(list.get(i).getText());

			// mobilelist.add(list.get(i).getText());
			// System.out.println(mobilelist);

		}
		// make a copy of the list
		// List<String> sortedList = new ArrayList<String>(mobilelist);

		// sort the list
		// Collections.sort(sortedList);

		// true if the prices are sorted
		// System.out.println(sortedList.equals(mobilelist));

		return isSorted;
	}

	public String mobilepageTitle() {

		String titleText = mobilepageTitle.getText();
		System.out.println(titleText);
		return titleText;

	}

	public MobilePage sortByName() {

		// Select select = new Select(sortDropDown);
		// select.selectByVisibleText("Name");

		selectByVisibleText(sortDropDown, "Position");
		return this;

	}

}
