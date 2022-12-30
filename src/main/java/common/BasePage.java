package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriverWait explicitWait;
	private long timeout = GlobalConstants.long_timeout;
	
	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public String getTextOfElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator)).getText();
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	
	public void sendKeysToElement(WebDriver driver, String locator, String valueInput) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(valueInput);
	}
	
	public void sendEnterToElement(WebDriver driver, String locator) {
		getElement(driver, locator).sendKeys(Keys.ENTER);
	}
	
	// explicit Wait
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			// displayed: visible on UI + In DOM
			// undisplayed: invisible on UI + In DOM
			return getElement(driver, locator).isDisplayed();
			
		} catch (Exception e) {
			// undisplayed: invisible on UI + Not In DOM
			e.printStackTrace();
			return false;
		}
	}
	
	public void selectDropdownByText(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getElement(driver, locator));
		select.selectByVisibleText(textItem);
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
}