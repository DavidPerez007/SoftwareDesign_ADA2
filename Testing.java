import java.util.HashMap;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        DAO dao = new DAO("input.csv");
        dao.anadirCalificaciones();
        System.out.println("¿Desea imprimir el archivo CSV?");
        Scanner input = new Scanner(System.in);
        Boolean answer = input.nextBoolean();
        if(answer == true){
            dao.writeNewFile();
            System.out.println("El archivo ha sido generado con éxito");
        } else {
            System.out.println("Calificaciones registradas con éxito");
        }
    }
}
