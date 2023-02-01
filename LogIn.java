import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;


public class LogIn {

    Boolean loggedIn;
    String nombre;
    String contrasenia;
    DAOUsuarios dao;

    public LogIn() {
        loggedIn = false;
        dao = new DAOUsuarios("files/users.csv");
    }

    public void initLoginMenu() {
        Scanner input = new Scanner(System.in);
        while (!this.loggedIn) {
            readName(input);
            readPassword(input);
            validateUser();
        }
    }

    public void readName(Scanner input) {
        System.out.println("Inserte su nombre de usuario: ");
        this.nombre = input.nextLine();
    }

    public void readPassword(Scanner input) {
        System.out.println("Inserte su contraseña: ");
        String password = input.nextLine();
        this.contrasenia = encryptPassword(password);
    }
    public void setEncryptedPassword(String password){
        this.contrasenia = encryptPassword(password);
    }

    public boolean validateUser() {
        if (userExists()) {
            if (isCorrectPassword()) {
                this.loggedIn = true;
            } else{
                System.out.println("Contraseña incorrecta");
            }
        } else{
            System.out.println("Usuario no encontrado");
        }
        return isLoggedIn();
    }

    public boolean userExists() {
        System.out.println(dao.users.get(this.nombre));
        return dao.users.containsKey(this.nombre);
    }

    public boolean isCorrectPassword() {
        return dao.users.get(this.nombre).contrasenia.equals(this.contrasenia);
    }

    public String encryptPassword(String password){
        String originalPassword = password;
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
            return encryptedPassword;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "It didnt work";
        
    }

    public String decryptPassword(String encryptedPassword){
        String encryptedText = encryptedPassword;
        String key = "This is a secret";
        // Crear objeto de clave simétrica
        Key aesKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        try{        
            // Crear objeto Cipher
            Cipher cipher = Cipher.getInstance("AES");
            // Inicializar Cipher en modo de desencriptación
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            // Decodificar la cadena encriptada en base64
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            // Desencriptar los bytes encriptados
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // Convertir los bytes desencriptados a una cadena
            String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println("Texto encriptado: " + encryptedText);
            System.out.println("Texto desencriptado: " + decryptedText);

            return decryptedText;
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return "It didnt work";
 
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }

}
