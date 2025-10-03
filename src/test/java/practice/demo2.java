package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class demo2 {

	public static void main(String[] args) throws IOException, Exception {
		//WebDriver driver=new ChromeDriver();
		FileInputStream fis=new FileInputStream("./src/test/resources/data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("safari"))
			driver=new SafariDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		driver.findElement(By.cssSelector("[type='submit']")).click();
		 
		Thread.sleep(3000);
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		// productDD = driver.findElement(By.name("productCategory"));
		//Select s=new Select(productDD);
		//s.selectByValue("Electronics");
		
		/*WebElement quantity=driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys("1");
		WebElement price=driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys("0.02");*/
		WebElement vendordd = driver.findElement(By.name("vendorId"));
		Select s1=new Select(vendordd);
		s1.selectByValue("VID_446");
		
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		

	}

}
