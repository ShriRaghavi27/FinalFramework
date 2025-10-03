package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	WebDriver driver;
	public CreateContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "organizationName") private WebElement organizationNameTF;
	@FindBy(name = "title") private WebElement titleTF;
	@FindBy(name="department") private WebElement departmentTF;
	@FindBy(name="officePhone") private WebElement officePhoneTF;
	@FindBy(name = "contactName") private WebElement contactNameTF;
	@FindBy(name = "mobile") private WebElement mobileTF;
	@FindBy(name="email") private WebElement emailTF;
	@FindBy(xpath  = "(//button[@type='button'])[2]") private WebElement plusBtn;
	@FindBy(xpath = "//button[text()='Create Contact']") private WebElement createContactBtn;
	public WebElement getOrganizationNameTF() {
		return organizationNameTF;
	}
	public WebElement getTitleTF() {
		return titleTF;
	}
	public WebElement getDepartmentTF() {
		return departmentTF;
	}
	public WebElement getOfficePhoneTF() {
		return officePhoneTF;
	}
	public WebElement getContactNameTF() {
		return contactNameTF;
	}
	public WebElement getMobileTF() {
		return mobileTF;
	}
	public WebElement getEmailTF() {
		return emailTF;
	}
	public WebElement getPlusBtn() {
		return plusBtn;
	}
	public WebElement getCreateContactBtn() {
		return createContactBtn;
	}
	
	

	
	
}
