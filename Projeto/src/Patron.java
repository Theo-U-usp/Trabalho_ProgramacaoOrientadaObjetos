import java.util.ArrayList;

public class Patron{


    private String name;
    private int ID;
    private String contact;
    private ArrayList<Book> BorrowedBooks;

    public Patron(String name, int ID, String contact,  ArrayList<Book> BorrowedBooks) {
        this.name = name;
        this.ID = ID;
        this.contact = contact;
        this.BorrowedBooks = BorrowedBooks;
        
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