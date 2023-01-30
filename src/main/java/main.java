import DB.commandSQL;
import customerManager.customerManage;

import java.util.HashMap;

public class main {
    public static void main(String[] args) {

        try {

//            userLogin.login();
//           users activeUser=userLogin.getUser();
//           if (Objects.equals(activeUser.getKou(), "E")){
//               System.out.println("please enter new customer data");
//

            //ctm.showCustomers();

            customerManage cm = new customerManage();
            commandSQL sql = new commandSQL();


            HashMap<String, String> hm = new HashMap<>();
            hm.put("idCustomer", "10015");
            hm.put("Customer_Family", "میرزایی");
            sql.delete_cmd("customer", hm);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
