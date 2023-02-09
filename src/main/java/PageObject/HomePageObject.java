package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObject {

	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	
	By featuredTitle = By.cssSelector("div[id='content'] h3");


	public boolean validateFeaturedTitle() {
		
		 boolean result = false;
		 HeadPageObject head = new HeadPageObject(driver);
		 head.goToHomePage();
		 if (driver.findElement(featuredTitle).isDisplayed()) result = true;
		 return result;
	 }


}
