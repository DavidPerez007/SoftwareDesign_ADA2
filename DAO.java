import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class DAO {
    public String path;
    public HashMap<Integer, Estudiante> data = new HashMap<>();

    public DAO(String path) {
        this.path = path;
        this.loadData();
    }

    public void loadData() {
        String line = "";
        String separator = "/n";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.path));
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(separator);
                String nombre = row[0];
                Estudiante estudiante = new Estudiante(nombre);
                this.data.put(i, estudiante);
                i++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean anadirCalificaciones() {
        int i = 0;
        while (i < this.data.size()) {
            Estudiante estudiante = this.data.get(i);
            System.out.println("Inserte calificacion del alumno: " + estudiante.getNombre());
            int grade = readGrade();
            while (isValidGrade(grade) == false) {
                grade = readGrade();
            }
            estudiante.asignarCalificacion(grade);
            i++;
        }
        return true;
    }

    // public HashMap<Integer, Estudiante> getData() {
    //     return this.data;
    // }

    public static boolean isValidGrade(int grade) {
        boolean isValid = true;
        if (grade < 0 || grade > 100) {
            System.out.println("Error, ingrese la calificación nuevamente");
            isValid = false;
        }
        return isValid;
    }

    public static int readGrade() {
        Scanner input = new Scanner(System.in);
        int grade = input.nextInt();
        return grade;
    }

    public void writeNewFile() {
        try {
            FileWriter fileWriter = new FileWriter("files//generatedCSV.csv");
            for (int key : this.data.keySet()) {
                Estudiante line = devolverEstudiante(key);  
                fileWriter.write(line.getNombre() + "," + line.getCalif() + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Estudiante devolverEstudiante(int i) {
        return data.get(i);
    }

}
