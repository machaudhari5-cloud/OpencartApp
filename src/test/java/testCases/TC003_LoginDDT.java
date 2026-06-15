package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
   // We cannot extends multiple classes without Interface 
   // no need to extends dataProvider class 
	
	@Test(dataProvider="OpencartLoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	//dataProvider is from different class so we need to specify the class name also
	// dataProviders are used where calculations are needed with multiple sets of data .
	public void test_LoginDDT(String email, String pwd, String exp) {
		
		logger.info("********* Starting TC003_LoginDDT *********");
	try {	
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
	    hp.lnkLogin();

		// LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(email); 
		lp.setPassword(pwd); 
		lp.clkLoginBtn();

		// MyAccountPage
		MyAccountPage myap = new MyAccountPage(driver);
		boolean targetPage = myap.isAccountPageExists();
		
		/* Notes:
		 * valid data   -- login pass   -- test case should pass -- logout 
		 *              -- login failed -- test case should fail -- no logout 
		 * Invalid data -- login failed -- test case should pass -- no logout 
		 *              -- login pass   -- test case should fail -- logout
		 */
	    //ImpNote :  After assertion no statement should execute 
		//           because the statement may not execute properly
		
//		//First Way :
//		if(exp.equalsIgnoreCase("valid") && targetPage==true) {
//			
//			 myap.clickLogout();
//			 System.out.println("login successful-->Testcase Pass");
//			//Assert.assertTrue(true, "login successful-->Testcase Pass");
//			 
//		}
//		else if(exp.equalsIgnoreCase("valid") && targetPage==false) {
//			 System.out.println("login unsuccesful--> Testcase Failed");
//			//Assert.assertTrue(false,"login unsuccesful--> Testcase Failed");
//		}
//		
//		else if(exp.equalsIgnoreCase("Invalid") && targetPage==true) {
//			System.out.println("login succesful--> Testcase Failed");
//			//Assert.assertTrue(false,"login succesful--> Testcase Failed");
//		}
//		else if(exp.equalsIgnoreCase("Invalid") && targetPage==false) {
//			System.out.println("login unsuccesful--> Testcase Pass");
//			//Assert.assertTrue(true, "login unsuccesful--> Testcase Pass");
//		}
		
		//Second Way :
		if(exp.equalsIgnoreCase("valid") && targetPage)
		{
			scrollForElementJS(myap.lnkLogout);
		    Assert.assertTrue(true);
		}
		else if(exp.equalsIgnoreCase("valid") && !targetPage)
		{
		    Assert.fail();
		}
		else if(exp.equalsIgnoreCase("invalid") && targetPage)
		{
			scrollForElementJS(myap.lnkLogout);
		    //myap.clickLogout();
		   //Thread.sleep(10000);
	 		    Assert.fail();
		}
		else if(exp.equalsIgnoreCase("invalid") && !targetPage)
		{
		    Assert.assertTrue(true);
		}
		
		Thread.sleep(5000);
		
//		// Third Way :
//		
//		boolean expected = exp.equalsIgnoreCase("valid");
//
//		if(expected == targetPage)
//		{
//		    System.out.println("Test Passed");
//
//		    if(targetPage)
//		    {
//		        myap.clickLogout();
//		    }
//		}
//		else
//		{
//		    System.out.println("Test Failed");
//		}
	  
	}
		catch(Exception e) {
			//Assert.fail();
			e.printStackTrace();
		}
		logger.info("********* Finished TC003_LoginDDT *********");
}
}
