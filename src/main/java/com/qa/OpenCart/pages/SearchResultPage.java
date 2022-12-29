package com.qa.OpenCart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.utilities.Constants;
import com.qa.OpenCart.utilities.ElementUtilities;

import io.qameta.allure.Step;

public class SearchResultPage {
    private WebDriver driver;
	private ElementUtilities eleUtil;
	
	private By header=By.cssSelector("div#content h1");
	private By productList=By.cssSelector("div.caption a");
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtilities(driver);
	}
	
	@Step("Search reult page header")
	public String getResultPageHeader() {
		if(eleUtil.doIsElementDisplayed(header)) {
			return eleUtil.doElementGetText(header);
		}
		return null;
	}
	
	@Step("product list count")
	public int getProductListCount() {
		return eleUtil.getElements(productList).size();	
	}
	
	@Step("getting product result list")
	public List<String> getProductResultList() {
		List<WebElement> productLists=eleUtil.waitForElementsToBeVisible(productList,Constants.DEFAULT_TIME_OUT);
		List<String> productValList=new ArrayList<String>();
		for(WebElement e:productLists) {
			String text=e.getText();
			productValList.add(text);	
		}
		return productValList;
	}
	
	
	@Step("selecting product {0}")
	public  ProductInfoPage  selectProduct(String productName) {
		System.out.println("Main prduct name is "+ productName);
		List<WebElement> productLists=eleUtil.waitForElementsToBeVisible(productList, Constants.DEFAULT_TIME_OUT);
		for(WebElement e:productLists) {
			String text=e.getText();
			if(text.equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
