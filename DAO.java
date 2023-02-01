
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
            aniadirCalificacionAUnEstudiante(i);
            i++;
        }
        return true;
    }

    public boolean aniadirCalificacionAUnEstudiante(int i) {
        Scanner input = new Scanner(System.in);
        Estudiante estudiante = this.data.get(i);
        System.out.println("Inserte calificacion del alumno: " + estudiante.getNombre());
        int grade = readGrade(input);
        while (isValidGrade(grade) == false) {
            grade = readGrade(input);
        }
        estudiante.asignarCalificacion(grade);
        return true;
    }

    public static boolean isValidGrade(int grade) {
        boolean isValid = true;
        if (grade < 0 || grade > 100) {
            System.out.println("Error, ingrese la calificación nuevamente");
            isValid = false;
        }
        return isValid;
    }

    public static int readGrade(Scanner input) {
        int grade = input.nextInt();
        return grade;
    }

    public void writeNewFile() {
        try {
            FileWriter fileWriter = new FileWriter("files/generatedCSV.csv");
            fileWriter.write("Apellido paterno" + "," + "Apellido materno" + "," + "Nombre" + "," + "Calificacion" + "," + "Asignatura" + "\n");
            for (int key : this.data.keySet()) {
                Estudiante line = devolverEstudiante(key);
                fileWriter.write(line.getNombre() + "," + line.getCalif() + "," + "Disenio de Software" + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNewPDF() {
        try {
            Document report = new Document();

            FileOutputStream ficheroPDF= new FileOutputStream("Reporte.pdf");
            PdfWriter.getInstance(report, ficheroPDF);
            report.open();
            
            Paragraph titulo= new Paragraph("Reporte de calificaciones\n",FontFactory.getFont("Courier",40
            ,Font.ITALIC,BaseColor.ORANGE));
            report.add(titulo);

            PdfPTable tabla= new PdfPTable(3);
            tabla.addCell("matricula");
            tabla.addCell("nombre");
            tabla.addCell("calificacion");

            for(int i=0; i<this.data.size(); i++){
                tabla.addCell(this.data.get(i).hashCode()+"");
                tabla.addCell(this.data.get(i).getNombre()+"");
                tabla.addCell(this.data.get(i).getCalif() +"");
            }
            report.add(tabla);
            report.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public Estudiante devolverEstudiante(int i) {
        return data.get(i);
    }

}