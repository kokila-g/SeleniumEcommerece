package SeleniumEccomerece.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumEccomerece.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> ProductsInCart;
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement Checkout;
	
	public boolean verifyProductInCart() {
		Boolean match = ProductsInCart.stream().anyMatch(ProductInCart->ProductInCart.getText().equalsIgnoreCase("ZARA COAT 3"));
		return match;
	}
	
	public CheckOutPage ClickOnCheckOut() {
		Checkout.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
	}
	
	

}
