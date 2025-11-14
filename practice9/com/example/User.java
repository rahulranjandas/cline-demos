package com.example;

import java.util.Objects;

/**
 * Represents a User in the system.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private UserStatus status;

    public enum UserStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }

    /**
     * Legacy constructor for backward compatibility.
     * Assigns status = ACTIVE and id = null by default.
     * @param name User's name
     * @param email User's email
     */
    public User(String name, String email) {
        this(null, name, email, UserStatus.ACTIVE);
    }

    /**
     * Full constructor with validation.
     * @param id User's id (nullable)
     * @param name User's name (not null, not blank)
     * @param email User's email (not null, valid format)
     * @param status User's status (not null, must be ACTIVE/INACTIVE/SUSPENDED)
     */
    public User(String id, String name, String email, UserStatus status) {
        setId(id);
        setName(name);
        setEmail(email);
        setStatus(status);
    }

    public String getId() { return id; }

    public void setId(String id) {
        // id can be null or non-blank
        if (id != null && id.isBlank()) throw new IllegalArgumentException("User id cannot be blank");
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be null or blank");
        }
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public UserStatus getStatus() { return status; }

    public void setStatus(UserStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        switch (status) {
            case ACTIVE, INACTIVE, SUSPENDED -> this.status = status;
            default -> throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    private boolean isValidEmail(String email) {
        // Simple validation; for demonstration only.
        return email.contains("@") && email.contains(".");
    }
}
