import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Encryptor {
    private static final Path DATA_DIR = Path.of("Vault/data");
    private static final Path KEY_PATH = Path.of("Vault/secret.key");
    private static final Path LOG_FILE = Path.of("operation.log");
    private static final Path REPORT_FILE = Path.of("encryption_report.txt");

    public static void encryptAll(JProgressBar progressBar, JLabel statusLabel) throws Exception {
        CryptoEngine engine = new CryptoEngine(KEY_PATH);

        // Ensure the Vault directory exists
        Files.createDirectories(DATA_DIR.getParent());  // Create the parent directory for Vault

        // Create log and report files if they don't exist
        if (!Files.exists(LOG_FILE)) {
            Files.createFile(LOG_FILE);  // Create log file if it doesn't exist
        }
        if (!Files.exists(REPORT_FILE)) {
            Files.createFile(REPORT_FILE);  // Create report file if it doesn't exist
        }

        // Use a mutable ArrayList to collect files to encrypt
        List<Path> filesToEncrypt = new ArrayList<>();  
        try (DirectoryStream<Path> files = Files.newDirectoryStream(DATA_DIR)) {
            for (Path file : files) {
                if (!Files.isRegularFile(file) || file.toString().endsWith(".enc")) continue;
                filesToEncrypt.add(file);  
            }
        }

        // Start logging
        try (BufferedWriter logWriter = Files.newBufferedWriter(LOG_FILE, StandardOpenOption.APPEND)) {
            logWriter.write("Encryption Started at: " + System.currentTimeMillis() + "\n");

            // Create report
            try (BufferedWriter reportWriter = Files.newBufferedWriter(REPORT_FILE)) {
                reportWriter.write("Encryption Report\n");
                reportWriter.write("Started at: " + System.currentTimeMillis() + "\n");
                reportWriter.write("-------------------------------------------------\n");

                int totalFiles = filesToEncrypt.size();
                for (int i = 0; i < totalFiles; i++) {
                    Path file = filesToEncrypt.get(i);

                    byte[] data = Files.readAllBytes(file);
                    byte[] encrypted = engine.encrypt(data);

                    String fileName = file.getFileName().toString();
                    Path encryptedFile = file.getParent().resolve(fileName + ".enc");

                    // Write encrypted content
                    Files.write(encryptedFile, encrypted);
                    Files.delete(file);

                    // Log the file processed
                    logWriter.write("Encrypted: " + fileName + "\n");
                    reportWriter.write("Encrypted: " + fileName + ", Type: " + getFileExtension(file) + "\n");

                    // Update progress bar
                    int progress = (int) ((i + 1) / (float) totalFiles * 100);
                    progressBar.setValue(progress);
                    statusLabel.setText("Encrypting... " + progress + "%");
                }

                reportWriter.write("-------------------------------------------------\n");
                reportWriter.write("Encryption Ended at: " + System.currentTimeMillis() + "\n");
            }
        }
    }

    private static String getFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "Unknown" : fileName.substring(dotIndex + 1);
    }
}
