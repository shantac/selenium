package com.letterboxd.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.letterboxd.selenium.util.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	private static WebDriver driver = null;

	public static WebDriver createWebDriver() throws MalformedURLException {
		return createWebDriver(RunMode.LOCAL, WebDriverType.CHROME);
	}

	public static WebDriver createWebDriver(RunMode runMode, WebDriverType driverType) throws MalformedURLException {
		switch (runMode) {
		case LOCAL:
			driver = createLocalBrowser(driverType);
			break;
		case GRID:
			driver = createRemoteBrowser(driverType);
			break;
		default:
			driver = createLocalBrowser(driverType);
			break;
		}
		return driver;
	}

	private static WebDriver createRemoteBrowser(WebDriverType driverType) throws MalformedURLException {
		String gridUrl = PropertyUtils.getValue("grid.url");
		if (StringUtils.isEmpty(gridUrl)) {
			throw new IllegalArgumentException("Grid URL cannot be null for remote run!");
		}
		switch (driverType) {
		case CHROME:
			driver = new RemoteWebDriver(new URL(gridUrl), getChromeOptions());
			break;
		case FIREFOX:
			driver = new RemoteWebDriver(new URL(gridUrl), getFirefoxOptions());
			break;
		case IE:
			driver = new RemoteWebDriver(new URL(gridUrl), getIEOptions());
			break;
		default:
			throw new IllegalArgumentException("Invalid driver type. Driver type must be defined.");
		}
		return driver;
	}

	private static WebDriver createLocalBrowser(WebDriverType driverType) {
		switch (driverType) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(getChromeOptions());
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(getFirefoxOptions());
			break;
		case IE:
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver(getIEOptions());
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(getChromeOptions());
			break;
		}
		return driver;
	}

	private static InternetExplorerOptions getIEOptions() {
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.disableNativeEvents();
		// add more capabilities
		return ieOptions;
	}

	private static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setHeadless(false);
		// add more capabilities
		return firefoxOptions;
	}

	private static ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(false);
		// add more as needed
		return chromeOptions;
	}

	public static WebDriver getCurrentDriver() {
		if (driver == null) {
			throw new RuntimeException(
					"You must call createWebDriver() method to create driver with appropriate parameters first!");
		}
		return driver;
	}
}
