package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testCases.TC001_AccountRegistrationTest;

 public class WaitsUtil {

		public static long PAGE_LOAD_TIME=40;
		public static long IMPLICIT_WAIT=20;
		
	    WebDriver driver;
	    
	    static WebDriverWait wait;

	    public WaitsUtil(WebDriver driver) {

	        this.driver = driver;

	        wait = new WebDriverWait(driver,
	                Duration.ofSeconds(2));
	    }
	    
	    public static WebElement waitForClick(WebElement element) {

	        return wait.until(ExpectedConditions
	                .elementToBeClickable(element));
	    }
	    
	    public static WebElement waitForVisibility(WebElement element) {
			  return wait.until(ExpectedConditions.visibilityOf(element)); 
			  }

		/*
		 * public WebElement waitForVisibility(By locator) {
		 * 
		 * return wait.until(ExpectedConditions .visibilityOfElementLocated(locator)); }
		 * 
		 * public WebElement waitForClick(By locator) {
		 * 
		 * return wait.until(ExpectedConditions .elementToBeClickable(locator)); }
		 */
	}
	

