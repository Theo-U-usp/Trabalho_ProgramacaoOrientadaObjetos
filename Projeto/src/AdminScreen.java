import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class AdminScreen extends JFrame {
    private JTextField searchField;
    private JRadioButton nameButton;
    private JRadioButton authorButton;
    private JRadioButton ISBNButton;
    private JRadioButton yearButton;
    private JRadioButton genreButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private JTextField searchFieldPatron;
    private JRadioButton nameButtonPatron;
    private JRadioButton IDButtonPatron;
    private JButton addButtonPatron;
    private JButton editButtonPatron;
    private JButton deleteButtonPatron;
    
    ArrayList<Book> booklist = new ArrayList<>();
    String[] columns = {"Name", "Author", "ISBN", "Year","Genre", "Avaible Copies"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

    ArrayList<Patron> patronlist = new ArrayList<>();
    String[] columnspatron = {"Name", "ID", "Contact"};
    DefaultTableModel tableModelpatron = new DefaultTableModel(columnspatron, 0);

    private void refreshTable() {

        tableModel.setRowCount(0);

        for (Book book : booklist) {

            tableModel.addRow(new Object[] {
                book.getName(),
                book.getAuthor(),
                book.getgenre(),
                book.getISBN(),
                book.getyear(),
                book.getcopies()
            });
        }
    }
    private void refreshTablePatron() {

        tableModelpatron.setRowCount(0);

        for (Patron patron : patronlist) {

            tableModelpatron.addRow(new Object[] {
                patron.getName(),
                patron.getID(),
                patron.getContact(),
                
            });
        }
    }
    public boolean isValidEmail(String email) {

        return email.matches(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    
    
    public AdminScreen() {
        setTitle(" Java Library");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        

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
            
            int ISBN;
            int year;
            int copies;
            String[] genres = {
                "Fiction",
                "Fantasy",
                "Science Fiction",
                "Mystery",
                "Thriller",
                "Horror",
                "Romance",
                "Adventure",
                "Historical Fiction",
                "Biography",
                "Poetry",
                "Education",
                "Technology"
            };

            while (true) {
                try {
                    ISBN = Integer.parseInt(
                        JOptionPane.showInputDialog("ISBN:")
                    );
                    if (bookManagement.isbnExists(booklist, ISBN)) {

                        JOptionPane.showMessageDialog(
                                this,
                                "ISBN already exists."
                        );

                        continue;
                    }

                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(
                        this,
                        "ISBN must be an integer."
                    );
                }
            }

            while (true) {
                try {
                    year = Integer.parseInt(
                        JOptionPane.showInputDialog("Year:")
                    );
                    if (year > 2026) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Year must be less than or equal to 2026."
                        );
                        continue;
                    }
                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Year must be an integer."
                    );
                }
            }

            

            String genre = (String) JOptionPane.showInputDialog(
            this,
            "Select a genre:",
            "Genre",
            JOptionPane.QUESTION_MESSAGE,
            null,
            genres,
            genres[0]
            );

            while (true) {
                try {
                    copies = Integer.parseInt(
                        JOptionPane.showInputDialog("Copies:")
                    );
                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Copies must be an integer."
                    );
                }
            }

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
            refreshTable();
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = booksTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a book first.");
                return;
            }
            Book selectedbook = booklist.get(selectedRow);
            bookManagement.Delete(booklist, selectedbook);
            tableModel.removeRow(selectedRow);
            refreshTable();
        });

        editButton.addActionListener(e -> {
            System.out.println("Edit button clicked");
            int selectedrow = booksTable.getSelectedRow();

            if (selectedrow == -1) {
                JOptionPane.showMessageDialog(this, "Select a book first.");
                return;
            }

            Book selectedBook = booklist.get(selectedrow);

            String[] options = {
                "Name",
                "Author",
                "Genre",
                "ISBN",
                "Year",
                "Copies"
            };

            String choice = (String) JOptionPane.showInputDialog(
                    this,
                    "Select what you want to edit:",
                    "Edit Book",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == null) {
                return;
            }

            if (choice.equals("Name") ||
                choice.equals("Author") ||
                choice.equals("Genre")) {

                String newValue = JOptionPane.showInputDialog(
                        this,
                        "New " + choice + ":"
                );

                if (newValue != null && !newValue.isEmpty()) {
                    bookManagement.EditString(selectedBook, choice, newValue);
                }

            } else {
                try {
                    int newValue = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "New " + choice + ":"
                            )
                    );
                    if (choice.equals("ISBN")) {

                        if (bookManagement.isbnExists(booklist, newValue)
                                && newValue != selectedBook.getISBN()) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "ISBN already exists."
                            );

                            return;
                        }
                    }

                    if (choice.equals("Year") && newValue > 2026) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Year must be less than or equal to 2026."
                        );
                        return;
                    }

                    bookManagement.EditInt(selectedBook, choice, newValue);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            choice + " must be an integer."
                    );
                }
            }

            refreshTable();
        });
        booksPanel.add(optionsPanel, BorderLayout.EAST);

        JPanel searchPanel = new JPanel();

        nameButton = new JRadioButton("Name");
        authorButton = new JRadioButton("Author");
        ISBNButton = new JRadioButton("ISBN");
        yearButton = new JRadioButton("Year");
        genreButton = new JRadioButton("Genre");

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(nameButton);
        searchGroup.add(authorButton);
        searchGroup.add(ISBNButton);
        searchGroup.add(yearButton);
        searchGroup.add(genreButton);


        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");


        booksPanel.add(searchPanel, BorderLayout.NORTH);



        
        searchButton.addActionListener(e -> {

            String command = "";

            if (nameButton.isSelected()) {
                command = "NAME";
            } else if (authorButton.isSelected()) {
                command = "AUTHOR";
            } else if (ISBNButton.isSelected()) {
                command = "ISBN";
            } else if (yearButton.isSelected()) {
                command = "YEAR";
            } else if (genreButton.isSelected()) {
                command = "GENRE";
            }

            String value = searchField.getText();

            ArrayList<Book> result = bookManagement.Search(
                    command,
                    booklist,
                    value
            );
            if (result.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No books found.",
                        "Search Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            tableModel.setRowCount(0);

            for (Book book : result) {
                tableModel.addRow(new Object[] {
                        book.getName(),
                        book.getAuthor(),
                        book.getgenre(),
                        book.getISBN(),
                        book.getyear(),
                        book.getcopies()
                });
            }
        });

        
        JButton clearSearchButton = new JButton("Clear Search");

        searchPanel.add(clearSearchButton);


        clearSearchButton.addActionListener(e -> {

            searchField.setText("");

            refreshTable();
        });

        searchPanel.add(nameButton);
        searchPanel.add(authorButton);
        searchPanel.add(ISBNButton);
        searchPanel.add(yearButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        booksPanel.add(searchPanel, BorderLayout.NORTH);

    


    //----------------------------------------------------------------------------------

                //Patron Tab


    //---------------------------------------------------------------------------------

    JTable patronsTable = new JTable(tableModelpatron);
        patronsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int rowpatron = booksTable.getSelectedRow();

        if (rowpatron != -1) {
            Patron selectedpatron = patronlist.get(rowpatron);

            System.out.println(selectedpatron.getName());
        }
        
        JScrollPane scrollPanepatron = new JScrollPane(patronsTable);
        patronsPanel.add(scrollPanepatron, BorderLayout.CENTER);

        JPanel optionsPanelpatron = new JPanel();

        addButtonPatron = new JButton("Add");
        editButtonPatron = new JButton("Edit");
        deleteButtonPatron = new JButton("Delete");
        optionsPanelpatron.add(addButtonPatron);
        optionsPanelpatron.add(editButtonPatron);
        optionsPanelpatron.add(deleteButtonPatron);

        PatronManagement patronManagement = new PatronManagement();

        addButtonPatron.addActionListener(e -> {

            String name = JOptionPane.showInputDialog(this, "Patron name:");
            
            int ID;

            String contact;

            

            ArrayList<Book> historic = new ArrayList<>();
            
            while (true) {
                try {
                    ID = Integer.parseInt(
                        JOptionPane.showInputDialog("ID:")
                    );
                    if (patronManagement.idExists(
                            patronlist,
                            ID)) {

                        JOptionPane.showMessageDialog(
                                this,
                                "ID already exists."
                        );

                        continue;
                    }
                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(
                        this,
                        "ID must be an integer."
                    );
                }
            }

            while (true) {

                contact = JOptionPane.showInputDialog(
                        this,
                        "Contact:"
                );

                if (contact.matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    break;
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid email format."
                );
            }

            Patron patron = new Patron(name, ID, contact, historic);

            patronManagement.AddPatron(patronlist, patron);

            tableModelpatron.addRow(new Object[]{
                patron.getName(),
                patron.getID(),
                patron.getContact(),
                patron.getBorrowedBooks()
                       
            });
            refreshTablePatron();
        });

        deleteButtonPatron.addActionListener(e -> {
            int selectedRowPatron = patronsTable.getSelectedRow();

            if (selectedRowPatron == -1) {
                JOptionPane.showMessageDialog(this, "Select a patron first.");
                return;
            }
            Patron selectedpatron = patronlist.get(selectedRowPatron);
            patronManagement.Delete(patronlist, selectedpatron);
            tableModelpatron.removeRow(selectedRowPatron);
            refreshTablePatron();
        });

        editButtonPatron.addActionListener(e -> {

            int selectedrowPatron = patronsTable.getSelectedRow();

            if (selectedrowPatron == -1) {
                JOptionPane.showMessageDialog(this, "Select a patron first.");
                return;
            }

            Patron selectedpatron = patronlist.get(selectedrowPatron);

            String[] optionspatron = {
                "Name",
                "ID",
                "Contact",
            };

            String choicepatron = (String) JOptionPane.showInputDialog(
                    this,
                    "Select what you want to edit:",
                    "Edit Patron",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionspatron,
                    optionspatron[0]
            );

            if (choicepatron == null) {
                return;
            }

            if (choicepatron.equals("Name")|| choicepatron.equals("Contact")){

                String newValuepatron = JOptionPane.showInputDialog(
                        this,
                        "New " + choicepatron + ":"
                );
                if(choicepatron.equals("Contact")){
                

                    while (true) {

                        if (isValidEmail(newValuepatron)) {
                            break;
                        }

                        JOptionPane.showMessageDialog(
                                this,
                                "Invalid email format."
                        );

                        newValuepatron = JOptionPane.showInputDialog(
                                this,
                                "New " + choicepatron + ":"
                        );
                    }

                }

                if (newValuepatron != null && !newValuepatron.isEmpty()) {
                    patronManagement.EditString(selectedpatron, choicepatron, newValuepatron);
                }

            } else {
                try {

                    
                    int newValuepatron = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "New " + choicepatron + ":"
                            )
                    );
                    if (patronManagement.idExists(
                            patronlist,
                            newValuepatron)
                            && newValuepatron != selectedpatron.getID()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "ID already exists."
                        );

                        return;
                    }

                

                    patronManagement.EditInt(selectedpatron, choicepatron, newValuepatron);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            choicepatron + " must be an integer."
                    );
                }
            }

            refreshTablePatron();
        });
        patronsPanel.add(optionsPanelpatron, BorderLayout.EAST);

        JPanel searchPanelPatron = new JPanel();

        nameButtonPatron = new JRadioButton("Name");
        IDButtonPatron = new JRadioButton("ID");
        
        ButtonGroup searchGroupPatron = new ButtonGroup();
        searchGroupPatron.add(nameButtonPatron);
        searchGroupPatron.add(IDButtonPatron);

        searchFieldPatron = new JTextField(20);
        JButton searchButtonPatron = new JButton("Search");

        
        



        
        searchButtonPatron.addActionListener(e -> {

            String command = "";

            if (nameButtonPatron.isSelected()) {
                command = "NAME";
            } else if (IDButtonPatron.isSelected()) {
                command = "ID";
            }

            String value = searchFieldPatron.getText();

            ArrayList<Patron> result = patronManagement.Search(
                    command,
                    patronlist,
                    value
            );
            if (result.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No patron found.",
                        "Search Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            tableModelpatron.setRowCount(0);

            for (Patron patron : result) {
                tableModelpatron.addRow(new Object[] {
                        patron.getName(),
                        patron.getID(),
                        patron.getContact(),
                        patron.getBorrowedBooks()

                });
            }
        });

        
        JButton clearSearchButtonPatron = new JButton("Clear Search");

        searchPanelPatron.add(clearSearchButtonPatron);


        clearSearchButtonPatron.addActionListener(e -> {

            searchFieldPatron.setText("");

            refreshTablePatron();
        });

        searchPanelPatron.add(nameButtonPatron);
        searchPanelPatron.add(IDButtonPatron);
        searchPanelPatron.add(searchFieldPatron);
        searchPanelPatron.add(searchButtonPatron);

        patronsPanel.add(searchPanelPatron, BorderLayout.NORTH);

    }
}
