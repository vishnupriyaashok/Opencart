package com.qa.OpenCart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCart.base.BaseTest;
import com.qa.OpenCart.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 200-Design account page for OpenCart application")
@Story("US 101-design login page features")
@Story("US 102-design account page features")
public class AccountPageTest extends BaseTest {
	@Description("pre login for account page tests")
	@BeforeClass
	public void accPageSetUp() {
		accountPage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("account page title test ")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPagetitleTest() {
		String actAccPageTitle = accountPage.getAccPageTitle();
		Assert.assertEquals(actAccPageTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Description("account page header test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageHeaderTest() {
		Assert.assertTrue(accountPage.isAccPageHeaderExist());
	}

	@Description("account page search filed exist test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageSearchFieldTest() {
		Assert.assertTrue(accountPage.isSearchFieldExist());
	}
	
	@Description("account page sections list test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageSectionsListTest() {
		List<String> actAccAPgeHeaderList = accountPage.getAccPageSectionsList();
		Assert.assertEquals(actAccAPgeHeaderList, Constants.ACCOUNT_PAGE_HEADER_LIST);
	}
	
	@Description("account page side list test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageSideList() {
		List<String> actAccPageSideList = accountPage.getAccPageSideList();
		Assert.assertEquals(actAccPageSideList, Constants.ACCOUNT_PAGE_SIDELIST);
	}
	
	@Description("search header test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void doSearchTest() {
		searchResultPage = accountPage.doSearch("MacBook");
		Assert.assertTrue(searchResultPage.getResultPageHeader().contains("MacBook"));
	}
	
	@Description("check product count after search")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchProductCountTest() {
		searchResultPage = accountPage.doSearch("iMac");
		int ActualProductCount = searchResultPage.getProductListCount();
		Assert.assertEquals(ActualProductCount, Constants.iMAC_PRODUCT_LIST_COUNT);
	}

	@Description("check product list after the search")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchProductListTest() {
		searchResultPage = accountPage.doSearch("iMac");
		List<String> actualProductList = searchResultPage.getProductResultList();
		System.out.println(actualProductList);
	}

}
