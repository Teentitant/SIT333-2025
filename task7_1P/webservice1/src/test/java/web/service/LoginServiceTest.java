package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginServiceTest {
	
	private WebDriver driver;
	
	private String baseLoginUrl = "file:///C:/SIT333/task7_1P/pages/login.html";
	private void sleep(long sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
	
	@Before
    public void setUp() {
      
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");   
        driver = new ChromeDriver();
        System.out.println("WebDriver Initialized: " + driver);
    }

    private void performLogin(String username, String password, String dob) {
        driver.navigate().to(baseLoginUrl);
        sleep(1); // Allow page to load


        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

       
        WebElement passwordElement = driver.findElement(By.id("passwd"));
        passwordElement.clear();
        passwordElement.sendKeys(password);

        
        WebElement dobElement = driver.findElement(By.id("dob"));
        dobElement.clear();
        // For <input type="date">, sendKeys expects "YYYY-MM-DD"
        if (dob != null && !dob.isEmpty()){ // Selenium might error on empty sendKeys for date type on some drivers
             dobElement.sendKeys(dob);
        }


        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        sleep(2); 
    }

    
    @Test
    public void testLogin_Successful() {
        performLogin("ahsan", "ahsan_pass", "1990-01-01");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Successful Login: " + pageTitle);
        Assert.assertEquals("success", pageTitle); // Verify page title [cite: 27]
    }

    @Test
    public void testLogin_Failure_IncorrectUsername() {
        performLogin("wronguser", "ahsan_pass", "1990-01-01");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Incorrect Username: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }

    @Test
    public void testLogin_Failure_IncorrectPassword() {
        performLogin("ahsan", "wrongpassword", "1990-01-01");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Incorrect Password: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }

    @Test
    public void testLogin_Failure_IncorrectDob() {
        performLogin("ahsan", "ahsan_pass", "1999-12-31");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Incorrect DOB: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }

    @Test
    public void testLogin_Failure_EmptyUsername() {
        performLogin("", "ahsan_pass", "1990-01-01");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Empty Username: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }

    @Test
    public void testLogin_Failure_EmptyPassword() {
        performLogin("ahsan", "", "1990-01-01");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Empty Password: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }
    
    @Test
    public void testLogin_Failure_EmptyDob() {
        performLogin("ahsan", "ahsan_pass", ""); // dob is sent as empty
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for Empty DOB: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }

    @Test
    public void testLogin_Failure_AllFieldsEmpty() {
        performLogin("", "", "");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for All Fields Empty: " + pageTitle);
        Assert.assertEquals("fail", pageTitle); // Expecting fail due to empty username
    }

    @Test
    public void testLogin_Failure_AllFieldsIncorrect() {
        performLogin("notauser", "notapass", "1111-11-11");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title for All Fields Incorrect: " + pageTitle);
        Assert.assertEquals("fail", pageTitle);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Use quit() to close all browser windows and end WebDriver session
        }
    }
}
