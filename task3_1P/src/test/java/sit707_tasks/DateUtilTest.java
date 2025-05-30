package sit707_tasks;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import sit707_tasks.DateUtil;
import java.util.Random;

/**
 * @author Ahsan Habib
 */
public class DateUtilTest {

    // Student Identity and Name Tests (as above)
    @Test
    public void testStudentIdentity() {
        String studentId = "219008217"; 
        Assert.assertNotNull("Student ID is null", studentId);
        System.out.println("Student ID: " + studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "TAN TAI NGUYEN"; 
        Assert.assertNotNull("Student name is null", studentName);
        System.out.println("Student Name: " + studentName);
    }


    @Test
    public void testMaxJanuary31ShouldIncrementToFebruary1() {
       
        DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldIncrementToFebruary1 -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getDay()); 
        Assert.assertEquals(2, date.getMonth()); 
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testMaxJanuary31ShouldDecrementToJanuary30() {
        // January max boundary area: max-1
        DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldDecrementToJanuary30 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testNominalJanuary() {
        // Test a nominal day in January
        int rand_day_1_to_31 = 1 + new Random().nextInt(29); // nominal day between 1 and 29
        DateUtil date = new DateUtil(rand_day_1_to_31, 1, 2024);
        System.out.println("testJanuaryNominal -> " + date);
        int expectedDay = rand_day_1_to_31 + 1;
        date.increment();
        System.out.println(date);
        Assert.assertEquals(expectedDay, date.getDay());
        Assert.assertEquals(1, date.getMonth());
    }

    @Test
    public void testMinJanuary1ShouldIncrementToJanuary2() {
        // January, min boundary area: min+1
        DateUtil date = new DateUtil(1, 1, 2024);
        System.out.println("testMinJanuary1ShouldIncrementToJanuary2 -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(2, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testMinJanuary1ShouldDecrementToDecember31PreviousYear() {
        // January, min boundary area: min-1
        DateUtil date = new DateUtil(1, 1, 2024);
        System.out.println("testMinJanuary1ShouldDecrementToDecember31PreviousYear -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    // --- New Test Cases Based on Populated Tables ---

    // Previous Date Tests (Decrement) 
    @Test
    public void testDecrement_1A_June1_1994() { // Test Case ID 1A
        DateUtil date = new DateUtil(1, 6, 1994);
        System.out.println("testDecrement_1A_June1_1994 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay()); // Expected: 31
        Assert.assertEquals(5, date.getMonth()); // Expected: May (5)
        Assert.assertEquals(1994, date.getYear());
    }
    
    @Test
    public void testDecrement_2A_June2_1994() { 
        DateUtil date = new DateUtil(2, 6, 1994);
        System.out.println("testDecrement_2A_June_1994 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(1, date.getDay()); 
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1994, date.getYear());
    }

    @Test
    public void testDecrement_3A_June15_1994() { 
        DateUtil date = new DateUtil(15, 6, 1994);
        System.out.println("testDecrement_3A_June_1994 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(14, date.getDay()); 
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1994, date.getYear());
    }
    
    @Test
    public void testDecrement_6A_January15_1994() { 
        DateUtil date = new DateUtil(15, 1, 1994);
        System.out.println("testDecrement_6A_January15_1994 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(14, date.getDay()); 
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(1994, date.getYear());
    }
    
    @Test
    public void testDecrement_7A_February15_1994() { 
        DateUtil date = new DateUtil(15, 2, 1994);
        System.out.println("testDecrement_7A_February15_1994 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(14, date.getDay()); 
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(1994, date.getYear());
    }
    
    @Test
    public void testDecrement_4A_June30_1994() { // Test Case ID 4A
        DateUtil date = new DateUtil(30, 6, 1994);
        System.out.println("testDecrement_4A_June30_1994 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1994, date.getYear());
    }
    
    // Test case 5A: 31/6/1994 is an invalid date and should be caught by the constructor.
    // Thus, it's not suitable for testing the decrement() method on an already constructed object.
    // A test for the constructor could be:
    @Test(expected = RuntimeException.class)
    public void testConstructor_InvalidDay_June31_1994() { // Related to 5A
        System.out.println("testConstructor_InvalidDay_June31_1994");
        new DateUtil(31, 6, 1994); // Should throw RuntimeException
    }

    @Test
    public void testDecrement_10A_June15_1700() { // Test Case ID 10A
        DateUtil date = new DateUtil(15, 6, 1700);
        System.out.println("testDecrement_10A_June15_1700 -> " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(14, date.getDay());
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1700, date.getYear());
    }

    // Next Date Tests (Increment) - Examples from new Table 2
    @Test
    public void testIncrement_4B_June30_1994() { // Test Case ID 4B
        DateUtil date = new DateUtil(30, 6, 1994);
        System.out.println("testIncrement_4B_June30_1994 -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getDay());  // Expected: 1
        Assert.assertEquals(7, date.getMonth()); // Expected: July (7)
        Assert.assertEquals(1994, date.getYear());
    }

    @Test
    public void testIncrement_6B_Feb28_1994_NonLeap() { // Test Case ID 6B
        DateUtil date = new DateUtil(28, 2, 1994); // 1994 is not a leap year
        System.out.println("testIncrement_6B_Feb28_1994_NonLeap -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth()); // Expected: March (3)
        Assert.assertEquals(1994, date.getYear());
    }

    @Test
    public void testIncrement_7B_Feb28_2024_Leap() { // Test Case ID 7B
        DateUtil date = new DateUtil(28, 2, 2024); // 2024 is a leap year
        System.out.println("testIncrement_7B_Feb28_2024_Leap -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(29, date.getDay()); // Expected: 29
        Assert.assertEquals(2, date.getMonth()); // Expected: February (2)
        Assert.assertEquals(2024, date.getYear());
    }
    
    @Test
    public void testIncrement_8B_Feb29_2024_Leap() { // Test Case ID 8B
        DateUtil date = new DateUtil(29, 2, 2024); // 2024 is a leap year
        System.out.println("testIncrement_8B_Feb29_2024_Leap -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth()); // Expected: March (3)
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testIncrement_10B_Dec31_1994() { // Test Case ID 10B
        DateUtil date = new DateUtil(31, 12, 1994);
        System.out.println("testIncrement_10B_Dec31_1994 -> " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(1, date.getMonth()); // Expected: January (1)
        Assert.assertEquals(1995, date.getYear());
    }
    
    @Test
    public void testIncrement_15B_Dec31_2024_MaxYearBoundary() { // Test Case ID 15B
        DateUtil date = new DateUtil(31, 12, 2024);
        System.out.println("testIncrement_15B_Dec31_2024_MaxYearBoundary -> " + date);
        date.increment(); // This will result in year 2025
        System.out.println(date);
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2025, date.getYear());
        // Note: The constructor validates year 1700-2024[cite: 18, 28].
        // Incrementing beyond 2024 currently results in year 2025.
        // A more robust DateUtil might throw an exception or handle this differently
        // if 2025 is considered out of the system's operational range.
        // For now, testing the current behavior.
    }
}