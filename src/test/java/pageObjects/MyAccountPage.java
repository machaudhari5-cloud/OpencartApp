package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[text()='Logout' and @class=\"list-group-item\"]")
	public WebElement lnkLogout;

	public boolean isAccountPageExists() {

		try {
			return (msgHeading.isDisplayed());
		} catch (Exception e) {
           return false;
		}
     }
	
	public void clickLogout() {
		
		lnkLogout.click();
	}
}