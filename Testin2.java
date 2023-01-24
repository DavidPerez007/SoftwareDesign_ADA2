public class Testin2 {
    public static void main(String[] args) {
        DAOUsuarios dao = new DAOUsuarios("files/users.csv");
        System.out.println(dao.users.get("joselito"));
    }

}
