package com.example;

public class TestUserService {
    public static void main(String[] args) {
        UserService service = new UserService();
        
        // Test 1: NullPointerException
        try {
            System.out.println("Test 1: " + service.getUserInfo(null));
        } catch (Exception e) {
            System.out.println("Test 1 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        // Test 2: Email validation issues
        try {
            User user1 = new User("John", "invalid-email");
            System.out.println("Test 2: " + service.validateUserEmail(user1));
        } catch (Exception e) {
            System.out.println("Test 2 FAILED: " + e.getClass().getSimpleName());
        }
        
        // Test 3: Array index issues
        try {
            User[] users = {new User("Alice", "alice@test.com"), null};
            System.out.println("Test 3: " + service.createUserReport(users));
        } catch (Exception e) {
            System.out.println("Test 3 FAILED: " + e.getClass().getSimpleName());
        }
        
        // Test 4: null array
        try {
            User[] users = null;
            System.out.println("Test 4: " + service.createUserReport(users));
        } catch (Exception e) {
            System.out.println("Test 4 FAILED: " + e.getClass().getSimpleName());
        }

        // Test 5: empty array
        try {
            User[] users = new User[0];
            System.out.println("Test 5: " + service.createUserReport(users));
        } catch (Exception e) {
            System.out.println("Test 5 FAILED: " + e.getClass().getSimpleName());
        }
    }
}
