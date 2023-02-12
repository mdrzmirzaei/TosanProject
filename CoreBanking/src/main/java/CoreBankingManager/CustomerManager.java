package CoreBankingManager;

import Entities.Customer;

import java.math.BigDecimal;
import java.util.*;


public class CustomerManager {

    public ArrayList<Customer> CustomerArray = new ArrayList<Customer>();
    Calendar c = new GregorianCalendar();
    CommandSQL cmd = new CommandSQL();
    private Scanner scanner = new Scanner(System.in);
    private String c_name;
    private String c_family;
    private String c_address;


    public CustomerManager(ArrayList<Customer> customerArray) {
        CustomerArray = customerArray;
    }

    public CustomerManager() {
    }

    public boolean insertCustomer(String c_name, String c_family, String c_address) {
        try {
     /*   System.out.println("please enter customer name :");
        c_name = scanner.nextLine();
        System.out.println("please enter customer family :");
        c_family = scanner.nextLine();
        System.out.println("please enter customer address :");
        c_address = scanner.nextLine();
      */
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getCause();
            return false;
        }
        return true;
    }


    public ArrayList<Customer> showCustomers() {
//cmd.select_cmd("customer");
//        cmd.select_customer_cmd("customer_family", "like", "%می%");
        // cmd.select_customer_cmd("idCustomer","=",idCustomer);
return cmd.selectAllCustomer();
    }

    public boolean customerEditor(String idCustomer, String customer_name, String customer_family, String customer_address) {
//        System.out.println("please enter id for edit cutomer");

        HashMap<String, String> cv /*columns(key), Values(value)*/ = new HashMap<>();

        cv.put("customer_name", customer_name);
        cv.put("customer_family", customer_family);
        cv.put("customer_address", customer_address);

        return cmd.update_cmd("customer", Integer.valueOf(idCustomer), cv);
    }


    public boolean customerDelete(String idCustomer) {
//        System.out.println("please enter id for edit cutomer");


        HashMap<String, String> cv /*columns(key), Values(value)*/ = new HashMap<>();

        cv.put("idCustomer", idCustomer.toString());


        return cmd.delete_cmd("customer",cv);

    }



    public boolean createCustomerAccount(String customer_id, char currency, String balance) {
//        System.out.println("pls enter customer_id : ");
//        int idcustomer = this.scanner.nextInt();
//        Customer selected_customer = cmd.select_customer_cmd("idCustomer", " = ", String.valueOf(idcustomer));

        HashMap<String, String> hm = new HashMap<>();
        hm.put("idbank_account", String.valueOf(customer_id) + "" + c.get(Calendar.DAY_OF_YEAR) + "" + c.get(Calendar.HOUR_OF_DAY));
        //System.out.println("please enter balance Amount of BankAccount :");
        hm.put("bank_account_balance", balance);
        //System.out.println("please enter kind of curency of BankAccount :");
        hm.put("kind_of_currency", String.valueOf(currency));
        hm.put("bank_account_customer_id", String.valueOf(customer_id));

        cmd.insert_cmd("bank_account", hm);
        return true;

    }


}
