package customerManager;

import DB.commandSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class customerManage {
    public Scanner scanner = new Scanner(System.in);
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


        HashMap<String, String> cv /*columns, Value*/=new HashMap<String,String>();
cv.put("customer_name", "c_name");
cv.put("customer_family","c_family");
cv.put("customer_address","c_address");

String table="customer";
//        String columns=" customer_name,customer_family,customer_address ";
//        String values=c_name +','+ c_family + ','+ c_address;

        commandSQL cmd = new commandSQL();
        cmd.insert_cmd(table,cv);

    }
}
