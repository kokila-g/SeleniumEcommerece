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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Kokila.TestComponents.BaseTest;
import SeleniumEccomerece.AbstractComponents.AbstractComponents;
import SeleniumEccomerece.PageObject.CartPage;
import SeleniumEccomerece.PageObject.CheckOutPage;
import SeleniumEccomerece.PageObject.ConfirmationPage;
import SeleniumEccomerece.PageObject.LandingPage;
import SeleniumEccomerece.PageObject.OrdersPage;
import SeleniumEccomerece.PageObject.ProductCatalogue;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest1 extends BaseTest {
String product = "ZARA COAT 3";
@Test (dataProvider="getData",groups = {"purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		ProductCatalogue productCatalogue = landingPage.loginWeb(input.get("email"),input.get("pwd"));
			
		
		
		List<WebElement> products = productCatalogue.getProductsList();
		WebElement prod = productCatalogue.getProductByName(product);
		CartPage cartpage = productCatalogue.AddProductToCart(prod);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		
		productCatalogue.GoToCart();
		
		
		
		boolean match = cartpage.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match);
		
		CheckOutPage checkOutpage = cartpage.ClickOnCheckOut();
		
		checkOutpage.FillCountryDropDown("india");
		ConfirmationPage confirmationpage = checkOutpage.ClickOnSubmit();
		
		
		String message = confirmationpage.getConfirmMessage();
		message.equalsIgnoreCase(" Thankyou for the order. ");
		

	}
@Test(dependsOnMethods={"submitOrder"})
public void productInOrderPage() {
	ProductCatalogue productCatalogue = landingPage.loginWeb("kokila12@gmail.com", "Kokila-12");
	OrdersPage orderpage=productCatalogue.GoToOrders();
	boolean productExist = orderpage.ProductInOrders(product);
	Assert.assertTrue(productExist);
}
@DataProvider
public Object[][] getData() throws IOException {
	//return new Object [][] {{"kokila12@gmail.com","Kokila-12","ZARA COAT 3"},{"kokila34@gmail.com","Kokila-34","ADIDAS ORIGINAL"}};
	//HashMap<String,String> map = new HashMap<String,String>();
	//map.put("email", "kokila12@gmail.com");
	//map.put("pwd", "Kokila-12");
	//map.put("product", "ZARA COAT 3");
	
	//HashMap<String,String> map1 = new HashMap<String,String>();
	//map1.put("email", "kokila12@gmail.com");
	//map1.put("pwd", "Kokila-12");
	//map1.put("product", "ZARA COAT 3");
	//return new Object[][] {{map},{map1}};
	
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Kokila\\data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}
}

