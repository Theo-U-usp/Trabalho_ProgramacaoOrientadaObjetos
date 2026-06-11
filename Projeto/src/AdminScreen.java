import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.awt.BorderLayout;

public class AdminScreen extends JFrame {
    private JTextField searchField;
    private JRadioButton nameButton;
    private JRadioButton authorButton;
    private JRadioButton ISBNButton;
    private JRadioButton yearButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    
    
    public AdminScreen() {
        setTitle(" Java Library");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ArrayList<Book> booklist = new ArrayList<>();

        JTabbedPane tabs = new JTabbedPane();

        JPanel booksPanel = new JPanel();
        booksPanel.add(new JLabel("Books"));

        JPanel patronsPanel = new JPanel();
        patronsPanel.add(new JLabel("Patrons"));

        JPanel loansPanel = new JPanel();
        loansPanel.add(new JLabel("Loans tab"));

        tabs.addTab("Books", booksPanel);
        tabs.addTab("Patrons", patronsPanel);
        tabs.addTab("Loans", loansPanel);

        add(tabs);

        String[] columns = {"Name", "Author", "ISBN", "Year","Genre", "Avaible Copies"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        Object[][] data = new Object[booklist.size()][6];

        for (int i = 0; i < booklist.size(); i++) {
            data[i][0] = booklist.get(i).getName();
            data[i][1] = booklist.get(i).getAuthor();
            data[i][2] = booklist.get(i).getISBN();
            data[i][3] = booklist.get(i).getyear();
            data[i][4] = booklist.get(i).getgenre();
            data[i][5] = booklist.get(i).getcopies();
        }
    

        JTable booksTable = new JTable(tableModel);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int row = booksTable.getSelectedRow();

        if (row != -1) {
            Book selectedbook = booklist.get(row);

            System.out.println(selectedbook.getName());
        }
        
        JScrollPane scrollPane = new JScrollPane(booksTable);
        booksPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        optionsPanel.add(addButton);
        optionsPanel.add(editButton);
        optionsPanel.add(deleteButton);

        BookManagement bookManagement = new BookManagement();

        addButton.addActionListener(e -> {

            String name = JOptionPane.showInputDialog(this, "Book name:");

            String author = JOptionPane.showInputDialog(this, "Author:");
            
            int ISBN = Integer.parseInt(JOptionPane.showInputDialog(this, "ISBN:"));
            
            int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Year:"));

            String genre = JOptionPane.showInputDialog(this, "Genre:");

            int copies = Integer.parseInt(JOptionPane.showInputDialog(this,"Number of copies:"));

            Book book = new Book(name, author,year, ISBN, genre, copies);

            bookManagement.AddBook(booklist, book);

            tableModel.addRow(new Object[]{
                book.getName(),
                book.getAuthor(),
                book.getISBN(),
                book.getyear(),
                book.getgenre(),
                book.getcopies()        
            });
        });

        booksPanel.add(optionsPanel, BorderLayout.EAST);

        JPanel searchPanel = new JPanel();

        nameButton = new JRadioButton("Name");
        authorButton = new JRadioButton("Author");
        ISBNButton = new JRadioButton("ISBN");
        yearButton = new JRadioButton("Year");



        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {

            String bookName = searchField.getText();

            for (Book book : booklist) {

                if (book.getName().equalsIgnoreCase(bookName)) {

                    JOptionPane.showMessageDialog(this,
                        "Book found!\n" +
                        "Name: " + book.getName() +
                        "\nAuthor: " + book.getAuthor());

                    return;
                }
            }

            JOptionPane.showMessageDialog(this,"Book not found.");
        });

        searchPanel.add(nameButton);
        searchPanel.add(authorButton);
        searchPanel.add(ISBNButton);
        searchPanel.add(yearButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        booksPanel.add(searchPanel, BorderLayout.NORTH);

    }
}
