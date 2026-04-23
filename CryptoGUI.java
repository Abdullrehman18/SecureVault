import java.awt.*;
import javax.swing.*;

public class CryptoGUI {

    private static JFrame frame;
    private static JLabel statusLabel;
    private static JButton encryptButton;
    private static JButton decryptButton;
    private static JProgressBar progressBar;

    public static void main(String[] args) {
        // Create the main frame
        frame = new JFrame("File Encryption/Decryption");
        frame.setSize(420, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create components
        statusLabel = new JLabel("Choose an operation:", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        encryptButton = new JButton("Encrypt Files");
        decryptButton = new JButton("Decrypt Files");

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false); // Initially hidden

        // Layout setup
        frame.setLayout(new BorderLayout());
        frame.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.add(progressBar, BorderLayout.SOUTH);

        // Action listeners
        encryptButton.addActionListener(e -> startEncryption());

        decryptButton.addActionListener(e -> startDecryption());

        frame.setVisible(true);
    }

    private static void startEncryption() {
        // Disable buttons during processing
        encryptButton.setEnabled(false);
        decryptButton.setEnabled(false);
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);
        statusLabel.setText("Encrypting files... Please wait.");

        // Run encryption in a separate thread
        new Thread(() -> {
            try {
                Encryptor.encryptAll(progressBar, statusLabel);  // Pass progress bar and status label
                statusLabel.setText("Encryption completed!");
            } catch (Exception e) {
                statusLabel.setText("Error during encryption.");
                System.err.println("Encryption error: " + e.getMessage());
                e.printStackTrace(System.err);
            } finally {
                encryptButton.setEnabled(true);
                decryptButton.setEnabled(true);
                progressBar.setVisible(false);
            }
        }).start();
    }

    private static void startDecryption() {
        // Disable buttons during processing
        encryptButton.setEnabled(false);
        decryptButton.setEnabled(false);
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);
        statusLabel.setText("Decrypting files... Please wait.");

        // Run decryption in a separate thread
        new Thread(() -> {
            try {
                Decryptor.decryptAll(progressBar, statusLabel);  // Pass progress bar and status label
                statusLabel.setText("Decryption completed!");
            } catch (Exception e) {
                statusLabel.setText("Error during decryption.");
                System.err.println("Decryption error: " + e.getMessage());
                e.printStackTrace(System.err);
            } finally {
                encryptButton.setEnabled(true);
                decryptButton.setEnabled(true);
                progressBar.setVisible(false);
            }
        }).start();
    }
}
