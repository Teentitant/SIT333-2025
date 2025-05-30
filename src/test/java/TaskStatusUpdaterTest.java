//package com.ontrack.example;
//for testing so I not using package. It could be replace with Ontrack but need a package from Ontrack.

import org.junit.Test;
import static org.junit.Assert.*;

public class TaskStatusUpdaterTest {

    @Test
    public void updateStatus_ValidInput_ReturnsConfirmation() {
        TaskStatusUpdater updater = new TaskStatusUpdater(); 
        String studentId = "S1001";
        String taskId = "T202";
        String newStatus = "MARKED";
        String confirmation = updater.updateTaskStatus(studentId, taskId, newStatus); 
        assertEquals("Status for task T202 (student S1001) updated to MARKED.", confirmation);
    }
    
    @Test
    public void updateStatus_InvalidNewStatus_ThrowsIllegalArgumentExceptionWithMessage() {
        TaskStatusUpdater updater = new TaskStatusUpdater();
        String invalidStatus = "COMPLETED_BY_MISTAKE";
        try {
            updater.updateTaskStatus("S1001", "T202", invalidStatus);
            fail("Expected an IllegalArgumentException to be thrown for invalid status");
        } catch (IllegalArgumentException e) {
            String expectedMessagePart = "Invalid status: " + invalidStatus;
            assertTrue("Exception message should contain details about the invalid status.", e.getMessage().contains(expectedMessagePart));
            assertTrue("Exception message should list valid statuses.", e.getMessage().contains("Valid statuses are:"));
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateStatus_NullStudentId_ThrowsIllegalArgumentException() {
        TaskStatusUpdater updater = new TaskStatusUpdater();
        updater.updateTaskStatus(null, "T202", "MARKED");
    }

    // More robust version to check message:
    @Test
    public void updateStatus_NullStudentId_ThrowsExceptionWithMessage() {
        TaskStatusUpdater updater = new TaskStatusUpdater();
        try {
            updater.updateTaskStatus(null, "T202", "MARKED");
            fail("Expected an IllegalArgumentException for null studentId");
        } catch (IllegalArgumentException e) {
            assertEquals("Student ID cannot be null or empty.", e.getMessage());
        }
    }
}