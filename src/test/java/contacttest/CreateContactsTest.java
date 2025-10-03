package contacttest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.ContactsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.CreateContactPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.SelectCampaignPage;

@Listeners(genericutilities.ListenerImplementation.class)
public class CreateContactsTest extends BaseClass {
	@Test(groups = { "smoke", "regression" })
	public void CreateContactWithMandatoryFieldsTest() throws IOException {
		System.out.println("CreateContactWithMandatoryFieldsTest");
		// read from excelsheet
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Contacts", 1, 2);
		String TARGETSIZE = eLib.readDataFromExcelFile("Contacts", 1, 3);
		String ORGANIZATION_NAME = eLib.readDataFromExcelFile("Contacts", 1, 4);
		String TITLE = eLib.readDataFromExcelFile("Contacts", 1, 5);
		String CONTACT_NAME = eLib.readDataFromExcelFile("Contacts", 1, 6);
		String WINDOWTITLE = eLib.readDataFromExcelFile("Contacts", 1, 8);
		String DROPDOWN_VALUE = eLib.readDataFromExcelFile("Contacts", 1, 9);

		// campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();
		// driver.findElement(By.cssSelector("[class='btn btn-info']")).click();

		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGETSIZE);
		ccp.getCreateCampaignBtn().click();

		HomePage hp = new HomePage(driver);
		wLib.waitUntilElementToBeVisible(driver, hp.getToastMsg());
		hp.getCloseToastMsg().click();

		// contacts
		hp.getContactsLink().click();

		ContactsPage conp = new ContactsPage(driver);
		conp.getAddCreateContactsBtn().click();

		CreateContactPage concp = new CreateContactPage(driver);
		concp.getOrganizationNameTF().sendKeys(ORGANIZATION_NAME);
		concp.getTitleTF().sendKeys(TITLE);
		concp.getContactNameTF().sendKeys(CONTACT_NAME);
		concp.getMobileTF().sendKeys("9" + jLib.generateMobileNumber());
		concp.getPlusBtn().click();

		String parent = driver.getWindowHandle();
		wLib.switchToWindowOnTitle(driver, WINDOWTITLE);

		SelectCampaignPage scp = new SelectCampaignPage(driver);
		WebElement sleElement = scp.getCampaignsDD();
		wLib.select(sleElement, DROPDOWN_VALUE);

		scp.getSearchBar().sendKeys(CAMPAIGN_NAME);
		wLib.waitUntilElementToBeVisible(driver, scp.getSelectBtn());
		scp.getSelectBtn().click();
		driver.switchTo().window(parent);

		concp.getCreateContactBtn().click();

		WebElement toastMsg = hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		System.out.println(toastMsg.getText());
		
		Assert.assertTrue(toastMsg.getText().contains("Successfully Added"));
		hp.getCloseToastMsg().click();
	}

	@Test(groups = "regression")
	public void CreateContactsWithAllFieldsTest() throws IOException {
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Contacts", 4, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Contacts", 4, 3);
		String ORGANIZATIO_NAME = eLib.readDataFromExcelFile("Contacts", 4, 4);
		String TITLE = eLib.readDataFromExcelFile("Contacts", 4, 5);
		String DEPARTMENT = eLib.readDataFromExcelFile("Contacts", 4, 6);
		// String OFFICE_PHONE = eLib.readDataFromExcelFile("Contacts", 4, 7);
		String CONTACT_NAME = eLib.readDataFromExcelFile("Contacts", 4, 8);
		// String MOBILENUMBER = eLib.readDataFromExcelFile("Contacts", 4, 9);
		String EMAIL = eLib.readDataFromExcelFile("Contacts", 4, 10);
		String WINDOW_TITLE = eLib.readDataFromExcelFile("Contacts", 4, 11);
		String DROPDOWN_VALUE = eLib.readDataFromExcelFile("Contacts", 4, 12);

		HomePage hp = new HomePage(driver);
		hp.getCampaignsLink().click();

		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();

		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGET_SIZE);
		ccp.getCreateCampaignBtn().click();
		
		wLib.waitUntilElementToBeVisible(driver, hp.getToastMsg());
		hp.getCloseToastMsg().click();

		hp.getContactsLink().click();
		
		ContactsPage conp=new ContactsPage(driver);
		conp.getAddCreateContactsBtn().click();
		
		CreateContactPage concp = new CreateContactPage(driver);
		concp.getOrganizationNameTF().sendKeys(ORGANIZATIO_NAME);
		concp.getTitleTF().sendKeys(TITLE);
		concp.getDepartmentTF().sendKeys(DEPARTMENT);
		concp.getOfficePhoneTF().sendKeys("9" + jLib.generateMobileNumber());
		concp.getContactNameTF().sendKeys(CONTACT_NAME);
		concp.getMobileTF().sendKeys("9" + jLib.generateMobileNumber());
		concp.getEmailTF().sendKeys(jLib.generateEmail());
		concp.getPlusBtn().click();
		
		String parent = driver.getWindowHandle();
		wLib.switchToWindowOnTitle(driver, WINDOW_TITLE);
		
		SelectCampaignPage scp=new SelectCampaignPage(driver);
		WebElement DDelement = scp.getCampaignsDD();
		wLib.select(DDelement, DROPDOWN_VALUE);
		
		
		scp.getSearchBar().sendKeys(CAMPAIGN_NAME);
		wLib.waitUntilElementToBeVisible(driver, scp.getSelectBtn());
		scp.getSelectBtn().click();
		driver.switchTo().window(parent);
		
		concp.getCreateContactBtn().click();
		
		WebElement toastMsg = hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		
		Assert.assertTrue(toastMsg.getText().contains("Successfully Added"));
		hp.getCloseToastMsg().click();
	}
}
