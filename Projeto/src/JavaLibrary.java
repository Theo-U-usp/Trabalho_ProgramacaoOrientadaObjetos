import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Iterator;


public class JavaLibrary {
}

public class Book{



    private String nome;
    private String autor;
    private int year;
    private int ISBN;
    private String genre;
    private int copies;


    public Book(String nome, String autor, int year, int ISBN, String genre, int copies) {
        this.nome = nome;
        this.autor = autor;
        this.year = year;
        this.ISBN = ISBN;
        this.genre = genre;
        this.copies = copies;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public int getyear() {
        return year;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getgenre() {
        return genre;
    }

    public int getcopies() {
        return copies;
    }

    public void addcopies(int quant) {
        copies += quant;
    }

    public void removecopies(int quant) {
        if (quant > 0 && quant <= copies) {
            copies -= quant;
        } else {
            System.out.println("Quantidade inválida.");
        }
    }


}

public class Patron{


    private String nome;
    private int ID;
    private String contact;
    private ArrayList<Book> BorrowedBooks;

    public Patron(String nome, int ID, String contact,  ArrayList<Book> BorrowedBooks) {
        this.nome = nome;
        this.ID = ID;
        this.contact = contact;
        this.BorrowedBooks = BorrowedBooks;
        
    }

    public String getNome() {
        return nome;
    }

    public int getAutor() {
        return ID;
    }
    public String contact() {
        return contact;
    }


    public void pegarLivro(Book book) {
        BorrowedBooks.add(book);
    }

    public void listarLivrosEmprestados() {
        System.out.println("Histórico de livros de " + nome + ":");

        for (Book book : BorrowedBooks) {
            System.out.println("- " + book.getNome());
        }
    }


    
}

public abstract class management{

    public abstract void Add();

    public abstract void Edit();

    public abstract void Delete();

    public abstract void Search();

}

public class BookManagement extends managment{

    public void Add(ArrayList<Book> bookslist, Book book){
        bookslist.add(book);
    }
    public void Edit(String comand){
        switch (comand.toUpperCase()) {
            case "NOME":
                System.out.println("Opção 1");
                break;

            case "AUTOR":
                System.out.println("Opção 2");
                break;

            case "ISBN":
                System.out.println("Opção 3");
                break;
            
            case "YEAR":
                System.out.println("Opção 3");
                break;

            case "GENRE":
                System.out.println("Opção 3");
                break;

            
            default:
                System.out.println("Opção inválida");
                break;
        }


    }
    public void Delete(ArrayList<Book> bookslist, Book book){
        Iterator<Book> it = bookslist.iterator();

        while (it.hasNext()) {
            Book livro = it.next();

            if (livro.getNome().equalsIgnoreCase(book.getNome())) {
                it.remove();
                break;
            }
        }
    }

}

public class PatronManagment{
    
}

public class LoanManagment{
    
}

public class Report{
    
}

public class TestBookManagment extends TestCase {

} 
public class TestPatronManagment extends TestCase {

} 
public class TestLoanManagment extends TestCase {

} 
public class TestReport extends TestCase {

} 