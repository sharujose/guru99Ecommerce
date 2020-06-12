package com.ecommerce.tests;

import org.testng.annotations.Test;

import com.ecommerce.pages.AccountLoginPage;
import com.ecommerce.pages.CreateNewAccountPage;
import com.ecommerce.pages.DashBoardPage;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.TVPage;
import com.ecommerce.pages.WishListPage;

public class AccountTests extends BaseTest {
	static HomePage homepageObject;
	static AccountLoginPage accountLoginPageObject;
	static CreateNewAccountPage createNewAccountPageObject;
	static DashBoardPage dashBoardPageObject;
	static TVPage tvPageObject;
	static WishListPage wishListPageObject;
	
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


	@Test(description =" Test: create an account and  share wishlist to other poeple using email")
	public void TEST05() {
		homepageObject = new HomePage();
		homepageObject.AccountDD();
		accountLoginPageObject = homepageObject.createNewAccount();
		createNewAccountPageObject = accountLoginPageObject.createNewUser();
		String message =createNewAccountPageObject.register("Shanu","Sha1","shanu1@gmail.com","wecando","wecando");
		homepageObject.AccountDD();
		homepageObject.logout();
		System.out.println(message);
	}

	
	@Test(enabled=false)
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
		
		
		
	}

}
