package com.qa.OpenCart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.qa.OpenCart.factory.DriverFactory;
import com.qa.OpenCart.pages.AccountsPage;
import com.qa.OpenCart.pages.LoginPage;
import com.qa.OpenCart.pages.ProductInfoPage;
import com.qa.OpenCart.pages.RegistrationPage;
import com.qa.OpenCart.pages.SearchResultPage;

import io.qameta.allure.testng.TestInstanceParameter;

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

	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setUp(String browser,String browserVersion) {

		df = new DriverFactory();
		prop = df.initializeProperties();

		if (browser != null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserVersion);
		}

		driver = df.initializeBrowser(prop);
		loginpage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
