package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.WaitsUtil;

public class BaseClass {
	
	public static WebDriver driver; // static because we want to use the same driver instance across all test
									// classes that extend this BaseClass.
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups = { "Master", "Sanity", "Regression" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {
		// Loading the properties file

		prop = new Properties();
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop.load(file);

		logger = LogManager.getLogger(this.getClass());
		// this.getClass() will return the actual class name (LoginPage, DashboardPage,
		// LoginTest, etc.) in the log file.
       
		// Code For Remote Execution -->
		if (prop.getProperty("executionENV").equalsIgnoreCase("remote")) {

			// The URL will be the IP address of hub machine + hub port + /wd/hub .
			// --> Standalone hub server IP - http://192.168.0.110:4444/wd/hub or Use this-
			// http://Localhost:4444/wd/hub

			String huburl = "http://localhost:4444/wd/hub";

			DesiredCapabilities dc = new DesiredCapabilities();

			// Windows--> dc.setPlatform(Platform.WIN10); /MAC-->
			// dc.setBrowserName("chrome");
			// Windows--> dc.setPlatform(Platform.MAC); /MAC-->
			// dc.setBrowserName("MicroSoftEdge");
			// Because we are getting os and browser parameter from xml, cant use above
			// statements.

			// For -- OS
			if (os.equalsIgnoreCase("windows")) {

				dc.setPlatform(Platform.WIN10);
			} 
			else if (os.equalsIgnoreCase("linux")) {
				dc.setPlatform(Platform.LINUX);
			}
			else if (os.equalsIgnoreCase("mac")) {
				dc.setPlatform(Platform.MAC);
			} 
			else {
				System.out.println("No matching OS");
				return;
			}

			// For --Browser

			switch (br.toLowerCase()) {//Cannot switch on a value of type Class<br>.
			                           //Only convertible int values, strings or enum variables are permitted
           
			case "chrome": dc.setBrowserName("chrome"); break;
			case "edge"  : dc.setBrowserName("edge"); break;
			case "firefox": dc.setBrowserName("firefox"); break;
			default: System.out.println("No matching Browser");
			return ;
			}
          
			driver=new RemoteWebDriver(new URL(huburl),dc);
			// URL import From java.net //http://192.168.0.110:4444/wd/hub
			// In any type of grid setup URL will be same, only the DesiredCapabilities will change based on the node configuration.
		}
     
		
		// Code for Local execution:
		if (prop.getProperty("executionENV").equalsIgnoreCase("local")) {
			
		switch (br.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser Name ");
			return;

		}
		}
    
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("appUrl"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Master", "Sanity", "Regression" }) // Even though class is extended we have to mention
																// groups tag ,it will not execute automatically
	public void tearDown() throws InterruptedException {
		
		driver.quit();
		// WaitsUtil wait =new WaitsUtil(driver);
	}

	public String randomString() {

		return RandomStringUtils.secure().nextAlphabetic(5);
	}

	public String randomNumber() {

		return RandomStringUtils.secure().nextNumeric(10);
	}

	public String randomAlphaNumeric() {

		return RandomStringUtils.secure().nextAlphanumeric(6);
	}

	public void scrollForElement(WebElement element) {

		Actions act = new Actions(driver);

		act.scrollToElement(element).perform();
		act.click(element).perform();
	}

	public void scrollForElementJS(WebElement element) {
		/*
		 * For a Selenium framework, a common practice is to create a logger in each
		 * class:
		 * 
		 * private static final Logger logger = LogManager.getLogger(LoginPage.class);
		 * 
		 * instead of using one global logger class. This makes log entries show the
		 * actual class name (LoginPage, DashboardPage, LoginTest, etc.) in the log
		 * file.
		 */

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
	}

	public String captureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

		TakesScreenshot takeSS = (TakesScreenshot) driver;
		File srcFile = takeSS.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\ScreenShots\\" + tname + "_" + timeStamp + ".html";

		File targetFile = new File(targetFilePath);// we can also use File.separator instead of "\\" for better
													// cross-platform compatibility
		srcFile.renameTo(targetFile);

		return targetFilePath;

	}

}
