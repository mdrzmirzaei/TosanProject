import DB.commandSQL;
import DB.initDB;
import DB.userLogin;
import Entities.customer;
import Entities.users;
import customerManager.customerManage;
import DB.commandSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class main {
    public static void main(String[] args) {

        try {

//            userLogin.login();
//           users activeUser=userLogin.getUser();
//           if (Objects.equals(activeUser.getKou(), "E")){
//               System.out.println("please enter new customer data");
//

            //ctm.showCustomers();

            customerManage cm=new customerManage();
  commandSQL sql=new commandSQL();


            HashMap<String,String> hm = new HashMap<>();
            hm.put("Customer_Name","ستایش");
            hm.put("Customer_Family","میرزایی");
 sql.update_cmd("customer",10015,hm);

           }
 catch (Exception e) {
            System.out.println(e.getMessage());
     System.out.println(e.getStackTrace());
        }
    }
}
