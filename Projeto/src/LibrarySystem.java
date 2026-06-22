import javax.swing.UIManager;
import javax.swing.SwingUtilities;

/**
 * Entry point of the Java Library system.
 *
 * <p>This class configures the Swing look and feel and starts the application
 * by opening the login screen.</p>
 */
public class LibrarySystem {

    /**
     * Starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }
}
