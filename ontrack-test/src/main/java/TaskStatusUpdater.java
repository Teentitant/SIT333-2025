import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskStatusUpdater {

    private static final Set<String> VALID_STATUSES = new HashSet<>(Arrays.asList(
            "SUBMITTED", "UNDER_REVIEW", "NEEDS_REVISION", "MARKED", "FEEDBACK_RELEASED"
    ));

    public String updateTaskStatus(String studentId, String taskId, String newStatus) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (taskId == null || taskId.trim().isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be null or empty.");
        }
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("New status cannot be null or empty.");
        }

        String upperCaseNewStatus = newStatus.trim().toUpperCase(); // Normalize status for checking

        if (!VALID_STATUSES.contains(upperCaseNewStatus)) {
            // Sort for consistent error message
            String validStatusesString = VALID_STATUSES.stream().sorted().collect(Collectors.joining(", "));
            throw new IllegalArgumentException(
                    String.format("Invalid status: %s. Valid statuses are: [%s]", newStatus, validStatusesString)
            );
        }

        System.out.printf("LOG: Status updated for student %s, task %s to %s%n", studentId, taskId, upperCaseNewStatus);
        return String.format("Status for task %s (student %s) updated to %s.", taskId, studentId, upperCaseNewStatus);
    }
}