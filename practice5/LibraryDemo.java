import java.util.List;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        // Add sample Books
        try {
            library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2));
            library.addBook(new Book("978-0596009205", "Head First Java", "Kathy Sierra", 3));
            library.addBook(new Book("978-0132350884", "Clean Code", "Robert C. Martin", 1));
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }

        // Register sample Members
        try {
            library.registerMember(new Member("M001", "Alice"));
            library.registerMember(new Member("M002", "Bob"));
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
        }

        // Successful borrow and return
        try {
            System.out.println("Alice borrows 'Effective Java'");
            library.borrowBook("M001", "978-0134685991");
            System.out.println("Alice borrows 'Head First Java'");
            library.borrowBook("M001", "978-0596009205");
            System.out.println("Books borrowed by Alice: " + library.getMember("M001").getBorrowedBookIsbns());

            System.out.println("Alice returns 'Effective Java'");
            library.returnBook("M001", "978-0134685991");
            System.out.println("Books borrowed by Alice: " + library.getMember("M001").getBorrowedBookIsbns());
        } catch (Exception e) {
            System.out.println("Borrow/Return Error: " + e.getMessage());
        }

        // Test borrowing limit
        try {
            System.out.println("Testing borrowing limit:");
            library.borrowBook("M001", "978-0134685991");
            library.borrowBook("M001", "978-0132350884");
            // Attempt to borrow a fourth book
            library.addBook(new Book("111-1111111111", "Test Book", "Test Author", 1));
            library.borrowBook("M001", "111-1111111111");
        } catch (Exception e) {
            System.out.println("Expected Limit Error: " + e.getMessage());
        }

        // Try to borrow already borrowed book
        try {
            System.out.println("Trying to borrow already borrowed book:");
            library.borrowBook("M001", "978-0134685991");
        } catch (Exception e) {
            System.out.println("Expected Duplicate Borrow Error: " + e.getMessage());
        }

        // Borrow with no available copies
        try {
            System.out.println("Bob borrows 'Clean Code'");
            library.borrowBook("M002", "978-0132350884");
            System.out.println("Bob tries to borrow 'Clean Code' again (should fail):");
            library.borrowBook("M001", "978-0132350884");
        } catch (Exception e) {
            System.out.println("Expected No Copies Error: " + e.getMessage());
        }

        // Return a book not borrowed
        try {
            System.out.println("Alice tries to return book she hasn't borrowed:");
            library.returnBook("M001", "111-1111111111");
        } catch (Exception e) {
            System.out.println("Expected Return Error: " + e.getMessage());
        }

        // Try to borrow/return with invalid member or book
        try {
            library.borrowBook("M999", "978-0134685991");
        } catch (Exception e) {
            System.out.println("Expected Invalid Member Error: " + e.getMessage());
        }
        try {
            library.borrowBook("M001", "9999-9999999999");
        } catch (Exception e) {
            System.out.println("Expected Invalid Book Error: " + e.getMessage());
        }

        // Perform search
        System.out.println("\n=== Search Demo ===");
        List<Book> foundByTitle = library.searchByTitle("Java");
        System.out.println("Books with 'Java' in title:");
        for (Book b : foundByTitle) {
            System.out.println(b);
        }
        List<Book> foundByAuthor = library.searchByAuthor("Martin");
        System.out.println("Books by author containing 'Martin':");
        for (Book b : foundByAuthor) {
            System.out.println(b);
        }

        // Show all members & books
        System.out.println("\n=== All Members ===");
        for (Member m : library.getAllMembers()) {
            System.out.println(m);
        }
        System.out.println("=== All Books ===");
        for (Book b : library.getAllBooks()) {
            System.out.println(b);
        }
    }
}
