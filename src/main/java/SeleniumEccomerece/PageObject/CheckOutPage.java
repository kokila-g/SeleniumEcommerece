package SeleniumEccomerece.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumEccomerece.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".form-group")
	WebElement CountryDropDown;
	@FindBy (xpath="//button[contains(@class,'ta-item')][2]")
	WebElement SelectCountry;
	@FindBy (css=".action__submit")
	WebElement Submit;
	
	public void FillCountryDropDown(String CountryName) {
		Actions a=new Actions(driver);
		a.sendKeys(CountryDropDown, CountryName).build().perform();
		WaitForTheVisibilityOfElement(By.cssSelector(".ta-results"));
		SelectCountry.click();
	}
	
	public ConfirmationPage ClickOnSubmit() {
		Submit.click();	
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
}
