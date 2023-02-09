package test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObject.BasePage;
import PageObject.HeadPageObject;
import PageObject.HomePageObject;
import PageObject.RegisterAccountPageObject;
import PageObject.SearchPageObject;
import PageObject.ShoppingCartPageObject;

public class YourStoreTest extends BasePage {

	/***
	 * This test register a new account
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void registerAccount() throws InterruptedException, IOException {
		setup();
		String phone = "1234567";
		RegisterAccountPageObject d = new RegisterAccountPageObject(driver);
		d.registerAccount(phone);
		assertTrue(d.validateMyAccountTitle());
		HomePageObject h= new HomePageObject(driver);
		assertTrue(h.validateFeaturedTitle());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("registerAccount executed--------------------------------------");
	}

	/***
	 * Test add 2 items on the cart
	 * @throws InterruptedException 
	 */
	@Test(dependsOnMethods = { "registerAccount" })
	public void verifyItemsCart() throws InterruptedException {

		HeadPageObject head = new HeadPageObject(driver);
		head.addItemsToCart();
		assertTrue(head.validateItemsOnCart());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("verifyItemsCart executed--------------------------------------");
	}
	
	/**	
	 * Estimate shipping and verify alert error appears when try to checkout
	 * @throws InterruptedException 
	 */
	@Test(dependsOnMethods = { "verifyItemsCart" })
	public void estimateShippingAndCheckoutAlert() throws InterruptedException {

		 HeadPageObject head = new HeadPageObject(driver);
		 ShoppingCartPageObject checkout = new ShoppingCartPageObject(driver);
		 String country = "Ireland";
		 String region = "Dublin";
		 String postCode = "D15YD94";
		 head.checkoutItems();
		 assertTrue(checkout.validateShoppingCartTitle());
		 checkout.estimateShipping(country, region, postCode);
	     assertTrue(checkout.checkoutWithAlert());
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("estimateShippingAndCheckoutAlert executed--------------------------------------");
	}
	
	/***
	 * Test add 2 items on the cart
	 * @throws InterruptedException 
	 */
	@Test(dependsOnMethods = { "registerAccount" })
	public void searchByLowest() throws InterruptedException {

		SearchPageObject s = new SearchPageObject(driver);
		String criteria = "0";
		String criteriaSort = "Rating (Lowest)";
		s.searchByLowestPrice(criteria, criteriaSort);
		assertTrue(s.validateSearchShowsResults());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("searchByLowest executed--------------------------------------");
	}

		
	/***
	 * Close all browsers and selenium web drivers
	 */
	@AfterTest
	public void cleanUp() {

		try {
			System.out.println("//******End of the suite**********************//");

		} finally {

			finish();
		}
	}

}
