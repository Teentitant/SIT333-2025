package web.service;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {

	private static final String VALID_USERNAME = "ahsan";
    private static final String VALID_PASSWORD = "ahsan_pass";
    private static final String VALID_DOB = "1990-01-01";
	/**
	 * Static method returns true for successful login, false otherwise.
	 * @param username
	 * @param password
	 * @return
	 */
    public static boolean login(String username, String password, String dob) {
        // Path 1: Username empty/null check
        if (username == null || username.isEmpty()) {
            System.out.println("[LoginService] Login failed: Username empty.");
            return false;
        }
        // Path 2: Password empty/null check
        if (password == null || password.isEmpty()) {
            System.out.println("[LoginService] Login failed: Password empty.");
            return false;
        }
        // Path 3: DOB empty/null check
        if (dob == null || dob.isEmpty()) {
            System.out.println("[LoginService] Login failed: DOB empty.");
            return false;
        }

        // Matching logic
        boolean usernameMatch = VALID_USERNAME.equals(username);
        boolean passwordMatch = VALID_PASSWORD.equals(password);
        boolean dobMatch = VALID_DOB.equals(dob);

        // Path 4 (True) or Path 5 (False)
        if (usernameMatch && passwordMatch && dobMatch) {
            System.out.println("[LoginService] Login successful for user: " + username);
            return true; // Path 4
        } else {
            System.out.println("[LoginService] Login failed for user: " + username + ". Details: userMatch=" + usernameMatch + ", passMatch=" + passwordMatch + ", dobMatch=" + dobMatch);
            return false; // Path 5
        }
    }
}