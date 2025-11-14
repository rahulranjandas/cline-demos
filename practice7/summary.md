# User Management System Documentation

## Overview

This project is a simple, thread-safe, in-memory user management system implemented in Java 11. It demonstrates robust data integrity, validation, error handling, and flexible search capabilities, suitable as a template for business logic in a broader application.

---

## Classes

### 1. `User`

- **Fields:** `name`, `email` (both `String`)
- **Validation:**  
  - Name is required, trimmed, and cannot be empty or null.
  - Email is required, must be in a valid format, and cannot be empty or null.
  - Violations throw `IllegalArgumentException`.
- **Immutability:** Defensive copies are used when returned by service methods.

### 2. `UserService`

- **Storage:** Uses an in-memory `ArrayList<User>` for users and a `HashSet<String>` for fast, case-insensitive duplicate-email detection.
- **Thread Safety:** All operations are protected by a private lock object and only expose defensive copies of data.
- **Registration (`registerUser`):**
  - Registers a new user if valid.
  - Throws checked `UserRegistrationException` for null user or duplicate email addresses (case-insensitive).
  - Stores defensive copies to prevent external modification.
- **Query Methods:**
  - `getAllUsers()`: Returns a list of all registered users.
  - `findUserByEmail(String email)`: Returns a copy of the user matching email (case-insensitive), or `null` if not found.
  - `searchUsersByName(String namePart)`: Returns a list of users whose names contain the search string (case-insensitive, partial match, never returns null).
- **Error Handling:** 
  - Registration errors provide detailed exception messages.
  - Query operations are null-safe.

### 3. `UserRegistrationException`
- A checked exception indicating registration failure due to invalid user or duplicate email.

---

## Example Usage: Main Class

See `src/main/java/com/example/usermanagement/Main.java` for a comprehensive demo. Scenarios covered:

- Registering users (success and error cases)
- Handling duplicate emails
- Handling null or invalid users
- Retrieving all users
- Searching users by email and partial name
- All operations use try-catch blocks for exception handling and print clear output to the console.

---

## Key Features

- **Data Integrity:** No duplicate emails, all input validated.
- **Thread Safety:** All mutating and query operations are thread-safe.
- **Robust Error Handling:** Detailed exceptions for invalid operations.
- **Flexible Query:** Supports both exact (email) and partial (name) case-insensitive searches.

---

## Running the Demo

To run the demonstration:

1. Compile the project as a standard Maven Java application.
2. Run the `Main` class:
   ```
   java -cp target/classes com.example.usermanagement.Main
   ```
3. Output will be shown in the console, illustrating all system features.

---
