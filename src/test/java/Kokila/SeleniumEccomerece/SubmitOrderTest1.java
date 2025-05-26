package Kokila.SeleniumEccomerece;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumEccomerece.PageObject.LandingPage;
import SeleniumEccomerece.PageObject.ProductCatalogue;

import java.util.List;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		LandingPage landingPage=  new LandingPage(driver);
		landingPage.GOTO();
		landingPage.loginWeb("kokila12@gmail.com", "Kokila-12");
			
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductsList();
		WebElement prod = productCatalogue.getProductByName("ZARA COAT 3");
		productCatalogue.AddProductToCart(prod);
		
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> ProductsInCart=driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = ProductsInCart.stream().anyMatch(ProductInCart->ProductInCart.getText().equalsIgnoreCase("ZARA COAT 3"));
		//Assert.assertTrue(match);
		System.out.println(match);
		
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		//driver.findElement(By.cssSelector(".form-group")).sendKeys("india");
		//List<WebElement> CountryDD=driver.findElements(By.xpath("//i[@class='fa fa-search']"));
		//CountryDD.stream().filter(singleDD->singleDD.getText());
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		confirmMessage.equalsIgnoreCase(" Thankyou for the order. ");
		
		

	}

}
