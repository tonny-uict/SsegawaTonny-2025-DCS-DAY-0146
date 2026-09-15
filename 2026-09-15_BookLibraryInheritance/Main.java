class Library {
    String libraryName;
    String location;
    Library(String libraryName, String location) {
        this.libraryName = libraryName;
        this.location = location;
    }
    void displayInfo() {
        System.out.println("Library: " + libraryName + " | Location: " + location);
    }
}

class Book extends Library {
    String title;
    String author;
    String isbn;
    boolean isBorrowed;

    Book(String title, String author, String isbn, String libraryName, String location) {
        super(libraryName, location);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    void borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("Borrowed: '" + title + "' by " + author + " [ISBN: " + isbn + "] from " + libraryName);
        } else {
            System.out.println("Book '" + title + "' is already borrowed");
        }
    }

    void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("Returned: '" + title + "' to " + libraryName);
        } else {
            System.out.println("Book '" + title + "' was not borrowed");
        }
    }

    void displayBook() {
        displayInfo();
        System.out.println("  Book: " + title + " | Author: " + author + " | ISBN: " + isbn + " | Borrowed: " + isBorrowed);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Student: Ssegawa Tonny - 2025/DCS/DAY/0146 | Assignment: Book extends Library");
        Book b1 = new Book("OOP in Java", "Ssegawa Tonny", "ISBN-0146", "UICT Library", "Kampala");
        Book b2 = new Book("Data Structures", "Musa Ali", "ISBN-0147", "UICT Library", "Kampala");
        b1.displayBook();
        b1.borrow();
        b1.borrow();
        b1.returnBook();
        b2.borrow();
        b2.displayBook();
    }
}
