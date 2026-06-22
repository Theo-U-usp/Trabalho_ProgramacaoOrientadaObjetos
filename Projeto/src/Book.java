import java.io.Serializable;

/**
 * Represents a book in the library system.
 *
 * <p>Each book stores basic information such as name, author, year,
 * ISBN, genre and number of available copies. In this project, ISBN is
 * stored as a String, but it should contain only numeric digits.</p>
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;            

    private String name;
    private String author;
    private int year;
    private String ISBN;
    private String genre;
    private int copies;

    /**
     * Creates a new book.
     *
     * @param name book name
     * @param author book author
     * @param year publication year
     * @param ISBN book ISBN as a String
     * @param genre book genre
     * @param copies number of available copies
     */
    public Book(String name, String author, int year, String ISBN, String genre, int copies) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
        this.genre = genre;
        this.copies = copies;
    }

    /** @return book name */
    public String getName() {
        return name;
    }

    /** @return book author */
    public String getAuthor() {
        return author;
    }

    /** @return publication year */
    public int getyear() {
        return year;
    }

    /** @return book ISBN */
    public String getISBN() {
        return ISBN;
    }

    /** @return book genre */
    public String getgenre() {
        return genre;
    }

    /** @return number of available copies */
    public int getcopies() {
        return copies;
    }

    /**
     * Sets the book name.
     *
     * @param name new book name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the book author.
     *
     * @param author new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the number of available copies.
     *
     * @param copies new number of copies
     */
    public void setCopies(int copies) {
        this.copies = copies;
    }

    /**
     * Sets the book ISBN.
     *
     * @param ISBN new ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Sets the publication year.
     *
     * @param year new year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the book genre.
     *
     * @param genre new genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Adds copies to the current available amount.
     *
     * @param quant quantity to add
     */
    public void addcopies(int quant) {
        copies += quant;
    }

    /**
     * Returns a readable representation of the book.
     *
     * @return book name and author
     */
    public String toString() {
        return getName() + " - " + getAuthor();
    }
}
