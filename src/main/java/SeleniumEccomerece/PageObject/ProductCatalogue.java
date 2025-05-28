package SeleniumEccomerece.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumEccomerece.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	By productsBy = By.cssSelector(".mb-3");
		
	public List<WebElement> getProductsList() {
		WaitForTheVisibilityOfElement(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public CartPage AddProductToCart(WebElement prod) throws InterruptedException {
		prod.findElement(By.className("fa-shopping-cart")).click();
		Thread.sleep(2000);
		WaitForTheInvisibilityOfElement(spinner);
		Thread.sleep(1000);
		//WaitForTheVisibilityOfElement(By.cssSelector("#toast-container"));
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	
	
	
	

}
