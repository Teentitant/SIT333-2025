package sit707_week2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files; 

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * This class demonstrates Selenium locator APIs to identify HTML elements.
 * 
 * Details in Selenium documentation https://www.selenium.dev/documentation/webdriver/elements/locators/
 * 
 * @author Ahsan Habib
 */
public class SeleniumOperations {

    // Helper method for sleep, if not already present
    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void officeworks_registration_page(String url) {
        // Step 1: Locate chrome driver folder in the local drive. [cite: 13]
        // Update this path to where your chromedriver is located
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe"); // TODO: Update this path

        // Step 2: Use above chrome driver to open up a chromium browser.
        System.out.println("Fire up chrome browser.");
        WebDriver driver = new ChromeDriver();
        System.out.println("Driver info: " + driver);

        sleep(2);

        // Load page in chromium browser [cite: 8]
        driver.get(url);

        /*
         * How to identify HTML input elements:
         * Step 1: Visit an HTML page, say https://www.officeworks.com.au/app/identity/create-account
         * Step 2: Right click on an input field, e.g. first name and select Inspect.
         * Step 3: Find out how to identify it, by id or by name attribute.
         */

        // Find first input field which is firstname [cite: 5, 13]
        System.out.println("Attempting to find 'firstname' element.");
        WebElement elementFirstname = driver.findElement(By.id("firstname")); // TODO: Verify ID on actual page
        System.out.println("Found element: " + elementFirstname);
        // Send first name
        elementFirstname.sendKeys("Ahsan"); // Example, change to your details

        // Locate all the other input fields and pass your personal information [cite: 2, 5]
        // ** YOU MUST INSPECT THE OFFICECWORKS PAGE TO GET THE CORRECT IDs/NAMES/XPATHs FOR THESE ELEMENTS **
        
        WebElement elementLastname = driver.findElement(By.id("lastname")); // TODO: Verify ID and use it
        elementLastname.sendKeys("YourLastName"); // Change to your details

        WebElement elementPhone = driver.findElement(By.id("phoneNumber")); // TODO: Verify ID and use it (this might be 'mobile' or similar)
        elementPhone.sendKeys("0412345678"); // Change to your details
        
        WebElement elementEmail = driver.findElement(By.id("email")); // TODO: Verify ID and use it
        elementEmail.sendKeys("your.email@example.com"); // Change to your details
        
        WebElement elementPassword = driver.findElement(By.id("password")); // TODO: Verify ID and use it
        // Enter a password that intentionally fails requirements to prevent actual account creation [cite: 17]
        elementPassword.sendKeys("short"); 

        // You might need to handle other fields like "confirm password" if present,
        // or checkboxes for terms and conditions. Example:
        // WebElement termsCheckbox = driver.findElement(By.id("terms-checkbox")); // TODO: Verify ID
        // termsCheckbox.click();

        sleep(1); // Wait a second for fields to populate if there's any dynamic behavior

        // Locate the Create account button and simulate a click action. [cite: 6, 9]
        // ** YOU MUST INSPECT THE OFFICECWORKS PAGE TO GET THE CORRECT LOCATOR FOR THE BUTTON **
        WebElement createAccountButton = driver.findElement(By.xpath("//button[contains(text(),'Create account')]")); // TODO: Verify this XPath or find a better ID/Name
        System.out.println("Attempting to click 'Create account' button.");
        createAccountButton.click();

        sleep(3); // Wait for the page to process the click and show errors

        // Take screenshot using Selenium screenshot API [cite: 11]
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            // Save the screenshot
            Files.copy(scrFile.toPath(), new File("officeworks_registration_screenshot.png").toPath());
            System.out.println("Screenshot saved to officeworks_registration_screenshot.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sleep a while
        sleep(5);

        // close chrome driver
        driver.quit();
    }

    public static void alternative_registration_page(String url) {
        // Step 1: Locate chrome driver folder in the local drive.
        // Update this path to where your chromedriver is located
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe"); // TODO: Update this path

        // Step 2: Use above chrome driver to open up a chromium browser.
        System.out.println("Fire up chrome browser for alternative site.");
        WebDriver driver = new ChromeDriver();
        System.out.println("Driver info (alternative): " + driver);

        sleep(2);

        // Load page in chromium browser
        driver.get(url);

        // ** YOU MUST INSPECT THE ALTERNATIVE WEBSITE'S PAGE TO GET THE CORRECT IDs/NAMES/XPATHs **
        // Example for a hypothetical alternative site:
        System.out.println("Filling form for alternative site: " + url);

        WebElement altFirstName = driver.findElement(By.id("user_first_name")); // TODO: Verify ID
        altFirstName.sendKeys("TestFirstName");

        WebElement altLastName = driver.findElement(By.id("user_last_name")); // TODO: Verify ID
        altLastName.sendKeys("TestLastName");

        WebElement altEmail = driver.findElement(By.name("user_email")); // TODO: Verify Name
        altEmail.sendKeys("test.alternative@example.com");

        WebElement altPassword = driver.findElement(By.id("user_password")); // TODO: Verify ID
        altPassword.sendKeys("weakpass"); // Intentionally weak password

        // WebElement altConfirmPassword = driver.findElement(By.id("user_password_confirmation")); // TODO: Verify ID
        // altConfirmPassword.sendKeys("weakpass");
        
        sleep(1);

        WebElement altSubmitButton = driver.findElement(By.name("commit")); // Example: often 'commit' or 'submit' by name
        // Or use XPath: //button[@type='submit'] or //input[@type='submit']
        // e.g., WebElement altSubmitButton = driver.findElement(By.xpath("//button[@type='submit']")); // TODO: Verify XPath
        System.out.println("Attempting to click submit button on alternative site.");
        altSubmitButton.click();

        sleep(3); // Wait for errors or page change

        // Take screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(scrFile.toPath(), new File("alternative_registration_screenshot.png").toPath());
            System.out.println("Screenshot saved to alternative_registration_screenshot.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sleep(5);
        driver.quit();
    }

    /*
    // Example of how you might call this from your Main.java (if you modify it)
    public static void main(String[] args) {
        officeworks_registration_page("https://www.officeworks.com.au/app/identity/create-account");
        
        // For the alternative site, choose a URL that has a registration form.
        // For example, a demo site like "https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/register"
        // (Note: The structure of this demo site might have changed, always inspect elements)
        // alternative_registration_page("YOUR_CHOSEN_ALTERNATIVE_REGISTRATION_URL"); // TODO: Provide a valid URL
    }
    */
}
