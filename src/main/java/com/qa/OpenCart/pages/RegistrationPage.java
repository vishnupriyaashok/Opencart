package com.qa.OpenCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.utilities.Constants;
import com.qa.OpenCart.utilities.ElementUtilities;

public class RegistrationPage {

	
	private ElementUtilities eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']");
	private By agreeBtn = By.xpath("//input[@name='agree']");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By successMsg = By.cssSelector("div#content h1");
	private By registerLink = By.linkText("Register");
	private By logOutLink = By.linkText("Logout");

	
	public RegistrationPage(WebDriver driver) {
		
		eleUtil = new ElementUtilities(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String phone, String password,
			String subscribe) {

		eleUtil.waitForElementVisible(this.firstName, Constants.DEFAULT_TIME_OUT).sendKeys(firstName);
		eleUtil.getElement(this.lastName).sendKeys(lastName);
		eleUtil.getElement(this.email).sendKeys(email);
		eleUtil.getElement(this.phone).sendKeys(phone);
		eleUtil.getElement(this.password).sendKeys(password);
		eleUtil.getElement(this.confirmPassword).sendKeys(password);
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeBtn);
		eleUtil.doClick(continueBtn);
		
		if (getAccountRegisterSuccessMessage().contains(Constants.REGISTER_ACCOUNT_SUCCESS_MESSAGE)) {
			goToRegisterPage();
			return true;
		}
		return false;

	}

	public String getAccountRegisterSuccessMessage() {
		return eleUtil.waitForElementVisible(successMsg, Constants.DEFAULT_TIME_OUT).getText();
	}

	public void goToRegisterPage() {
		eleUtil.doClick(logOutLink);
		eleUtil.doClick(registerLink);
	}

}
