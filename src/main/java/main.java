public class main {

    public static void main(String[] args) {
     /*   loanRequest LR = new loanRequest();
        System.out.println("please enter your amount of loan");
        Scanner s = new Scanner (System.in);
        LR.acceptRequest(s.nextBigDecimal(), 25);
      */

//loanRequest lr =new loanRequest();

//lr.test();

        loanCalculate lc = new loanCalculate();
        lc.paymentAmount = 30000000d;
        lc.payMonths = 120;
        lc.rate = 10d;
        lc.fillLoanData();
        lc.showloanFile();

    }
}
