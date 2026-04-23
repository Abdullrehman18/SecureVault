import java.nio.file.Files;
import java.nio.file.Path;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGeneratorUtil {

    public static void main(String[] args) throws Exception {

        Path keyPath = Path.of("Vault/secret.key");

        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);

        SecretKey key = generator.generateKey();
        Files.write(keyPath, key.getEncoded());

        System.out.println("Key generated successfully.");
    }
}
