import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assume;

import java.awt.GraphicsEnvironment;
import java.awt.Component;
import java.awt.Container;
import javax.swing.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Unit and simple Swing tests for the Java Library system.
 */
public class LibraryTests {

    @Test
    public void testCreateBook() {
        Book book = new Book("Dom Casmurro", "Machado de Assis", 1899, "123", "Fiction", 5);

        assertEquals("Dom Casmurro", book.getName());
        assertEquals("Machado de Assis", book.getAuthor());
        assertEquals(1899, book.getyear());
        assertEquals("123", book.getISBN());
        assertEquals("Fiction", book.getgenre());
        assertEquals(5, book.getcopies());
    }

    @Test
    public void testEditBookString() {
        Book book = new Book("Old", "Old Author", 2000, "1", "Fiction", 3);
        BookManagement manager = new BookManagement();

        manager.EditString(book, "Name", "New Name");
        manager.EditString(book, "Author", "New Author");
        manager.EditString(book, "Genre", "Fantasy");
        manager.EditString(book, "ISBN", "999");

        assertEquals("New Name", book.getName());
        assertEquals("New Author", book.getAuthor());
        assertEquals("Fantasy", book.getgenre());
        assertEquals("999", book.getISBN());
    }

    @Test
    public void testEditBookIntFields() {
        Book book = new Book("Book", "Author", 2000, "1", "Fiction", 3);
        BookManagement manager = new BookManagement();

        manager.EditInt(book, "Year", 2020);
        manager.EditInt(book, "Copies", 10);

        assertEquals(2020, book.getyear());
        assertEquals(10, book.getcopies());
    }

    @Test
    public void testAddBook() {
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book("Book", "Author", 2000, "1", "Fiction", 3);
        BookManagement manager = new BookManagement();

        manager.AddBook(books, book);

        assertEquals(1, books.size());
        assertTrue(books.contains(book));
    }

    @Test
    public void testAddBookWithSameNameAddsCopies() {
        ArrayList<Book> books = new ArrayList<>();
        BookManagement manager = new BookManagement();

        Book book1 = new Book("Book", "Author", 2000, "1", "Fiction", 3);
        Book book2 = new Book("Book", "Author", 2000, "2", "Fiction", 4);

        manager.AddBook(books, book1);
        manager.AddBook(books, book2);

        assertEquals(1, books.size());
        assertEquals(7, books.get(0).getcopies());
    }

    @Test
    public void testDeleteBook() {
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book("Book", "Author", 2000, "1", "Fiction", 3);
        BookManagement manager = new BookManagement();

        books.add(book);
        manager.Delete(books, book);

        assertEquals(0, books.size());
        assertFalse(books.contains(book));
    }

    @Test
    public void testIsbnValidation() {
        BookManagement manager = new BookManagement();

        assertTrue(manager.isValidISBN("123456"));
        assertFalse(manager.isValidISBN("123A56"));
        assertFalse(manager.isValidISBN(""));
        assertFalse(manager.isValidISBN(null));
    }

    @Test
    public void testIsbnExists() {
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book("Book", "Author", 2000, "123", "Fiction", 3);
        BookManagement manager = new BookManagement();

        books.add(book);

        assertTrue(manager.isbnExists(books, "123"));
        assertFalse(manager.isbnExists(books, "999"));
        assertFalse(manager.isbnExists(books, "123", book));
    }

    @Test
    public void testSearchBookByName() {
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book("Dom Casmurro", "Machado", 1899, "123", "Fiction", 5);
        BookManagement manager = new BookManagement();

        books.add(book);

        ArrayList<Book> result = manager.Search("NAME", books, "Dom Casmurro");

        assertEquals(1, result.size());
        assertEquals(book, result.get(0));
    }

    @Test
    public void testSearchBookByISBN() {
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book("Book", "Author", 2000, "123", "Fiction", 5);
        BookManagement manager = new BookManagement();

        books.add(book);

        ArrayList<Book> result = manager.Search("ISBN", books, "123");

        assertEquals(1, result.size());
        assertEquals(book, result.get(0));
    }

