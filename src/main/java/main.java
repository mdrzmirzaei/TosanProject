import DB.commandSQL;
import DB.initDB;
import DB.userLogin;
import Entities.users;
import customerManager.customerManage;

import java.util.Objects;

public class main {
    public static void main(String[] args) {
        customerManage ctm=new customerManage();
        try {

            userLogin.login();
           users activeUser=userLogin.getUser();
           if (Objects.equals(activeUser.getKou(), "E")){
               System.out.println("please enter new customer data");

               ctm.insertCustomer();
           }








        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
