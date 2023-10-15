package com.example.todo.app.Exceptions;

public class TaskException extends Exception {

    public TaskException(String errorMessage) {
        super(errorMessage);
    }

    public static class NotValidName extends TaskException {
        public NotValidName(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NotValidDescription extends TaskException {
        public NotValidDescription(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NotValidDuration extends TaskException {
        public NotValidDuration(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NotValidPriority extends TaskException {
        public NotValidPriority(String errorMessage) {
            super(errorMessage);
        }
    }

    // Add more specific exception classes as needed
}
