package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_Login() {

		logger.info("*******Starting TC002_LoginTest ********* ");
		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.lnkLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmailAddress(prop.getProperty("email")); // email is key which will return some value
			lp.setPassword(prop.getProperty("password")); // so it should be in double quote
			lp.clkLoginBtn();

			// MyAccountPage
			MyAccountPage myap = new MyAccountPage(driver);
			boolean targetPage = myap.isAccountPageExists();

			// Assert.assertEquals(targetPage, true,"Login failed");
			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail("Testcase failed");
		}

		logger.info("******TC002_LoginTest Ended*******");

	}

}
