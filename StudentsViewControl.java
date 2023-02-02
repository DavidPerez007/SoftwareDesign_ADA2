import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

public class StudentsViewControl implements ActionListener {

    private StudentsView view;

    public StudentsViewControl(StudentsView view) {
        this.view = view;
        this.view.jButton2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) view.StudentsTable.getModel();
        int numberOfRows = model.getRowCount();
        int numberOfColumns = model.getColumnCount();
        DAO dao = new DAO("files/input.csv");
        for (int i = 0; i < numberOfRows; i++) {
            int calif = Integer.parseInt((String) model.getValueAt(i, 2).toString());
            Estudiante estudiante = dao.data.get(i);
            dao.aniadirCalificacionAUnEstudiante(i, calif);
        }
        dao.writeNewFile();
        dao.writeNewPDF();
    }

    public void initView() {
        this.view.setTitle("calificaciones");
        this.view.setLocationRelativeTo(null);
    }

}
