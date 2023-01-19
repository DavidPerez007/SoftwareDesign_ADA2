import java.util.HashMap;
import java.util.Scanner;

public class testin {
    public static void main(String[] args) {
        DAO dao = new DAO("files//input.csv");
        dao.anadirCalificaciones();
        System.out.println("¿Desea imprimir el archivo CSV?");
        Scanner input = new Scanner(System.in);
        Boolean answer = input.nextBoolean();
        if(answer == true){
            dao.writeNewFile();
        } else {
            System.out.println("Calificaciones registradas con éxito");
        }
    }
}
