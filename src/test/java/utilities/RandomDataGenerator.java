package utilities;

public class RandomDataGenerator {

	
	// Notes : Random String Genaration
	
			/*import org.apache.commons.text.RandomStringGenerator;

			   RandomStringGenerator generator =
			        new RandomStringGenerator.Builder()
			        .withinRange('a', 'z')
			        .build();

			   String username = generator.generate(8);

			   System.out.println(username);
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
}
