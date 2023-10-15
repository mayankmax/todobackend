package com.example.todo.app.Utils;

import com.example.todo.app.Models.Users;
import com.example.todo.app.Models.enums.TaskPriority;
import com.example.todo.app.Models.enums.TaskStatus;

import java.util.regex.Pattern;

public class TaskConstraints {

    public static String isValidTaskName(String taskName) {
        if (taskName.isEmpty()) {
            return "Task name is empty.";
        } else if (taskName.length() < 5 || taskName.length() > 100) {
            return "Task name must be between 5 and 100 characters long.";
        } else if (!taskName.matches(".*[a-zA-Z].*")) {
            return "Task name must contain at least one letter.";
        } else if (!taskName.matches("[a-zA-Z0-9\\s.]+")) {
            return "Task name must not contain special characters (other than spaces and periods).";
        }
        return null; // Indicates successful validation
    }

    public static String isValidTaskDescription(String taskDescription) {
        if (taskDescription.isEmpty()) {
            return "Task description is empty.";
        } else if (taskDescription.length() < 10 || taskDescription.length() > 500) {
            return "Task description must be between 10 and 500 characters long.";
        }
        else if (taskDescription.matches(".*(?:profanity1|profanity2|profanity3).*")) {
            return "Task description must not contain profanity.";
        }
        return null; // Indicates successful validation
    }

    public static String isValidTaskDuration(String taskDuration) {
        try {
            int duration = Integer.parseInt(taskDuration);
            if (duration <= 0) {
                return "Task duration must be a positive integer.";
            } else if (duration > 1000) {
                return "Task duration must be less than or equal to 1000 days.";
            }
        } catch (NumberFormatException e) {
            return "Task duration must be a positive integer.";
        }
        return null; // Indicates successful validation
    }

    public static String isValidTaskStatus(TaskStatus taskStatus) {
        if (taskStatus == null) {
            return "Task status is not valid.";
        }
        return null; // Indicates successful validation
    }

    public static String isValidTaskPriority(TaskPriority taskPriority) {
        if (taskPriority == null) {
            return "Task priority is not valid.";
        }
        return null; // Indicates successful validation
    }

    public static String isValidUser(Users user) {
        if (user == null) {
            return "User is not valid.";
        }
        return null; // Indicates successful validation
    }

    public static String isValidTaskDurationFormat(String taskDuration) {
        if (!Pattern.matches("\\d+\\s+(hours|days)", taskDuration)) {
            return "Task duration format is not valid.";
        }
        return null; // Indicates successful validation
    }
}
