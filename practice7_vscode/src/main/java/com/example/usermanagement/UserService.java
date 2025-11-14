package com.example.usermanagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class UserService {
    // In-memory storage for users
    private final List<User> users = new ArrayList<>();
    // For O(1) duplicate email detection
    private final Set<String> emails = new HashSet<>();
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Registers a new user only if the email is not already used.
     * @param user the User to be registered (must not be null)
     * @throws UserRegistrationException if user is null or email already registered
     */
    public void registerUser(User user) throws UserRegistrationException {
        if (user == null) {
            throw new UserRegistrationException("User cannot be null");
        }
        lock.lock();
        try {
            String email = user.getEmail();
            if (emails.contains(email)) {
                throw new UserRegistrationException("Email already registered: " + email);
            }
            users.add(new User(user)); // Store a copy defensively
            emails.add(email);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves all registered users as copies to prevent external mutation.
     * @return a List of User copies (never original references)
     */
    public List<User> getAllUsers() {
        lock.lock();
        try {
            List<User> copies = new ArrayList<>(users.size());
            for (User u : users) {
                copies.add(new User(u)); // Return copies
            }
            return copies;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Finds a user by their email, case-sensitive.
     * @param email Email address to search for
     * @return User defensive copy if found, null otherwise
     */
    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        lock.lock();
        try {
            return users.stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .map(User::new)
                .orElse(null);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Searches for users by name (case-insensitive, partial match). Returns all users whose names contain the substring.
     * @param name Name substring to search for (case-insensitive)
     * @return List of User copies whose names match; never null
     */
    public List<User> searchUsersByName(String name) {
        if (name == null) {
            return new ArrayList<>();
        }
        final String lowerName = name.toLowerCase();
        lock.lock();
        try {
            return users.stream()
                .filter(u -> u.getName() != null && u.getName().toLowerCase().contains(lowerName))
                .map(User::new)
                .collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
    }
}
