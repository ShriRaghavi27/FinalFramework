package genericutilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class BaseClass {
	public WebDriver driver=null;
	public PropertyFileUtility pLib=new PropertyFileUtility();
	public ExcelFileUtility eLib=new ExcelFileUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public static WebDriver sdriver=null;//is this for listener
	
	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuite() {
		System.out.println("Establish database connection");
	}
	@BeforeTest(groups = {"smoke","regression"})
	public void beforeTest() {
		System.out.println("pre conditions for parallel execution");
	}
	//@Parameters("Browser")
	@BeforeClass(groups = {"smoke","regression"})
	//public void beforeClass(String BROWSER) throws IOException
	public void beforeClass() throws IOException {
		System.out.println("Launch Browser");
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		//String BROWSER = System.getProperty("Browser");// mvn command line execution--(mvn -DBrowser=Chrome test)
		
		//Ignore the chrome popup
		ChromeOptions settings =new ChromeOptions();
		Map<String,Object> prefs= new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		//WebDriverManager.edgedriver().setup();
		if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver(settings);
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("safari"))
			driver=new SafariDriver();
		sdriver=driver;
		
		wLib.implicitWait(driver);
		driver.manage().window().maximize();
	}
	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethod() throws IOException {
		System.out.println("Login to Ninza CRM");
		/*String URL=System.getProperty("url");
		String USERNAME=System.getProperty("username");
		String PASSWORD=System.getProperty("password");*/
		//mvn -Durl=http://49.249.28.218:8098 -Dusername=rmgyantra -Dpassword=rmgy@9999 -DBrowser=Chrome test
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.login(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethod() {
		System.out.println("logout from Ninza CRM");
		HomePage hp=new HomePage(driver);
		hp.logout();

	}
	@AfterClass(groups = {"smoke","regression"})
	public void afterClass() {
		System.out.println("Close Browser");
		driver.quit();
	}

	@AfterTest(groups = {"smoke","regression"})
	public void afterTest() {
		System.out.println("post conditions for parallel execution");
	}
	@AfterSuite(groups = {"smoke","regression"})
	public void afterSuite() {
		System.out.println("Close database connection");
	}
}
