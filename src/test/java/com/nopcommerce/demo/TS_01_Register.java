package com.nopcommerce.demo;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class TS_01_Register extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	String randomEmail;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
//	public void beforeClass() {
		driver = getBrowserDriver(browserName);
//		driver = getBrowserDriver("chrome");
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		registerPage = new RegisterPageObject(driver);
		randomEmail = GlobalConstants.username;
		System.out.println("email using:" + randomEmail);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isFirstnameErrorMessage("First name is required."));
		Assert.assertTrue(registerPage.isLastnameErrorMessage("Last name is required."));
		Assert.assertTrue(registerPage.isEmailErrorMessage("Email is required."));
		Assert.assertTrue(registerPage.isPasswordErrorMessage("Password is required."));
		Assert.assertTrue(registerPage.isConfirmPasswordErrorMessage("Password is required."));
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		registerPage.refreshCurrentPage(driver);
		registerPage.enterToEmailTextbox("abcxyz");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isEmailErrorMessage("Wrong email"));
		sleepInSecond(3);
	}

	@Test
	public void TC_03_RegisterWithValidInformation() {
		registerPage.refreshCurrentPage(driver);
		registerPage.enterToFirstNameTextbox("Cuong");
		registerPage.enterToLastNameTextbox("Huynh");
		registerPage.enterToEmailTextbox(randomEmail);
		registerPage.enterToPasswordTextbox(GlobalConstants.password);
		registerPage.enterToConfirmPasswordTextbox(GlobalConstants.password);

		// enter data to dropdown
		registerPage.enterToDateDropdown("6");
		registerPage.enterToMonthDropdown("June");
		registerPage.enterToYearDropdown("2000");

		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isValidInformationMessage("Your registration completed"));
		registerPage.clickLogoutLink();
		homePage = new HomePageObject(driver);
		homePage.clickRegisterLink();
	}

	@Test
	public void TC_04_RegisterWithExistEmail() {
		registerPage.refreshCurrentPage(driver);
		registerPage.enterToFirstNameTextbox("Cuong");
		registerPage.enterToLastNameTextbox("Huynh");
		registerPage.enterToEmailTextbox(randomEmail);
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isEmailExistMessage("The specified email already exists"));
	}

	@Test
	public void TC_05_RegisterWithPasswordLeast6Characters() {
		registerPage.refreshCurrentPage(driver);
		registerPage.enterToFirstNameTextbox("Cuong");
		registerPage.enterToLastNameTextbox("Huynh");
		registerPage.enterToEmailTextbox("cuongphuynh@gmail.com");
		registerPage.enterToPasswordTextbox("12345");
		registerPage.enterToConfirmPasswordTextbox("12345");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isPasswordLeast6Characters("Password must meet the following rules:"));
		Assert.assertTrue(registerPage.isPasswordLeast6Characters("must have at least 6"));
	}

	@Test
	public void TC_06_RegisterWithConfirmPassword() {
		registerPage.refreshCurrentPage(driver);
		registerPage.enterToFirstNameTextbox("Cuong");
		registerPage.enterToLastNameTextbox("Huynh");
		registerPage.enterToEmailTextbox("cuongphuynh@gmail.com");
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("654321");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(
				registerPage.isPasswordConfirmPassword("The password and confirmation password do not match"));
	}

}