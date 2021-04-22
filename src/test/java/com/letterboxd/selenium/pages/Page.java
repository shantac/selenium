package com.letterboxd.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.letterboxd.selenium.helpers.TimeoutConstants;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

	private WebDriver driver;
	private WebDriverWait wait;

	public Page(WebDriver driver, By knownElementLocator) {
		this.driver = driver;
		wait = new WebDriverWait(driver, TimeoutConstants.CONDITIONAL_TIMEOUT_IN_SEC);
		this.waitForReadyState();
		this.isCorrectPageLoaded(knownElementLocator);
		driver.manage().timeouts().pageLoadTimeout(TimeoutConstants.PAGE_LOAD_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	private void isCorrectPageLoaded(By knownElementLocator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(knownElementLocator));
	}

	private void waitForReadyState() {

		ExpectedCondition<Boolean> waitForJQuery = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> waitForDomReadyState = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		wait.until(waitForJQuery);
		wait.until(waitForDomReadyState);
	}

	protected WebDriverWait getWebDriverWait() {
		return wait;
	}

	public WebDriver getDriver() {
		return driver;
	}

	protected void click(By byLocator) {
		wait.until(ExpectedConditions.elementToBeClickable(byLocator)).click();
	}

	protected void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	protected void clickUsingJs(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	protected void clearAndType(WebElement element, String text) {
		WebElement webElement = wait.until(ExpectedConditions.visibilityOf(element));
		webElement.clear();
		webElement.sendKeys(text);
	}

	protected boolean isElementVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
