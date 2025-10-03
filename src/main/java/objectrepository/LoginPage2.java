package objectrepository;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 {
	WebDriver driver;
	public LoginPage2(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id = "username") private WebElement usernameTF;
	@FindBy(id = "inputPassword") private WebElement PasswordTF;
	@FindBy(xpath  = "//button[text()='Sign In']") private WebElement signInTF;
	
	public WebElement getUsernameTF() {
		return usernameTF;
	}
	public WebElement getInputPasswordTF() {
		return PasswordTF;
	}
	public WebElement getSignInTF() {
		return signInTF;
	}
	public void login(String url,String un,String pwd)
	{
		driver.get(url);
		usernameTF.sendKeys(un);
		PasswordTF.sendKeys(pwd);
		signInTF.click();
		
	}
}
