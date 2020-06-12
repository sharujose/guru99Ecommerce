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
	
	

/*     Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.  
Test Steps:
1. Go to http://live.demoguru99.com/
2. Click on my account link
3. Click Create an Account link and fill New User information except Email ID
4. Click Register
5. Verify Registration is done. Expected account registration done.
6. Go to TV menu
7. Add product in your wish list - use product - LG LCD
8. Click SHARE WISHLIST 
9. In next page enter Email and a message and click SHARE WISHLIST
10.Check wishlist is shared. Expected wishlist shared successfully. 

*/

	
	@Test(priority=1,description ="Test :create an account and can share wishlist to other poeple using email.")
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
	
	/*  Verify user is able to purchase product using registered email id(USE CHROME BROWSER)     
	Test Steps:
	1. Go to http://live.demoguru99.com/
	2. Click on my account link
	3. Login in application using previously created credential
	4. Click on MY WISHLIST link 
	5. In next page, Click ADD TO CART link
	6. Enter general shipping country, state/province and zip for the shipping cost estimate
	7. Click Estimate 
	8. Verify Shipping cost generated 
	9. Select Shipping Cost, Update Total 
	10. Verify shipping cost is added to total 
	11. Click "Proceed to Checkout"
	12a. Enter Billing Information, and click Continue
	12b. Enter Shipping Information, and click Continue
	13. In Shipping Method, Click Continue
	14. In Payment Information select 'Check/Money Order' radio button. Click Continue
	15. Click 'PLACE ORDER' button 
	16. Verify Oder is generated. Note the order number

	NOTE: PROCEED TO CHECKOUT (step 6 ) was taken out, so as to allow the Estimate button step to get processed. 
	      Rest of the steps renumbered accordingly. 
	*/

	
	@Test(priority=2,dependsOnMethods ="TEST05_01",description ="Test : Verify user is able to purchase product using registered email")
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
		String order_id =checkoutPageObject.billingInfo();
		System.out.println(order_id);
	}
	
	
	/*  Verify Discount Coupon works correctly
	 *
	Test Case Description:
	1. Go to http://live.demoguru99.com/
	2. Go to Mobile and add IPHONE to cart
	3. Enter Coupon Code
	4. Verify the discount generated

	TestData:  Coupon Code: GURU50
	Expected result:
	1) Price is discounted by 5%

	*/

	
	@Test(priority=3,description ="Test : Discount coupon")
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
