import javax.swing.*;
import java.awt.*;

/**
 * Login screen for the Java Library system.
 *
 * <p>The screen allows the user to choose between Admin and Librarian roles
 * using radio buttons. Credentials are hard-coded for this version.</p>
 */
public class LoginScreen extends JFrame {

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    private static final String LIBRARIAN_USER = "librarian";
    private static final String LIBRARIAN_PASSWORD = "5678";

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton adminOption;
    private JRadioButton librarianOption;
    private JButton loginButton;

    /**
     * Builds the login screen.
     */
    public LoginScreen() {
        setTitle("Library Login");
        setSize(450, 300);
        setMinimumSize(new Dimension(400, 250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "Java Library System",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));

        adminOption = new JRadioButton("Admin");
        librarianOption = new JRadioButton("Librarian");

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminOption);
        roleGroup.add(librarianOption);

        adminOption.setSelected(true);

        JPanel rolePanel = new JPanel();
        rolePanel.add(adminOption);
        rolePanel.add(librarianOption);

        loginButton = new JButton("Login");

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Role:"));
        formPanel.add(rolePanel);

        formPanel.add(new JLabel(""));
        formPanel.add(loginButton);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(formPanel);

        add(titleLabel, BorderLayout.NORTH);
        add(wrapperPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> checkLogin());
    }

    /**
     * Checks the typed credentials and opens the correct screen.
     */
    private void checkLogin() {

        if (adminOption.isSelected()) {

            String username = ADMIN_USER;
            String password = ADMIN_PASSWORD;
            if (username.equals(ADMIN_USER) && password.equals(ADMIN_PASSWORD)) {
                new AdminScreen().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid admin login.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else {

            String username = LIBRARIAN_USER;
            String password = LIBRARIAN_PASSWORD;
            if (username.equals(LIBRARIAN_USER) && password.equals(LIBRARIAN_PASSWORD)) {
                new LibrarianScreen().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid librarian login.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
