package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.pages.AccountLoginPage;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.CreateNewAccountPage;
import com.ecommerce.pages.DashBoardPage;
import com.ecommerce.pages.DetailsPage;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.MobilePage;
import com.ecommerce.pages.TVPage;
import com.ecommerce.pages.WishListPage;

public class PurchaseTests extends BaseTest {

	static HomePage homepageObject;
	static AccountLoginPage accountLoginPageObject;
	static CreateNewAccountPage createNewAccountPageObject;
	static DashBoardPage dashBoardPageObject;
	static WishListPage wishListPageObject;
	static CheckoutPage checkoutPageObject;
	static TVPage tvPageObject;
	static MobilePage mobilepageObject;
	static DetailsPage detailpageObject;
	
	@Test(priority=1/*dependsOnMethods ="TEST05"*/)
	public void TEST05_01() {
		homepageObject = new HomePage();
		homepageObject.AccountDD();
		accountLoginPageObject = homepageObject.loginlink();
		dashBoardPageObject = accountLoginPageObject.login("shanu1@gmail.com","wecando");
		String message =homepageObject.welcomemessage();
		System.out.println(message);
		tvPageObject =homepageObject.tvLink();
		tvPageObject.addProducttoWishList("LG LCD");
		wishListPageObject=dashBoardPageObject.shareWishlist();
		wishListPageObject.shareWishlisttoEmail();
		System.out.println(dashBoardPageObject.dashboardMessage());
		homepageObject.AccountDD();
		homepageObject.logout();
		//System.out.println(dashBoardPageObject.dashboardMessage());
		
		
		
	}
	
	
	
	@Test(priority=2,dependsOnMethods ="TEST05_01")
	public void TEST06() throws Exception {
		homepageObject = new HomePage();
		homepageObject.AccountDD();
		accountLoginPageObject = homepageObject.loginlink();
		dashBoardPageObject = accountLoginPageObject.login("shanu1@gmail.com","wecando");
		wishListPageObject= new WishListPage();
		dashBoardPageObject.selectDashBoardMenu("My Wishlist");
		checkoutPageObject=wishListPageObject.addtoCartFromWishList();
		float shippingestimate = (checkoutPageObject.estimateShipping());
		float subtotal =checkoutPageObject.subtotal();
		float total = checkoutPageObject.total();
		checkoutPageObject.proceedCheckOut();
		Assert.assertEquals(shippingestimate+subtotal,total);
		String id =checkoutPageObject.billingInfo();
		System.out.println(id);
	}
	
	
	@Test(priority=3)
	public void TEST09() 
	{		homepageObject = new HomePage();
	mobilepageObject = homepageObject.mobileLink();
	checkoutPageObject = mobilepageObject.addtoCart("IPhone");
	checkoutPageObject.applyDiscount("guru50");
	float discount =checkoutPageObject.discountAmount();
	float subtotal= checkoutPageObject.subtotal();
	float discountpercentage = ((discount*100)/subtotal);
	System.out.println("discount"+discount);
	System.out.println("subtotal"+subtotal);
	System.out.println("discount percentage"+discountpercentage);
	Assert.assertEquals(discountpercentage, 5.0f);
	//homepageObject.AccountDD();
	//homepageObject.logout();
	
	}
}
