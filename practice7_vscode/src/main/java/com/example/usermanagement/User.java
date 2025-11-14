package com.example.usermanagement;

public class User {
    private String name;
    private String email;

    /**
     * Constructs a User with validated name and email.
     * @param name User's name (non-null, non-empty, trimmed)
     * @param email User's email (non-null, non-empty, valid format)
     * @throws IllegalArgumentException if validation fails
     */
    public User(String name, String email) {
        setName(name);
        setEmail(email);
    }

    /**
     * Gets the user's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name after validation.
     * Trims leading and trailing whitespace.
     * @param name User's name (non-null, non-empty after trimming)
     * @throws IllegalArgumentException if invalid
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = trimmedName;
    }

    /**
     * Gets the user's email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email after validation.
     * @param email User's email (non-null, non-empty, valid format)
     * @throws IllegalArgumentException if invalid
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        this.email = email;
    }

    /**
     * Validates email format using a basic regex.
     * @param email The email to validate.
     * @return true if valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        // Simple RFC 5322-compliant regex; adjust as needed for your system.
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Copy constructor for User.
     * Creates a new User as a defensive copy of another instance.
     * @param other the User to copy
     */
    public User(User other) {
        this.name = other.name;
        this.email = other.email;
    }
}
