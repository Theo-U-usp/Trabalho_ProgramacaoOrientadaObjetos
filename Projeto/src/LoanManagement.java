import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LoanManagement {
    private float fine;
    private LocalDate checkIndate;

    public LoanManagement(float fine, LocalDate checkIndate) {
        this.fine = fine;
        this.checkIndate = checkIndate;
    }

    public float getFine() {
        return fine;
    }

    public LocalDate getCheckIndate() {
        return checkIndate;
    }

    public long getOverdue(Loan loan) {
        long overdue = ChronoUnit.DAYS.between(loan.getDuedate(), checkIndate);
        return Math.max(0, overdue);
    }
}
