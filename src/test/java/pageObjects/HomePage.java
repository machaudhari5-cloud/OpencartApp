package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	// Elements:

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//a[text()='Register']//ancestor-or-self::ul[@class='dropdown-menu dropdown-menu-right']")
	WebElement lnkRegister;

	@FindBy(xpath = "//a[text()='Login']//ancestor:: div[@id=\"top-links\"]")
	WebElement lnkLogin;

	// Actions:

	public void clickMyAccount() {

		lnkMyAccount.click();
	}

	public void clickRegister() {

		lnkRegister.click();
	}
	
	public void lnkLogin() {
		
		lnkLogin.click();
	}

}
