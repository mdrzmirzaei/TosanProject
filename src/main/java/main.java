import DB.commandSQL;
import installments.installmentCalculate;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
     /*   loanRequest LR = new loanRequest();
        System.out.println("please enter your amount of loan");
        Scanner s = new Scanner (System.in);
        LR.acceptRequest(s.nextBigDecimal(), 25);
      */

//loanRequest lr =new loanRequest();

//lr.test();

        installmentCalculate lc = new installmentCalculate();
        //lc.paymentAmount = 30000000d;
        //lc.payMonths = 120;
        //lc.rate = 10d;
        //lc.fillLoanData();

Scanner scanner=new Scanner(System.in);
commandSQL cmd=new commandSQL();
        System.out.println("please enter idcustomer:");
cmd.select_installment_cmd(cmd.select_customer("idcustomer","=",String.valueOf(scanner.nextInt())));

    }
}
