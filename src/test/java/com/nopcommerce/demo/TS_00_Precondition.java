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

public class TS_00_Precondition extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		registerPage = new RegisterPageObject(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@Test
	public void TC_01_CreateAccountForTesting() {
		// Test case dùng để tạo 1 account cố định xài cho những test case khác
		registerPage.enterToFirstNameTextbox("auto");
		registerPage.enterToLastNameTextbox("test");
		registerPage.enterToEmailTextbox(GlobalConstants.username);
		registerPage.enterToPasswordTextbox(GlobalConstants.password);
		registerPage.enterToConfirmPasswordTextbox(GlobalConstants.password);

		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isValidInformationMessage("Your registration completed"));
		registerPage.clickLogoutLink();
		homePage = new HomePageObject(driver);
		homePage.clickRegisterLink();
	}
}