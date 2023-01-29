
/*
 * Actividad realizada por:
 * Perez Cruz David Leobardo
 * Rosado Rodriguez Daniel Alejandro
 */
import java.util.Scanner;

import com.itextpdf.text.pdf.PdfWriter;

public class Testing {
    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        logIn.initLoginMenu();
        if (logIn.isLoggedIn()) {
            DAO dao = new DAO("files/input.csv");
            renderOptionMenu(dao);
            
        }
    }

    public static void renderOptionMenu(DAO dao) {
        Menu menu = new Menu();
        menu.renderMenu();
        switch (menu.selectedOption) {
            case 1:
                aniadirCalificacionUnaPorUna(dao);
                break;
            case 2:
                aniadirCalificacionAlumno(dao);
                break;
            default:
                System.out.println("opción incorrecta");
        }
    }

    public static void aniadirCalificacionUnaPorUna(DAO dao) {
        dao.anadirCalificaciones();
        askToWriteCSV(dao);
    }

    public static void aniadirCalificacionAlumno(DAO dao) {
        System.out.println("Inserte el id del estudiante: ");
        Scanner input = new Scanner(System.in);
        int idEstudiante = input.nextInt();
        dao.aniadirCalificacionAUnEstudiante(idEstudiante);
        askToWriteCSV(dao);
    }

    public static void askToWriteCSV(DAO dao){
        if (shouldCreateCSV()) {
            generateCSV(dao);
            dao.writeNewPDF();
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
