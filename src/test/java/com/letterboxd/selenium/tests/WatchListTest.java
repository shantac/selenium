package com.letterboxd.selenium.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.letterboxd.selenium.TestBase;
import com.letterboxd.selenium.pages.LoginPage;
import com.letterboxd.selenium.pages.TopNavigation;

public class WatchListTest extends TestBase {

	private LoginPage homepage;

	@Before
	public void initPageObjects() {
		homepage = new LoginPage(driver);
	}

	@Test
	@Ignore("This test cannot be run because of captcha")
	public void testAccountSignup() {
		homepage.clickCreateAccountLink()
		.typeEmailAddress(faker.name().firstName() + "@gmail.com")
		.typeUsername(faker.name().username())
		.typePassword(faker.name().firstName())
		.checkAge()
		.checkConsent()
		.clickSignUpBtn();
		// Add assertions as needed
	}

	@Test
	public void addItemToExistingWatchList() {
		homepage.clickSignInMenuLink()
		.typeUserName(username)
		.typePassword(password)
		.clickSignInBtn();
		
		String notificationMessage = new TopNavigation(driver)
				.hoverOverAccountNavMenu()
				.clickWatchListLink()
				.clearWatchListIfExist(password)
				.searchAndAddAFilm("Godzilla vs Kong (2021)")
				.getNotificationMessage();
		
		assertTrue("Notification sucess message DID NOT appear!",
				notificationMessage.contains("‘Godzilla vs. Kong’ was added to your watchlist."));
	}

	@Test
	public void removeItemFromExistingWatchList() {
		homepage.clickSignInMenuLink()
		.typeUserName(username)
		.typePassword(password)
		.clickSignInBtn();
		
		String notificationMessage = new TopNavigation(driver)
				.hoverOverAccountNavMenu()
				.clickWatchListLink()
				.clearWatchListIfExist(password)
				.searchAndAddAFilm("Godzilla vs Kong (2021)")
				.deleteAddedMovieFromList()
				.getNotificationMessage();
		
		assertTrue("Notification sucess message DID NOT appear!",
				notificationMessage.contains("‘Godzilla vs. Kong’ was removed from your watchlist."));
	}

	@Test
	public void validateCountOfAddedMovies() {
		homepage.clickSignInMenuLink()
		.typeUserName(username)
		.typePassword(password)
		.clickSignInBtn();
		
		int moviesCount = new TopNavigation(driver)
				.hoverOverAccountNavMenu()
				.clickWatchListLink()
				.clearWatchListIfExist(password)
				.searchAndAddAFilm("Godzilla vs Kong (2021)")
				.searchAndAddAFilm("DEADPOOL (2016)")
				.getAddedMovieCount();

		assertTrue("Movies count is not same!", moviesCount == 2);
	}

}
