package com.qa.OpenCart.utilities;

public interface Browser {
	
	public String CHROME_BROWSER_VALUE="chrome";
	public String FIREFOX_BROWSER_VALUE="firefox";
	public String SAFARI_BROWSER_VALUE="safari";
	
	public String CHROME_DRIVER_BINARY_KEY="webdriver.chrome.driver";
	public String GECKO_DRIVER_BINARY_KEY="webdriver.gecko.driver";
	
	public String CHROME_DRIVER_PATH=".\\src\\test\\resource\\drivers\\chromedriver.exe";
	public String FIREFOX_DRIVER_PATH=".\\src\\test\\resource\\drivers\\geckodriver.exe";
}
