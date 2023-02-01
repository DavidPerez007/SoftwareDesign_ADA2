

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInControl implements ActionListener{

    private LogInView view;
    private LogIn login;

    public LogInControl(LogInView view, LogIn login){
        this.view = view;
        this.login = login;
        this.view.ConfirmButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        login.nombre = view.NameField.getText();
        login.setEncryptedPassword(view.PasswordField.getText());
        login.validateUser();
        if(login.isLoggedIn()){
            StudentsView newView = new StudentsView();
            StudentsViewControl newControl = new StudentsViewControl(newView);
            newControl.initView();
            newView.setVisible(true);
            this.view.setVisible(false);
        }
    }
    public void iniciar(){
        view.setTitle("nulasdfsdfl");
        view.setLocationRelativeTo(null);
    }
    
}
