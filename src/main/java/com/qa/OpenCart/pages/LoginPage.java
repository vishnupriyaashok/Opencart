package com.qa.OpenCart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.utilities.AppErrors;
import com.qa.OpenCart.utilities.Constants;
import com.qa.OpenCart.utilities.ElementUtilities;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtilities eleUtil;

	// 1.private By locators

	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By register = By.linkText("Register");
	private By footerList = By.xpath("//div[@class='col-sm-3']//a");
	private By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By contactUs = By.linkText(("contactus"));
	// 2.public constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilities(driver);
	}

	// 3. public page actions
	@Step("getting login page title")
	public String getPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}

	@Step("getting login page url")
	public String getLoginPageURl() {
		return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}

	@Step("checking forgot password link exist or not")
	public boolean isFotgotPwdLinkExist() {
		return eleUtil.doIsElementDisplayed(forgotPwd);
	}

	@Step("login to tvhe application with username{0} and password{1}")
	public AccountsPage login(String UN, String pwd) {
		eleUtil.waitForElementVisible(emailID, Constants.DEFAULT_TIME_OUT).sendKeys(UN);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	public boolean inValidLogin(String userName, String pasWord) {
		eleUtil.waitForElementsToBeVisible(emailID, Constants.DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(password, pasWord);
		eleUtil.doClick(loginBtn);
		String actErrorMessage = eleUtil.doElementGetText(loginErrorMessg);
		System.out.println(actErrorMessage);
		if (actErrorMessage.contains(AppErrors.LOGIN_PAGE_ERROR_MESSAGE)) {
			return true;
		}
		return false;
	}

	@Step("checking register link exist or not")
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForElementVisible(register, Constants.DEFAULT_TIME_OUT).isDisplayed();
	}

	@Step("navigating to registration page")
	public RegistrationPage navigateToRegisterPage() {
		if (isRegisterLinkExist()) {
			eleUtil.doClick(register);
			return new RegistrationPage(driver);
		}
		return null;
	}

	@Step("getting footer links")
	public List<String> getFooterList() {
		List<String> actFooterList = new ArrayList<String>();
		List<WebElement> footList = eleUtil.getElements(footerList);
		for (WebElement e : footList) {
			String text = e.getText();
			actFooterList.add(text);
		}
		return actFooterList;

	}
}
