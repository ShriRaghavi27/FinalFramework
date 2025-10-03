package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreateCampaignWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException, IOException {
		//WebDriverManager.edgedriver().setup(); 
		//WebDriver driver=new EdgeDriver();
		//ctrl+shift+f=>for align the pom
		//read from properties file

		//framework TDD->test driven development -> data driven frame,key driven, moduler driven, hybird driven 
		//BDD->cucumber

		FileInputStream fis=new FileInputStream("./src/test/resources/data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");

		//read from excelsheet
		FileInputStream fis1=new FileInputStream("./src/test/resources/Ninza_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String CAMPAIGN_NAME = wb.getSheet("Campaign").getRow(1).getCell(2).getStringCellValue();
		String TARGETSIZE = wb.getSheet("Campaign").getRow(1).getCell(3).getStringCellValue();
		wb.close();

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

		Thread.sleep(5000);

		driver.findElement(By.cssSelector("[class='btn btn-info']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		driver.findElement(By.name("targetSize")).sendKeys(TARGETSIZE);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement msg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(msg));
		System.out.println(msg.getText());
		//Assert.assertTrue(msg.getText().contains("test"), "Campaign test Not Successfully Added");//checking contains and test failed msg
		Assert.assertEquals(msg.getText(), "Campaign test Successfully Added");//both of them true 
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		Actions actions=new Actions(driver);
		WebElement btn = driver.findElement(By.className("user-icon"));
		actions.moveToElement(btn).perform();
		WebElement logout = driver.findElement(By.className("logout"));
		actions.moveToElement(logout).click().perform();

		driver.close();

	}

}
