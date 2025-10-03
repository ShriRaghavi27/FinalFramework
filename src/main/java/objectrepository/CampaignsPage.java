package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	WebDriver driver;
	public CampaignsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//@FindAll({@FindBy(name="addCreateCampaignBtn"),@FindBy(css ="[class='btn btn-info']")})-->Multiple
	//@FindBys({@FindBy(name="addCreateCampaignBtn"),@FindBy(css ="[class='btn btn-info']")})-->AND
	@FindBy(css ="[class='btn btn-info']") private WebElement addCreateCampaignBtn;
	
	public WebElement getAddCreateCampaignBtn() {
		return addCreateCampaignBtn;
	}
	
}
