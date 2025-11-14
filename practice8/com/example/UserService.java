
package com.example;

public class UserService {
    public String getUserInfo(User user) {
        if (user == null) {
            return "Error: User object cannot be null";
        }
        String name = user.getName();
        String email = user.getEmail();
        if (name == null || name.isBlank()) {
            return "Error: User name is missing";
        }
        if (email == null || email.isBlank()) {
            return "Error: User email is missing";
        }
        return "Name: " + name + ", Email: " + email;
    }
    
    public boolean validateUserEmail(User user) {
        // Bug 2: Potential NPE and logic error
        return user.getEmail().contains("@") && user.getEmail().contains(".");
    }
    
    public String createUserReport(User[] users) {
        if (users == null || users.length == 0) {
            return "Empty user list";
        }
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if (user == null) {
                continue;
            }
            String name = user.getName();
            report.append("User: ").append(name).append("\n");
        }
        if (report.length() == 0) {
            return "Empty user list";
        }
        return report.toString();
    }
}
