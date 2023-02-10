package LoanManager;

import CoreBankingManager.CommandSQL;
import Entities.Customer;
import org.apache.poi.ss.formula.functions.Finance;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InstallmentsManager {
    private CommandSQL cmd = new CommandSQL();
    Scanner scanner=new Scanner(System.in);
    Customer customer =new Customer();

//public void selectInstallment(){
//    System.out.println("please enter customer Id:");
//    customer =cmd.select_customer_cmd("idcustomer"," = ",String.valueOf(scanner.nextInt()));
//
//}


    public void test() {

        Double ra = (18 / 12d) / 100;
Double d=Double.valueOf(String.format("%.3f",ra)) ;

        System.out.println();
        int nper = 120;
        Double pv = 30000000d;

        HashMap<Integer, String> hm = new HashMap<>();

        for (int i = 1; i <= nper; i++) {


            hm.put(i, "mablaghe ghest :" + Math.round(Finance.pmt(ra, nper, pv) * -1) + "  mablaghe asle pool:" + Math.round(Finance.ppmt(ra, i, nper, pv) * -1) + "   mablaghe sooood: " + Math.round(Finance.ipmt(ra, i, nper, pv) * -1));
        }

        for (Map.Entry me : hm.entrySet()
        ) {
            System.out.println(me.getKey() + "   " + me.getValue());

        }


    }
}
