package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import genericutilities.PropertyFileUtility;
import objectrepository.LoginPage2;

public class CreateCampaignWithProducts {

	public static void main(String[] args) throws IOException, InterruptedException {
		PropertyFileUtility p=new PropertyFileUtility();
		
		String BROWSER=p.test("browser");
		String URL = p.test("url");
		String USERNAME = p.test("username");
		String PASSWORD = p.test("password");
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get(URL);
		LoginPage2 l=new LoginPage2(driver);
		l.login(URL, USERNAME, PASSWORD);
		
		
		driver.findElement(By.linkText("Products")).click();

	}

}
