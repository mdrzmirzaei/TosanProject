package DB;

import Entities.users;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class userLogin {
    private static users user = new users();
    private static String user_name = "";
    private static String password = "";


    public static boolean login() {
        try {
            System.out.println("please Enter Your User name:");
            Scanner scanner = new Scanner(System.in);
            user_name = scanner.nextLine();
            System.out.println("please Enter Your User password:");
            password = scanner.nextLine();

            initDB.ConnectOk();
            CachedRowSet cachedRowSet = initDB.initCachedRowset();
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
                return false;

            } else {

                System.out.println("     Wellcome");
                System.out.println("You are Login now...");

                // initial user details in Object of user

                user = new users(cachedRowSet.getString("person_name"), cachedRowSet.getString("person_family"), cachedRowSet.getString("kind_of_user"));
                System.out.println(user.getName() + "  " + user.getFamily() + "   " + user.getKou());


            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println("user not found");
        } finally {

            initDB.releaseDB();
        }

        return true;


    }

    public static users getUser() {
        if (user != null) {
            return user;
        } else return null;
    }
}
