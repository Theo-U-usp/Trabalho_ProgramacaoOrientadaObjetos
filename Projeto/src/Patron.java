import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a library patron.
 *
 * <p>A patron has a name, ID, contact, borrowed books, loan history,
 * current borrowing status and total fine. In this project, ID is stored
 * as a String, but should contain only numeric digits.</p>
 */
public class Patron implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String ID;
    private String contact;
    private ArrayList<Book> BorrowedBooks;
    private int havebook;
    private float totalFine;
    private ArrayList<Loan> history;

    /**
     * Creates a new patron.
     *
     * @param name patron name
     * @param ID patron ID as a String
     * @param contact patron contact email
     * @param BorrowedBooks list of currently borrowed books
     * @param havebook 1 if the patron has a borrowed book, 0 otherwise
     * @param totalFine total accumulated fine
     * @param history loan history
     */
    public Patron(String name, String ID, String contact, ArrayList<Book> BorrowedBooks,
                  int havebook, float totalFine, ArrayList<Loan> history) {
        this.name = name;
        this.ID = ID;
        this.contact = contact;
        this.BorrowedBooks = BorrowedBooks;
        this.havebook = havebook;
        this.totalFine = totalFine;
        this.history = history;
    }

    /** @return patron name */
    public String getName() {
        return name;
    }

    /** @return patron ID */
    public String getID() {
        return ID;
    }

    /** @return patron contact */
    public String getContact() {
        return contact;
    }

    /** @return list of currently borrowed books */
    public ArrayList<Book> getBorrowedBooks() {
        return BorrowedBooks;
    }

    /** @return 1 if the patron currently has a borrowed book, 0 otherwise */
    public int getHaveBook() {
        return havebook;
    }

    /** @return total fine */
    public float getTotalFine() {
        return totalFine;
    }

    /** @return loan history */
    public ArrayList<Loan> getHistory() {
        return history;
    }

    /**
     * Sets patron name.
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets patron ID.
     *
     * @param ID new ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets patron contact.
     *
     * @param contact new contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Sets currently borrowed books.
     *
     * @param BorrowedBooks new borrowed books list
     */
    public void setBorrowedBooks(ArrayList<Book> BorrowedBooks) {
        this.BorrowedBooks = BorrowedBooks;
    }

    /**
     * Sets borrowing status.
     *
     * @param havebook 1 if the patron has a book, 0 otherwise
     */
    public void setHaveBook(int havebook) {
        this.havebook = havebook;
    }

    /**
     * Sets total fine.
     *
     * @param totalFine new total fine
     */
    public void setTotalFine(float totalFine) {
        this.totalFine = totalFine;
    }

    /**
     * Sets loan history.
     *
     * @param history new history list
     */
    public void setHistory(ArrayList<Loan> history) {
        this.history = history;
    }

    /**
     * Adds a loan to the patron history.
     *
     * @param loan loan to add
     */
    public void addLoanToHistory(Loan loan) {
        history.add(loan);
    }

    /**
     * Adds a book to the borrowed books list.
     *
     * @param book borrowed book
     */
    public void pegarLivro(Book book) {
        BorrowedBooks.add(book);
    }

    /**
     * Prints the patron book history in the console.
     */
    public void booksHistoric() {
        System.out.println("Histórico de livros de " + name + ":");

        for (Book book : BorrowedBooks) {
            System.out.println("- " + book.getName());
        }
    }

    /**
     * Returns a readable representation of the patron.
     *
     * @return patron name and ID
     */
    public String toString() {
        return getName() + " (ID: " + getID() + ")";
    }
}
