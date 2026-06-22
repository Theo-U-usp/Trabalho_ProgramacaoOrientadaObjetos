import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Provides loan-related calculations.
 *
 * <p>This class stores the fine value and check-in date used to calculate
 * overdue days for loans.</p>
 */
public class LoanManagement {

    private float fine;
    private LocalDate checkIndate;

    /**
     * Creates a loan management helper.
     *
     * @param fine fine value per overdue day
     * @param checkIndate check-in date
     */
    public LoanManagement(float fine, LocalDate checkIndate) {
        this.fine = fine;
        this.checkIndate = checkIndate;
    }

    /** @return fine value per overdue day */
    public float getFine() {
        return fine;
    }

    /** @return check-in date */
    public LocalDate getCheckIndate() {
        return checkIndate;
    }

    /**
     * Calculates how many days a loan is overdue.
     *
     * @param loan loan to check
     * @return number of overdue days, or 0 if not overdue
     */
    public long getOverdue(Loan loan) {
        long overdue = ChronoUnit.DAYS.between(loan.getDuedate(), checkIndate);
        return Math.max(0, overdue);
    }
}
