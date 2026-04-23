import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Decryptor {
    private static final Path DATA_DIR = Path.of("Vault/data");
    private static final Path KEY_PATH = Path.of("Vault/secret.key");
    private static final Path LOG_FILE = Path.of("operation.log");
    private static final Path REPORT_FILE = Path.of("decryption_report.txt");

    public static void decryptAll(JProgressBar progressBar, JLabel statusLabel) throws Exception {
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

        // Use a mutable ArrayList to collect files to decrypt
        List<Path> filesToDecrypt = new ArrayList<>();
        try (DirectoryStream<Path> files = Files.newDirectoryStream(DATA_DIR, "*.enc")) {
            for (Path file : files) {
                if (!Files.isRegularFile(file)) continue;
                filesToDecrypt.add(file);  
            }
        }

        // Start logging
        try (BufferedWriter logWriter = Files.newBufferedWriter(LOG_FILE, StandardOpenOption.APPEND)) {
            logWriter.write("Decryption Started at: " + System.currentTimeMillis() + "\n");

            // Create report
            try (BufferedWriter reportWriter = Files.newBufferedWriter(REPORT_FILE)) {
                reportWriter.write("Decryption Report\n");
                reportWriter.write("Started at: " + System.currentTimeMillis() + "\n");
                reportWriter.write("-------------------------------------------------\n");

                int totalFiles = filesToDecrypt.size();
                for (int i = 0; i < totalFiles; i++) {
                    Path file = filesToDecrypt.get(i);

                    byte[] encrypted = Files.readAllBytes(file);
                    byte[] decrypted = engine.decrypt(encrypted);

                    String fileName = file.getFileName().toString();
                    if (fileName.endsWith(".enc")) {
                        fileName = fileName.substring(0, fileName.length() - 4);  // Remove .enc extension
                    }

                    Path originalFile = file.getParent().resolve(fileName);

                    // Write decrypted content
                    Files.write(originalFile, decrypted);
                    Files.delete(file);

                    // Log the file processed
                    logWriter.write("Decrypted: " + fileName + "\n");
                    reportWriter.write("Decrypted: " + fileName + ", Type: " + getFileExtension(file) + "\n");

                    // Update progress bar
                    int progress = (int) ((i + 1) / (float) totalFiles * 100);
                    progressBar.setValue(progress);
                    statusLabel.setText("Decrypting... " + progress + "%");
                }

                reportWriter.write("-------------------------------------------------\n");
                reportWriter.write("Decryption Ended at: " + System.currentTimeMillis() + "\n");
            }
        }
    }

    private static String getFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "Unknown" : fileName.substring(dotIndex + 1);
    }
}
