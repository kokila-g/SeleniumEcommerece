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

import java.util.List;
import org.openqa.selenium.WebElement;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("kokila12@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kokila-12");
		driver.findElement(By.id("login")).click();
	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.className("fa-shopping-cart")).click();
		
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
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
