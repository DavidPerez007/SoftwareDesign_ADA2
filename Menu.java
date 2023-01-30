import java.util.Scanner;

public class Menu {

    public int selectedOption;

    public void renderMenu(){
        showMenuOptions();
        Scanner input = new Scanner(System.in);
        this.selectedOption = input.nextInt();
        System.out.println(this.selectedOption);
    }

    private void showMenuOptions() {
        System.out.println("¿Cómo desea insertar las calificaciones?");
        System.out.println("1) Todos los almunos");
        System.out.println("2) Insertar calificación a un alumno");
        System.out.println("3) salir");
    }

}
