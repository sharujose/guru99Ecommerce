package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.ecommerce.browser.DriverManager;

public class CheckoutPage extends BasePage {

	@FindBy(xpath = "//input[@class='input-text qty']")
	WebElement quantityTextBox;

	@FindBy(xpath = "//button[@title='Update']")
	WebElement updateQuantityButton;

	@FindBy(xpath = "//p[@class='item-msg error']")
	WebElement errorMessage;

	@FindBy(id = "empty_cart_button")
	WebElement emptyCartButton;
	
	@FindBy(id = "coupon_code")
	WebElement coupon_code;
	
	@FindBy(xpath = "//button[@type='button' and @title='Apply']")
	WebElement applydiscount;
	

	@FindBy(xpath = "//div[@class='page-title']")
	WebElement titleMessage;

	@FindBy(id = "country")
	WebElement countryDD;
	@FindBy(id = "region_id")
	WebElement region_DD;
	@FindBy(id = "postcode")
	WebElement postcodeTextBox;
	@FindBy(xpath = "//button[@title='Estimate']")
	WebElement estimateButton;
	@FindBy(id = "s_method_flatrate_flatrate")
	WebElement rateRadioButton;
	@FindBy(xpath = "//button[@title='Update Total']")
	WebElement updateTotalButton;
	@FindBy(xpath = "//label[@for='s_method_flatrate_flatrate']/span")
	WebElement shippingCharge;

	@FindBy(xpath = "//table[@id='shopping-cart-totals-table']/tbody/tr/td[2]")
	WebElement subTotal;
	
	@FindBy(xpath = "//table[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]")
	WebElement discountAmount;
	

	@FindBy(xpath = "//table[@id='shopping-cart-totals-table']//tr[1]/td[2]/strong/span[@class='price']")
	WebElement totalAmount;
	@FindBy(xpath = "//button[@title='Proceed to Checkout']")
	WebElement proceedtoCheckOut;

	@FindBy(id = "billing:firstname")
	WebElement billing_firstname;

	@FindBy(id = "billing:lastname")
	WebElement billing_lastname;

	@FindBy(id = "billing:street1")
	WebElement billing_street1;

	@FindBy(id = "billing:city")
	WebElement billing_city;

	@FindBy(id = "billing:postcode")
	WebElement billing_postcode;

	@FindBy(id = "billing:country_id")
	WebElement billing_country_dd;

	@FindBy(id = "billing:region_id")
	WebElement billing_region_id;

	@FindBy(id = "billing:telephone")
	WebElement billing_telephone;

	@FindBy(xpath = "//input[@type='radio' and @title='Ship to this address']")
	WebElement shippingAddressRadio;

	
	@FindBy(xpath = "//input[@type='radio' and @title='Ship to different address']")
	WebElement shippingDifferentAddressRadio;
	
	@FindBy(xpath = "//button[@title='Continue']")
	WebElement ContinueButton;

	@FindBy(xpath = "//button[@onclick='shippingMethod.save()']")
	WebElement shippingmethodContinueButton;

	@FindBy(id = "p_method_checkmo")
	WebElement p_method_checkmo;

	@FindBy(xpath = "//button[@onclick='payment.save()']")
	WebElement paymentContinueButton;

	@FindBy(xpath = "//button[@title='Place Order']")
	WebElement place_OrderButton;

	@FindBy(xpath = "//p[contains(text(),'Your order #')]")
	WebElement orderid;
	
	@FindBy(xpath = "//span[@class='cart-price']/span[1]")
	WebElement unitPrice;
	

	By framecloseBtn = By.id("closeBtn");
	
	
	public String reOrderBillinfo() {

		switchFrame("flow_close_btn_iframe", framecloseBtn);

		try {
			Select bAddr = new Select(DriverManager.getDriver().findElement(By.name("billing_address_id")));
			//int bAddrSize = bAddr.getOptions().size();
			bAddr.selectByIndex(0);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("No dropdown element present");
		}
		
		click(shippingAddressRadio);
		click(ContinueButton);

		click(shippingmethodContinueButton);
		click(p_method_checkmo);
		click(paymentContinueButton);
		click(place_OrderButton);

		String message = orderid.getText();

		return message;
	}
	
	

	public String billingInfo() {

		switchFrame("flow_close_btn_iframe", framecloseBtn);

		try {
			Select bAddr = new Select(DriverManager.getDriver().findElement(By.name("billing_address_id")));
			int bAddrSize = bAddr.getOptions().size();
			bAddr.selectByIndex(bAddrSize - 1);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("No dropdown element present");
		}

		sendkeys(billing_firstname, "Shanu");
		sendkeys(billing_lastname, "Sham");
		sendkeys(billing_street1, "ABC");
		sendkeys(billing_city, "NewYork");
		selectByValue(billing_region_id, "43");
		sendkeys(billing_postcode, "542896");

		selectByValue(billing_country_dd, "US");

		sendkeys(billing_telephone, "12345678");
		click(shippingAddressRadio);
		click(ContinueButton);

		click(shippingmethodContinueButton);
		click(p_method_checkmo);
		click(paymentContinueButton);
		click(place_OrderButton);

		String message = orderid.getText();

		return message;
	}

	public float estimateShipping() {

		selectByValue(countryDD, "US");
		selectByValue(region_DD, "43");
		sendkeys(postcodeTextBox, "542896");
		click(estimateButton);
		click(rateRadioButton);
		click(updateTotalButton);

		String message = shippingCharge.getText();
		String estimate = message.replaceAll("[$,]", "");
		return Float.parseFloat(estimate);

	}

	public float productPrice() {

		String message = unitPrice.getText();
		String unitprice = message.replaceAll("[$,]", "");
		return Float.parseFloat(unitprice);
	}
	
	public CheckoutPage applyDiscount(String discountcode) {

		sendkeys(coupon_code,discountcode);
		click(applydiscount);
		return this;
		
	}
	
	
	public float discountAmount() {

		String message = discountAmount.getText();
		String subtotal = message.replaceAll("[-$,]", "");
		return Float.parseFloat(subtotal);
	}
	
	public float subtotal() {

		String message = subTotal.getText();
		String subtotal = message.replaceAll("[$,]", "");
		return Float.parseFloat(subtotal);
	}
	
	public float total() {

		String message = totalAmount.getText();
		String totalAmount = message.replaceAll("[$,]", "");
		return Float.parseFloat(totalAmount);
		}
	

	public CheckoutPage proceedCheckOut() {

		

		click(proceedtoCheckOut);
		return this;
		
	}

	public String updateQuantity(int quantity) {

		quantityTextBox.clear();
		sendkeys(quantityTextBox, quantity);
		click(updateQuantityButton);
		String message = "";
        try
        {
		message = errorMessage.getText();
		
        }
        catch(Exception e)
        {
        	System.out.println("sucessfully updated quantity");
	}
        return message;
	}

	public String emptyCart() {

		click(emptyCartButton);
		String message = titleMessage.getText();
		return message;
	}

}
