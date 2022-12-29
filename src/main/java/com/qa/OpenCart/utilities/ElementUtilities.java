package com.qa.OpenCart.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.OpenCart.factory.DriverFactory;

public class ElementUtilities {
	private WebDriver driver;
	private JavaScriptUtilities jsUtil;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtilities(driver);
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;

		case "name":
			locator = By.name(locatorValue);
			break;

		case "xpath":
			locator = By.xpath(locatorValue);
			break;

		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;

		case "linkText":
			locator = By.linkText(locatorValue);
			break;

		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;

		case "tagName":
			locator = By.tagName(locatorValue);
			break;

		default:
			break;
		}
		return locator;

	}

	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By loactor) {
		return driver.findElements(loactor);

	}

	public List<String> getLinksTexts(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> linkList = getElements(locator);

		for (WebElement e : linkList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				textList.add(text);
			}
		}
		return textList;
	}

	public List<String> getElementAttributeList(By locator, String attributeName) {
		List<WebElement> eleList = getElements(locator);
		List<String> attriList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attriName = e.getAttribute(attributeName);
			System.out.println(attriName);
			attriList.add(attriName);
		}
		return attriList;
	}

	public void doSendKeys(By locator, String str) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(str);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsElementEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public boolean isElementPresent(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;

	}

	// ------------Drop down utilities--------------

	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public List<String> doGetOptionsList(By locator) {
		Select select = new Select(getElement(locator));

		List<String> optionsValList = new ArrayList<String>();
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsValList.add(text);
		}
		return optionsValList;
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> opList = select.getOptions();
		for (WebElement e : opList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public List<String> doGetSuggestionList(By locator, String value) {
		List<WebElement> sugList = getElements(locator);
		List<String> sugVal = new ArrayList<String>();
		for (WebElement e : sugList) {
			String text = e.getText();
			sugVal.add(text);
		}
		return sugVal;
	}

	// -------------Actions class------------

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
		;
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		;
	}

	public void selectSubMenu(By parentMenu, By childMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(3000);
		getElement(childMenu).click();
	}

	public void selectSubMenu(By parentMenu, By childMenu1, By childMenu2) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(childMenu1)).build().perform();
		Thread.sleep(3000);
		getElement(childMenu2).click();
	}

	public void selectSubMenu(By parentMenu, By childMenu1, By childMenu2, By childMenu3) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(childMenu1)).build().perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(childMenu2)).build().perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(childMenu3)).click().build().perform();
	}

	// ------context click------------

	public void doRightClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();

	}

	public void SelectRightClickMenu(By locator, By rightClickLocator, String text) {
		doRightClick(locator);
		List<WebElement> rightClickList = getElements(rightClickLocator);
		for (WebElement e : rightClickList) {
			String textval = e.getText();
			System.out.println(textval);
			if (textval.equals(text)) {
				e.click();
				break;
			}
		}
	}

	public List<String> getRightclickMenuList(By locator, By rightClickLocator) {

		List<String> rightClickMenuList = new ArrayList<String>();

		doRightClick(locator);
		List<WebElement> rightClickList = getElements(rightClickLocator);
		for (WebElement e : rightClickList) {
			String text = e.getText();
			rightClickMenuList.add(text);
		}
		return rightClickMenuList;

	}

	public int getRightclickMenuListCount(By locator, By rightClickLocator) {
		return getRightclickMenuList(locator, rightClickLocator).size();
	}

	// ------------------- Wait ------------

	/*
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param loactor
	 * 
	 * @param timeOut return WebElement
	 */

	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/*
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @Param locator
	 * 
	 * @Param timeOut
	 * 
	 * @Param pollingTime =500ms return WebElement
	 */

	public WebElement waitForElementPresent(By locator, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/*
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * 
	 * @param timeOut return WebElement
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/*
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @Param locator
	 * 
	 * @Param timeOut
	 * 
	 * @Param pollingTime =500ms return WebElemen
	 */
	public WebElement waitForElementVisible(By locator, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();

	}

	public String waitForUrl(int timeOut, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForUrlToBe(int timeOut, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForTitle(int timeOut, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;

	}

	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;

	}

	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebDriver waitForFrameByString(int timeOut, String frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void waitElementClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<String> getAllElementsTextListWithWait(By locator, int timeOut) {
		List<String> list = new ArrayList<String>();
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		for (WebElement e : eleList) {
			String text = e.getText();
			list.add(text);
		}
		return list;
	}

	// -------------Synchronization-----------------\

	public WebElement retryElement(By locator, int timeOut, int intervalTime) {
		int attempt = 0;
		WebElement element = null;

		while (attempt < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("No element found");
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempt++;
		}

		if (element == null) {
			try {
				throw new Exception("NoEleMentFoundFound");
			} catch (Exception e) {
				System.out.println("tried to find the element " + timeOut + "times" + " with an interval of "
						+ intervalTime + "ms");
			}
		}
		return element;
	}

}
