package producttest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CreateProductPage;
import objectrepository.HomePage;
import objectrepository.ProductPage;
@Listeners(genericutilities.ListenerImplementation.class)
public class CreateProductTest extends BaseClass {
	@Test(groups = {"smoke","regression"})
	public void CreateProductWithMandatoryFieldsTest() throws InterruptedException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		ProductPage pp = new ProductPage(driver);
		pp.getAddProductCampaignBtn().click();
		
		
		ExcelFileUtility eLib=new ExcelFileUtility();
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Product", 1, 4);
		String DROPDOWN_VALUE_CATEGORY = eLib.readDataFromExcelFile("Product", 1, 5);
		String QUANTITY = eLib.readDataFromExcelFile("Product", 1, 6);
		String PRICE_PER_UNIT = eLib.readDataFromExcelFile("Product", 1, 7);
		String DROPDOWN_VALUE_VENDOR = eLib.readDataFromExcelFile("Product", 1, 8);
		String MESSAGE = eLib.readDataFromExcelFile("Product", 1, 9);
		
		
		CreateProductPage cpp=new CreateProductPage(driver);
		cpp.getProductNameTF().sendKeys(PRODUCT_NAME+jLib.generateRandomNumber());
		
		WebElement sleElementCategory = cpp.getproductCategoryTF();
		
		wLib.select(sleElementCategory, DROPDOWN_VALUE_CATEGORY);
		
		
		cpp.getQuantityTF().clear();
		cpp.getQuantityTF().sendKeys(QUANTITY);
		cpp.getPricePerUnitTF().clear();
		cpp.getPricePerUnitTF().sendKeys(PRICE_PER_UNIT);
		
		WebElement sleElementVendor = cpp.getVendorTF();
		wLib.select(sleElementVendor, DROPDOWN_VALUE_VENDOR);
		
		cpp.getProductAddBtn().click();
		
		wLib.waitUntilElementToBeVisible(driver, hp.getToastMsg());
		String toastMsg = hp.getToastMsg().getText();
		System.out.println(toastMsg);
		hp.getCloseToastMsg().click();
		Assert.assertTrue(toastMsg.contains("Successfully Added"));
		
		
	}
}
