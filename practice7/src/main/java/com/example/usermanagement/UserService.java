package com.example.usermanagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {
    private final List<User> users = new ArrayList<>();
    private final Set<String> emails = new HashSet<>();
    private final Object lock = new Object();

    /**
     * Registers a user if valid. Thread-safe.
     * @param user The user to register
     * @throws UserRegistrationException if user is null or email already registered
     */
    public void registerUser(User user) throws UserRegistrationException {
        if (user == null) {
            throw new UserRegistrationException("User cannot be null");
        }
        String emailKey = user.getEmail().toLowerCase();
        synchronized (lock) {
            if (emails.contains(emailKey)) {
                throw new UserRegistrationException("Email already registered: " + user.getEmail());
            }
            users.add(new User(user.getName(), user.getEmail())); // store a defensive copy
            emails.add(emailKey);
        }
    }

    /**
     * Returns a list of copies of all registered users. Thread-safe.
     * @return a list of User objects (copies)
     */
    public List<User> getAllUsers() {
        synchronized (lock) {
            List<User> result = new ArrayList<>();
            for (User u : users) {
                result.add(new User(u.getName(), u.getEmail())); // copy each user
            }
            return result;
        }
    }

    /**
     * Finds a user by email (case-insensitive). Returns a defensive copy, or null if not found. Thread-safe.
     * @param email email to search (null-safe)
     * @return copy of found User, or null
     */
    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        String target = email.toLowerCase();
        synchronized (lock) {
            return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(target))
                .findFirst()
                .map(u -> new User(u.getName(), u.getEmail()))
                .orElse(null);
        }
    }

    /**
     * Searches users whose name contains the provided string (case-insensitive, partial match). Returns copies. Thread-safe.
     * @param namePart part of the name to search (null-safe)
     * @return list of User copies matching name substring
     */
    public List<User> searchUsersByName(String namePart) {
        if (namePart == null) {
            return new ArrayList<>();
        }
        String searchLower = namePart.toLowerCase();
        synchronized (lock) {
            return users.stream()
                .filter(u -> u.getName() != null && u.getName().toLowerCase().contains(searchLower))
                .map(u -> new User(u.getName(), u.getEmail()))
                .toList();
        }
    }
}
