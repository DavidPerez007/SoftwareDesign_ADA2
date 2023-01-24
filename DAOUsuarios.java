import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DAOUsuarios {

    public String path;
    public HashMap<String, User> users = new HashMap<>();

    public DAOUsuarios(String path){
        this.path = path;
        loadData();
    }


    public void loadData() {
        String line = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.path));
            while ((line = br.readLine()) != null) {
                User user = buildUser(line);
                this.users.put(user.nombre, user);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public User buildUser(String line){
        String[] row = line.split(",");
        System.out.println("AQUI"+ row[0] + row[1]);
        String contrasenia = readName(row);
        String nombre = readContrasenia(row);
        return new User(nombre, contrasenia);
    }

    public String readName(String[] row){
        return row[0];
    }

    public String readContrasenia(String[] row){
        return row[1];
    }
}
