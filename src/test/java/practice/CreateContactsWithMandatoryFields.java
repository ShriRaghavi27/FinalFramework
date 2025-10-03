package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreateContactsWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\Manish\\eclipse-workspace2\\NINZA_CRM\\src\\test\\resources\\data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//read from excelsheet
		FileInputStream fis1=new FileInputStream("C:\\Users\\Manish\\OneDrive\\Documents\\Ninza_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String CAMPAIGN_NAME = wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
		String TARGETSIZE = wb.getSheet("Contacts").getRow(1).getCell(3).getStringCellValue();
		String ORGANIZATION_NAME = wb.getSheet("Contacts").getRow(1).getCell(4).getStringCellValue();
		String TITLE = wb.getSheet("Contacts").getRow(1).getCell(5).getStringCellValue();
		String CONTACT_NAME = wb.getSheet("Contacts").getRow(1).getCell(6).getStringCellValue();
		String MOBILE_NUMBER = wb.getSheet("Contacts").getRow(1).getCell(7).getStringCellValue();
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
		//campaign
		driver.findElement(By.cssSelector("[class='btn btn-info']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		driver.findElement(By.name("targetSize")).sendKeys(TARGETSIZE);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		//contacts
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		
		driver.findElement(By.name("organizationName")).sendKeys(ORGANIZATION_NAME);
		driver.findElement(By.name("title")).sendKeys(TITLE);
		driver.findElement(By.name("contactName")).sendKeys(CONTACT_NAME);
		driver.findElement(By.name("mobile")).sendKeys(MOBILE_NUMBER);
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		String parent = driver.getWindowHandle();
		Set<String> all = driver.getWindowHandles();
		all.remove(parent);
		for (String child : all) {
			driver.switchTo().window(child);
			if(driver.getTitle().contains("Select Campaign"))
			break;
		}
		
		WebElement sleElement = driver.findElement(By.id("search-criteria"));
		Select s=new Select(sleElement);
		s.selectByValue("campaignName");
		
		driver.findElement(By.id("search-input")).sendKeys("test");
		driver.findElement(By.className("select-btn")).click();
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
		
		WebElement msg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(msg));
		System.out.println(msg.getText());
		Assert.assertTrue(msg.getText().contains("Contact"), "Test Failed");
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		//loqout
		Actions actions=new Actions(driver);
		WebElement lbtn = driver.findElement(By.className("user-icon"));
		actions.moveToElement(lbtn).perform();
		WebElement logout = driver.findElement(By.className("logout"));
		actions.moveToElement(logout).click().perform();
		
		driver.close();

	}

}
