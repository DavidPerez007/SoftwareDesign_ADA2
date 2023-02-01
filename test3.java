public class test3 {
    public static void main(String[] args) {
        LogIn login = new LogIn();
        LogInView view = new LogInView();
        LogInControl control = new LogInControl(view, login);
        control.iniciar();
        view.setVisible(true);
    }
    
}
