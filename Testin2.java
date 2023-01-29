import java.util.function.LongToIntFunction;

import javax.security.auth.login.LoginContext;

public class Testin2 {
    public static void main(String[] args) {
        DAOUsuarios dao = new DAOUsuarios("files/users.csv");
        LogIn login = new LogIn();
        login.decryptPassword("/Cyh8E846Lfs6KUtssIieqRCYag4aAr2xcoZkP/gBJk=");
    }

}
