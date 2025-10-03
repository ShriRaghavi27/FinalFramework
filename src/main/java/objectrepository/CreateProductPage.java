package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	WebDriver driver;

	public CreateProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "productName") private WebElement productNameTF;
	@FindBy(name = "productCategory") private WebElement productCategoryTF;
	@FindBy(name = "quantity") private WebElement quantityTF;
	@FindBy(name = "price") private WebElement pricePerUnitTF;
	@FindBy(name = "vendorId") private WebElement vendorTF;
	@FindBy(xpath = "//button[@type='submit']") private WebElement productAddBtn;
	public WebElement getProductNameTF() {
		return productNameTF;
	}
	
	public WebElement getproductCategoryTF() {
		return productCategoryTF;
	}

	public WebElement getQuantityTF() {
		return quantityTF;
	}

	public WebElement getPricePerUnitTF() {
		return pricePerUnitTF;
	}
	

	public WebElement getVendorTF() {
		return vendorTF;
	}

	public WebElement getProductAddBtn() {
		return productAddBtn;
	}
	
	
}
