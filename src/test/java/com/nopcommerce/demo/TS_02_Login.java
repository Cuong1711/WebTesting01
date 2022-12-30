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
import pageObjects.LoginPageObject;

public class TS_02_Login extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	String randomEmail;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage = new LoginPageObject(driver);
		randomEmail = GlobalConstants.username;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		loginPage.clickToLoginButton();
		Assert.assertTrue(loginPage.isEmailErrorMessage("Please enter your email"));
	}

//    TC_02: Login với invalid data
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailTextbox("abcd");
		loginPage.clickToLoginButton();
		Assert.assertTrue(loginPage.isEmailErrorMessage("Wrong email"));
	}

//  TC_03: Login với email chưa đăng ký
	@Test
	public void TC_03_LoginWithUnregisteredEmail() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailTextbox("abcpq@mail.com");
		loginPage.clickToLoginButton();
		Assert.assertTrue(
				loginPage.isEmail_PassMessage("Login was unsuccessful. Please correct the errors and try again."));
		Assert.assertTrue(loginPage.isEmail_PassMessage("No customer account found"));
	}

//TC_04: Login với email đã đăng ký + password để trống
	@Test
	public void TC_04_LoginWithPasswordBlank() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailTextbox(GlobalConstants.username);
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();
		Assert.assertTrue(
				loginPage.isEmail_PassMessage("Login was unsuccessful. Please correct the errors and try again."));
		Assert.assertTrue(loginPage.isEmail_PassMessage("The credentials provided are incorrect"));
	}

//TC_05: Login với email đã đăng ký + password nhập sai
	@Test
	public void TC_05_LoginWithPasswordWrong() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailTextbox(GlobalConstants.username);
		loginPage.enterToPasswordTextbox("654321");
		loginPage.clickToLoginButton();
		Assert.assertTrue(
				loginPage.isEmail_PassMessage("Login was unsuccessful. Please correct the errors and try again."));
		Assert.assertTrue(loginPage.isEmail_PassMessage("The credentials provided are incorrect"));
	}

//TC_06: Login với email đã đăng ký + password nhập đúng
//Verify Login Page chuyển qua trang Home Page thành công
	@Test
	public void TC_06_LoginSuccessfulHomePage() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailTextbox(GlobalConstants.username);
		loginPage.enterToPasswordTextbox(GlobalConstants.password);
		homePage = loginPage.clickToLoginButton();
//		homePage = new HomePageObject(driver);
		Assert.assertEquals(homePage.getPageUrl(driver), "https://demo.nopcommerce.com/");
	}
}