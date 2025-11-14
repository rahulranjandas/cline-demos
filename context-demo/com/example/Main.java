package com.example;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Valid users
        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", "bob123@domain.com");

        // Invalid user (invalid email: missing @)
        User invalidEmailUser = new User("Carol", "caroldomain.com");

        // Null user reference
        User nullUser = null;

        // Add users (including the invalid email user)
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(invalidEmailUser);

        // Demonstrate getUserInfo with valid users
        System.out.println("=== Valid user info ===");
        System.out.println("User 1: " + userService.getUserInfo(user1));
        System.out.println("User 2: " + userService.getUserInfo(user2));

        // Demonstrate getUserInfo with invalid email user
        System.out.println("\n=== Invalid user (bad email) ===");
        System.out.println("Invalid Email User: " + userService.getUserInfo(invalidEmailUser));

        // Demonstrate getUserInfo with null user
        System.out.println("\n=== Null user ===");
        System.out.println("Null User: " + userService.getUserInfo(nullUser));

        // Demonstrate retrieval by email
        System.out.println("\n=== Look up by email ===");
        User found = userService.getUserByEmail("alice@example.com");
        System.out.println("Found Alice: " + userService.getUserInfo(found));

        // Demonstrate update and delete
        System.out.println("\n=== Update user ===");
        boolean updated = userService.updateUser("bob123@domain.com", "Bobby", "bobby@example.com");
        System.out.println("Update Bob successful? " + updated);
        System.out.println("Updated Bob: " + userService.getUserInfo(userService.getUserByEmail("bobby@example.com")));

        System.out.println("\n=== Delete user ===");
        boolean deleted = userService.deleteUser("alice@example.com");
        System.out.println("Delete Alice successful? " + deleted);

        System.out.println("\n=== All current users ===");
        for (User user : userService.getAllUsers()) {
            System.out.println(userService.getUserInfo(user));
        }
    }
}
