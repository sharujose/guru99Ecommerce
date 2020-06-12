package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.pages.AccountLoginPage;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.CreateNewAccountPage;
import com.ecommerce.pages.DashBoardPage;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.MyOrdersPage;
import com.ecommerce.pages.TVPage;
import com.ecommerce.pages.WishListPage;

public class OrderTests extends BaseTest {

	static HomePage homepageObject;
	static AccountLoginPage accountLoginPageObject;
	static DashBoardPage dashBoardPageObject;
	static WishListPage wishListPageObject;
	static MyOrdersPage myOrdersPageObject;
	static CheckoutPage checkoutPageObject;

	@Test(priority=1 ,description ="Test: Verify the previously created accounr status is pending")
	/*
	 * 1. Go to http://live.demoguru99.com/ 2. Click on My Account link 3. Login in
	 * application using previously created credential 4. Click on 'My Orders' 5.
	 * Click on 'View Order' 6. Verify the previously created order is displayed and
	 * status is Pending 7. Click on 'Print Order' link
	 */
	public void TEST07() {
		homepageObject = new HomePage();
		homepageObject.AccountDD();
		accountLoginPageObject = homepageObject.loginlink();
		dashBoardPageObject = accountLoginPageObject.login("sharukutty@gmail.com", "wecando");
		String message = homepageObject.welcomemessage();
		System.out.println(message);
		dashBoardPageObject.selectDashBoardMenu("My Orders");
		myOrdersPageObject = new MyOrdersPage();
		String orderStatus = myOrdersPageObject.getOrderStatus("100011880");
		System.out.println("order status of requested order no is :" + orderStatus);
		Assert.assertEquals(orderStatus, "Pending");
		myOrdersPageObject.viewandPrintOrder("100011880");
		//homepageObject.AccountDD();
		//homepageObject.logout();

	}

	
	/*  Verify you are able to change or reorder previously added product
	 *  Test Data = QTY = 10
	Test Steps:
	1. Go to http://live.demoguru99.com/
	2. Click on my account link
	3. Login in application using previously created credential
	4. Click on 'REORDER' link , change QTY & click Update
	5. Verify Grand Total is changed
	6. Complete Billing & Shipping Information
	7. Verify order is generated and note the order number

	Expected outcomes:
	1) Grand Total is Changed
	2) Order number is generated
	*/

	@Test(priority=2 ,description ="Test : order previously added product")
	public void TEST08() {
		int productQuantity = 10;
		homepageObject = new HomePage();
		homepageObject.AccountDD();
		accountLoginPageObject = homepageObject.loginlink();
		dashBoardPageObject = accountLoginPageObject.login("sharukutty@gmail.com", "wecando");
		String message = homepageObject.welcomemessage();
		System.out.println(message);
		dashBoardPageObject.selectDashBoardMenu("My Orders");
		myOrdersPageObject = new MyOrdersPage();
		checkoutPageObject = myOrdersPageObject.reOrder("100011880");
		checkoutPageObject.updateQuantity(productQuantity);
		float shippingestimate = (checkoutPageObject.estimateShipping());
		float subtotal = checkoutPageObject.subtotal();
		float unitPrice = checkoutPageObject.productPrice();
		Assert.assertEquals((unitPrice * productQuantity), subtotal);
		float total = checkoutPageObject.total();
		checkoutPageObject.proceedCheckOut();
		Assert.assertEquals(shippingestimate + subtotal, total);
		String id = checkoutPageObject.reOrderBillinfo();
		System.out.println(id);
		// System.out.println("order status of requested order no is :" + orderStatus);
		// Assert.assertEquals(orderStatus, "Pending");
		// myOrdersPageObject.viewandPrintOrder("100011880");
		homepageObject.AccountDD();
		homepageObject.logout();

	}

}
