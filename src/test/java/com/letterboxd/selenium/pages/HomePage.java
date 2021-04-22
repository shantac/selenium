package com.letterboxd.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {



	public HomePage(WebDriver webDriver) {
		super(webDriver, By.xpath("//a[contains(text(),'Log')]"));
	}

	
}
