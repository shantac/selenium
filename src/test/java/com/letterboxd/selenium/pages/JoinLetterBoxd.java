package com.letterboxd.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class JoinLetterBoxd extends Page {

	@FindBy(how = How.ID, using = "frm-reg-email")
	private WebElement email;

	@FindBy(how = How.ID, using = "frm-reg-username")
	private WebElement username;

	@FindBy(how = How.ID, using = "frm-reg-password")
	private WebElement password;

	@FindBy(how = How.ID, using = "frm-age-check")
	private WebElement ageCheckBox;

	@FindBy(how = How.ID, using = "frm-privacy-policy-check")
	private WebElement consentCheckBox;

	@FindBy(how = How.XPATH, using = "//input[@value='Sign up']")
	private WebElement signUpBtn;

	public JoinLetterBoxd(WebDriver driver) {
		super(driver, By.xpath("//h1[text()='Join Letterboxd']"));
	}

	public JoinLetterBoxd typeEmailAddress(String emailAddress) {
		clearAndType(email, emailAddress);
		return this;
	}

	public JoinLetterBoxd typeUsername(String username) {
		clearAndType(this.username, username);
		return this;
	}

	public JoinLetterBoxd typePassword(String password) {
		clearAndType(this.password, password);
		return this;
	}

	public JoinLetterBoxd checkAge() {
		clickUsingJs(ageCheckBox);
		return this;
	}

	public JoinLetterBoxd checkConsent() {
		clickUsingJs(consentCheckBox);
		return this;
	}

	public void clickSignUpBtn() {
		click(signUpBtn);
	}

}
