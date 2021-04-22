package com.letterboxd.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WatchListPage extends Page {
	@FindBy(how = How.XPATH, using = "//nav[@class='profile-navigation']//a[contains(text(),'Watchlist')]")
	private WebElement watchList;

	@FindBy(how = How.ID, using = "frm-watchlist-autocomplete")
	private WebElement searchAFilm;

	@FindBy(how = How.CLASS_NAME, using = "ac_results")
	private WebElement searchResultWindow;

	@FindBy(how = How.XPATH, using = "//a[@data-form-id='filmlist-reset']")
	private WebElement clearWatchList;

	@FindBy(how = How.ID, using = "frm-reg-password")
	private WebElement passwordRequiredTxt;

	@FindBy(how = How.XPATH, using = "//*[@id='enter-password-form']//input[@value='Clear Watchlist']")
	private WebElement clearWatchListBtn;

	@FindBy(how = How.CLASS_NAME, using = "jnotify-notification")
	private WebElement notificationMessage;

	@FindBy(how = How.CSS, using = "section.js-watchlist-main-content li")
	private List<WebElement> watchListMovies;

	@FindBy(how = How.CSS, using = ".replace.menu-link.icon")
	private WebElement replaceMenuIcon;

	@FindBy(how = How.CSS, using = ".remove-from-watchlist.ajax-click-action")
	private WebElement removeFromWatchListAjaxIcon;

	@FindBy(how = How.XPATH, using = "//*[@id='content-nav']//*[text()='Sort by']/..//label")
	private WebElement sortByDropdown;

	public WatchListPage(WebDriver driver) {
		super(driver, By.id("frm-watchlist-autocomplete"));
	}

	public WatchListPage clickWatchList() {
		click(watchList);
		return this;
	}

	public WatchListPage searchAndAddAFilm(String filmName) {
		clearAndType(searchAFilm, filmName);
		getWebDriverWait().until(ExpectedConditions.visibilityOf(searchResultWindow));
		new Actions(getDriver()).sendKeys(Keys.ENTER).build().perform();
		return new WatchListPage(getDriver());
	}

	public WatchListPage clearWatchListIfExist(String password) {
		if (isElementVisible(clearWatchList)) {
			click(clearWatchList);
			typePassword(password);
			clickClearWatchListBtn();
		}
		return new WatchListPage(getDriver());
	}

	public WatchListPage typePassword(String password) {
		clearAndType(passwordRequiredTxt, password);
		return this;
	}

	public WatchListPage clickClearWatchListBtn() {
		click(clearWatchListBtn);
		return new WatchListPage(getDriver());
	}

	public String getNotificationMessage() {
		return getWebDriverWait().until(ExpectedConditions.visibilityOf(notificationMessage)).getText();
	}

	public WatchListPage deleteAddedMovieFromList() {
		WebElement firstMovie = getWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(watchListMovies))
				.get(0);
		new Actions(getDriver()).moveToElement(firstMovie).build().perform();
		replaceMenuIcon.click();
		new Actions(getDriver()).moveToElement(removeFromWatchListAjaxIcon).click().build().perform();
		return new WatchListPage(getDriver());
	}

	public WatchListPage sortBy(String sortOrder) {
		sortByDropdown.click();
		By customLocator = By.linkText(sortOrder);// By.xpath(String.format("//label[contains(text(),'%s')]",
													// sortOrder));
		WebElement webElement = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(customLocator));
		clickUsingJs(webElement);
		return new WatchListPage(getDriver());

	}

	public String getFirstMovieTitle() {
		WebElement firstMovie = getWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(watchListMovies))
				.get(0);
		String attribute = firstMovie.findElement(By.tagName("a")).getAttribute("data-original-title");
		return attribute;
	}

	public int getAddedMovieCount() {
		return watchListMovies.size();
	}
}
