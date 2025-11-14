import java.util.*;

public class Library {
    private final HashMap<String, Book> catalog;
    private final HashMap<String, Member> members;

    public Library() {
        catalog = new HashMap<>();
        members = new HashMap<>();
    }

    public void addBook(Book book) {
        if (catalog.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists: " + book.getIsbn());
        }
        catalog.put(book.getIsbn(), book);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getMemberId())) {
            throw new IllegalArgumentException("Member with this ID already exists: " + member.getMemberId());
        }
        members.put(member.getMemberId(), member);
    }

    public void borrowBook(String memberId, String isbn) {
        Book book = catalog.get(isbn);
        if (book == null) {
            throw new NoSuchElementException("Book not found for ISBN: " + isbn);
        }
        Member member = members.get(memberId);
        if (member == null) {
            throw new NoSuchElementException("Member not found for ID: " + memberId);
        }
        if (!member.canBorrow()) {
            throw new IllegalStateException("Member has already borrowed maximum allowed books");
        }
        if (book.getAvailableCopies() == 0) {
            throw new IllegalStateException("No copies available for this book");
        }
        member.borrowBook(isbn);
        if (!book.borrow()) {
            // Should not occur - availableCopies was checked
            member.returnBook(isbn);
            throw new IllegalStateException("Unexpected: Unable to decrement available copies.");
        }
    }

    public void returnBook(String memberId, String isbn) {
        Book book = catalog.get(isbn);
        if (book == null) {
            throw new NoSuchElementException("Book not found for ISBN: " + isbn);
        }
        Member member = members.get(memberId);
        if (member == null) {
            throw new NoSuchElementException("Member not found for ID: " + memberId);
        }
        member.returnBook(isbn);
        if (!book.returned()) {
            // Should not occur - can't exceed total copies
            member.borrowBook(isbn);
            throw new IllegalStateException("Unexpected: More copies returned than total available.");
        }
    }

    public List<Book> searchByTitle(String titlePart) {
        List<Book> found = new ArrayList<>();
        String query = titlePart.toLowerCase();
        for (Book book : catalog.values()) {
            if (book.getTitle().toLowerCase().contains(query)) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> searchByAuthor(String authorPart) {
        List<Book> found = new ArrayList<>();
        String query = authorPart.toLowerCase();
        for (Book book : catalog.values()) {
            if (book.getAuthor().toLowerCase().contains(query)) {
                found.add(book);
            }
        }
        return found;
    }

    public Book getBook(String isbn) {
        return catalog.get(isbn);
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableCollection(catalog.values());
    }

    public Collection<Member> getAllMembers() {
        return Collections.unmodifiableCollection(members.values());
    }
}
