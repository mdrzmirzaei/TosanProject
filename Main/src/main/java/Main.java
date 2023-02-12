import UI.TosanProject;

public class Main {

    public static void main(String[] args) {
     /*   loanRequest LR = new loanRequest();
        System.out.println("please enter your amount of loan");
        Scanner s = new Scanner (System.in);
        LR.acceptRequest(s.nextBigDecimal(), 25);
      */

//loanRequest lr =new loanRequest();

//lr.test();

//        installmentCalculate lc = new installmentCalculate();
        //lc.paymentAmount = 30000000d;
        //lc.payMonths = 120;
        //lc.rate = 10d;
        //lc.fillLoanData();

//        Scanner scanner = new Scanner(System.in);
//        CommandSQL cmd = new CommandSQL();

//        System.out.println(cmd.get_financial_ressource_cmd()) ;
//        cmd.set_financial_ressource_cmd(2000000d,'+');
//        customer c = new customer();
//        System.out.println("please enter idcustomer:");
////cmd.select_installment_cmd(cmd.select_customer("idcustomer","=",String.valueOf(scanner.nextInt())));
////        installmentPayment ip=new installmentPayment();
//        c = cmd.select_customer_cmd("idcustomer", "= ", String.valueOf(scanner.nextInt()));
//        scanner.nextLine();
//        System.out.println("please enter name of customer: ");
//        cmd.update_cmd("customer", c.getIdCustomer(), "customer_name", scanner.nextLine());
////        ip.deposit();

//        System.out.println(cmd.get_financial_ressource_cmd());
//        cmd.setLoanRate(18.5d);
//        System.out.println("Date      ,   Time     ,Amount, result, customer_id");
//        for (int i =0 ; i <cmd.getTransacitons('A').size() ; i++) {
//
//            System.out.println(cmd.getTransacitons('A').get(i));
//
//        }

//        System.out.println("the Total of interest is :    "+cmd.getTotalInterest()+" Rial");


//        ArrayList<String> transactions = cmd.getTransacitons('A');
//        for (int i = 0; i <transactions.size() ; i++) {
//            System.out.println(transactions.get(i));

//        }

//        LoanManager lm = new LoanManager();
//        InstallmentCalculate ins = new InstallmentCalculate();
//        ins.setRate(Double.valueOf(lm.getLoanRate()));
//        ins.accountToaccount();
//        ins.fillInstallmentsData();


        //  System.out.println(Math.round(Finance.ppmt(((16.5/12)/100), 1, 18, 540000000d) * -1));
        new TosanProject();

    }
}
