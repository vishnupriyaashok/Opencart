package com.qa.OpenCart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCart.base.BaseTest;
import com.qa.OpenCart.utilities.Constants;
import com.qa.OpenCart.utilities.ExcelUtilities;

public class RegistrationPageTests extends BaseTest {

	@BeforeClass
	public void regPagesetUp() {
		registrationPage = loginpage.navigateToRegisterPage();
	}

	public String getRandomEmail() {
		Random random = new Random();
		String email = "automation" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

//	@DataProvider
//	public Object[][] getRegisterData() {
//		return new Object[][] { { "Anu", "Agarwal", "1234567654", "Anu123", "yes" },
//				{ "Mak", "Kuppu", "7654123456", "Makkuppu", "no" },
//				{ "Ven", "Kuppu", "7654561234", "Makkuppu", "Yes" }, };
//	}
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtilities.getTestData(Constants.REGISTERDATA_SHEET);
	}
	
	

	@Test(dataProvider = "getRegisterData")
	public void accountRegistrationTest(String fName, String lName, String phone, String pwd, String subScribe) {
		Assert.assertTrue(registrationPage.accountRegistration(fName, lName, getRandomEmail(), phone, pwd, subScribe));

	}
}