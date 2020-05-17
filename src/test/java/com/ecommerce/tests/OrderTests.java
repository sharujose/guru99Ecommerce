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

	@Test(priority=1)
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

	@Test(priority=2)
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
