import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class BookManagement {

    public void AddBook(ArrayList<Book> bookslist, Book book){
        

        Iterator<Book> it = bookslist.iterator();

        while (it.hasNext()) {
            Book book2 = it.next();

            if (book2.getName().equalsIgnoreCase(book.getName())) {
                book2.addcopies(book.getcopies());
                book2.getcopies();
   
                return;
            }
        }
        bookslist.add(book);

    }
    public void Edit(String comand, Book book){
        Scanner entrada = new Scanner(System.in);
        
        switch (comand.toUpperCase()) {
            case "NAME":
                System.out.println("Digite o novo nome: ");
                String name = entrada.next();
                book.setName(name);
                break;

            case "AUTHOR":
                System.out.println("Digite o novo autor: ");
                String author = entrada.next();
                book.setAuthor(author);
                break;

            case "ISBN":
                System.out.println("Digite a nova ISBN: ");
                int ISBN = entrada.nextInt();
                book.setISBN(ISBN);
                break;
            
            case "YEAR":
                System.out.println("Digite o novo ano: ");
                int year = entrada.nextInt();
                book.setYear(year);
                break;

            case "GENRE":
                System.out.println("Digite o novo genero: ");
                String genre = entrada.next();
                book.setGenre(genre);
                break;
            case "COPIES":
                System.out.println("Digite o novo numero de copias: ");
                int copies = entrada.nextInt();
                book.setCopies(copies);
                break;
            
            default:
                System.out.println("Opção inválida");
                break;
        }
        entrada.close();

    }
    public void Delete(ArrayList<Book> bookslist, Book book){
        Iterator<Book> it = bookslist.iterator();

        while (it.hasNext()) {
            Book livro = it.next();

            if (livro.getName().equalsIgnoreCase(book.getName())) {
                it.remove();
                break;
            }
        }
    }


    public void Search(String comand, ArrayList<Book> booklist){
            Scanner entrada = new Scanner(System.in);
            Iterator<Book> it = booklist.iterator();
            switch (comand.toUpperCase()) {
                case "NAME":
                    System.out.println("Digite o nome: ");
                    String name = entrada.next();
                    while (it.hasNext()) {
                        Book book = it.next();

                        if (book.getName().equalsIgnoreCase(name)) {
                            book.getName();
                            book.getAuthor();
                            book.getyear();
                            book.getgenre();
                            book.getcopies();
                            break;
                        }
                    }
                    break;

                case "AUTHOR":
                    System.out.println("Digite o novo autor: ");
                    String author = entrada.next();
                    while (it.hasNext()) {
                        Book book = it.next();

                        if (book.getAuthor().equalsIgnoreCase(author)) {
                            book.getName();
                            book.getAuthor();
                            book.getyear();
                            book.getgenre();
                            book.getcopies();
                            
                        }
                    }
                    break;

                case "ISBN":
                    System.out.println("Digite a nova ISBN: ");
                    int ISBN = entrada.nextInt();
                    while (it.hasNext()) {
                        Book book = it.next();

                        if (book.getISBN()== ISBN) {
                            book.getName();
                            book.getAuthor();
                            book.getyear();
                            book.getgenre();
                            book.getcopies();
                            break;
                        }
                    }
                    break;
                
                case "YEAR":
                    System.out.println("Digite o novo ano: ");
                    int year = entrada.nextInt();
                    while (it.hasNext()) {
                        Book book = it.next();

                        if (book.getyear() == year) {
                            book.getName();
                            book.getAuthor();
                            book.getyear();
                            book.getgenre();
                            book.getcopies();
                        
                        }
                    }
                    break;

                case "GENRE":
                    System.out.println("Digite o novo genero: ");
                    String genre = entrada.next();
                    while (it.hasNext()) {
                        Book book = it.next();

                        if (book.getgenre().equalsIgnoreCase(genre)) {
                            book.getName();
                            book.getAuthor();
                            book.getyear();
                            book.getgenre();
                            book.getcopies();
                            
                        }
                    }
                    break;
            
                
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            entrada.close();

    }
}