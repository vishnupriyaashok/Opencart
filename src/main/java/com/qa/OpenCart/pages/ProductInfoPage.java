package com.qa.OpenCart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.utilities.ElementUtilities;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtilities eleUtil;
	private HashMap<String, String> productInfoMap;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div#content img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By priceMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addtoCart = By.id("button-cart");
	private By successMSG = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilities(driver);
	}

	public String getProductHeaderText() {
		return eleUtil.doElementGetText(productHeader).trim();
	}

	public int getProductImageCount() {
		return eleUtil.getElements(productImages).size();
	}

	
	
	public HashMap<String, String> getProductInfo() {
		productInfoMap=new LinkedHashMap<String,String>();
		productInfoMap.put("productname", getProductHeaderText());
		productInfoMap.put("productimagecount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}
	
	
	
	
	private void getProductMetaData() {
		List<WebElement> productData = eleUtil.getElements(productMetaData);
		for (WebElement e : productData) {
			String text = e.getText().trim();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceData = eleUtil.getElements(priceMetaData);
		String price = priceData.get(0).getText().trim();
		String exPrice = priceData.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exprice", exPrice);
	}
	

}
