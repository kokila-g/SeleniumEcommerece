package SeleniumEccomerece.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

WebDriver driver;
public LandingPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}




//page factory
@FindBy(id="userEmail")
WebElement userName;

@FindBy(id="userPassword")
WebElement password;

@FindBy(id="login")
WebElement submit;

public ProductCatalogue loginWeb(String userid,String pwd) {
	userName.sendKeys(userid);
	password.sendKeys(pwd);
	submit.click();
	ProductCatalogue productCatalogue = new ProductCatalogue(driver);
	return productCatalogue;
}

public void GOTO() {
	driver.get("https://rahulshettyacademy.com/client");
}
}
