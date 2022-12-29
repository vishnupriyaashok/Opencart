package com.qa.OpenCart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCart.base.BaseTest;
import com.qa.OpenCart.utilities.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 100-Desigh login page for OpenCart application")
@Story("US 101-design login page features")

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object [][] getLoginNegativeData() {
		return new Object [][] {
			{"test@123","123445"},
			{"seleniumtesting123@gmail.com","123445"},
			{" ","123445"} 
			
			
		};
	}
	
	
	@Test(dataProvider="getLoginNegativeData")
	@Description("Login with invalid credentials")
	@Severity(SeverityLevel.NORMAL)
	public void invalidLoginTest(String un,String pwd) {
	Assert.assertTrue(loginpage.inValidLogin(un, pwd),AppErrors.LOGIN_PAGE_ERROR_MESSG_NOT_DISPLAYED);	
	}
	
	
	
	
}
