package customerManager;

import DB.commandSQL;

import java.util.HashMap;
import java.util.Scanner;


public class customerManage {
    commandSQL cmd = new commandSQL();
    private Scanner scanner = new Scanner(System.in);
    private String c_name;
    private String c_family;
    private String c_address;

    public void insertCustomer() {
        System.out.println("please enter customer name :");
        c_name = scanner.nextLine();
        System.out.println("please enter customer family :");
        c_family = scanner.nextLine();
        System.out.println("please enter customer address :");
        c_address = scanner.nextLine();

/**
 *this hash map contained details of columns name & values
 */
        HashMap<String, String> cv /*columns(key), Values(value)*/ = new HashMap<String, String>();

        cv.put("customer_name", c_name);
        cv.put("customer_family", c_family);
        cv.put("customer_address", c_address);

        String table = "customer";
//        String columns=" customer_name,customer_family,customer_address ";
//        String values=c_name +','+ c_family + ','+ c_address;

        cmd.insert_cmd(table, cv);

    }


    public void showCustomers(){
//cmd.select_cmd("customer");
cmd.select_cmd("customer","customer_family","like","%می%");


    }
}
