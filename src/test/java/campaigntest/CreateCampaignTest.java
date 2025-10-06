package campaigntest;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericutilities.BaseClass;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;

@Listeners(genericutilities.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass{
	@Test(groups = {"smoke","regression"})
	public void CreateCampaignWithMandatoryFieldsTest() throws Exception {
		//read from excelsheet
		String CAMPAIGN_NAME=eLib.readDataFromExcelFile("Campaign", 1, 2);
		String TARGETSIZE = eLib.readDataFromExcelFile("Campaign", 1, 3);

		CampaignsPage cp=new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();

		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGETSIZE);
		ccp.getCreateCampaignBtn().click();

		HomePage hp=new HomePage(driver);
		WebElement toastMsg=hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		
		Assert.assertTrue(toastMsg.getText().contains("Successfully Added"));
		hp.getCloseToastMsg().click();
	}

	@Test(groups = "regression")
	public void CreateCampaignWithStatusTest() throws IOException {
		String CAMPAIGN_NAME=eLib.readDataFromExcelFile("Campaign", 4, 2);
		String STATUS=eLib.readDataFromExcelFile("Campaign", 4, 3);
		String TARGETSIZE = eLib.readDataFromExcelFile("Campaign", 4, 4);
		//campaign
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();

		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGETSIZE);
		ccp.getStatusTF().sendKeys(STATUS);
		ccp.getCreateCampaignBtn().click();

		HomePage hp=new HomePage(driver);
		WebElement toastMsg=hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		
		Assert.assertEquals(toastMsg.getText(), "Campaign test Successfully Added");
		hp.getCloseToastMsg().click();
	}

	@Test(groups = "regression")
	public void CreateCampaignWithCloseDateTest() throws IOException {

		String CAMPAIGN_NAME=eLib.readDataFromExcelFile("Campaign", 1, 2);
		String TARGETSIZE = eLib.readDataFromExcelFile("Campaign", 7, 4);

		//close date
		String requireDate = jLib.getRequireDate(10);
		System.out.println(requireDate);
		//campaign
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();

		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGETSIZE);
		//ccp.getCloseDateTF().sendKeys(requireDate);
		ccp.getCreateCampaignBtn().click();

		HomePage hp=new HomePage(driver);
		WebElement toastMsg=hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		
		Assert.assertEquals(toastMsg.getText(), "Campaign test Successfully Added");
		hp.getCloseToastMsg().click();
	}

}
