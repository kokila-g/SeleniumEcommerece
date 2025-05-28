package Kokila.SeleniumEccomerece.test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Kokila.TestComponents.BaseTest;
import SeleniumEccomerece.AbstractComponents.AbstractComponents;
import SeleniumEccomerece.PageObject.CartPage;
import SeleniumEccomerece.PageObject.CheckOutPage;
import SeleniumEccomerece.PageObject.ConfirmationPage;
import SeleniumEccomerece.PageObject.LandingPage;
import SeleniumEccomerece.PageObject.ProductCatalogue;

import java.util.List;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest1 extends BaseTest {

@Test
public void submitOrder() throws IOException, InterruptedException {
		
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		ProductCatalogue productCatalogue = landingPage.loginWeb("kokila12@gmail.com", "Kokila-12");
			
		
		
		List<WebElement> products = productCatalogue.getProductsList();
		WebElement prod = productCatalogue.getProductByName("ZARA COAT 3");
		CartPage cartpage = productCatalogue.AddProductToCart(prod);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		
		productCatalogue.GoToCart();
		
		
		
		boolean match = cartpage.verifyProductInCart();
		Assert.assertTrue(match);
		
		CheckOutPage checkOutpage = cartpage.ClickOnCheckOut();
		
		checkOutpage.FillCountryDropDown("india");
		ConfirmationPage confirmationpage = checkOutpage.ClickOnSubmit();
		
		
		String message = confirmationpage.getConfirmMessage();
		message.equalsIgnoreCase(" Thankyou for the order. ");
		

	}
}