    @Test
    public void testSearchBookByAuthorYearGenreAndNotFound() {
        BookManagement manager = new BookManagement();
        ArrayList<Book> books = new ArrayList<>();

        Book book = new Book("Book A", "Author A", 2020, "111", "Fantasy", 3);
        books.add(book);

        assertEquals(1, manager.Search("AUTHOR", books, "Author A").size());
        assertEquals(1, manager.Search("YEAR", books, "2020").size());
        assertEquals(1, manager.Search("GENRE", books, "Fantasy").size());
        assertTrue(manager.Search("NAME", books, "Unknown").isEmpty());
        assertTrue(manager.Search("YEAR", books, "abc").isEmpty());
    }

    @Test
    public void testBookToStringUsesNameOrReadableText() {
        Book book = new Book("Book A", "Author A", 2020, "111", "Fiction", 3);

        assertNotNull(book.toString());
        assertTrue(book.toString().contains("Book A"));
    }

    @Test
    public void testCreatePatron() {
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        ArrayList<Loan> history = new ArrayList<>();

        Patron patron = new Patron(
                "Joao",
                "1",
                "joao@email.com",
                borrowedBooks,
                0,
                0,
                history
        );

        assertEquals("Joao", patron.getName());
        assertEquals("1", patron.getID());
        assertEquals("joao@email.com", patron.getContact());
        assertEquals(0, patron.getHaveBook());
        assertEquals(0, patron.getTotalFine(), 0.001);
    }

    @Test
    public void testAddPatron() {
        ArrayList<Patron> patrons = new ArrayList<>();
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());
        PatronManagement manager = new PatronManagement();

        manager.AddPatron(patrons, patron);

