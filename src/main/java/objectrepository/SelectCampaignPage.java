package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectCampaignPage {
	WebDriver driver;
	public SelectCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "search-criteria") private WebElement CampaignsDD;
	@FindBy(id = "search-input") private WebElement searchBar;
	@FindBy(className  = "select-btn") private WebElement selectBtn;
	
	public WebElement getCampaignsDD() {
		return CampaignsDD;
	}
	public WebElement getSearchBar() {
		return searchBar;
	}
	public WebElement getSelectBtn() {
		return selectBtn;
	}
	
	
}
