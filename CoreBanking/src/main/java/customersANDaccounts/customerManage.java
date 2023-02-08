package customersANDaccounts;

import DB.commandSQL;
import Entities.customer;

import java.util.*;


public class customerManage {

    public ArrayList<customer> CustomerArray = new ArrayList<customer>();
    Calendar c = new GregorianCalendar();
    commandSQL cmd = new commandSQL();
    private Scanner scanner = new Scanner(System.in);
    private String c_name;
    private String c_family;
    private String c_address;


    public customerManage(ArrayList<customer> customerArray) {
        CustomerArray = customerArray;
    }

    public customerManage() {
    }

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


    public void showCustomers() {
//cmd.select_cmd("customer");
        cmd.select_customer_cmd("customer_family", "like", "%می%");
    }

    public void customerEditor() {
        System.out.println("please enter id for edit cutomer");


    }

    public void createCustomerAccount() {
        System.out.println("pls enter customer_id : ");
        int idcustomer = this.scanner.nextInt();
        customer selected_customer = cmd.select_customer_cmd("idCustomer", " = ", String.valueOf(idcustomer));

        HashMap<String, String> hm = new HashMap<>();
        hm.put("idbank_account", String.valueOf(idcustomer) + "" + c.get(Calendar.DAY_OF_YEAR) + "" + c.get(Calendar.HOUR_OF_DAY));
        System.out.println("please enter balance Amount of BankAccount :");
        hm.put("bank_account_balance", this.scanner.nextBigDecimal().toString());
        System.out.println("please enter kind of cureency of BankAccount :");
        hm.put("kind_of_currency", String.valueOf(this.scanner.next().charAt(0)));
        hm.put("bank_account_customer_id", String.valueOf(idcustomer));

        cmd.insert_cmd("bank_account", hm);

    }

}
