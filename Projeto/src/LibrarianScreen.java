import javax.swing.*;

public class LibrarianScreen extends JFrame {
    public LibrarianScreen() {
        setTitle("Librarian Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Welcome, Librarian!", SwingConstants.CENTER));
    }
}