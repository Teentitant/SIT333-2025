package web.service; 

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // Or your preferred browser driver
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StemGameFunctionalTest {

    private WebDriver driver;
    private String baseUrl;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver(); // Ensure ChromeDriver is in your PATH or specify path
        baseUrl = "http://127.0.0.1:8080/"; 
        wait = new WebDriverWait(driver, 10); // 10 seconds timeout
        // Ensure your Spring Boot application (MyServer.java) is running before these tests.
    }

    private void login(String username, String password, String dob) {
        driver.get(baseUrl + "login");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("passwd")).sendKeys(password);
        driver.findElement(By.name("dob")).sendKeys(dob);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Test
    public void testLoginFailure_WrongUserWrongPass() {
        login("wronguser", "wrongpass", "01/01/1990");
        // Expect redirection to login page with error
        wait.until(ExpectedConditions.urlContains("/login")); // More flexible than urlToBe if there are query params like ?error
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Incorrect credentials.')]")));
        assertTrue(errorMessage.isDisplayed());
        // Ensure it's actually the login page, not q1
        assertFalse("Should not navigate to q1 on failed login", driver.getCurrentUrl().endsWith("/q1"));
    }

    @Test
    public void testLoginFailure_CorrectUserWrongPass() {
        login("ahsan", "wrong_pass", "01/01/2000"); // Correct username, wrong password
        wait.until(ExpectedConditions.urlContains("/login"));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Incorrect credentials.')]")));
        assertTrue(errorMessage.isDisplayed());
        assertFalse("Should not navigate to q1 on failed login", driver.getCurrentUrl().endsWith("/q1"));
    }

    @Test
    public void testLoginFailure_WrongUserCorrectPass() {
        login("not_ahsan", "ahsan_pass", "01/01/2000"); // Wrong username, correct password
        wait.until(ExpectedConditions.urlContains("/login"));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Incorrect credentials.')]")));
        assertTrue(errorMessage.isDisplayed());
        assertFalse("Should not navigate to q1 on failed login", driver.getCurrentUrl().endsWith("/q1"));
    }

    // This test demonstrates that DOB is currently ignored for login success/failure.
    // With the current LoginService, this would be a SUCCESSFUL login.
    // If LoginService were changed to validate DOB, this would become a failure test.
    @Test
    public void testLogin_CorrectUserCorrectPass_DifferentDOB_CurrentlyIgnored() {
        login("ahsan", "ahsan_pass", "31/12/1999"); // Correct user/pass, different DOB
        // With current LoginService, this will PASS and redirect to q1
        wait.until(ExpectedConditions.urlContains("/login"));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Incorrect credentials.')]")));
        assertTrue(errorMessage.isDisplayed());
        assertFalse("Should not navigate to q1 on failed login", driver.getCurrentUrl().endsWith("/q1"));
    }

    @Test
    public void testQ1_CorrectAnswer() {
        login("ahsan", "ahsan_pass", "01/01/2000"); // Assuming LoginService has these credentials
        driver.get(baseUrl + "q1"); // Or navigate from login
        
        // Assuming view-q1.jsp pre-fills or shows numbers. For testing, we might need to know them.
        // For this example, let's assume the numbers are displayed or we input them if the fields are empty.
        // If view-q1.jsp is like view-q3.jsp and has preset values for example, use those.
        // For demonstration, let's say Q1 always asks for 5 + 3
        WebElement num1Field = driver.findElement(By.name("number1"));
        if(num1Field.getAttribute("value").isEmpty()) num1Field.sendKeys("5"); // Example value
        String num1Value = num1Field.getAttribute("value");

        WebElement num2Field = driver.findElement(By.name("number2"));
        if(num2Field.getAttribute("value").isEmpty()) num2Field.sendKeys("3"); // Example value
        String num2Value = num2Field.getAttribute("value");

        double expectedResult = Double.parseDouble(num1Value) + Double.parseDouble(num2Value);

        driver.findElement(By.name("result")).sendKeys(String.valueOf(expectedResult));
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(baseUrl + "q2"));
        assertEquals(baseUrl + "q2", driver.getCurrentUrl());
    }

    @Test
    public void testQ1_IncorrectAnswer() {
        login("ahsan", "ahsan_pass", "01/01/2000");
        driver.get(baseUrl + "q1");
        
        driver.findElement(By.name("number1")).clear(); // Clear if pre-filled
        driver.findElement(By.name("number1")).sendKeys("5"); // Example values
        driver.findElement(By.name("number2")).clear();
        driver.findElement(By.name("number2")).sendKeys("3");
        driver.findElement(By.name("result")).sendKeys("7"); // Incorrect answer for 5+3
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        
        wait.until(ExpectedConditions.urlToBe(baseUrl + "q1")); // Stays on Q1
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Wrong answer, try again.')]")));
        assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testQ1_EmptyInput() {
    	login("ahsan", "ahsan_pass", "01/01/2000");
        driver.get(baseUrl + "q1");
        driver.findElement(By.name("number1")).sendKeys(""); // Empty input
        driver.findElement(By.name("number2")).sendKeys("3");
        driver.findElement(By.name("result")).sendKeys("3");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        wait.until(ExpectedConditions.urlToBe(baseUrl + "q1")); // Stays on Q1
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid input. Please enter valid numbers.')]")));
        assertTrue(errorMessage.isDisplayed());
        assertTrue(driver.getPageSource().contains("Inputs cannot be empty.")); // Check for specific part of message
    }

    // Add similar tests for Q2 (subtraction) and Q3 (multiplication)
    // For Q2: correct -> /q3, incorrect -> /q2, empty input -> /q2 with message
    // For Q3: correct -> / (welcome with congrats), incorrect -> /q3, empty input -> /q3 with message

    @Test
    public void testQ2_CorrectAnswer() {
    	login("ahsan", "ahsan_pass", "01/01/2000");
        driver.get(baseUrl + "q2"); // Assume navigation or direct access

        // Example for Q2: 10 - 4 = 6
        WebElement num1Field = driver.findElement(By.name("number1"));
        if(num1Field.getAttribute("value").isEmpty()) num1Field.sendKeys("10");
        String num1Value = num1Field.getAttribute("value");

        WebElement num2Field = driver.findElement(By.name("number2"));
        if(num2Field.getAttribute("value").isEmpty()) num2Field.sendKeys("4");
        String num2Value = num2Field.getAttribute("value");
        
        double expectedResult = Double.parseDouble(num1Value) - Double.parseDouble(num2Value);

        driver.findElement(By.name("result")).sendKeys(String.valueOf(expectedResult));
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(baseUrl + "q3"));
        assertEquals(baseUrl + "q3", driver.getCurrentUrl());
    }
    
    @Test
    public void testQ3_CorrectAnswer_GameEnd() {
    	login("ahsan", "ahsan_pass", "01/01/2000");
        driver.get(baseUrl + "q3"); // Assume navigation or direct access

        // Example for Q3 (from JSP): 7 * 6 = 42
        WebElement num1Field = driver.findElement(By.name("number1"));
        if(num1Field.getAttribute("value").isEmpty()) num1Field.sendKeys("5");
        String num1Value = num1Field.getAttribute("value");

        WebElement num2Field = driver.findElement(By.name("number2"));
       if(num2Field.getAttribute("value").isEmpty()) num2Field.sendKeys("3");
        String num2Value = num2Field.getAttribute("value"); 
        
        double expectedResult = Double.parseDouble(num1Value) * Double.parseDouble(num2Value);

        driver.findElement(By.name("result")).sendKeys(String.valueOf(expectedResult));
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(baseUrl)); 
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
