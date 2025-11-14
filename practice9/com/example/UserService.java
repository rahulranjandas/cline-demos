package com.example;

/**
 * Provides operations for managing User entities.
 */
public class UserService {
    /**
     * Returns a formatted string with the user's name and email.
     * @param user The user to describe
     * @return Info string
     * @throws IllegalArgumentException for null, blank, or invalid fields
     */
    public String getUserInfo(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }
        String name = user.getName();
        String email = user.getEmail();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name is missing");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("User email is missing");
        }
        return "Name: " + name + ", Email: " + email;
    }

    /**
     * Validates the email of the provided user.
     * @param user The user whose email to validate
     * @return true if valid, false otherwise
     * @throws IllegalArgumentException if user or email is null/blank
     */
    public boolean validateUserEmail(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        String email = user.getEmail();
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("User email cannot be null or blank");
        }
        return email.contains("@") && email.contains(".");
    }

    /**
     * Creates a report string for an array of users.
     * @param users Array of users
     * @return Multi-line user report
     * @throws IllegalArgumentException if input is null or empty
     */
    public String createUserReport(User[] users) {
        if (users == null || users.length == 0) {
            throw new IllegalArgumentException("Empty user list");
        }
        StringBuilder report = new StringBuilder();
        for (User user : users) {
            if (user == null) continue; // skip
            String name = user.getName();
            report.append("User: ").append(name).append("\n");
        }
        if (report.length() == 0) {
            throw new IllegalArgumentException("Empty user list");
        }
        return report.toString();
    }

    /**
     * Updates the status of the given user.
     * @param user The user object to update
     * @param newStatus The new status to assign (must be ACTIVE, INACTIVE, or SUSPENDED)
     * @throws IllegalArgumentException for invalid input
     */
    public void updateUserStatus(User user, User.UserStatus newStatus) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        // Status validation handled by setter in User class
        user.setStatus(newStatus);
    }
}
