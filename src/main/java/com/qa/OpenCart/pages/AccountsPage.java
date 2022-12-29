package com.qa.OpenCart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.utilities.Constants;
import com.qa.OpenCart.utilities.ElementUtilities;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtilities eleUtil;
	
	private By searchField=By.name("search");
	private By header=By.cssSelector("div#logo a");
	private By accPageSectionsList=By.cssSelector("div#content h2");
	private By sideList=By.xpath("//aside[@id='column-right']//a");
	private By searchButton=By.cssSelector("div#search button");
	               
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtilities(driver);
	}
	
	public String getAccPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNT_PAGE_TITLE);
	}
	 
	public boolean isAccPageHeaderExist() {
		return eleUtil.doIsElementDisplayed(header);
	}
	
	public boolean isSearchFieldExist() {
		return eleUtil.doIsElementDisplayed(searchField);	
	}
	 
	public SearchResultPage doSearch(String productName) {
		if(isSearchFieldExist()) {
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
		}
		return null;
	}
	
	public List<String> getAccPageSectionsList() {
		List<WebElement> secList=eleUtil.getElements(accPageSectionsList);
		List<String> accsecList=new ArrayList<String>();
		for(WebElement e:secList) {
			String text=e.getText();
			accsecList.add(text);
		}
		return accsecList;	
	}
	
	public List<String> getAccPageSideList(){
		List<String>actSideList=new ArrayList<String>();
		List<WebElement> accPagesideList=eleUtil.getElements(sideList);
		for(WebElement e:accPagesideList) {
			String text=e.getText();
			actSideList.add(text);
		}
		return actSideList;
		
	}
	


}
