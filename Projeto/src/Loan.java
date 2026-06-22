import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a loan in the library system.
 *
 * <p>A loan stores the borrowed book, checkout date, due date,
 * optional return date and fine calculated after check-in.</p>
 */
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    private Book book;
    private LocalDate checkOutdate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private float fine;

    /**
     * Creates a new loan.
     *
     * @param book borrowed book
     * @param checkOutdate checkout date
     * @param dueDate due date
     */
    public Loan(Book book, LocalDate checkOutdate, LocalDate dueDate) {
        this.book = book;
        this.checkOutdate = checkOutdate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fine = 0;
    }

    /** @return borrowed book */
    public Book getBook() {
        return book;
    }

    /** @return checkout date */
    public LocalDate getCheckOutdate() {
        return checkOutdate;
    }

    /** @return due date */
    public LocalDate getDuedate() {
        return dueDate;
    }

    /** @return return date, or null if the book has not been returned */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     *
     * @param returnDate return date
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /** @return loan fine */
    public float getFine() {
        return fine;
    }

    /**
     * Sets the loan fine.
     *
     * @param fine calculated fine
     */
    public void setFine(float fine) {
        this.fine = fine;
    }
}
