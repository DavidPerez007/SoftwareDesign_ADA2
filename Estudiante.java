public class Estudiante {
    
    private String nombre;
    private int calificacion;

    public Estudiante(String nombre){
        this.nombre = nombre;
    }

    public void asignarCalificacion(int calif){
        this.calificacion = calif;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCalif(){
        return this.calificacion;
    }

}
