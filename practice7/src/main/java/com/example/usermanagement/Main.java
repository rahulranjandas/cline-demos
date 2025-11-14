package com.example.usermanagement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        System.out.println("=== User Registration Demo ===");
        try {
            User alice = new User("Alice Johnson", "alice@test.com");
            userService.registerUser(alice);
            System.out.println("Successfully registered: " + alice.getName());

            User bob = new User("Bob Smith", "bob@example.com");
            userService.registerUser(bob);
            System.out.println("Successfully registered: " + bob.getName());
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }

        System.out.println("\n=== Duplicate Email Registration ===");
        try {
            User duplicate = new User("Alicia", "alice@test.com");
            userService.registerUser(duplicate);
        } catch (UserRegistrationException e) {
            System.out.println("Caught expected duplicate exception: " + e.getMessage());
        }

        System.out.println("\n=== Invalid User Registration ===");
        try {
            userService.registerUser(null);
        } catch (UserRegistrationException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            userService.registerUser(new User("", "invalid-email"));
        } catch (IllegalArgumentException | UserRegistrationException e) {
            System.out.println("Caught expected invalid input exception: " + e.getMessage());
        }

        try {
            userService.registerUser(new User("   ", "   "));
        } catch (IllegalArgumentException | UserRegistrationException e) {
            System.out.println("Caught expected invalid input exception: " + e.getMessage());
        }

        System.out.println("\n=== Get All Users ===");
        List<User> allUsers = userService.getAllUsers();
        for (User u : allUsers) {
            System.out.println("User: " + u.getName() + " | Email: " + u.getEmail());
        }

        System.out.println("\n=== Find User By Email ===");
        User user1 = userService.findUserByEmail("bob@example.com");
        System.out.println(user1 != null ? "Found user: " + user1.getName() : "User not found");

        User user2 = userService.findUserByEmail("notfound@none.com");
        System.out.println(user2 != null ? "Found user: " + user2.getName() : "User not found");

        System.out.println("\n=== Search Users By Name (partial, case-insensitive) ===");
        List<User> found = userService.searchUsersByName("ali");
        if (found.isEmpty()) {
            System.out.println("No results for search 'ali'");
        } else {
            for (User u : found) {
                System.out.println("Match: " + u.getName() + " | " + u.getEmail());
            }
        }

        List<User> none = userService.searchUsersByName("xyz");
        System.out.println("Results for search 'xyz': " + none.size());
    }
}
