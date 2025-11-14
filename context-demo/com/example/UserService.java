package com.example;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUser(String email, String newName, String newEmail) {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setName(newName);
            user.setEmail(newEmail);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email) {
        return users.removeIf(user -> user.getEmail().equals(email));
    }

    private String validateUser(User user) {
        if (user == null) {
            return "User not found";
        }
        String email = user.getEmail();
        if (email == null || !email.contains("@")) {
            return "Invalid email address";
        }
        return null; // valid
    }

    public String getUserInfo(User user) {
        String validationError = validateUser(user);
        if (validationError != null) {
            return validationError;
        }
        return "Name: " + user.getName() + ", Email: " + user.getEmail();
    }
}
