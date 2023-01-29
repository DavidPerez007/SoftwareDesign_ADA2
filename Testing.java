
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
            Menu menu = new Menu();
            menu.renderMenu();
            switch (menu.selectedOption) {
                case 1:
                    aniadirCalificacionUnaPorUna(dao);
                default:
                    System.out.println("opción incorrecta");
            }

        }
    }

    public static void aniadirCalificacionUnaPorUna(DAO dao) {
        dao.anadirCalificaciones();
        if (shouldCreateCSV()) {
            generateCSV(dao);
        } else {
            System.out.println("Calificaciones registradas con éxito");
        }
    }

    public static void generateCSV(DAO dao) {
        dao.writeNewFile();
        System.out.println("El archivo ha sido generado con éxito");
    }

    public static boolean shouldCreateCSV() {
        System.out.println("Desea imprimir el CSV?");
        Scanner input = new Scanner(System.in);
        Boolean shouldPrint = input.nextBoolean();
        return shouldPrint;
    }
}
