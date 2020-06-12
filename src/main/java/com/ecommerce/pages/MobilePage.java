package com.ecommerce.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.browser.DriverManager;

public class MobilePage extends BasePage {

	@FindBy(xpath = "//div[@class='page-title category-title']")
	WebElement mobilepageTitle;

	@FindBy(xpath = "//select[@title='Sort By']")
	WebElement sortDropDown;

	@FindBy(xpath = "//button[@title='Compare']")
	WebElement compareButton;

	@FindBy(xpath = "//button[@title='Close Window']")
	WebElement closeButton;

	@FindBy(xpath = "//div[@class='page-title title-buttons']//a")
	WebElement print;

	By compareProductNames = By.xpath("//ol[@id='compare-items']//p[@class='product-name']//a");

	By popupproductNames = By.xpath("//h2[@class='product-name']//a");

	public String searchresults = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li/div/h2/a";

	public String productList = "//div[@class='category-products']/ul/li/div/h2/a";

	public MobilePage selectProductCompare(String productName) {

		WebElement addtoCompareButton = DriverManager.getDriver().findElement(By.xpath("//a[@title='" + productName
				+ "']//following::div[@class='actions']//child::a[contains(text(),'Add to Compare')]"));

		click(addtoCompareButton);

		return this;

	}

	public boolean compareProducts() {

		boolean flag = false;
		List<WebElement> productCompareList = DriverManager.getDriver().findElements(compareProductNames);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();

		for (int i = 0; i < productCompareList.size(); i++) {

			list.add(productCompareList.get(i).getText());

		}
		System.out.println(list);
		click(compareButton);
		switchToNewWindow();
		System.out.println(print.getText());
		List<WebElement> popupproductList = DriverManager.getDriver().findElements(popupproductNames);
		for (int i = 0; i < popupproductList.size(); i++) {

			list2.add(popupproductList.get(i).getText());

		}
		System.out.println(list2);

		if (list.containsAll(list2) && list.size() == list2.size())

		{
			System.out.println("The products are matching in both the list");
			flag = true;

		}

		//closeButton.click();
		//DriverManager.getDriver().close();
		
		
		return flag;

	}

	public DetailsPage selectProduct(String productName) {

		List<WebElement> list = DriverManager.getDriver().findElements(By.xpath(productList));

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().equalsIgnoreCase(productName)) {
				click(list.get(i));
				break;
			}
		}
		return new DetailsPage();
	}

	public String getPrice(String productName) {

		String price = DriverManager.getDriver()
				.findElement(
						By.xpath("//div[@class='product-info']/h2/a[@title='" + productName + "']//following::span[2]"))
				.getText();
		System.out.println("Price of selected product"+productName+"is"+price);
		return price;

	}

	public CheckoutPage addtoCart(String productName) {

		WebElement addtoCartButton = DriverManager.getDriver().findElement(
				By.xpath("//div[@class='product-info']/h2/a[@title='" + productName + "']//following::button"));

		click(addtoCartButton);
		return new CheckoutPage();

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
		System.out.println("Title of mobilepage is"+titleText);
		return titleText;

	}

	public MobilePage sortByName(String name) {

		// Select select = new Select(sortDropDown);
		// select.selectByVisibleText("Name");

		selectByVisibleText(sortDropDown, name);
		return this;

	}

}
