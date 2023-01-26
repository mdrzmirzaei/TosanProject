import DB.commandSQL;
import DB.initDB;
import DB.userLogin;

public class main {
    public static void main(String[] args) {

        try {

            userLogin.login();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
