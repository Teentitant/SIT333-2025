package web.service;

public class MathQuestionService {

	/**
	 * Calculate Q1 result.
	 * @param number1
	 * @param number2
	 * @return
	 */
	 public static double q1Addition(String number1, String number2) throws IllegalArgumentException {
	        try {
	            if (number1 == null || number1.trim().isEmpty() || number2 == null || number2.trim().isEmpty()) {
	                throw new IllegalArgumentException("Inputs cannot be empty.");
	            }
	            double num1 = Double.valueOf(number1);
	            double num2 = Double.valueOf(number2);
	            return num1 + num2;
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid number format provided.");
	        }
	    }
	
	/**
	 * Calculate Q2 result.
	 * @param number1
	 * @param number2
	 * @return
	 */
	 public static double q2Subtraction(String number1, String number2) throws IllegalArgumentException {
	        try {
	            if (number1 == null || number1.trim().isEmpty() || number2 == null || number2.trim().isEmpty()) {
	                throw new IllegalArgumentException("Inputs cannot be empty.");
	            }
	            double num1 = Double.valueOf(number1);
	            double num2 = Double.valueOf(number2);
	            return num1 - num2;
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid number format provided.");
	        }
	    }

	
	 public static double q3Multiplication(String number1, String number2) throws IllegalArgumentException {
	       
	        try {
	            if (number1 == null || number1.trim().isEmpty() || number2 == null || number2.trim().isEmpty()) {
	              
	                throw new IllegalArgumentException("Inputs cannot be empty.");
	            }

	            double num1 = Double.valueOf(number1);
	            double num2 = Double.valueOf(number2);

	            double result = num1 * num2;

	            return result;

	        } catch (NumberFormatException e) {
	           
	            throw new IllegalArgumentException("Invalid number format provided. " + e.getMessage());
	        }
	    }

}