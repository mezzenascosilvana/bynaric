package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HeadPageObject {

	WebDriver driver;

	public HeadPageObject(WebDriver driver) {
		this.driver = driver;
	}

	By homePage = By.cssSelector("a[href='http://tutorialsninja.com/demo/index.php?route=common/home']");
	By conatinerItems = By.cssSelector("div[id='content'] div[class='row']");
	By addToCartButton = By.xpath("(//span[contains(text(),'Add to Cart')])"); 
	By itemsOnCart = By.id("cart-total");
	By openShoppingCartPanel = By.id("cart-total");
	By checkoutButton = By.id("cart-total");
	By chekoutLink = By.partialLinkText("Checkout");
	By itemName = By.xpath("//div[@class='caption']//a");

	/***
	 * Adding items to the cart
	 * @throws InterruptedException
	 */
	public void addItemsToCart() throws InterruptedException {

		goToHomePage();
		Actions at = new Actions(driver);
		at.sendKeys(Keys.PAGE_DOWN).build().perform();
		List<WebElement> containerItemsDashboard = driver.findElement(conatinerItems).findElements(addToCartButton);
		//List<WebElement> containerItemsNameDashboard = driver.findElement(conatinerItems).findElements(itemName);
	
		int items = 0;
		for (WebElement cell : containerItemsDashboard ) {
			System.out.println(cell.getSize());
			cell.click();
			Thread.sleep(1000);
			items++;
			at.sendKeys(Keys.PAGE_DOWN).build().perform();
			if (items>=2) {break;}				
		}
		at.sendKeys(Keys.PAGE_UP).build().perform();
	}

	/**
	 * Validate how many items there are on the cart
	 * @return boolean
	 */
	public boolean validateItemsOnCart() {

		boolean result = false;
		String str = driver.findElement(itemsOnCart).getText();
		if (str.contains("2 item(s)"))
			result = true;
		return result;

	}
	
    /**
     * Go to Home Page
     */
	public void goToHomePage() {

		driver.findElement(homePage).click();
	}
	
	/**
	 * Press on checkout button and then on checkout link
	 * @throws InterruptedException 
	 */
	public void checkoutItems() throws InterruptedException {
		
		driver.findElement(checkoutButton).click();
		driver.findElement(chekoutLink).click();
		Thread.sleep(1000);
		
	}

}
