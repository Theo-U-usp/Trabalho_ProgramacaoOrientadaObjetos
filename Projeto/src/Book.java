public class Book{



    private String name;
    private String author;
    private int year;
    private int ISBN;
    private String genre;
    private int copies;


    public Book(String name, String author, int year, int ISBN, String genre, int copies) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
        this.genre = genre;
        this.copies = copies;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
    
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    
    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
