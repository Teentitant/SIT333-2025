package web.service; // Ensure this matches the package of LoginService

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceUnitTest {

    // Covers Path 4: Successful login (all conditions true)
    @Test
    public void testLogin_CorrectCredentials_ShouldReturnTrue() {
        Assert.assertTrue("Login should succeed with correct username, password, and DOB",
                LoginService.login("ahsan", "ahsan_pass", "1990-01-01"));
    }

    // --- Tests for Path 1 (Username validation) ---
    @Test
    public void testLogin_NullUsername_ShouldReturnFalse() {
        Assert.assertFalse("Login should fail when username is null",
                LoginService.login(null, "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLogin_EmptyUsername_ShouldReturnFalse() {
        // Hits: if (username == null || username.isEmpty()) -> true because username.isEmpty()
        Assert.assertFalse("Login should fail when username is empty",
                LoginService.login("", "ahsan_pass", "1990-01-01"));
    }

    // --- Tests for Path 2 (Password validation, assuming username is valid) ---
    @Test
    public void testLogin_ValidUser_NullPassword_ShouldReturnFalse() {
        // Skips Path 1 (username is not empty)
        // Hits: if (password == null || password.isEmpty()) -> true because password == null
        Assert.assertFalse("Login should fail with valid username but null password",
                LoginService.login("ahsan", null, "1990-01-01"));
    }

    @Test
    public void testLogin_ValidUser_EmptyPassword_ShouldReturnFalse() {
        // Skips Path 1
        // Hits: if (password == null || password.isEmpty()) -> true because password.isEmpty()
        Assert.assertFalse("Login should fail with valid username but empty password",
                LoginService.login("ahsan", "", "1990-01-01"));
    }

    // --- Tests for Path 3 (DOB validation, assuming username and password are valid) ---
    @Test
    public void testLogin_ValidUserAndPass_NullDob_ShouldReturnFalse() {
        // Skips Path 1 & 2
        // Hits: if (dob == null || dob.isEmpty()) -> true because dob == null
        Assert.assertFalse("Login should fail with valid username/password but null DOB",
                LoginService.login("ahsan", "ahsan_pass", null));
    }

    @Test
    public void testLogin_ValidUserAndPass_EmptyDob_ShouldReturnFalse() {
        // Skips Path 1 & 2
        // Hits: if (dob == null || dob.isEmpty()) -> true because dob.isEmpty()
        Assert.assertFalse("Login should fail with valid username/password but empty DOB",
                LoginService.login("ahsan", "ahsan_pass", ""));
    }

    // --- Tests for Path 5 (Credential mismatch after passing initial empty/null checks) ---
    @Test
    public void testLogin_IncorrectUsername_ShouldReturnFalse() {
        // All inputs non-empty, but username is wrong
        // boolean usernameMatch = false; leads to final else block.
        Assert.assertFalse("Login should fail with incorrect username",
                LoginService.login("wronguser", "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLogin_IncorrectPassword_ShouldReturnFalse() {
        // All inputs non-empty, but password is wrong
        // boolean passwordMatch = false; leads to final else block.
        Assert.assertFalse("Login should fail with incorrect password",
                LoginService.login("ahsan", "wrongpass", "1990-01-01"));
    }

    @Test
    public void testLogin_IncorrectDob_ShouldReturnFalse() {
        // All inputs non-empty, but DOB is wrong
        // boolean dobMatch = false; leads to final else block.
        Assert.assertFalse("Login should fail with incorrect DOB",
                LoginService.login("ahsan", "ahsan_pass", "1999-12-31"));
    }

    @Test
    public void testLogin_AllCredentialsIncorrect_ShouldReturnFalse() {
        // All inputs non-empty, all are wrong
        // usernameMatch=false, passwordMatch=false, dobMatch=false; leads to final else.
        Assert.assertFalse("Login should fail if all credentials are wrong",
                LoginService.login("wronguser", "wrongpass", "1888-01-01"));
    }

    // Test case to ensure usernameMatch, passwordMatch, dobMatch boolean variables are evaluated
    @Test
    public void testLogin_UsernameCorrect_PasswordIncorrect_DobCorrect_ShouldReturnFalse() {
        // usernameMatch=T, passwordMatch=F, dobMatch=T. Overall (T && F && T) is F. Hits Path 5.
        Assert.assertFalse(LoginService.login("ahsan", "wrongpass", "1990-01-01"));
    }
    
    @Test
    public void testLogin_UsernameCorrect_PasswordCorrect_DobIncorrect_ShouldReturnFalse() {
        // usernameMatch=T, passwordMatch=T, dobMatch=F. Overall (T && T && F) is F. Hits Path 5.
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "1111-11-11"));
    }

    @Test
    public void testLogin_UsernameIncorrect_PasswordCorrect_DobCorrect_ShouldReturnFalse() {
        // usernameMatch=F, passwordMatch=T, dobMatch=T. Overall (F && T && T) is F. Hits Path 5.
        Assert.assertFalse(LoginService.login("notahsan", "ahsan_pass", "1990-01-01"));
    }
}