import java.time.LocalDate;

public class Loan {
    private Book book;
    private LocalDate checkOutdate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private float fine;

    public Loan(Book book, LocalDate checkOutdate, LocalDate dueDate) {
        this.book = book;
        this.checkOutdate = checkOutdate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fine = 0;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getCheckOutdate() {
        return checkOutdate;
    }

    public LocalDate getDuedate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }
}
