package com.example;

public class TestUserService {
    public static void main(String[] args) {
        UserService service = new UserService();

        // Test 1: getUserInfo(null)
        try {
            System.out.println("Test 1: " + service.getUserInfo(null));
        } catch (Exception e) {
            System.out.println("Test 1 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 2: User creation with invalid email
        try {
            User user1 = new User("John", "invalid-email");
            System.out.println("Test 2: " + service.validateUserEmail(user1));
        } catch (Exception e) {
            System.out.println("Test 2 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 3: createUserReport with valid and null user
        try {
            User[] users = {new User("Alice", "alice@test.com"), null};
            System.out.println("Test 3: " + service.createUserReport(users));
        } catch (Exception e) {
            System.out.println("Test 3 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 4: createUserReport with null array
        try {
            User[] users = null;
            System.out.println("Test 4: " + service.createUserReport(users));
        } catch (Exception e) {
            System.out.println("Test 4 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 5: createUserReport with empty array
        try {
            User[] users = new User[0];
            System.out.println("Test 5: " + service.createUserReport(users));
        } catch (Exception e) {
            System.out.println("Test 5 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 6: Valid user info and email validation
        try {
            User u = new User("U007", "Bond", "bond@mi6.co.uk", User.UserStatus.ACTIVE);
            System.out.println("Test 6: " + service.getUserInfo(u));
            System.out.println("Test 6 (email valid?): " + service.validateUserEmail(u));
        } catch (Exception e) {
            System.out.println("Test 6 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 7: updateUserStatus to SUSPENDED
        try {
            User u = new User("U009", "Q", "q@mi6.co.uk", User.UserStatus.ACTIVE);
            service.updateUserStatus(u, User.UserStatus.SUSPENDED);
            System.out.println("Test 7: " + u.getName() + " new status: " + u.getStatus());
        } catch (Exception e) {
            System.out.println("Test 7 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 8: updateUserStatus invalid user (null)
        try {
            service.updateUserStatus(null, User.UserStatus.ACTIVE);
            System.out.println("Test 8: Unexpected success");
        } catch (Exception e) {
            System.out.println("Test 8 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 9: updateUserStatus invalid status (null)
        try {
            User u = new User("U010", "M", "m@mi6.co.uk", User.UserStatus.ACTIVE);
            service.updateUserStatus(u, null);
            System.out.println("Test 9: Unexpected success");
        } catch (Exception e) {
            System.out.println("Test 9 FAILED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Test 10: setStatus to an invalid string (reflection/hack, not possible directly) -- omitted
        System.out.println("Test 10: Impossible to set invalid status via API due to enum enforcement.");
    }
}
