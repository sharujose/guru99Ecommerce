package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.MobilePage;

public class TitleTests extends BaseTest {

	static HomePage homepageObject;
	static MobilePage mobilepageObject;

	//@Test
	public static void TEST01() {
		homepageObject = new HomePage();
		String homepageTitle = homepageObject.homepageTitle();
		// Assert.assertEquals(homepageTitle, "THIS IS DEMO SITE FOR ", "failed");
		Assert.assertTrue(homepageTitle.contains("THIS IS DEMO SITE"));
		mobilepageObject = homepageObject.mobileLink();
		String mobilepageTitle = mobilepageObject.mobilepageTitle();
		Assert.assertTrue(mobilepageTitle.contains("MOBILE"));
		boolean productList = mobilepageObject.sortByName().isProductListSorted();
		Assert.assertTrue(productList);

	}

	@Test
	public static void TEST02() {
		homepageObject = new HomePage();
		mobilepageObject = homepageObject.mobileLink();
		mobilepageObject.getPrice("Sony Xperia");

	}
}
