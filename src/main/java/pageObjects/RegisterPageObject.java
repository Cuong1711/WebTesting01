package pageObjects;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	// constructor
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterToFirstNameTextbox(String firstname) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstname);
	}
	
	public void enterToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastname);
	}
	
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	public boolean isFirstnameErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isLastnameErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isEmailErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	// New
	public boolean isPasswordErrorMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return message.contains(value);
	}
	
	// New
		public boolean isConfirmPasswordErrorMessage(String value) {
			String message = getTextOfElement(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
			return message.contains(value);
		}
	
	public boolean isValidInformationMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.VALID_INFORMATION_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isEmailExistMessage(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.EMAIL_EXIST_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isPasswordLeast6Characters(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.PASSWORD_LEAST_6_CHARACTERS_MESSAGE);
		return message.contains(value);
	}
	
	public boolean isPasswordConfirmPassword(String value) {
		String message = getTextOfElement(driver, RegisterPageUI.CONFIRMATION_PASSWORD_MESSAGE);
		return message.contains(value);
	}
	
	public void enterToDateDropdown(String value) {
		selectDropdownByText(driver, RegisterPageUI.DATE_DROPDOWN, value);
	}
	
	public void enterToMonthDropdown(String value) {
		selectDropdownByText(driver, RegisterPageUI.MONTH_DROPDOWN, value);
	}
	
	public void enterToYearDropdown(String value) {
		selectDropdownByText(driver, RegisterPageUI.YEAR_DROPDOWN, value);
	}
	
	public void clickLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}
}