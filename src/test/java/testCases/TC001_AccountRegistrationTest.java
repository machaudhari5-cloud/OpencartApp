package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verifyAccountRegistration() throws InterruptedException {
		try {
			logger.info("Starting TC001_AccountRegistrationTest");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickRegister();
			logger.info("Clicked on Register link");
			Thread.sleep(10);
			AccountRegistrationPage arp = new AccountRegistrationPage(driver);
			logger.info("Filling the registration form with random data");
			arp.setFirstName(randomString().toUpperCase());
			arp.setLastName(randomString().toUpperCase());
			arp.setEmail(randomString() + "@gmail.com");
			arp.setTelephone(randomNumber());

			String password = randomAlphaNumeric();
			arp.setPassword(password);
			arp.setPasswordConfirm(password);
			arp.setPolicy();
			arp.clkContinue();
			Thread.sleep(5);
			logger.info("Submitted the registration form");

			String confmsg = arp.getConfirmMsg();
			logger.info("Confirmation message received: " + confmsg);

			if ((confmsg.equals("Your Account Has Been Created!"))) {
				logger.info("Account registration successful");
				Assert.assertTrue(true);
			} else {
				logger.error("Account registration failed. Expected confirmation message not received.");
				Assert.fail();
			}

			// Assert.assertEquals(confmsg, "Congratulations! Your new account has been
			// successfully created!");
		} catch (Exception e) {
			// logger.error("Test Failed...");
			// logger.debug("Debug logs...");
			// Assert.fail();
			logger.error("Exception occurred during account registration test: " + e.getMessage());
			
		}

	}
}
// Notes : Random String Genaration

/*
 * import org.apache.commons.text.RandomStringGenerator;
 * 
 * RandomStringGenerator generator = new RandomStringGenerator.Builder()
 * .withinRange('a', 'z') .build();
 * 
 * String username = generator.generate(8);
 * 
 * System.out.println(username);
 */
/*
 * String firstName = RandomStringUtils.secure().nextAlphabetic(6); String
 * lastName = RandomStringUtils.secure().nextAlphabetic(8); String email =
 * RandomStringUtils.secure().nextAlphabetic(5) + "@gmail.com";
 * 
 * driver.findElement(By.name("firstName")).sendKeys(firstName);
 * driver.findElement(By.name("lastName")).sendKeys(lastName);
 * driver.findElement(By.name("email")).sendKeys(email);
 */

// Alternate way with pure java 17 :
/*
 * import java.util.UUID;
 * 
 * String username = UUID.randomUUID() .toString() .substring(0, 8);
 * 
 * System.out.println(username);
 * 
 * RandomStringUtils.;
 */
