	package web.service;

import org.junit.Test; 
import static org.junit.Assert.*; 

public class TestMathQuestionService {

    private static final double DELTA = 1e-9; 

 
    @Test
    public void testQ1Addition_validInputs() {
        assertEquals(5.0, MathQuestionService.q1Addition("2", "3"), DELTA);
        assertEquals(-1.0, MathQuestionService.q1Addition("2", "-3"), DELTA);
        assertEquals(0.0, MathQuestionService.q1Addition("0", "0"), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_emptyInput1() {
        MathQuestionService.q1Addition("", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_emptyInput2() {
        MathQuestionService.q1Addition("2", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_nullInput2() { 
        MathQuestionService.q1Addition("2", null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_bothNull() { 
        MathQuestionService.q1Addition(null, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_nonNumericInput2() { 
        MathQuestionService.q1Addition("2", "xyz");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_bothNonNumeric() { 
        MathQuestionService.q1Addition("abc", "xyz");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_bothEmpty() {
        MathQuestionService.q1Addition(" ", " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_nonNumericInput() {
        MathQuestionService.q1Addition("abc", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ1Addition_nullInput() {
        MathQuestionService.q1Addition(null, "3");
    }


    @Test
    public void testQ2Subtraction_validInputs() {
        assertEquals(-1.0, MathQuestionService.q2Subtraction("2", "3"), DELTA);
        assertEquals(5.0, MathQuestionService.q2Subtraction("2", "-3"), DELTA);
        assertEquals(0.0, MathQuestionService.q2Subtraction("0", "0"), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_emptyInput1() {
        MathQuestionService.q2Subtraction("", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_nonNumericInput() {
        MathQuestionService.q2Subtraction("xyz", "5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_emptyInput2() {
        MathQuestionService.q2Subtraction("2", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_nullInput1() {
        MathQuestionService.q2Subtraction(null, "3");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_nullInput2() { 
        MathQuestionService.q2Subtraction("2", null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_bothNull() { 
        MathQuestionService.q2Subtraction(null, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_nonNumericInput2() { 
        MathQuestionService.q2Subtraction("2", "xyz");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_bothNonNumeric() {
        MathQuestionService.q2Subtraction("abc", "xyz");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQ2Subtraction_bothEmpty() {
        MathQuestionService.q2Subtraction(" ", " ");
    }
    

    @Test
    public void testQ3Multiplication_validInputs() {
        assertEquals(6.0, MathQuestionService.q3Multiplication("2", "3"), DELTA);
        assertEquals(-6.0, MathQuestionService.q3Multiplication("2", "-3"), DELTA);
        assertEquals(0.0, MathQuestionService.q3Multiplication("0", "5"), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_emptyInput1() {
        MathQuestionService.q3Multiplication("", "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_emptyInput2() {
        MathQuestionService.q3Multiplication("3", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_bothEmpty() {
        MathQuestionService.q3Multiplication(" ", " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_nullInput1() {
        MathQuestionService.q3Multiplication(null, "3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_nullInput2() {
        MathQuestionService.q3Multiplication("3", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_bothNull() {
        MathQuestionService.q3Multiplication(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_nonNumericInput1() {
        MathQuestionService.q3Multiplication("def", "7");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_nonNumericInput2() {
        MathQuestionService.q3Multiplication("7", "ghi");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQ3Multiplication_bothNonNumeric() {
        MathQuestionService.q3Multiplication("def", "ghi");
    }
}