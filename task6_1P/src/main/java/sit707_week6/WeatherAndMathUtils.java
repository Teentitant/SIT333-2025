package sit707_week6;

public class WeatherAndMathUtils {
	public static final double DANGEROUS_WINDSPEED = 70.0;
	public static final double DANGEROUS_RAINFALL = 6.0;
	public static final double CONCERNING_WINDSPEED = 45.0;
	public static final double CONCERNING_RAINFALL = 4.0;

	/**
	 * Advises weather alert based on wind-speed and precipitation.
	 * 
	 * 
	 * @param windSpeed
	 * @param precipitation
	 * @return
	 */
	public static String weatherAdvice(double windSpeed, double precipitation) {
		if (windSpeed < 0 || precipitation < 0) {
			throw new IllegalArgumentException();
		}

		String advice = "ALL CLEAR";

		if (windSpeed > DANGEROUS_WINDSPEED || precipitation > DANGEROUS_RAINFALL
				|| (windSpeed > CONCERNING_WINDSPEED && precipitation > CONCERNING_RAINFALL)) {
			advice = "CANCEL";
		} else if (windSpeed > CONCERNING_WINDSPEED || precipitation > CONCERNING_RAINFALL) {
			advice = "WARN";
		}

		return advice;
	}

	/**
	 * Calculates if a number is even.
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isEven(int a) {
		if (a % 2 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Calculates if a number is prime.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n) {
	    if (n <= 1) {  // 1, 0, and negative numbers are not prime.
	        return false;
	    }
	    if (n == 2) {  // 2 is the smallest and only even prime number.
	        return true;
	    }
	    if (n % 2 == 0) { // Other even numbers are not prime (optimization).
	        return false;
	    }
	    // Check for divisibility by odd numbers from 3 up to the square root of n.
	    // The loop iterates through potential odd factors.
	    for (int i = 3; i * i <= n; i += 2) {
	        if (n % i == 0) {
	            return false; // Found a divisor, so n is not prime.
	        }
	    }
	    return true; // No divisors found, so n is prime.
	}
}
