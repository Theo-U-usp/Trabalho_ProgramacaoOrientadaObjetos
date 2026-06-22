import java.util.ArrayList;
import java.util.Iterator;

/**
 * Provides management operations for books.
 *
 * <p>This class contains methods to add, edit, delete, search and validate
 * books. ISBN is treated as a String and validated by checking if every
 * character is a numeric digit.</p>
 */
public class BookManagement {

    /**
     * Adds a book to the list. If another book with the same name already
     * exists, the number of copies is increased instead of adding a duplicate.
     *
     * @param bookslist list of books
     * @param book book to add
     */
    public void AddBook(ArrayList<Book> bookslist, Book book) {
        Iterator<Book> it = bookslist.iterator();

        while (it.hasNext()) {
            Book book2 = it.next();

            if (book2.getName().equalsIgnoreCase(book.getName())) {
                book2.addcopies(book.getcopies());
                return;
            }
        }

        bookslist.add(book);
    }

    /**
     * Edits a String field of a book.
     *
     * @param book book to edit
     * @param field field name
     * @param newValue new String value
     */
    public void EditString(Book book, String field, String newValue) {
        switch (field) {
            case "Name":
                book.setName(newValue);
                break;

            case "Author":
                book.setAuthor(newValue);
                break;

            case "ISBN":
                book.setISBN(newValue);
                break;

            case "Genre":
                book.setGenre(newValue);
                break;
        }
    }

    /**
     * Edits an integer field of a book.
     *
     * @param book book to edit
     * @param field field name
     * @param newValue new integer value
     */
    public void EditInt(Book book, String field, int newValue) {
        switch (field) {
            case "Year":
                book.setYear(newValue);
                break;

            case "Copies":
                book.setCopies(newValue);
                break;
        }
    }

    /**
     * Deletes a book from the list.
     *
     * @param bookslist list of books
     * @param book book to delete
     */
    public void Delete(ArrayList<Book> bookslist, Book book) {
        Iterator<Book> it = bookslist.iterator();

        while (it.hasNext()) {
            Book book2 = it.next();

            if (book2.getISBN().equals(book.getISBN())) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Searches books by name, author, ISBN, year or genre.
     *
     * @param command search field
     * @param bookList list of books
     * @param value searched value
     * @return list of matching books
     */
    public ArrayList<Book> Search(String command, ArrayList<Book> bookList, String value) {
        ArrayList<Book> result = new ArrayList<>();

        if (value == null) {
            return result;
        }

        String searchedValue = value.trim();

        for (Book book : bookList) {
            switch (command.toUpperCase()) {
                case "NAME":
                    if (book.getName().equalsIgnoreCase(searchedValue)) {
                        result.add(book);
                    }
                    break;

                case "AUTHOR":
                    if (book.getAuthor().equalsIgnoreCase(searchedValue)) {
                        result.add(book);
                    }
                    break;

                case "ISBN":
                    if (book.getISBN().equalsIgnoreCase(searchedValue)) {
                        result.add(book);
                    }
                    break;

                case "YEAR":
                    try {
                        if (book.getyear() == Integer.parseInt(searchedValue)) {
                            result.add(book);
                        }
                    } catch (NumberFormatException e) {
                        return result;
                    }
                    break;

                case "GENRE":
                    if (book.getgenre().equalsIgnoreCase(searchedValue)) {
                        result.add(book);
                    }
                    break;
            }
        }

        return result;
    }

    /**
     * Checks whether a text is null or empty.
     *
     * @param value text to check
     * @return true if the text is null or empty
     */
    public boolean isEmptyInput(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Validates an ISBN.
     *
     * <p>The ISBN is stored as String, but every character must be a digit.</p>
     *
     * @param isbn ISBN to validate
     * @return true if the ISBN is not empty and contains only digits
     */
    public boolean isValidISBN(String isbn) {
        if (isEmptyInput(isbn)) {
            return false;
        }

        String cleanISBN = isbn.trim();

        for (int i = 0; i < cleanISBN.length(); i++) {
            if (!Character.isDigit(cleanISBN.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if an ISBN already exists in the book list.
     *
     * @param bookslist list of books
     * @param isbn ISBN to check
     * @return true if the ISBN already exists
     */
    public boolean isbnExists(ArrayList<Book> bookslist, String isbn) {
        return isbnExists(bookslist, isbn, null);
    }

    /**
     * Checks if an ISBN already exists, ignoring one book when necessary.
     *
     * <p>This is useful when editing a book, because the selected book should
     * be allowed to keep its own ISBN.</p>
     *
     * @param bookslist list of books
     * @param isbn ISBN to check
     * @param ignoredBook book ignored during the check
     * @return true if another book already has this ISBN
     */
    public boolean isbnExists(ArrayList<Book> bookslist, String isbn, Book ignoredBook) {
        if (isbn == null) {
            return false;
        }

        String cleanISBN = isbn.trim();

        for (Book book : bookslist) {
            if (book != ignoredBook && book.getISBN().equals(cleanISBN)) {
                return true;
            }
        }

        return false;
    }
}
