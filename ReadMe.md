# Project Description
letterboxd.com is a site where you can keep your movie history by creating a watchlist. The watchlist can be made private or public. This is a good way of having a list of the movies you watched, planning to watch, and share the history with your friends. There are two different test classes I have written to accomplish the assignment I was given. Both are placed under `tests` package in `/src/test/` directory.
	
	SortOrderTest.java
This class contains the test cases for 2 (b): Verify that every sort by type modifies the list correctly.
I have used Junit `@Parameters` technique to feed different options as well as the verification points to the test and essentially making it a data driven test. (For brevity I included only two options from the dropdown which can be increased to all)
	
	WatchListTest.java
This class contains all other tests from exercise. Notice I have marked `testAccountSignup` test with `@Ignore` annotation because of the captcha settings of the website. Selenium does not have any built in support to handle captcha and for testing the captcha should not be handled either. It's not recommended.


# Framework and Its Components
1. This is a maven based java project. Hence, I have all the dependencies listed in the `pom.xml` file. 
2. The browser is controlled by `Selenium` library. In addition to `Selenium` I have used `Junit` as test framework, `Faker` for some fake data to generate test data, `surefire and surefire reporting` plugins for executing the tests and generating the `html` report. 
3. Popular [Page Object Model (POM)](https://github.com/SeleniumHQ/selenium/wiki/Design-Patterns) design pattern is used to design the test pages. This is popular for managing the locators in a centralized place to make the refactoring effort minimum and in one place. I also used [PageFactory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory) to initialize the DOM elements. I have utilized `explciti` wait to handle dynamic elements of the page.
4. There is a good use of OOP by utilizing Inheritance in test and page objects.
5. I also used fluent style aka method chaining for test. This style increase the code and test readability.

# How to Run and Reporting
1. You can use any IDE that supports java for debugging. I am using eclipse. You need to import the project as maven project using your IDE.
2. Open a commandline or terminal and `cd` to root diretory. Such as `c:\test\selenium`. You have to have the maven configured in your system for this. [Here](https://maven.apache.org/install.html) is a sample instruction of how to set up maven for windows.
3. From commandline run `mvn surefire-report:report`. This command will utilize surefire-report plugin to execute all the tests and generate a report under `/target/site` which you can open open in any browser and see.


