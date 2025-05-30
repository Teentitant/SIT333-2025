package sit707_week4;

import org.junit.Assert;

import org.junit.Test;
import sit707_week4.LoginForm;
import sit707_week4.LoginStatus;

/**
 * Tests functions in LoginForm.
 * @author Ahsan Habib
 */
public class LoginFormTest {

    
    @Test
    public void testStudentIdentity() {
        String studentId = "219008217"; 
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "TAN TAI NGUYEN"; 
        Assert.assertNotNull("Student name is null", studentName);
    }

    // Test cases based on the decision table rules:

    // Rule 1: Empty Username, Empty Password
    @Test
    public void testLoginRule1_EmptyUser_EmptyPass() {
        LoginStatus status = LoginForm.login(null, null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
    }

    // Rule 2: Empty Username, Wrong Password
    @Test
    public void testLoginRule2_EmptyUser_WrongPass() {
        LoginStatus status = LoginForm.login("", "wrongpass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
    }

    // Rule 3: Empty Username, Correct Password
    @Test
    public void testLoginRule3_EmptyUser_CorrectPass() {
        LoginStatus status = LoginForm.login(null, "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
    }

    // Rule 4: Wrong Username, Empty Password
    @Test
    public void testLoginRule4_WrongUser_EmptyPass() {
        LoginStatus status = LoginForm.login("wronguser", null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
    }

    // Rule 5: Wrong Username, Wrong Password
    @Test
    public void testLoginRule5_WrongUser_WrongPass() {
        LoginStatus status = LoginForm.login("wronguser", "wrongpass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
    }

    // Rule 6: Wrong Username, Correct Password
    @Test
    public void testLoginRule6_WrongUser_CorrectPass() {
        LoginStatus status = LoginForm.login("wronguser", "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
    }

    // Rule 7: Correct Username, Empty Password
    @Test
    public void testLoginRule7_CorrectUser_EmptyPass() {
        LoginStatus status = LoginForm.login("ahsan", "");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
    }

    // Rule 8: Correct Username, Wrong Password
    @Test
    public void testLoginRule8_CorrectUser_WrongPass() {
        LoginStatus status = LoginForm.login("ahsan", "wrongpass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
    }

    // Rule 9: Correct Credentials, then validate with Empty Code
    @Test
    public void testLoginRule9_CorrectCred_ValidateEmptyCode() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        Assert.assertEquals("123456", status.getErrorMsg()); // Validation code is returned in errorMsg on success [cite: 17]
        
        boolean validationResult = LoginForm.validateCode(null); // Empty code
        Assert.assertFalse(validationResult);
    }

    // Rule 10: Correct Credentials, then validate with Wrong Code
    @Test
    public void testLoginRule10_CorrectCred_ValidateWrongCode() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        Assert.assertEquals("123456", status.getErrorMsg());
        
        boolean validationResult = LoginForm.validateCode("wrongcode"); // Wrong code
        Assert.assertFalse(validationResult);
    }

    // Rule 11: Correct Credentials, then validate with Correct Code
    @Test
    public void testLoginRule11_CorrectCred_ValidateCorrectCode() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        Assert.assertEquals("123456", status.getErrorMsg());
        
        // The validation code returned in errorMsg should be used for successful validation
        boolean validationResult = LoginForm.validateCode(status.getErrorMsg()); // Correct code ("123456")
        Assert.assertTrue(validationResult);
    }
}
