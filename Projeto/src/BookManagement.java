import java.util.ArrayList;
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
    public void EditString(Book book, String field, String newValue) {

        switch (field) {
            case "Name":
                book.setName(newValue);
                break;

            case "Author":
                book.setAuthor(newValue);
                break;

            case "Genre":
                book.setGenre(newValue);
                break;
        }
    }
    public void EditInt(Book book, String field, int newValue) {

        switch (field) {
            case "ISBN":
                book.setISBN(newValue);
                break;

            case "Year":
                book.setYear(newValue);
                break;

            case "Copies":
                book.setCopies(newValue);
                break;
        }
    }
    public void Delete(ArrayList<Book> bookslist, Book book){
        Iterator<Book> it = bookslist.iterator();

        while (it.hasNext()) {
            Book book2 = it.next();

            if (book2.getName().equalsIgnoreCase(book.getName())) {
                it.remove();
                break;
            }
        }
    }


    public ArrayList<Book> Search(String command, ArrayList<Book> bookList, String value) {

        ArrayList<Book> result = new ArrayList<>();

        for (Book book : bookList) {

            switch (command.toUpperCase()) {

                case "NAME":
                    if (book.getName()
                            .equalsIgnoreCase(value)) {
                        result.add(book);
                    }
                    break;

                case "AUTHOR":
                    if (book.getAuthor()
                            .equalsIgnoreCase(value)) {
                        result.add(book);
                    }
                    break;

                case "ISBN":
                    if (book.getISBN()
                            == Integer.parseInt(value)) {
                        result.add(book);
                    }
                    break;

                case "YEAR":
                    if (book.getyear()
                            == Integer.parseInt(value)) {
                        result.add(book);
                    }
                    break;

                case "GENRE":
                    if (book.getgenre()
                            .equalsIgnoreCase(value)) {
                        result.add(book);
                    }
                    break;
            }
        }

        return result;
    }
}