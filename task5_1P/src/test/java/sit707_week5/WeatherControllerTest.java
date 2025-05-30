package sit707_week5;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] S_hourlyTemperatures; // To store temperatures fetched once
    private static double S_expectedMinTemp;
    private static double S_expectedMaxTemp;
    private static double S_expectedAvgTemp;
    private static final double DELTA = 0.00001; // For comparing double values

    @BeforeClass
    public static void setUpOnce() {
        System.out.println("****** @BeforeClass: Initializing WeatherController and fetching data ONCE... ******");
        // Arrange: Initialise controller (slow operation, done once) [cite: 5, 312]
        wController = WeatherController.getInstance();

        // Arrange: Retrieve all hourly temperatures recorded for today (slow operation, done once) [cite: 5, 34]
        int nHours = wController.getTotalHours(); // Should be 3, based on HOURS_PER_DAY [cite: 25]
        S_hourlyTemperatures = new double[nHours];

        double sumForAvg = 0;
        S_expectedMinTemp = 1000; // Initialize with a high value for min calculation
        S_expectedMaxTemp = -1;   // Initialize with a low value for max calculation (assuming non-negative temps from random.nextInt(30)+1)

        if (nHours > 0) {
            for (int i = 0; i < nHours; i++) {
                // Hour range for getTemperatureForHour is 1 to nHours
                S_hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1); // Fetch once [cite: 19]
                
                if (S_expectedMinTemp > S_hourlyTemperatures[i]) {
                    S_expectedMinTemp = S_hourlyTemperatures[i];
                }
                if (S_expectedMaxTemp < S_hourlyTemperatures[i]) {
                    S_expectedMaxTemp = S_hourlyTemperatures[i];
                }
                sumForAvg += S_hourlyTemperatures[i];
            }
            S_expectedAvgTemp = sumForAvg / nHours;
        } else {
            
            S_expectedMinTemp = 0; 
            S_expectedMaxTemp = 0;
            S_expectedAvgTemp = 0;
        }
        System.out.println("****** Data fetched and expectations pre-calculated. ******");
        System.out.println("Fetched temperatures: " + java.util.Arrays.toString(S_hourlyTemperatures));
        System.out.println("Pre-calculated Expected Min: " + S_expectedMinTemp);
        System.out.println("Pre-calculated Expected Max: " + S_expectedMaxTemp);
        System.out.println("Pre-calculated Expected Avg: " + S_expectedAvgTemp);
    }

    @AfterClass
    public static void tearDownOnce() {
        System.out.println("****** @AfterClass: Closing WeatherController ONCE... ******");
        if (wController != null) {
            wController.close(); // 
        }
        System.out.println("****** WeatherController closed. ******");
    }

    @Test
    public void testStudentIdentity() {
        System.out.println("--- testStudentIdentity ---");
        String studentId = "219008217"; 
        Assert.assertNotNull("Student ID should not be null", studentId); 
        System.out.println("Student ID: " + studentId);
    }

    @Test
    public void testStudentName() {
        System.out.println("--- testStudentName ---");
        String studentName = "TAN TAI NGUYEN"; 
        Assert.assertNotNull("Student name should not be null", studentName);
        System.out.println("Student Name: " + studentName);
    }

    @Test
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");
        
        // Act: Get the cached minimum temperature from the controller.
        double cachedMin = wController.getTemperatureMinFromCache(); 
        System.out.println("Cached Min from controller: " + cachedMin);
        
        
        Assert.assertEquals("Min temperature from cache should match pre-calculated min.", S_expectedMinTemp, cachedMin, DELTA); // Original was Assert.assertTrue [cite: 340]
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");
        // Arrange: S_expectedMaxTemp is already calculated in @BeforeClass.

        // Act: Get the cached maximum temperature from the controller.
        double cachedMax = wController.getTemperatureMaxFromCache(); // This should be fast [cite: 12, 311]
        System.out.println("Cached Max from controller: " + cachedMax);

        // Assert: Compare the cached value with our pre-calculated expected maximum.
        Assert.assertEquals("Max temperature from cache should match pre-calculated max.", S_expectedMaxTemp, cachedMax, DELTA);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");
        // Arrange: S_expectedAvgTemp is already calculated in @BeforeClass.

        // Act: Get the cached average temperature from the controller.
        double cachedAvg = wController.getTemperatureAverageFromCache(); // This should be fast [cite: 12, 311]
        System.out.println("Cached Avg from controller: " + cachedAvg);
        
        // Assert: Compare the cached value with our pre-calculated expected average.
        Assert.assertEquals("Average temperature from cache should match pre-calculated average.", S_expectedAvgTemp, cachedAvg, DELTA);
    }
	
	@Test
	public void testTemperaturePersist() {
		/*
		 * Remove below comments ONLY for 5.3C task.
		 */
//		System.out.println("+++ testTemperaturePersist +++");
//		
//		// Initialise controller
//		WeatherController wController = WeatherController.getInstance();
//		
//		String persistTime = wController.persistTemperature(10, 19.5);
//		String now = new SimpleDateFormat("H:m:s").format(new Date());
//		System.out.println("Persist time: " + persistTime + ", now: " + now);
//		
//		Assert.assertTrue(persistTime.equals(now));
//		
//		wController.close();
	}
}
