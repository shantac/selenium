package com.letterboxd.selenium.tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.letterboxd.selenium.TestBase;
import com.letterboxd.selenium.pages.LoginPage;
import com.letterboxd.selenium.pages.TopNavigation;

@RunWith(Parameterized.class)
public class SortOrderTest extends TestBase {
	@Parameter(0)
	public String sortOrder;
	@Parameter(1)
	public String title;
	    
	@Parameters(name = "{index}: sortOrder=\"{0}\"; title=\"{1}\";")
	public static Collection<Object[]> values() {
		return Arrays.asList(new Object[][] { 
			{ "Film Name", "Deadpool (2016)" }, 
			{ "When Added", "Deadpool (2016)" } 
			}
		//add more sort options if needed
		);
	}
	
	private LoginPage homepage;

	@Before
	public void initPageObjects() {
		homepage = new LoginPage(driver);
	}
	
	@Test
	public void sortOrderTest() {
		homepage.clickSignInMenuLink()
		.typeUserName(username)
		.typePassword(password)
		.clickSignInBtn();
		
		String firstMovieTitle = new TopNavigation(driver)
				.hoverOverAccountNavMenu()
				.clickWatchListLink()
				.clearWatchListIfExist(password)
				.searchAndAddAFilm("Godzilla vs Kong (2021)")
				.searchAndAddAFilm("DEADPOOL (2016)")
				.sortBy(sortOrder)
				.getFirstMovieTitle();
				
		assertTrue("Movies are not sorted as expected",
				firstMovieTitle.equalsIgnoreCase(title));
	}
	
	
}
