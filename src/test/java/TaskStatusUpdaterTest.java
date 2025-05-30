//package com.ontrack.example;
//for testing so I not using package. It could be replace with Ontrack but need a package from Ontrack.

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskStatusUpdaterTest {

    private TaskStatusUpdater updater;

    @Before 
    public void setUp() {
        updater = new TaskStatusUpdater();
    }

    @Test
    public void updateStatus_ValidInputMarked_ReturnsConfirmation() {
        String studentId = "S1001";
        String taskId = "T202";
        String newStatus = "MARKED";
        String expectedConfirmation = "Status for task T202 (student S1001) updated to MARKED.";
        String actualConfirmation = updater.updateTaskStatus(studentId, taskId, newStatus);
        assertEquals(expectedConfirmation, actualConfirmation);
    }

    	@Test
    public void updateStatus_ValidInputSubmittedLowercaseWithSpaces_ReturnsNormalizedConfirmation() {
        String studentId = "S1002";
        String taskId = "T203";
        String newStatusWithSpaces = "  submitted  ";
        // Expecting the status to be normalized to uppercase and trimmed
        String expectedConfirmation = "Status for task T203 (student S1002) updated to SUBMITTED.";
        String actualConfirmation = updater.updateTaskStatus(studentId, taskId, newStatusWithSpaces);
        assertEquals(expectedConfirmation, actualConfirmation);
    }

    
    @Test
    public void updateStatus_InvalidNewStatus_ThrowsIllegalArgumentExceptionWithMessage() {
        String invalidStatus = "COMPLETED_BY_MISTAKE";
        try {
            updater.updateTaskStatus("S1001", "T202", invalidStatus);
            fail("Expected an IllegalArgumentException to be thrown"); // Should not reach here
        } catch (IllegalArgumentException e) {
            assertTrue("Exception message should contain: Invalid status", e.getMessage().contains("Invalid status: " + invalidStatus));
            assertTrue("Exception message should contain: Valid statuses are", e.getMessage().contains("Valid statuses are:"));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateStatus_NullStudentId_ThrowsIllegalArgumentException() {
        updater.updateTaskStatus(null, "T202", "MARKED");
    }

    @Test
    public void updateStatus_EmptyStudentId_ThrowsIllegalArgumentExceptionWithMessage() {
        try {
            updater.updateTaskStatus("  ", "T202", "MARKED");
            fail("Expected an IllegalArgumentException for empty studentId");
        } catch (IllegalArgumentException e) {
            assertEquals("Student ID cannot be null or empty.", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateStatus_NullTaskId_ThrowsIllegalArgumentException() {
        updater.updateTaskStatus("S1001", null, "MARKED");
    }

     @Test
    public void updateStatus_EmptyTaskId_ThrowsIllegalArgumentExceptionWithMessage() {
        try {
            updater.updateTaskStatus("S1001", "\t", "MARKED");
            fail("Expected an IllegalArgumentException for empty taskId");
        } catch (IllegalArgumentException e) {
            assertEquals("Task ID cannot be null or empty.", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateStatus_NullNewStatus_ThrowsIllegalArgumentException() {
        updater.updateTaskStatus("S1001", "T202", null);
    }

    @Test
    public void updateStatus_EmptyNewStatus_ThrowsIllegalArgumentExceptionWithMessage() {
         try {
            updater.updateTaskStatus("S1001", "T202", " ");
            fail("Expected an IllegalArgumentException for empty newStatus");
        } catch (IllegalArgumentException e) {
            assertEquals("New status cannot be null or empty.", e.getMessage());
        }
    }
}