package com.qa.OpenCart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCart.base.BaseTest;
import com.qa.OpenCart.utilities.Constants;

public class ProductInfoPageTests extends BaseTest {
	@BeforeTest
	public void productInfoSetuo() {
		accountPage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "MacBook", "MacBook" }, { "MacBook", "MacBook Air" }, { "MacBook", "MacBook Pro" },
				{ "Apple", "Apple Cinema 30\"" } };
	}

	@Test(dataProvider = "getProductData")
	public void productInfoHeaderTest(String sProduct, String mainProduct) {
		searchResultPage = accountPage.doSearch(sProduct);
		productInfoPage = searchResultPage.selectProduct(mainProduct);
		Assert.assertEquals(productInfoPage.getProductHeaderText(), mainProduct);
	}

	@DataProvider
	public Object[][] getImageCountData() {
		return new Object[][] {
			{"MacBook", "MacBook","5"},
			{"MacBook", "MacBook Air","4"},
			{"MacBook", "MacBook Pro","4"},
				
		};
	}

	@Test(dataProvider = "getImageCountData")
	public void productImageCountTest(String sProduct,String mainProduct,String imageCount) {
		searchResultPage = accountPage.doSearch(sProduct);
		productInfoPage = searchResultPage.selectProduct(mainProduct);
		Assert.assertTrue(productInfoPage.getProductImageCount()==Integer.valueOf(imageCount));
	}


	@Test
	public void productInfoTest() {
		searchResultPage = accountPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));

		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");

		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}

}
