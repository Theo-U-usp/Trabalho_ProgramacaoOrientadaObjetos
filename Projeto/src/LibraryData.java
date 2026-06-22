import java.io.Serializable;
import java.util.ArrayList;

/**
 * Serializable container for all library data.
 *
 * <p>This class is used by ObjectOutputStream and ObjectInputStream to save
 * and load books, patrons and active loans in a single file.</p>
 */
public class LibraryData implements Serializable {

    private static final long serialVersionUID = 1L;

    /** List of books in the system. */
    public ArrayList<Book> books;

    /** List of patrons in the system. */
    public ArrayList<Patron> patrons;

    /** List of active loans in the system. */
    public ArrayList<Loan> loans;

    /**
     * Creates a data container.
     *
     * @param books list of books
     * @param patrons list of patrons
     * @param loans list of active loans
     */
    public LibraryData(ArrayList<Book> books,
                       ArrayList<Patron> patrons,
                       ArrayList<Loan> loans) {
        this.books = books;
        this.patrons = patrons;
        this.loans = loans;
    }
}
