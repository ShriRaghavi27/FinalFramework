package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {

	WebDriver driver;
	public CreateCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "campaignName") private WebElement campaignNameTF;
	@FindBy(name = "targetSize") private WebElement targetSizeTF;
	@FindBy(name = "campaignStatus") private WebElement statusTF;
	@FindBy(name = "expectedCloseDate") private WebElement closeDateTF;
	@FindBy(xpath = "//button[@type='submit']") private WebElement createCampaignBtn;
	
	public WebElement getCampaignNameTF() {
		return campaignNameTF;
	}
	public WebElement getTargetSizeTF() {
		return targetSizeTF;
	}
	
	public WebElement getStatusTF() {
		return statusTF;
	}
	
	public WebElement getCloseDateTF() {
		return closeDateTF;
	}
	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}

	
}
