import java.io.Serializable;
import java.util.ArrayList;

public class LibraryData implements Serializable {
    public ArrayList<Book> books;
    public ArrayList<Patron> patrons;
    public ArrayList<Loan> loans;

    public LibraryData(ArrayList<Book> books,
                       ArrayList<Patron> patrons,
                       ArrayList<Loan> loans) {
        this.books = books;
        this.patrons = patrons;
        this.loans = loans;
    }
}