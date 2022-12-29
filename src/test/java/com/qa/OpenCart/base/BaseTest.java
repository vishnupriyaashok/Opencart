package com.qa.OpenCart.base;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import com.qa.OpenCart.factory.DriverFactory;
import com.qa.OpenCart.pages.AccountsPage;
import com.qa.OpenCart.pages.LoginPage;
import com.qa.OpenCart.pages.ProductInfoPage;
import com.qa.OpenCart.pages.RegistrationPage;
import com.qa.OpenCart.pages.SearchResultPage;

public class BaseTest {
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginpage;
	public AccountsPage accountPage;
	public SearchResultPage searchResultPage;
	public RegistrationPage registrationPage;
	public ProductInfoPage productInfoPage;
	public SoftAssert softAssert;

	
	@BeforeTest
	public void setUp() {
	
		df = new DriverFactory();
		prop = df.initializeProperties();
		driver = df.initializeBrowser(prop);
		loginpage = new LoginPage(driver);
	    softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
