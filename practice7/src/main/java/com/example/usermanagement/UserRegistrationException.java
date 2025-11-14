package com.example.usermanagement;

/**
 * Exception thrown when user registration fails due to invalid input or duplicate email.
 */
public class UserRegistrationException extends Exception {
    public UserRegistrationException(String message) {
        super(message);
    }
}
