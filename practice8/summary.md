# UserService Defensive Programming Summary

## Overview

This document summarizes the defensive programming improvements applied to `UserService.java` to increase performance, safety, and robustness against null input or data inconsistencies, as per the requirements.

---

## Methods Updated

### 1. getUserInfo(User user)

- **Null Handling:**  
  Returns `"Error: User object cannot be null"` if the input `user` is `null`.  
  No exceptions are thrown for null values.
- **Field Validation:**  
  Checks for missing or blank user name/email.  
  Returns descriptive errors if the name or email are `null` or blank.
    - `"Error: User name is missing"`
    - `"Error: User email is missing"`
- **Return Value:**  
  If all fields are valid, returns  
  `"Name: <name>, Email: <email>"`
- **Defensive Principles:**  
  All error cases are handled by returning clear error messages, not exceptions.  
  Method signature is unchanged.

---

### 2. createUserReport(User[] users)

- **Null and Empty Array Handling:**  
  Returns `"Empty user list"` if the input array is `null` or has zero elements.
- **Safe Iteration:**  
  Iterates with `i < users.length`– avoids out-of-bounds errors.
- **Skipping Null Users:**  
  Null elements in the array are safely skipped.
- **Report Construction:**  
  For valid users, appends `User: <name>\n` to the report string.
  If all elements are null, result is `"Empty user list"`.
- **No Exceptions:**  
  Implementation ensures no `NullPointerException` or `ArrayIndexOutOfBoundsException` can occur.

---

## Usage Example

```java
UserService service = new UserService();
// Null user for getUserInfo
String info = service.getUserInfo(null); // Returns: Error: User object cannot be null

// User array with null and valid entries for createUserReport
User[] users = {null, new User("Alice", "alice@test.com")};
String report = service.createUserReport(users); // Returns: User: Alice\n

// Empty array for createUserReport
String empty = service.createUserReport(new User[0]); // Returns: Empty user list
```

---

## Constraints Addressed

- Robust to null and empty input
- Descriptive, non-exception error reporting
- Defensive programming principles adhered to throughout
- Existing method signatures maintained