        assertEquals(1, patrons.size());
        assertTrue(patrons.contains(patron));
    }

    @Test
    public void testAddPatronWithSameIdDoesNotDuplicate() {
        ArrayList<Patron> patrons = new ArrayList<>();
        PatronManagement manager = new PatronManagement();

        Patron patron1 = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());
        Patron patron2 = new Patron("Maria", "1", "maria@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());

        manager.AddPatron(patrons, patron1);
        manager.AddPatron(patrons, patron2);

        assertEquals(1, patrons.size());
        assertEquals("Joao", patrons.get(0).getName());
    }

    @Test
    public void testDeletePatron() {
        ArrayList<Patron> patrons = new ArrayList<>();
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());
        PatronManagement manager = new PatronManagement();

        patrons.add(patron);
        manager.Delete(patrons, patron);

        assertEquals(0, patrons.size());
        assertFalse(patrons.contains(patron));
    }

    @Test
    public void testPatronIdValidation() {
        PatronManagement manager = new PatronManagement();

        assertTrue(manager.isValidPatronID("123456"));
        assertFalse(manager.isValidPatronID("123A56"));
        assertFalse(manager.isValidPatronID(""));
        assertFalse(manager.isValidPatronID(null));
    }

    @Test
    public void testIdExists() {
        ArrayList<Patron> patrons = new ArrayList<>();
        Patron patron = new Patron("Joao", "10", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());
        PatronManagement manager = new PatronManagement();

        patrons.add(patron);

        assertTrue(manager.idExists(patrons, "10"));
        assertFalse(manager.idExists(patrons, "99"));
        assertFalse(manager.idExists(patrons, "10", patron));
    }

    @Test
    public void testEditPatronString() {
        Patron patron = new Patron("Old", "1", "old@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());
        PatronManagement manager = new PatronManagement();

        manager.EditString(patron, "Name", "New Name");
        manager.EditString(patron, "Contact", "new@email.com");
        manager.EditString(patron, "ID", "99");

        assertEquals("New Name", patron.getName());
        assertEquals("new@email.com", patron.getContact());
        assertEquals("99", patron.getID());
    }

    @Test
    public void testSearchPatronByNameIdAndNotFound() {
        PatronManagement manager = new PatronManagement();
        ArrayList<Patron> patrons = new ArrayList<>();

        Patron patron = new Patron("Joao", "10", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());

        patrons.add(patron);

        assertEquals(1, manager.Search("NAME", patrons, "Joao").size());
        assertEquals(1, manager.Search("ID", patrons, "10").size());
        assertTrue(manager.Search("NAME", patrons, "Maria").isEmpty());
    }

    @Test
    public void testPatronHaveBookAndFine() {
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());

        patron.setHaveBook(1);
        patron.setTotalFine(12.5f);

        assertEquals(1, patron.getHaveBook());
        assertEquals(12.5f, patron.getTotalFine(), 0.01f);
    }

    @Test
    public void testPatronToStringUsesNameOrReadableText() {
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());

        assertNotNull(patron.toString());
        assertTrue(patron.toString().contains("Joao"));
    }

    @Test
    public void testAddLoanToHistory() {
        Book book = new Book("Book A", "Author A", 2020, "111", "Fiction", 3);

        Loan loan = new Loan(
                book,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 15)
        );

        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());

        patron.addLoanToHistory(loan);

        assertEquals(1, patron.getHistory().size());
        assertEquals(loan, patron.getHistory().get(0));
    }

    @Test
    public void testLoanCreation() {
        Book book = new Book("Book", "Author", 2020, "123", "Fiction", 3);

        LocalDate checkout = LocalDate.of(2026, 6, 1);
        LocalDate due = LocalDate.of(2026, 6, 15);

        Loan loan = new Loan(book, checkout, due);

        assertEquals(book, loan.getBook());
        assertEquals(checkout, loan.getCheckOutdate());
        assertEquals(due, loan.getDuedate());
        assertNull(loan.getReturnDate());
        assertEquals(0, loan.getFine(), 0.001);
    }

    @Test
    public void testLoanNotOverdue() {
        Book book = new Book("Book", "Author", 2020, "123", "Fiction", 3);

        Loan loan = new Loan(
                book,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 15)
        );

        LoanManagement manager = new LoanManagement(
                1.20f,
                LocalDate.of(2026, 6, 10)
        );

        assertEquals(0, manager.getOverdue(loan));
    }

    @Test
    public void testLoanOverdue() {
        Book book = new Book("Book", "Author", 2020, "123", "Fiction", 3);

        Loan loan = new Loan(
                book,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 15)
        );

        LoanManagement manager = new LoanManagement(
                1.20f,
                LocalDate.of(2026, 6, 20)
        );

        assertEquals(5, manager.getOverdue(loan));
    }

    @Test
    public void testLoanFineAndReturnDate() {
        Book book = new Book("Book A", "Author A", 2020, "111", "Fiction", 3);

        Loan loan = new Loan(
                book,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 15)
        );

        LocalDate returnDate = LocalDate.of(2026, 6, 20);

        loan.setReturnDate(returnDate);
        loan.setFine(6.0f);

        assertEquals(returnDate, loan.getReturnDate());
        assertEquals(6.0f, loan.getFine(), 0.01f);
    }

    @Test
    public void testCheckOutLogicManually() {
        Book book = new Book("Book", "Author", 2020, "123", "Fiction", 2);
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());

        LocalDate checkout = LocalDate.of(2026, 6, 1);
        LocalDate due = checkout.plusDays(14);

        Loan loan = new Loan(book, checkout, due);

        patron.getBorrowedBooks().add(book);
        patron.addLoanToHistory(loan);
        patron.setHaveBook(1);
        book.setCopies(book.getcopies() - 1);

        assertEquals(1, patron.getBorrowedBooks().size());
        assertEquals(1, patron.getHistory().size());
        assertEquals(1, patron.getHaveBook());
        assertEquals(1, book.getcopies());
    }

    @Test
    public void testCheckInLogicManually() {
        Book book = new Book("Book", "Author", 2020, "123", "Fiction", 1);
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 1, 0, new ArrayList<Loan>());

        LocalDate checkout = LocalDate.of(2026, 6, 1);
        LocalDate due = LocalDate.of(2026, 6, 15);
        LocalDate checkin = LocalDate.of(2026, 6, 20);

        Loan loan = new Loan(book, checkout, due);

        patron.getBorrowedBooks().add(book);
        patron.addLoanToHistory(loan);

        LoanManagement loanManagement = new LoanManagement(1.20f, checkin);

        long overdueDays = loanManagement.getOverdue(loan);
        float fine = overdueDays * 1.20f;

        loan.setReturnDate(checkin);
        loan.setFine(fine);

        patron.setTotalFine(patron.getTotalFine() + fine);
        patron.setHaveBook(0);
        patron.getBorrowedBooks().remove(book);
        book.setCopies(book.getcopies() + 1);

        assertEquals(5, overdueDays);
        assertEquals(6.0f, patron.getTotalFine(), 0.01f);
        assertEquals(0, patron.getHaveBook());
        assertTrue(patron.getBorrowedBooks().isEmpty());
        assertEquals(2, book.getcopies());
        assertEquals(checkin, loan.getReturnDate());
    }

    @Test
    public void testLibraryDataStoresLists() {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Patron> patrons = new ArrayList<>();
        ArrayList<Loan> loans = new ArrayList<>();

        Book book = new Book("Book", "Author", 2020, "123", "Fiction", 1);
        Patron patron = new Patron("Joao", "1", "joao@email.com",
                new ArrayList<Book>(), 0, 0, new ArrayList<Loan>());
        Loan loan = new Loan(book, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 15));

        books.add(book);
        patrons.add(patron);
        loans.add(loan);

        LibraryData data = new LibraryData(books, patrons, loans);

        assertEquals(1, data.books.size());
        assertEquals(1, data.patrons.size());
        assertEquals(1, data.loans.size());
    }

    @Test
    public void testLoginScreenOpens() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            LoginScreen screen = new LoginScreen();

            assertNotNull(screen);
            assertEquals("Library Login", screen.getTitle());

            screen.dispose();
        });
    }

    @Test
    public void testAdminScreenOpens() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            AdminScreen screen = new AdminScreen();

            assertNotNull(screen);
            assertTrue(screen.getTitle().contains("Library"));

            screen.dispose();
        });
    }

    @Test
    public void testLibrarianScreenOpens() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            LibrarianScreen screen = new LibrarianScreen();

            assertNotNull(screen);
            assertTrue(screen.getTitle().contains("Librarian"));

            screen.dispose();
        });
    }

    private JButton findButton(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;

                if (text.equals(button.getText())) {
                    return button;
                }
            }

            if (component instanceof Container) {
                JButton found = findButton((Container) component, text);

                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }

    private JRadioButton findRadioButton(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JRadioButton) {
                JRadioButton button = (JRadioButton) component;

                if (text.equals(button.getText())) {
                    return button;
                }
            }

            if (component instanceof Container) {
                JRadioButton found = findRadioButton((Container) component, text);

                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }

    @Test
    public void testLoginButtonsExist() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            LoginScreen screen = new LoginScreen();

            assertNotNull(findButton(screen, "Login"));
            assertNotNull(findRadioButton(screen, "Admin"));
            assertNotNull(findRadioButton(screen, "Librarian"));

            screen.dispose();
        });
    }

    @Test
    public void testAdminButtonsExist() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            AdminScreen screen = new AdminScreen();

            assertNotNull(findButton(screen, "Add"));
            assertNotNull(findButton(screen, "Edit"));
            assertNotNull(findButton(screen, "Delete"));
            assertNotNull(findButton(screen, "History"));
            assertNotNull(findButton(screen, "Check Out"));
            assertNotNull(findButton(screen, "Check In"));
            assertNotNull(findButton(screen, "Current Loans"));
            assertNotNull(findButton(screen, "Overdue Loans"));

            screen.dispose();
        });
    }

    @Test
    public void testLibrarianDoesNotHaveAdminCrudButtons() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            LibrarianScreen screen = new LibrarianScreen();

            assertNull(findButton(screen, "Add"));
            assertNull(findButton(screen, "Edit"));
            assertNull(findButton(screen, "Delete"));

            assertNotNull(findButton(screen, "History"));
            assertNotNull(findButton(screen, "Check Out"));
            assertNotNull(findButton(screen, "Check In"));
            assertNotNull(findButton(screen, "Current Loans"));
            assertNotNull(findButton(screen, "Overdue Loans"));

            screen.dispose();
        });
    }

    @Test
    public void testSearchButtonsExist() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            AdminScreen screen = new AdminScreen();

            assertNotNull(findButton(screen, "Search"));
            assertNotNull(findButton(screen, "Clear Search"));

            screen.dispose();
        });
    }

    @Test
    public void testRadioButtonsExistInAdminScreen() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        SwingUtilities.invokeAndWait(() -> {
            AdminScreen screen = new AdminScreen();

            assertNotNull(findRadioButton(screen, "Name"));
            assertNotNull(findRadioButton(screen, "Author"));
            assertNotNull(findRadioButton(screen, "ISBN"));
            assertNotNull(findRadioButton(screen, "Year"));
            assertNotNull(findRadioButton(screen, "Genre"));
            assertNotNull(findRadioButton(screen, "ID"));

            screen.dispose();
        });
    }
}
