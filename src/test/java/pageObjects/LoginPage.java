package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitsUtil;

public class LoginPage extends BasePage {
	// public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id=\"input-email\"]")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@id=\"input-password\"]")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login' and @type='submit']")
	WebElement btnLogin;

	public void setEmailAddress(String email) {
		txtEmailAddress.clear();
		txtEmailAddress.sendKeys(email);
	}
    
	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clkLoginBtn() {
		   
		//WaitsUtil wait = new WaitsUtil(driver);
		// WaitsUtil.waitForVisibility(btnLogin).click();
		
		btnLogin.click();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
