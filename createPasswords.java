import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
public class createPasswords {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String originalPassword = input.nextLine();
        String key = "This is a secret"; // Debe ser de 128, 192 o 256 bits
        // Crear objeto de clave simétrica
        Key aesKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        try {        
            // Crear objeto Cipher
            Cipher cipher = Cipher.getInstance("AES");
            // Inicializar Cipher en modo de encriptación
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            // Encriptar el texto original
            byte[] encryptedBytes = cipher.doFinal(originalPassword.getBytes(StandardCharsets.UTF_8));
            // Convertir los bytes encriptados a una cadena codificada en base64
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println(encryptedPassword); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
