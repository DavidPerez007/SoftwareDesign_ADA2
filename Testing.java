/*
 * Actividad realizada por:
 * Perez Cruz David Leobardo
 * Rosado Rodriguez Daniel Alejandro
 */
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        logIn.initLoginMenu();
        if (logIn.isLoggedIn()) {
            DAO dao = new DAO("files/input.csv");
            dao.anadirCalificaciones();
            if (shouldCreateCSV() == true) {
                generateCSV(dao);
            }else {
                System.out.println("Calificaciones registradas con éxito");
            }
        }
    }

    public static void generateCSV(DAO dao) {
        dao.writeNewFile();
        System.out.println("El archivo ha sido generado con éxito");
    }

    public static boolean shouldCreateCSV() {
        Scanner input = new Scanner(System.in);
        Boolean shouldPrint = input.nextBoolean();
        return shouldPrint;
    }
}
