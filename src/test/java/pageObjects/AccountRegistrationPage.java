package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {

		super(driver);
	}

//	@FindBy(xpath = "//h1[text()='Register Account']")
//	WebElement txtRegisterAccount;

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastName;

	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@placeholder='Telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@placeholder='Password Confirm']")
	WebElement txtPasswordConfirm;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@value='Continue' and @class='btn btn-primary']")
	WebElement btnContinue;

	@FindBy(xpath = "//div[@id='content' and @class='col-sm-9']//child::h1[text()='Your Account Has Been Created!']")
	WebElement txtConfirmMsg;

	// Actions:

//	public String getRegisterAccountText() {
//
//		String strRegAcc = txtRegisterAccount.getText();
//		return strRegAcc;
//	}

	public void setFirstName(String fname) {
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tel) {// Even if it is a no we have to pass in string format
		                                  //--> UI I/P always a string
		txtTelephone.clear();
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void setPasswordConfirm(String pwd) {
		txtPasswordConfirm.clear();
		txtPasswordConfirm.sendKeys(pwd);
	}

	public String setPolicy() {
		
		/*isDisplayed() → "Can I see it?"
          isEnabled() → "Can I interact with it?"
          isSelected() → "Is it currently selected?"
           
		  WebElement checkbox = driver.findElement(By.id("agree"));
		  System.out.println(checkbox.isDisplayed()); // Visible?
		  System.out.println(checkbox.isEnabled()); // Clickable?
		  System.out.println(checkbox.isSelected()); // Checked?
		 */
		if (!chkPolicy.isSelected()) {
			chkPolicy.click();
		}
	    return "CheckBox already Selected";
	}

	public void clkContinue() {

		btnContinue.click();
		
		/*
		 * btnContinue.submit();
		 * 
		 * Actions act=new Actions(driver);
		 * act.moveToElement(btnContinue).click().build();
		 * 
		 * JavascriptExecutor js=(JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click();",btnContinue);
		 * 
		 * btnContinue.sendKeys(Keys.RETURN);
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		 * wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		 */
		
	}
	
	public String getConfirmMsg() {
	   try {
		  return (txtConfirmMsg.getText());
	      }
      catch(Exception e){
    	  return(e.getMessage());
      }
}
}