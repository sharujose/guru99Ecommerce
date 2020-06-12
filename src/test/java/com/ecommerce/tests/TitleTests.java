package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.DetailsPage;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.MobilePage;

public class TitleTests extends BaseTest {

	static HomePage homepageObject;
	static MobilePage mobilepageObject;
	static DetailsPage detailpageObject;
	static CheckoutPage checkoutPage;

	/*
	 * 
	 * Test Steps Step 1. Goto http://live.demoguru99.com/
	 * 2. Verify Title of the page
	 * 3. Click on ‘MOBILE’ menu  
	 * 4. Verify Title of the page
	 * 5. In the list of all mobile , select ‘SORT BY’ dropdown as ‘name’
	 * 6.Verify all products are sorted by name
	 */

	@Test(priority=1, description ="Test:Verify all products are sorted by name")
	public static void TEST01() {
		homepageObject = new HomePage();
		String homepageTitle = homepageObject.homepageTitle();
		// Assert.assertEquals(homepageTitle, "THIS IS DEMO SITE FOR ", "failed");
		Assert.assertTrue(homepageTitle.contains("THIS IS DEMO SITE"));
		mobilepageObject = homepageObject.mobileLink();
		String mobilepageTitle = mobilepageObject.mobilepageTitle();
		Assert.assertTrue(mobilepageTitle.contains("MOBILE"));
		boolean productList = mobilepageObject.sortByName("Name").isProductListSorted();
		Assert.assertTrue(productList);

	}

	/*
	 * Test Steps: 1. Goto http://live.demoguru99.com/ 2. Click on ‘MOBILE’ menu 
	 * 3.In the list of all mobile , read the cost of Sony Xperia mobile (which is
	 * $100) 
	 * 4. Click on Sony Xperia mobile 
	 * 5. Read the Sony Xperia mobile from detail page. Product value in list and details page should be equal ($100).
	 */

	 @Test(priority=2 ,description ="Test:product value should match in details and list")
	public static void TEST02() {
		homepageObject = new HomePage();
		mobilepageObject = homepageObject.mobileLink();
		String price = mobilepageObject.getPrice("Sony Xperia");
		detailpageObject = mobilepageObject.selectProduct("Sony Xperia");
		Assert.assertEquals(detailpageObject.verifyPrice(), price, "price not matching");

	}

	/*
	 * Test Steps: 1. Goto http://live.demoguru99.com/ 2. Click on ‘MOBILE’ menu 3.
	 * In the list of all mobile , click on ‘ADD TO CART’ for Sony Xperia mobile 4.
	 * Change ‘QTY’ value to 1000 and click ‘UPDATE’ button. Expected that an error
	 * is displayed "The requested quantity for "Sony Xperia" is not available. 5.
	 * Verify the error message 6. Then click on ‘EMPTY CART’ link in the footer of
	 * list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown. 7. Verify
	 * cart is empty
	 */

	@Test(priority=3,description ="Test:Verify error message on adding more items into cart")
	public static void TEST03() {
		homepageObject = new HomePage();
		mobilepageObject = homepageObject.mobileLink();
		checkoutPage = mobilepageObject.addtoCart("Sony Xperia");
		String expectederrorMessage = "* The maximum quantity allowed for purchase is 500.";
		// String expectederrorMessage = "* The requested quantity for \"Sony Xperia\"
		// is not available.";
		String expectedMessage = "SHOPPING CART IS EMPTY";

		try {

			Assert.assertEquals(checkoutPage.updateQuantity(1000), expectederrorMessage, "price not matching");
			Assert.assertEquals(checkoutPage.emptyCart(), expectedMessage, "cart empty message not appearing");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*      
	Test Steps:
	1. Goto http://live.demoguru99.com/
	2. Click on ‘MOBILE’ menu
	3. In mobile products list , click on ‘Add To Compare’ for 2 mobiles (Sony Xperia & Iphone)
	4. Click on ‘COMPARE’ button. A popup window opens
	5. Verify the pop-up window and check that the products are reflected in it
	   Heading "COMPARE PRODUCTS" with selected products in it.
	6. Close the Popup Windows
	*/

	@Test(priority=4,description ="Test:Verify compare popup window and selected products are same ")
	public static void TEST04() {
		homepageObject = new HomePage();
		mobilepageObject = homepageObject.mobileLink();
		
		Assert.assertTrue(mobilepageObject.selectProductCompare("IPhone").selectProductCompare("Sony Xperia").compareProducts());
		}

	
	
	
	
	
}
