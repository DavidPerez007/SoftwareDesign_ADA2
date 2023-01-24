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
        System.out.println("El nombre es: " + this.nombre);
    }

    public void readPassword(Scanner input) {
        System.out.println("Inserte su contraseña: ");
        this.contrasenia = input.nextLine();
        System.out.println("El nombre es: " + this.contrasenia);
    }

    public void validateUser() {
        if (userExists()) {
            System.out.println("el usuario existe");
            if (isCorrectPassword()) {
                this.loggedIn = true;
            } else{
                System.out.println("Contraseña incorrecta");
            }
        } else{
            System.out.println("Usuario no encontrado");
        }
        
    }

    public boolean userExists() {
        System.out.println(dao.users.get(this.nombre));
        return dao.users.containsKey(this.nombre);
    }

    public boolean isCorrectPassword() {
        return dao.users.get(this.nombre).contrasenia.equals(this.contrasenia);
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }

}
