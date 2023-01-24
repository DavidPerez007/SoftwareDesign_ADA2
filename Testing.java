import java.util.Scanner;


public class Testing {
    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        logIn.initLoginMenu();
        if (logIn.isLoggedIn()) {
            DAO dao = new DAO("files/input.csv");
            dao.anadirCalificaciones();
            System.out.println("¿Desea imprimir el archivo CSV?");
            Scanner input = new Scanner(System.in);
            Boolean shouldPrint = input.nextBoolean();
            if (shouldPrint) {
                dao.writeNewFile();
                System.out.println("El archivo ha sido generado con éxito");
            } else {
                System.out.println("Calificaciones registradas con éxito");
            }
            input.close();
        }
    }
}
