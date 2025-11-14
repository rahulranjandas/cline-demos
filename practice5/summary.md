# Library Management System – Java Implementation

## Overview

This is a complete object-oriented library management solution in Java, managing book inventory and member operations using robust, efficient data structures (`HashMap`, `HashSet`). The design enforces business rules and provides comprehensive error handling.

---

## System Architecture

### Core Classes

1. **Book.java**
    - Models a library book.
    - Fields: ISBN, Title, Author, Total Copies, Available Copies.
    - Methods for borrowing and returning copies.

2. **Member.java**
    - Represents a library member.
    - Fields: Member ID, Name, Set of Borrowed Book ISBNs.
    - Uses a `HashSet` to prevent duplicate borrows and efficiently track up to 3 books.
    - Methods to borrow/return books with rule enforcement.

3. **Library.java**
    - Manages collections of books and members.
    - Uses:
        - `HashMap<String, Book>` for catalog (ISBN key).
        - `HashMap<String, Member>` for member registry.
    - Operations:
        - Add books and register members.
        - Borrow/return books (enforces availability and member book limit).
        - Search functions (by title, by author; case-insensitive substring match).
        - Exposes accessors for diagnostic/demo use.

4. **LibraryDemo.java**
    - Main demo/executable class.
    - Demonstrates:
        - Book addition and member registration
        - Book borrowing/returning, edge cases, and error handling
        - Business rule enforcement
        - Title/author search
        - Output of all members and books

---

## Key Features

- **Efficient Operations:** Catalog and members use `HashMap` for O(1) lookups; borrowed books use `HashSet`.
- **Rule Enforcement:** Each member may have a maximum of 3 borrowed books at a time.
- **Comprehensive Error Handling:** Runtime exceptions for invalid operations, clearly shown in demo output.
- **Search Capabilities:** Title and author substring search (case-insensitive).
- **Demonstration:** `LibraryDemo.java` gives full coverage of all required features.
- **No External Libraries:** Pure Java Standard Library.

---

## Usage

1. Compile all Java files in the directory:
   ```
   javac *.java
   ```
2. Run the demonstration:
   ```
   java LibraryDemo
   ```
   The terminal will print the result of each operation, clearly showing business rule applications and error cases.

---

## Example Operations

- Add books and members.
- Members borrow up to 3 unique books; further attempts denied.
- Return books and borrow again.
- Borrow attempts for unavailable books or duplicate borrows denied.
- Search by book title or author (flexible, partial, and case-insensitive).
- Robust exception messages facilitate easy debugging and testing.

---

## File Manifest

- `Book.java` – Book entity implementation
- `Member.java` – Member profile entity
- `Library.java` – Core management and logic
- `LibraryDemo.java` – Demo and test runner for all features
- `summary.md` – This documentation

---

## Notes

- All classes are in the default (unnamed) Java package for simplicity and ease of compilation in the working directory.
- The code is structured for easy expansion (e.g., new rules, multi-threaded safety, persistent storage).
