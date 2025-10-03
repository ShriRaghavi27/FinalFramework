package practice;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class WorkingWithDataProvider {
	@Test(dataProvider = "loginDetails")
	public void login(String un,String pwd)
	{
		//Ignore the chrome popup
		ChromeOptions settings =new ChromeOptions();
		Map<String,Object> prefs= new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		WebDriver driver=new ChromeDriver(settings);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LoginPage lp=new LoginPage(driver);
		String url="http://49.249.28.218:8098";
		lp.login(url,un, pwd);
		System.out.println(un+" "+pwd);
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	@DataProvider
	public Object[][] loginDetails() throws IOException
	{
		Object[][] arr=new Object[4][2];
		ExcelFileUtility eLib=new ExcelFileUtility();
		int rowcount = eLib.getRowCount("DataProvider");
		System.out.println(rowcount);
		for(int i=1;i<=rowcount;i++)
		{
			arr[i-1][0]=eLib.readDataFromExcelFile("DataProvider",i,0);
			arr[i-1][1]=eLib.readDataFromExcelFile("DataProvider",i,1);
		}
		System.out.println();
		return arr;

	}

}
