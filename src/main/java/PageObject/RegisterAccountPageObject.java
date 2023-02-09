package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class RegisterAccountPageObject  {

	WebDriver driver;

	public RegisterAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	By myAccount = By.cssSelector("a[title='My Account'] span[class='hidden-xs hidden-sm hidden-md']");
	By regAccount = By.cssSelector("a[href='http://tutorialsninja.com/demo/index.php?route=account/register']");
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By phoneNumber = By.id("input-telephone");
	By pwd = By.id("input-password");
	By pwdConfirm = By.id("input-confirm");
	By privacyPolicy = By.cssSelector("input[value='1'][name='agree']");
	By btnContinue = By.cssSelector("input[value='Continue']");
	By accountCreatedTitle = By.cssSelector("div[id='content'] h1");
	By continueBtn = By.cssSelector(".btn.btn-primary");
	By myAccountTitle = By.cssSelector("h2:nth-child(1)");
	By homePage = By.cssSelector("a[href='http://tutorialsninja.com/demo/index.php?route=common/home']");
	


	public void registerAccount(String phone) {
		
		String wordGenerated = createRandomWord(6);
		driver.findElement(myAccount).click();
		driver.findElement(regAccount).click();
		driver.findElement(firstName).sendKeys(wordGenerated+"first");
		driver.findElement(lastName).sendKeys(wordGenerated+"last");
		driver.findElement(email).sendKeys(wordGenerated+"@automation.com");
		driver.findElement(phoneNumber).sendKeys(phone);
		driver.findElement(pwd).sendKeys(wordGenerated);
		driver.findElement(pwdConfirm).sendKeys(wordGenerated);
		driver.findElement(privacyPolicy).click();
		driver.findElement(btnContinue).click();
		if (driver.findElement(accountCreatedTitle).isDisplayed()) {
			driver.findElement(continueBtn).click();		
		}
		validateMyAccountTitle();
	}
	
	 public static String createRandomWord(int len) {
	        
		 String randomWord = "";
	        for (int i = 0; i < len; i++) {
	            int v = 1 + (int) (Math.random() * 26);
	            char c = (char) (v + (i == 0 ? 'A' : 'a') - 1);
	            randomWord += c;
	        }
	        return randomWord;
	   } 
	
	 public boolean validateMyAccountTitle() {
		
		 boolean result = false;
		 if (driver.findElement(myAccountTitle).isDisplayed()) result = true;
		 return result;
	 }
	 
	 
	 
	 

}
