package Kokila.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumEccomerece.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\SeleniumEccomerece\\resources\\GlobalData.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
		    driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
		//read the json and converting the json to string
			String jsonContent= FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
			//string to hashmap - jackson data bind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
	});
return data;
}
	
	@BeforeMethod(groups = {"ErrorHandling","purchase"})
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage=  new LandingPage(driver);
		landingPage.GOTO();
		return landingPage;
	}
	
	@AfterMethod(groups = {"ErrorHandling","purchase"})
	public void TearDown(){
		driver.close();
	}

}
