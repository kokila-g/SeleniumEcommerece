package Kokila.SeleniumEccomerece.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import Kokila.TestComponents.BaseTest;
import SeleniumEccomerece.PageObject.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test
	public void IncorrectLogin() {
		ProductCatalogue productCatalogue = landingPage.loginWeb("kokila@gmail.com", "Kokila12");
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errorMessage);
		
	}
}
