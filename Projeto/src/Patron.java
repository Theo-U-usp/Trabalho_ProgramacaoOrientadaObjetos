import java.util.ArrayList;

public class Patron{


    private String name;
    private int ID;
    private String contact;
    private ArrayList<Book> BorrowedBooks;
    private int havebook;
    private float totalFine;
    private ArrayList<Loan> history;

    public Patron(String name, int ID, String contact,  ArrayList<Book> BorrowedBooks,
         int havebook, float totalFine, ArrayList<Loan> history) {
        this.name = name;
        this.ID = ID;
        this.contact = contact;
        this.BorrowedBooks = BorrowedBooks;
        this.havebook = havebook;
        this.totalFine = totalFine;
        this.history = new ArrayList<>();
        
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
    public String getContact() {
        return contact;
    }
    public ArrayList<Book> getBorrowedBooks() {
        return BorrowedBooks;
    }
    public int getHaveBook() {
        return havebook;
    }
    public float getTotalFine() {
        return totalFine;
    }
    public ArrayList<Loan> getHistory() {
        return history;
    }

    public void setName(String name){
          this.name = name;  

    }
    public void setID(int ID){
          this.ID = ID;  

    }
    public void setContact(String contact){
          this.contact = contact;  

    }
    public void setBorrowedBooks(ArrayList<Book> BorrowedBooks){
          this.BorrowedBooks = BorrowedBooks;  

    }
    public void setHaveBook(float totalFine){
        this.totalFine = totalFine;

    }

    public void setTotalFine(float totalFine){
        this.totalFine = totalFine;

    }
    public void setHistory(ArrayList<Loan> history) {
        this.history = history;
    }

    public void addLoanToHistory(Loan loan) {
        history.add(loan);
    }

    public void pegarLivro(Book book) {
        BorrowedBooks.add(book);
    }

    public void booksHistoric() {
        System.out.println("Histórico de livros de " + name + ":");

        for (Book book : BorrowedBooks) {
            System.out.println("- " + book.getName());
        }
    }


    
}