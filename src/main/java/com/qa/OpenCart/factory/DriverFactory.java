package com.qa.OpenCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.OpenCart.utilities.AppErrors;
import com.qa.OpenCart.utilities.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = Logger.getLogger(DriverFactory.class);

	/*
	 * This method is used to initialize the WebDriver on the basis of given
	 * browsername This method will take care of remote and local execution
	 * 
	 * @param browserName
	 * 
	 * @return WebDriver
	 */
	public WebDriver initializeBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		log.info("Browser name is " + browserName);

		// System.out.println("Browser name is " + browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))){
				initRemoteWebDriver(Browser.CHROME_BROWSER_VALUE);
			} else {// local execution
				WebDriverManager.chromedriver().setup();
				// System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY,
				// Browser.CHROME_DRIVER_PATH);
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

			}

		} else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteWebDriver(Browser.FIREFOX_BROWSER_VALUE);
			} else {
				WebDriverManager.firefoxdriver().setup();
				// System.setProperty(Browser.GECKO_DRIVER_BINARY_KEY,
				// Browser.FIREFOX_DRIVER_PATH);
				// driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));

			}

		} else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE)) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		} else {
			System.out.println(AppErrors.BROWSER_NOT_FOUND_ERROR_MESSAGE + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/*
	 * this method will return the local copy of webdriver(driver)
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	/*
	 * This method is used to run test cases on (Docker)
	 */

	private void initRemoteWebDriver(String browserName) {
		System.out.println("Runnings test cases on Selenoid Grid Server " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			if (browserName.equalsIgnoreCase("firefox")) {
				try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),
							optionsManager.getFireFoxOptions()));
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
			}

		}
	}
	/*
	 * This method is used to initialize the properties on the basis of given
	 * Environment: Environment:QA/DEV/STAGE/PROD
	 */

	public Properties initializeProperties() {

		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running tests on the environment " + envName);
		log.info("Running tests on the environment " + envName);
		if (envName == null) {
			log.info("No environment given, hence running on QA");
			 System.out.println("No environment given, hence running on QA");
			try {
				ip = new FileInputStream(".\\src\\test\\resource\\config\\qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					log.info("Running tests on the qa environment ");
					ip = new FileInputStream(".\\src\\test\\resource\\config\\qa.config.properties");
					break;
				case "stage":
					log.info("Running tests on the stage environment ");
					ip = new FileInputStream(".\\src\\test\\resource\\config\\stage.config.properties");
					break;
				case "dev":
					log.info("Running tests on the dev environment ");
					ip = new FileInputStream(".\\src\\test\\resource\\config\\dev.config.properties");
					break;
				case "uat":
					log.info("Running tests on the uat environment ");
					ip = new FileInputStream(".\\src\\test\\resource\\config\\uat.config.properties");
					break;
				case "prod":
					log.info("Running tests on the prod environment ");
					ip = new FileInputStream(".src\\test\\resource\\config\\config.properties");
					break;
				default:
					log.error("Please pass the right environment");
					// log.warn("Please pass the right environment");
					// System.out.println("Please pass the right environment" + envName);
					break;
				}
			} catch (Exception e) {
			}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
