package com.example.usermanagement;

/**
 * Exception thrown when user registration fails due to integrity constraints.
 */
public class UserRegistrationException extends Exception {
    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
