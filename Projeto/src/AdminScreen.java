// =========================
// AdminScreen.java
// =========================

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class AdminScreen extends JFrame {
    
    private static final String DATA_FILE = "library.dat";
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
    private JButton historyButtonPatron;

    private JButton checkoutButton;
    private JButton checkinButton;

    ArrayList<Book> booklist = new ArrayList<>();
    String[] columns = {"Name", "Author", "ISBN", "Year", "Genre", "Avaible Copies"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

    ArrayList<Patron> patronlist = new ArrayList<>();
    String[] columnspatron = {"Name", "ID", "Contact", "Have Book", "Total Fine"};
    DefaultTableModel tableModelpatron = new DefaultTableModel(columnspatron, 0);

    ArrayList<Loan> loanList = new ArrayList<>();

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Book book : booklist) {
            tableModel.addRow(new Object[] {
                book.getName(),
                book.getAuthor(),
                book.getISBN(),
                book.getyear(),
                book.getgenre(),
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
                patron.getHaveBook(),
                patron.getTotalFine()
            });
        }
    }
    private void saveData() {
        try {
            LibraryData data = new LibraryData(
                    booklist,
                    patronlist,
                    loanList
            );

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(DATA_FILE)
                    );

            output.writeObject(data);
            output.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error saving library data.",
                "Save Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadData() {
        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(DATA_FILE)
                    );

            LibraryData data = (LibraryData) input.readObject();

            input.close();

            booklist.clear();
            booklist.addAll(data.books);

            patronlist.clear();
            patronlist.addAll(data.patrons);

            loanList.clear();
            loanList.addAll(data.loans);

            refreshTable();
            refreshTablePatron();

        } catch (FileNotFoundException e) {
            // First execution: there is no saved file yet.
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error loading library data.",
                "Load Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void configureTable(JTable table) {
        table.setRowHeight(28);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
    }

    private JPanel createSidePanel(String title) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private JPanel createSearchPanel(String title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        return email.matches(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    private boolean isEmptyInput(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean confirmDelete(String message) {
        int answer = JOptionPane.showConfirmDialog(
            this,
            message,
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        return answer == JOptionPane.YES_OPTION;
    }

    public AdminScreen() {
        setTitle("Java Library");
        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu accountMenu = new JMenu("Account");

        JMenuItem logoutItem = new JMenuItem("Logout");

        logoutItem.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                new LoginScreen().setVisible(true);
                dispose();
            }
        });

        accountMenu.add(logoutItem);
        menuBar.add(accountMenu);

        setJMenuBar(menuBar);

        JTabbedPane tabs = new JTabbedPane();

        JPanel booksPanel = new JPanel(new BorderLayout(10, 10));
        booksPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel patronsPanel = new JPanel(new BorderLayout(10, 10));
        patronsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel loansPanel = new JPanel(new BorderLayout(10, 10));
        loansPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        tabs.addTab("Books", booksPanel);
        tabs.addTab("Patrons", patronsPanel);
        tabs.addTab("Loans", loansPanel);

        add(tabs);

        BookManagement bookManagement = new BookManagement();
        PatronManagement patronManagement = new PatronManagement();

        // =========================
        // Books tab
        // =========================

        JTable booksTable = new JTable(tableModel);
        configureTable(booksTable);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(booksTable);
        booksPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel optionsPanel = createSidePanel("Book Options");

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        optionsPanel.add(addButton);
        optionsPanel.add(editButton);
        optionsPanel.add(deleteButton);

        booksPanel.add(optionsPanel, BorderLayout.EAST);

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Book name:");
            if (name == null) {
                return;
            }
            if (isEmptyInput(name)) {
                JOptionPane.showMessageDialog(this, "Book name cannot be empty.");
                return;
            }

            String author = JOptionPane.showInputDialog(this, "Author:");
            if (author == null) {
                return;
            }
            if (isEmptyInput(author)) {
                JOptionPane.showMessageDialog(this, "Author cannot be empty.");
                return;
            }

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
                String input = JOptionPane.showInputDialog(this, "ISBN:");
                if (input == null) {
                    return;
                }

                try {
                    ISBN = Integer.parseInt(input);

                    if (ISBN <= 0) {
                        JOptionPane.showMessageDialog(this, "ISBN must be positive.");
                        continue;
                    }

                    if (bookManagement.isbnExists(booklist, ISBN)) {
                        JOptionPane.showMessageDialog(this, "ISBN already exists.");
                        continue;
                    }

                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(this, "ISBN must be an integer.");
                }
            }

            while (true) {
                String input = JOptionPane.showInputDialog(this, "Year:");
                if (input == null) {
                    return;
                }

                try {
                    year = Integer.parseInt(input);

                    if (year <= 0) {
                        JOptionPane.showMessageDialog(this, "Year must be positive.");
                        continue;
                    }

                    if (year > 2026) {
                        JOptionPane.showMessageDialog(this, "Year must be less than or equal to 2026.");
                        continue;
                    }

                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(this, "Year must be an integer.");
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

            if (genre == null) {
                return;
            }

            while (true) {
                String input = JOptionPane.showInputDialog(this, "Copies:");
                if (input == null) {
                    return;
                }

                try {
                    copies = Integer.parseInt(input);

                    if (copies < 0) {
                        JOptionPane.showMessageDialog(this, "Copies cannot be negative.");
                        continue;
                    }

                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(this, "Copies must be an integer.");
                }
            }

            Book book = new Book(name, author, year, ISBN, genre, copies);

            bookManagement.AddBook(booklist, book);
            refreshTable();
            saveData();
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = booksTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a book first.");
                return;
            }

            Book selectedbook = booklist.get(selectedRow);

            if (!confirmDelete("Are you sure you want to delete this book?")) {
                return;
            }

            for (Loan loan : loanList) {
                if (loan.getBook() == selectedbook) {
                    JOptionPane.showMessageDialog(
                            this,
                            "This book cannot be deleted because it has an active loan."
                    );
                    return;
                }
            }
            bookManagement.Delete(booklist, selectedbook);

            refreshTable();
            saveData();
        });

        editButton.addActionListener(e -> {
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

            if (choice.equals("Name") || choice.equals("Author") || choice.equals("Genre")) {
                String newValue = JOptionPane.showInputDialog(this, "New " + choice + ":");

                if (newValue == null) {
                    return;
                }

                if (isEmptyInput(newValue)) {
                    JOptionPane.showMessageDialog(this, choice + " cannot be empty.");
                    return;
                }

                bookManagement.EditString(selectedBook, choice, newValue);
            } else {
                while (true) {
                    String input = JOptionPane.showInputDialog(this, "New " + choice + ":");

                    if (input == null) {
                        return;
                    }

                    try {
                        int newValue = Integer.parseInt(input);

                        if (choice.equals("ISBN")) {
                            if (newValue <= 0) {
                                JOptionPane.showMessageDialog(this, "ISBN must be positive.");
                                continue;
                            }

                            if (bookManagement.isbnExists(booklist, newValue)
                                    && newValue != selectedBook.getISBN()) {
                                JOptionPane.showMessageDialog(this, "ISBN already exists.");
                                continue;
                            }
                        }

                        if (choice.equals("Year") && newValue <= 0) {
                            JOptionPane.showMessageDialog(this, "Year must be positive.");
                            continue;
                        }

                        if (choice.equals("Year") && newValue > 2026) {
                            JOptionPane.showMessageDialog(this, "Year must be less than or equal to 2026.");
                            continue;
                        }

                        if (choice.equals("Copies") && newValue < 0) {
                            JOptionPane.showMessageDialog(this, "Copies cannot be negative.");
                            continue;
                        }

                        bookManagement.EditInt(selectedBook, choice, newValue);
                        break;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, choice + " must be an integer.");
                    }
                }
            }

            refreshTable();
            saveData();
        });

        JPanel searchPanel = createSearchPanel("Search Books");

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

        nameButton.setSelected(true);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton clearSearchButton = new JButton("Clear Search");

        searchPanel.add(clearSearchButton);
        searchPanel.add(nameButton);
        searchPanel.add(authorButton);
        searchPanel.add(ISBNButton);
        searchPanel.add(yearButton);
        searchPanel.add(genreButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

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

            if (isEmptyInput(value)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Search field cannot be empty.",
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (ISBNButton.isSelected() || yearButton.isSelected()) {
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Search value must be an integer.",
                        "Search Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }

            ArrayList<Book> result = bookManagement.Search(command, booklist, value);

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
                    book.getISBN(),
                    book.getyear(),
                    book.getgenre(),
                    book.getcopies()
                });
            }
        });

        clearSearchButton.addActionListener(e -> {
            searchField.setText("");
            refreshTable();
        });

        // =========================
        // Patrons tab
        // =========================

        JTable patronsTable = new JTable(tableModelpatron);
        configureTable(patronsTable);
        patronsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPanepatron = new JScrollPane(patronsTable);
        patronsPanel.add(scrollPanepatron, BorderLayout.CENTER);

        JPanel optionsPanelpatron = createSidePanel("Patron Options");

        addButtonPatron = new JButton("Add");
        editButtonPatron = new JButton("Edit");
        deleteButtonPatron = new JButton("Delete");
        historyButtonPatron = new JButton("History");

        optionsPanelpatron.add(addButtonPatron);
        optionsPanelpatron.add(editButtonPatron);
        optionsPanelpatron.add(deleteButtonPatron);
        optionsPanelpatron.add(historyButtonPatron);

        patronsPanel.add(optionsPanelpatron, BorderLayout.EAST);

        addButtonPatron.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Patron name:");
            if (name == null) {
                return;
            }
            if (isEmptyInput(name)) {
                JOptionPane.showMessageDialog(this, "Patron name cannot be empty.");
                return;
            }

            int ID;
            String contact;

            ArrayList<Book> historic = new ArrayList<>();
            ArrayList<Loan> loanHistory = new ArrayList<>();

            while (true) {
                String input = JOptionPane.showInputDialog(this, "ID:");
                if (input == null) {
                    return;
                }

                try {
                    ID = Integer.parseInt(input);

                    if (ID <= 0) {
                        JOptionPane.showMessageDialog(this, "ID must be positive.");
                        continue;
                    }

                    if (patronManagement.idExists(patronlist, ID)) {
                        JOptionPane.showMessageDialog(this, "ID already exists.");
                        continue;
                    }

                    break;
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(this, "ID must be an integer.");
                }
            }

            while (true) {
                contact = JOptionPane.showInputDialog(this, "Contact:");

                if (contact == null) {
                    return;
                }

                if (isEmptyInput(contact)) {
                    JOptionPane.showMessageDialog(this, "Contact cannot be empty.");
                    continue;
                }

                if (isValidEmail(contact)) {
                    break;
                }

                JOptionPane.showMessageDialog(this, "Invalid email format.");
            }

            Patron patron = new Patron(name, ID, contact, historic, 0, 0,loanHistory);
            patron.setHistory(loanHistory);

            patronManagement.AddPatron(patronlist, patron);

            refreshTablePatron();
            saveData();
        });

        deleteButtonPatron.addActionListener(e -> {
            int selectedRowPatron = patronsTable.getSelectedRow();

            if (selectedRowPatron == -1) {
                JOptionPane.showMessageDialog(this, "Select a patron first.");
                return;
            }

            Patron selectedpatron = patronlist.get(selectedRowPatron);

            if (!confirmDelete("Are you sure you want to delete this patron?")) {
                return;
            }

            if (selectedpatron.getHaveBook() == 1 ||
                !selectedpatron.getBorrowedBooks().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "This patron cannot be deleted because they have an active loan."
                );

                return;
            }
            patronManagement.Delete(patronlist, selectedpatron);

            refreshTablePatron();
            saveData();
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
                "Total Fine"
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

            if (choicepatron.equals("Name") || choicepatron.equals("Contact")) {
                String newValuepatron = JOptionPane.showInputDialog(this, "New " + choicepatron + ":");

                if (newValuepatron == null) {
                    return;
                }

                if (choicepatron.equals("Contact")) {
                    while (true) {
                        if (newValuepatron == null) {
                            return;
                        }

                        if (isValidEmail(newValuepatron)) {
                            break;
                        }

                        JOptionPane.showMessageDialog(this, "Invalid email format.");

                        newValuepatron = JOptionPane.showInputDialog(this, "New " + choicepatron + ":");
                    }
                }

                if (isEmptyInput(newValuepatron)) {
                    JOptionPane.showMessageDialog(this, choicepatron + " cannot be empty.");
                    return;
                }

                patronManagement.EditString(selectedpatron, choicepatron, newValuepatron);

            } else if (choicepatron.equals("ID")) {
                while (true) {
                    String input = JOptionPane.showInputDialog(this, "New ID:");

                    if (input == null) {
                        return;
                    }

                    try {
                        int newValuepatron = Integer.parseInt(input);

                        if (newValuepatron <= 0) {
                            JOptionPane.showMessageDialog(this, "ID must be positive.");
                            continue;
                        }

                        if (patronManagement.idExists(patronlist, newValuepatron)
                                && newValuepatron != selectedpatron.getID()) {
                            JOptionPane.showMessageDialog(this, "ID already exists.");
                            continue;
                        }

                        patronManagement.EditInt(selectedpatron, choicepatron, newValuepatron);
                        break;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "ID must be an integer.");
                    }
                }

            } else if (choicepatron.equals("Total Fine")) {
                while (true) {
                    String input = JOptionPane.showInputDialog(this, "New Total Fine:");

                    if (input == null) {
                        return;
                    }

                    try {
                        float newFine = Float.parseFloat(input);

                        if (newFine < 0) {
                            JOptionPane.showMessageDialog(this, "Total Fine cannot be negative.");
                            continue;
                        }

                        selectedpatron.setTotalFine(newFine);
                        break;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Total Fine must be a number.");
                    }
                }
            }

            refreshTablePatron();
            saveData();
        });

        historyButtonPatron.addActionListener(e -> {
            int selectedRowPatron = patronsTable.getSelectedRow();

            if (selectedRowPatron == -1) {
                JOptionPane.showMessageDialog(this, "Select a patron first.");
                return;
            }

            Patron selectedPatron = patronlist.get(selectedRowPatron);

            String[] historyColumns = {
                "Book",
                "Borrowed",
                "Due",
                "Returned",
                "Fine"
            };

            ArrayList<Loan> history = selectedPatron.getHistory();

            Object[][] data = new Object[history.size()][5];

            for (int i = 0; i < history.size(); i++) {
                Loan loan = history.get(i);

                data[i][0] = loan.getBook().getName();
                data[i][1] = loan.getCheckOutdate();
                data[i][2] = loan.getDuedate();

                if (loan.getReturnDate() == null) {
                    data[i][3] = "";
                    data[i][4] = "";
                } else {
                    data[i][3] = loan.getReturnDate();
                    data[i][4] = "R$ " + loan.getFine();
                }
            }

            JTable historyTable = new JTable(data, historyColumns);

            JScrollPane historyScrollPane = new JScrollPane(historyTable);
            historyScrollPane.setPreferredSize(new Dimension(800, 300));

            JOptionPane.showMessageDialog(
                this,
                historyScrollPane,
                "Patron History",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        JPanel searchPanelPatron = createSearchPanel("Search Patrons");

        nameButtonPatron = new JRadioButton("Name");
        IDButtonPatron = new JRadioButton("ID");

        ButtonGroup searchGroupPatron = new ButtonGroup();
        searchGroupPatron.add(nameButtonPatron);
        searchGroupPatron.add(IDButtonPatron);

        nameButtonPatron.setSelected(true);

        searchFieldPatron = new JTextField(20);
        JButton searchButtonPatron = new JButton("Search");
        JButton clearSearchButtonPatron = new JButton("Clear Search");

        searchPanelPatron.add(clearSearchButtonPatron);
        searchPanelPatron.add(nameButtonPatron);
        searchPanelPatron.add(IDButtonPatron);
        searchPanelPatron.add(searchFieldPatron);
        searchPanelPatron.add(searchButtonPatron);

        patronsPanel.add(searchPanelPatron, BorderLayout.NORTH);

        searchButtonPatron.addActionListener(e -> {
            String command = "";

            if (nameButtonPatron.isSelected()) {
                command = "NAME";
            } else if (IDButtonPatron.isSelected()) {
                command = "ID";
            }

            String value = searchFieldPatron.getText();

            if (isEmptyInput(value)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Search field cannot be empty.",
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (IDButtonPatron.isSelected()) {
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "ID must be an integer.",
                        "Search Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }

            ArrayList<Patron> result = patronManagement.Search(command, patronlist, value);

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
                    patron.getHaveBook(),
                    patron.getTotalFine()
                });
            }
        });

        clearSearchButtonPatron.addActionListener(e -> {
            searchFieldPatron.setText("");
            refreshTablePatron();
        });

        // =========================
        // Loans tab
        // =========================

        checkoutButton = new JButton("Check Out");
        checkinButton = new JButton("Check In");

        checkoutButton.addActionListener(e -> {
            if (patronlist.isEmpty() || booklist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "There must be patrons and books registered.");
                return;
            }

            JComboBox<Patron> patronBox = new JComboBox<>(patronlist.toArray(new Patron[0]));
            JComboBox<Book> bookBox = new JComboBox<>(booklist.toArray(new Book[0]));

            JPanel panel = new JPanel();
            panel.add(new JLabel("Patron:"));
            panel.add(patronBox);
            panel.add(new JLabel("Book:"));
            panel.add(bookBox);

            int option = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Check Out",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (option != JOptionPane.OK_OPTION) {
                return;
            }

            Patron selectedPatron = (Patron) patronBox.getSelectedItem();
            Book selectedBook = (Book) bookBox.getSelectedItem();

            if (selectedPatron == null || selectedBook == null) {
                return;
            }

            if (!selectedPatron.getBorrowedBooks().isEmpty() || selectedPatron.getHaveBook() == 1) {
                JOptionPane.showMessageDialog(this, "This patron already has a borrowed book.");
                return;
            }

            if (selectedBook.getcopies() <= 0) {
                JOptionPane.showMessageDialog(this, "This book has no available copies.");
                return;
            }

            LocalDate checkoutDate = LocalDate.now();
            LocalDate dueDate = checkoutDate.plusDays(14);

            Loan loan = new Loan(selectedBook, checkoutDate, dueDate);

            loanList.add(loan);
            selectedPatron.getBorrowedBooks().add(selectedBook);
            selectedPatron.addLoanToHistory(loan);
            selectedPatron.setHaveBook(1);

            selectedBook.setCopies(selectedBook.getcopies() - 1);

            JOptionPane.showMessageDialog(this, "Book borrowed successfully.");

            refreshTable();
            refreshTablePatron();
            saveData();
        });

        checkinButton.addActionListener(e -> {
            if (patronlist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "There are no patrons.");
                return;
            }

            JComboBox<Patron> patronBox = new JComboBox<>(patronlist.toArray(new Patron[0]));

            int option = JOptionPane.showConfirmDialog(
                this,
                patronBox,
                "Select Patron",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (option != JOptionPane.OK_OPTION) {
                return;
            }

            Patron selectedPatron = (Patron) patronBox.getSelectedItem();

            if (selectedPatron == null) {
                return;
            }

            if (selectedPatron.getBorrowedBooks().isEmpty()) {
                JOptionPane.showMessageDialog(this, "This patron has no borrowed book.");
                return;
            }

            Book borrowedBook = selectedPatron.getBorrowedBooks().get(0);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Book: " + borrowedBook.getName() +
                    "\nAuthor: " + borrowedBook.getAuthor() +
                    "\nPatron: " + selectedPatron.getName() +
                    "\n\nConfirm return?",
                    "Check In",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            Loan foundLoan = null;

            for (Loan loan : loanList) {
                if (loan.getBook() == borrowedBook) {
                    foundLoan = loan;
                    break;
                }
            }

            if (foundLoan == null) {
                JOptionPane.showMessageDialog(this, "Loan not found.");
                return;
            }

            LoanManagement loanManagement = new LoanManagement(1.20f, LocalDate.now());

            long overdueDays = loanManagement.getOverdue(foundLoan);
            float fine = overdueDays * 1.20f;

            foundLoan.setReturnDate(LocalDate.now());
            foundLoan.setFine(fine);

            selectedPatron.setTotalFine(selectedPatron.getTotalFine() + fine);
            selectedPatron.setHaveBook(0);

            selectedPatron.getBorrowedBooks().remove(borrowedBook);
            borrowedBook.setCopies(borrowedBook.getcopies() + 1);
            loanList.remove(foundLoan);

            JOptionPane.showMessageDialog(
                this,
                "Book returned successfully.\nOverdue days: " + overdueDays + "\nFine: R$ " + fine
            );

            refreshTable();
            refreshTablePatron();
            saveData();
        });

        JButton currentLoansButton = new JButton("Current Loans");
        JButton overdueLoansButton = new JButton("Overdue Loans");
        currentLoansButton.addActionListener(e -> {

            if (loanList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "There are no active loans.");
                return;
            }

            String[] columns = {
                "Book",
                "Patron",
                "Borrowed",
                "Due"
            };

            Object[][] data = new Object[loanList.size()][4];

            for (int i = 0; i < loanList.size(); i++) {
                Loan loan = loanList.get(i);

                String patronName = "";

                for (Patron patron : patronlist) {
                    if (patron.getBorrowedBooks().contains(loan.getBook())) {
                        patronName = patron.getName();
                        break;
                    }
                }

                data[i][0] = loan.getBook().getName();
                data[i][1] = patronName;
                data[i][2] = loan.getCheckOutdate();
                data[i][3] = loan.getDuedate();
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane2 = new JScrollPane(table);
            scrollPane2.setPreferredSize(new Dimension(800, 300));

            JOptionPane.showMessageDialog(
                this,
                scrollPane2,
                "Currently Checked-Out Books",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        overdueLoansButton.addActionListener(e -> {

            ArrayList<Object[]> overdueRows = new ArrayList<>();

            LocalDate today = LocalDate.now();

            for (Loan loan : loanList) {

                if (today.isAfter(loan.getDuedate())) {

                    Patron loanPatron = null;

                    for (Patron patron : patronlist) {
                        if (patron.getBorrowedBooks().contains(loan.getBook())) {
                            loanPatron = patron;
                            break;
                        }
                    }

                    if (loanPatron != null) {
                        long overdueDays =
                            java.time.temporal.ChronoUnit.DAYS.between(
                                loan.getDuedate(),
                                today
                            );

                        overdueRows.add(new Object[] {
                            loan.getBook().getName(),
                            loanPatron.getName(),
                            loanPatron.getContact(),
                            loan.getDuedate(),
                            overdueDays
                        });
                    }
                }
            }

            if (overdueRows.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "There are no overdue loans."
                );
                return;
            }

            String[] columns = {
                "Book",
                "Patron",
                "Contact",
                "Due",
                "Overdue Days"
            };

            Object[][] data = new Object[overdueRows.size()][5];

            for (int i = 0; i < overdueRows.size(); i++) {
                data[i] = overdueRows.get(i);
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane3 = new JScrollPane(table);
            scrollPane3.setPreferredSize(new Dimension(900, 300));

            JOptionPane.showMessageDialog(
                this,
                scrollPane3,
                "Overdue Loans",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        JPanel loanButtonsPanel = createSidePanel("Loan Options");
        loanButtonsPanel.add(checkoutButton);
        loanButtonsPanel.add(checkinButton);
        loanButtonsPanel.add(currentLoansButton);
        loanButtonsPanel.add(overdueLoansButton);

        JLabel loanTitle = new JLabel("Loan Management", SwingConstants.CENTER);
        loanTitle.setFont(new Font("SansSerif", Font.BOLD, 22));

        loansPanel.add(loanTitle, BorderLayout.NORTH);
        loansPanel.add(loanButtonsPanel, BorderLayout.WEST);

        loadData();
    }
}






