package com.qa.OpenCart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.OpenCart.base.BaseTest;
import com.qa.OpenCart.utilities.AppErrors;
import com.qa.OpenCart.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
@Epic("Epic 100-Desigh login page for OpenCart application")
@Story("US 101-design login page features")

public class LoginPageTest extends BaseTest {

	@Test 
	@Description("Login page title test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginpage.getPageTitle();
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE,AppErrors.LOGIN_PAGE_TITILE_MISMATCHED);
	}

	@Test
	@Description("Login page URL test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageURl();
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test
	@Description("Forgot passworl link exist")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotpwdLinkExistTest() {
		Assert.assertTrue(loginpage.isFotgotPwdLinkExist());
	}

	@Test
	@Description("Login test with correct username and password")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accountPage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountPage.isAccPageHeaderExist());
	}

	@Test
	@Description("Register link test")
	@Severity(SeverityLevel.CRITICAL)
	public void isregisterLinkExistTest() {
		Assert.assertTrue(loginpage.isRegisterLinkExist());
	}
	
	@Test
	@Description("Footer list test")
	@Severity(SeverityLevel.NORMAL)
	public void footerListTest() {
		List<String> actFooterList=loginpage.getFooterList();
		Assert.assertEquals(actFooterList, Constants.FOOTER_LIST);
	}
	
	

}
