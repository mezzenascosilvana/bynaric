package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPageObject {

	WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	
	By shippingPanel = By.cssSelector(".accordion-toggle[href='#collapse-shipping']");
	By shoppingCartTitle = By.cssSelector("div[id='content'] h1");
	By countryLocator = By.id("input-country");
	By regionLocator = By.id("input-zone");
	By postCodeLocator = By.id("input-postcode");
	By getQuotesButton = By.id("button-quote");
	By flatRateRadioBtn = By.cssSelector("input[value='flat.flat']");
	By applyShippingButton = By.id("button-shipping");
	By checkoutButton = By.cssSelector("a[class='btn btn-primary']");
	By errorAlertCheckout = By.cssSelector(".alert.alert-danger.alert-dismissible");
	
	
	

	/***
	 * Estimate Shipping
	 * @throws InterruptedException
	 */
	public void estimateShipping(String countryValue, String regionValue, String postCodeValue) throws InterruptedException {
	
		driver.findElement(shippingPanel).click();
		Thread.sleep(1000);
		destinationShipping(countryLocator, countryValue); //"Ireland"
		destinationShipping(regionLocator,regionValue);	 //"Dublin"
		driver.findElement(postCodeLocator).sendKeys(postCodeValue);
		driver.findElement(getQuotesButton).click();
		driver.findElement(flatRateRadioBtn).click();
		driver.findElement(applyShippingButton).click();	
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean validateShoppingCartTitle() {
		
		boolean result = false;
		if ( driver.findElement(shoppingCartTitle).isDisplayed()) result= true;	
		return result;
	}
	
	
	/**
	 * Search on a dropdown list
	 * @param locator
	 * @param value
	 */
	public void destinationShipping(By locator, String value) {
		
		Select drpSearch = new Select(driver.findElement(locator));
		drpSearch.selectByVisibleText(value); 
	}
	
	public boolean checkoutWithAlert() throws InterruptedException {
		
		boolean result = false;
		Thread.sleep(1000);
		driver.findElement(checkoutButton).click();
		if (driver.findElement(errorAlertCheckout).isDisplayed()) result = true;
		return result;
	}
	
}
