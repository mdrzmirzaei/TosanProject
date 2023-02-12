package CoreBankingManager;

import DatabaseManager.InitDB;
import Entities.Users;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public abstract class UserLogin {
    public static Users user = new Users();
    private static String user_name = "";
    private static String password = "";
    public static Users users = new Users();



    public static Users login(String user, String pass) {
        try {
          /*  System.out.println("please Enter Your User name:");
            Scanner scanner = new Scanner(System.in);
            user_name = scanner.nextLine();
            System.out.println("please Enter Your User password:");
            password = scanner.nextLine();

           */
            user_name = user;
            password = pass;

            InitDB.ConnectOk();
            CachedRowSet cachedRowSet = InitDB.initCachedRowset();
            cachedRowSet.setCommand("select * from users where user_name =? and user_password =?");
            cachedRowSet.setString(1, user_name);
            cachedRowSet.setString(2, password);
            cachedRowSet.execute();


   /*     PreparedStatement preparedStatement=initDB.ConnectOk().prepareStatement("select * from users where user_name =? and user_password =?");
        preparedStatement.setString(1,user_name);
        preparedStatement.setString(2,password);
        preparedStatement.execute();

        ResultSet resultset=preparedStatement.getResultSet();
*/
            if (cachedRowSet.first() == false) {
                System.out.println("User or Password is wrong!!!! pls try Again");
                return null;

            } else {

                System.out.println("     Wellcome");
                System.out.println("You are Login now...");

                // initial user details in Object of user

                users = new Users(cachedRowSet.getString("person_name"), cachedRowSet.getString("person_family"), cachedRowSet.getString("kind_of_user").charAt(0));
                System.out.println(users.getName() + "  " + users.getFamily() + "   " + users.getKou());

                return users;
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println("user not found");
        } finally {

            InitDB.releaseDB();
        }

        return
                users;

    }

    public static Users getUser() {
        if (users != null) {
            return users;
        } else return null;
    }

}
