import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Member {
    private final String memberId;
    private final String name;
    private final HashSet<String> borrowedBookIsbns;
    private static final int MAX_BORROWED = 3; // Max books a member can borrow

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBookIsbns = new HashSet<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Set<String> getBorrowedBookIsbns() {
        return Collections.unmodifiableSet(borrowedBookIsbns);
    }

    public boolean canBorrow() {
        return borrowedBookIsbns.size() < MAX_BORROWED;
    }

    public void borrowBook(String isbn) {
        if (borrowedBookIsbns.contains(isbn)) {
            throw new IllegalArgumentException("Book already borrowed by member: " + isbn);
        }
        if (!canBorrow()) {
            throw new IllegalStateException("Borrowing limit reached (" + MAX_BORROWED + " books)");
        }
        borrowedBookIsbns.add(isbn);
    }

    public void returnBook(String isbn) {
        if (!borrowedBookIsbns.contains(isbn)) {
            throw new IllegalArgumentException("Book not currently borrowed by member: " + isbn);
        }
        borrowedBookIsbns.remove(isbn);
    }

    @Override
    public String toString() {
        return String.format("Member[ID: %s, Name: %s, BorrowedBooks: %d]", memberId, name, borrowedBookIsbns.size());
    }
}
