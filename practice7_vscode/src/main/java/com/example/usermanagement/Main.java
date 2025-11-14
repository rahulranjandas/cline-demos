package com.example.usermanagement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // 1. Register valid users
        try {
            userService.registerUser(new User("Alice", "alice@test.com"));
            System.out.println("Registered user: Alice (alice@test.com)");

            userService.registerUser(new User("Bob", "bob@test.com"));
            System.out.println("Registered user: Bob (bob@test.com)");

            userService.registerUser(new User("John Doe", "john@example.com"));
            System.out.println("Registered user: John Doe (john@example.com)");

            userService.registerUser(new User("john smith", "jsmith@example.com"));
            System.out.println("Registered user: john smith (jsmith@example.com)");
        } catch (UserRegistrationException ex) {
            System.out.println("Registration failed: " + ex.getMessage());
        }

        // 2. Display all registered users
        System.out.println("\nAll registered users:");
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(u -> System.out.println(" - " + u.getName() + " (" + u.getEmail() + ")"));

        // 3. Attempt to register duplicate email
        try {
            userService.registerUser(new User("Eve", "alice@test.com"));
            System.out.println("ERROR: Duplicate email registration should not succeed!");
        } catch (UserRegistrationException ex) {
            System.out.println("\nDuplicate registration error handled: " + ex.getMessage());
        }

        // 4. Attempt to register invalid user (empty name)
        try {
            userService.registerUser(new User("   ", "new@user.com"));
            System.out.println("ERROR: Invalid user should not be registered!");
        } catch (IllegalArgumentException | UserRegistrationException ex) {
            System.out.println("\nInvalid registration error handled: " + ex.getMessage());
        }

        // 5. Attempt to register invalid user (null email)
        try {
            userService.registerUser(new User("Null Email", null));
            System.out.println("ERROR: Invalid user should not be registered!");
        } catch (IllegalArgumentException | UserRegistrationException ex) {
            System.out.println("\nInvalid registration error handled: " + ex.getMessage());
        }

        // 6. Search users by exact email
        String searchEmail = "john@example.com";
        User found = userService.findUserByEmail(searchEmail);
        System.out.println("\nSearch by email: " + searchEmail);
        if (found != null) {
            System.out.println("Found: " + found.getName() + " (" + found.getEmail() + ")");
        } else {
            System.out.println("No user found with email: " + searchEmail);
        }

        // 7. Search users by partial/case-insensitive name
        String partialName = "john";
        List<User> results = userService.searchUsersByName(partialName);
        System.out.println("\nSearch users by name containing \"" + partialName + "\":");
        if (results.isEmpty()) {
            System.out.println("No users found with name containing \"" + partialName + "\"");
        } else {
            results.forEach(u -> System.out.println("Matched: " + u.getName() + " (" + u.getEmail() + ")"));
        }
    }
}
