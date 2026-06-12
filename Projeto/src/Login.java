import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JRadioButton admButton;
    private JRadioButton librarianButton;

    public Login() {
        setTitle("Login");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        add(usernameLabel);
        add(usernameField);
        
        add(passwordLabel);
        add(passwordField);

        loginButton = new JButton("Login");
        admButton = new JRadioButton("Admin");
        librarianButton = new JRadioButton("Librarian");

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(admButton);
        roleGroup.add(librarianButton);

        
        
        add(librarianButton);
        add(admButton);

        add(new JLabel(""));
        add(loginButton);

        

       loginButton.addActionListener(e -> checkLogin());
    }

    private void checkLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (admButton.isSelected()) {
            if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                new AdminScreen().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid admin login.");
            }
        } else if (librarianButton.isSelected()) {
            if (username.equals("librarian") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Librarian login successful!");
                new LibrarianScreen().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid librarian login.");
            }
        }
    }

    public static void main(String[] args) {
        Login screen = new Login();
        screen.setVisible(true);
    }
}