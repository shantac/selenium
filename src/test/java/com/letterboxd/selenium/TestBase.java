package com.letterboxd.selenium;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;
import com.letterboxd.selenium.util.PropertyUtils;

/**
 * Base class for all the JUnit-based test classes
 */
public class TestBase {
	protected WebDriver driver;
	protected static Faker faker;
	protected static String username;
	protected static String password;

	@BeforeClass
	public static void beforeClass() throws IOException {
		loadProperties();
		faker = new Faker();
		username = System.getProperty("username");
		password = System.getProperty("password");
	}

	@Before
	public void before() throws IOException {

		driver = WebDriverFactory.createWebDriver();
		driver.get(System.getProperty("site.url"));
		driver.manage().window().maximize();
	}

	@After
	public void after() {
		if (driver != null) {
			driver.quit();
		}
	}

	private static void loadProperties() throws IOException {
		System.getProperties()
				.load(TestBase.class.getResourceAsStream(PropertyUtils.getValue("application.properties")));
	}
}
