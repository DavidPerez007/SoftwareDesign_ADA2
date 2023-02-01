import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsViewControl implements ActionListener{

    private StudentsView view;
    
    public StudentsViewControl(StudentsView view){
        this.view = view;
        this.view.jButton2.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("actualizando");
    }
    public void initView(){
        this.view.setTitle("calificaciones");
        this.view.setLocationRelativeTo(null);
    }
    
}
