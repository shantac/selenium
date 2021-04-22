package com.letterboxd.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TopNavigation extends Page {

	@FindBy(how = How.CSS, using = ".navitem.nav-account.js-nav-account")
	private WebElement accountNav;
	@FindBy(how = How.LINK_TEXT, using = "Watchlist")
	private WebElement watchListLink;

	public TopNavigation(WebDriver driver) {
		super(driver, By.cssSelector(".navitem.nav-account.js-nav-account"));
	}

	public TopNavigation hoverOverAccountNavMenu() {
		new Actions(getDriver()).moveToElement(accountNav).build().perform();
		return this;
	}

	public WatchListPage clickWatchListLink() {
		click(watchListLink);
		return new WatchListPage(getDriver());
	}
}
