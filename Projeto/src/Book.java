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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumeroCopias(int copies) {
        this.copies = copies;
    }
    
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setgenre(String genre) {
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
