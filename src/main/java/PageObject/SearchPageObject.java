package PageObject;

import java.util.List;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import PageObject.helpers.SmartWaits;

public class SearchPageObject {

	WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	
	By zoomIcon = By.cssSelector("button[class='btn btn-default btn-lg']");
	By searchButton = By.id("button-search");
	By searchCriteria = By.id("input-search");
	By conatinerItems = By.cssSelector("div[id='content'] div[class='row']");
	By addToCartButton = By.xpath("(//span[contains(text(),'Add to Cart')])");
	By sortCriteriaLocator = By.id("input-sort");


	// Handle time
	SmartWaits w = new SmartWaits(driver);

	/***
	
	 * @throws InterruptedException 
	 */
	public void searchByLowestPrice(String criteriaSerching, String criteriaSortValue) throws InterruptedException {
		
		driver.findElement(zoomIcon).click();
		driver.findElement(searchCriteria).sendKeys(criteriaSerching);
		driver.findElement(searchButton).click();
		Select drpSearch = new Select(driver.findElement(sortCriteriaLocator));
		drpSearch.selectByVisibleText(criteriaSortValue);

	}
	
	public boolean validateSearchShowsResults() {
		
		boolean result = false;
		List<WebElement> containerItemsDashboard = driver.findElement(conatinerItems)
				.findElements(addToCartButton);
		int itemsResultsSearch = containerItemsDashboard.size();
		if (itemsResultsSearch >=1) result =true;
		return result;
		
	}

}
