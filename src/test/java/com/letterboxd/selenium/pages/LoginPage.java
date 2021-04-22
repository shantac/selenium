package com.letterboxd.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends Page {

	@FindBy(how = How.XPATH, using = "//a[@href='/create-account/']")
	private WebElement createAccountLink;

	@FindBy(how = How.XPATH, using = "//a[@href='/sign-in/']")
	private WebElement signInMenuLink;

	@FindBy(how = How.ID, using = "username")
	private WebElement username;

	@FindBy(how = How.ID, using = "password")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//input[@value='Sign in']")
	private WebElement signBtn;

	public LoginPage(WebDriver webDriver) {
		super(webDriver, By.className("sign-in-menu"));
	}

	public JoinLetterBoxd clickCreateAccountLink() {
		click(createAccountLink);
		return new JoinLetterBoxd(getDriver());
	}

	public LoginPage typeUserName(String value) {
		clearAndType(username, value);
		return this;
	}

	public LoginPage typePassword(String value) {
		clearAndType(password, value);
		return this;
	}

	public LoginPage clickSignInMenuLink() {
		click(signInMenuLink);
		return new LoginPage(getDriver());
	}

	public HomePage clickSignInBtn() {
		click(signBtn);
		return new HomePage(getDriver());
	}
}
