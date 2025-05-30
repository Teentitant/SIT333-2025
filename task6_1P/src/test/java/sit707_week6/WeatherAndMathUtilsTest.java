package sit707_week6;


import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {

    // Standard student identity tests - correct these with your details
    @Test
    public void testStudentIdentity() {
        String studentId = "219008217"; // TODO: Replace with your student ID
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "TAN TAI NGUYEN"; // TODO: Replace with your name
        Assert.assertNotNull("Student name is null", studentName);
    }

    // --- Tests for weatherAdvice ---

    // Branch 1: Negative inputs leading to IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testWeatherAdvice_NegativeWindSpeed_ThrowsException() {
        WeatherAndMathUtils.weatherAdvice(-1.0, 5.0); // windSpeed < 0 is true
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherAdvice_NegativePrecipitation_ThrowsException() {
        WeatherAndMathUtils.weatherAdvice(10.0, -1.0); // windSpeed >= 0, but precipitation < 0 is true
    }

    // Branch 2: "CANCEL" outcomes
    // Let C2a = (windSpeed > DANGEROUS_WINDSPEED)
    // Let C2b = (precipitation > DANGEROUS_RAINFALL)
    // Let C2c = (windSpeed > CONCERNING_WINDSPEED && precipitation > CONCERNING_RAINFALL)
    // The condition for CANCEL is (C2a || C2b || C2c)

    @Test
    public void testWeatherAdvice_Cancel_C2a_True() {
        // Goal: C2a=T. This makes the whole OR true.
        // (70.1, 0.0) -> C2a=T. (T || C2b_eval_F || C2c_eval_F) -> CANCEL
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
    }

    @Test
    public void testWeatherAdvice_Cancel_C2a_False_C2b_True() {
        // Goal: C2a=F, C2b=T. This makes the whole OR true.
        // (70.0, 6.1) -> C2a=F. C2b=T. (F || T || C2c_eval_T_but_shortcircuited) -> CANCEL
        // To be very specific and ensure C2c is false if only C2b is to be tested:
        // (40.0, 6.1) -> C2a(F) || C2b(T) || C2c(F: 40.0 is not > 45) -> CANCEL
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(40.0, 6.1));
    }

    @Test
    public void testWeatherAdvice_Cancel_C2a_False_C2b_False_C2c_True() {
        // Goal: C2a=F, C2b=F, C2c=T. C2c = (C2c1 && C2c2)
        // C2c1 = (windSpeed > CONCERNING_WINDSPEED)
        // C2c2 = (precipitation > CONCERNING_RAINFALL)
        // (45.1, 4.1) -> C2a=F. C2b=F. C2c1=T, C2c2=T -> C2c=T. (F || F || T) -> CANCEL
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(45.1, 4.1));
    }

    // Branch 3: "WARN" outcomes
    // This branch is taken if (C2a || C2b || C2c) is FALSE.
    // Let C3a = (windSpeed > CONCERNING_WINDSPEED)
    // Let C3b = (precipitation > CONCERNING_RAINFALL)
    // The condition for WARN is (C3a || C3b)

    @Test
    public void testWeatherAdvice_Warn_C3a_True() {
        // Goal: Overall CANCEL condition is False. Then C3a=T.
        // (45.1, 0.0)
        // Check CANCEL: C2a(F) || C2b(F) || C2c(C2c1=T && C2c2=F -> F). So CANCEL is False.
        // Check WARN: C3a(T) || C3b(F). WARN is True.
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(45.1, 0.0));
    }

    @Test
    public void testWeatherAdvice_Warn_C3a_False_C3b_True() {
        // Goal: Overall CANCEL condition is False. Then C3a=F, C3b=T.
        // (0.0, 4.1)
        // Check CANCEL: C2a(F) || C2b(F) || C2c(C2c1=F && C2c2=T -> F). So CANCEL is False.
        // Check WARN: C3a(F) || C3b(T). WARN is True.
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(0.0, 4.1));
    }
    
    // Case to ensure the sub-expressions of C2c are evaluated correctly to FALSE for WARN path
    @Test
    public void testWeatherAdvice_Warn_C2c1_True_C2c2_False() {
        // (46.0, 3.0)
        // CANCEL check: C2a(F), C2b(F), C2c = (46>45 T && 3>4 F) = F. Overall CANCEL = F.
        // WARN check: C3a(46>45 T) || C3b(3>4 F) = T. Result: WARN
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(46.0, 3.0));
    }

    @Test
    public void testWeatherAdvice_Warn_C2c1_False_C2c2_True() {
        // (40.0, 4.5)
        // CANCEL check: C2a(F), C2b(F), C2c = (40>45 F && 4.5>4 T) = F. Overall CANCEL = F.
        // WARN check: C3a(40>45 F) || C3b(4.5>4 T) = T. Result: WARN
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(40.0, 4.5));
    }


    // Branch 4: "ALL CLEAR" outcome
    // This branch is taken if (C2a || C2b || C2c) is FALSE AND (C3a || C3b) is FALSE.
    @Test
    public void testWeatherAdvice_AllClear_NormalConditions() {
        // (45.0, 4.0) -> All > conditions are false.
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(45.0, 4.0));
    }

    @Test
    public void testWeatherAdvice_AllClear_ZeroValues() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(0.0, 0.0));
    }
    
    @Test
    public void testWeatherAdvice_AllClear_BelowAllThresholds() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(10.0, 1.0));
    }

    // --- Tests for isEven ---
    @Test
    public void testIsEven_TrueCases() {
        Assert.assertTrue("2 should be even", WeatherAndMathUtils.isEven(2));
        Assert.assertTrue("0 should be even", WeatherAndMathUtils.isEven(0));
        Assert.assertTrue("-4 should be even", WeatherAndMathUtils.isEven(-4));
    }

    @Test
    public void testIsEven_FalseCases() {
        Assert.assertFalse("1 should be odd", WeatherAndMathUtils.isEven(1));
        Assert.assertFalse("-3 should be odd", WeatherAndMathUtils.isEven(-3));
    }

    // --- Tests for isPrime ---
    // Assuming a standard implementation:
    // if (num <= 1) return false;
    // for (int i = 2; i * i <= num; i++) { if (num % i == 0) return false; }
    // return true;

    @Test
    public void testIsPrime_NumberLessThanOrEqualToOne() { // Covers "if (num <= 1) return false;"
        Assert.assertFalse("0 is not prime", WeatherAndMathUtils.isPrime(0));
        Assert.assertFalse("1 is not prime", WeatherAndMathUtils.isPrime(1));
        Assert.assertFalse("-5 is not prime", WeatherAndMathUtils.isPrime(-5));
    }

    @Test
    public void testIsPrime_SmallPrimes_LoopNotEnteredOrOnce() { // Covers loop condition and early exit for true
        Assert.assertTrue("2 is prime", WeatherAndMathUtils.isPrime(2)); // Loop condition i*i <= num (4<=2) is false. Returns true.
        Assert.assertTrue("3 is prime", WeatherAndMathUtils.isPrime(3)); // Loop condition i*i <= num (4<=3) is false. Returns true.
    }
    
    @Test
    public void testIsPrime_Primes_LoopRuns() { // Covers loop running and exiting to return true
        Assert.assertTrue("5 is prime", WeatherAndMathUtils.isPrime(5)); // i=2, 5%2!=0. Loop ends (3*3 > 5). Returns true.
        Assert.assertTrue("7 is prime", WeatherAndMathUtils.isPrime(7)); // i=2, 7%2!=0. Loop ends (3*3 > 7). Returns true.
    }

    @Test
    public void testIsPrime_NonPrimes_LoopHitsDivisor() { // Covers "if (num % i == 0) return false;"
        Assert.assertFalse("4 is not prime", WeatherAndMathUtils.isPrime(4));   // i=2, 4%2==0. Returns false.
        Assert.assertFalse("9 is not prime", WeatherAndMathUtils.isPrime(9));   // i=2, 9%2!=0; i=3, 9%3==0. Returns false.
        Assert.assertFalse("49 is not prime", WeatherAndMathUtils.isPrime(49)); // i=2..6 no; i=7, 49%7==0. Returns false.
    }
}
