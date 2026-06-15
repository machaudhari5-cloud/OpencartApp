package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest test;
	String repName;

	public void onStart(ITestContext testContext) {
		// ITestContext--> It is an interface provided by TestNG that allows you to
		// access and manipulate the test context during the execution of your tests.
		// This class defines a test context which contains all the information for a
		// given test run.
		// An instance of this context is passed to the test listeners so they can query
		// information about their environment.

		System.out.println("Extent Report Started");
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss"); Date date
		 * = new Date(); String currentDateTime =sdf.format(date); // Formats the
		 * current date and time as a string in the specified format.
		 */

		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

		repName = "Test-Report-" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// System.getProperty("user.dir")+"/test-output/"+repName);
		// creates a new instance of the ExtentSparkReporter class, which is responsible
		// for generating the HTML report.
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);// (Theme.DARK);

		extent = new ExtentReports(); // Main engine that creates report
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os"); // Get the value of the "os" parameter from the
																		// TestNG XML file
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));

		String browser = testContext.getCurrentXmlTest().getParameter("browser"); // Get the value of the "browser"
																					// parameter from the TestNG XML
																					// file
		extent.setSystemInfo("Browser", browser);

		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups(); // Get the list of included
																							// groups from the TestNG
																							// context

		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Included Groups", includeGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		// test.pass(result.getMethod().getMethodName()+" is Passed");
		test.log(Status.PASS, result.getMethod().getMethodName() + " got Passed successfully");

	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getMethod().getMethodName() + " got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			
		   String imgPath = new BaseClass().captureScreen(result.getName());
		   test.addScreenCaptureFromPath(imgPath);
		}
		catch (Exception e) {
			e.printStackTrace();// This will print the stack trace of the exception to the console,
			                    // which can help you identify the cause of the error.
		}
	/*	try {
			String screenshotPath = new ScreenshotUtility().captureScreen(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) 
			e.printStackTrace(); }
	*/

	}
	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP ,result.getMethod().getMethodName()+"Method got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();// This method is used to write the test information to the report file.
		               // It should be called at the end of the test execution.
		String  pathofExtentReport = System.getProperty("user.dir")+"\\Reports\\"+repName;
		File extentReport= new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			// URI-- Uniform Resource Identifier 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
