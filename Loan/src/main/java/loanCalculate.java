import DB.commandSQL;
import Entities.bank_account;
import Entities.customer;
import org.apache.poi.ss.formula.functions.Finance;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class loanCalculate {
    static Double rate;
    static int payMonths;
    static Double paymentAmount;
    ArrayList<loanFile> LoanFile = new ArrayList<>();


    public void fillLoanData() {
        Scanner scanner = new Scanner(System.in);
        commandSQL cmd = new commandSQL();

        customer Customer = cmd.select_customer("idCustomer", "=", "10016");
        bank_account selectedBankAccount = cmd.select_one_bank_account("bank_account_customer_id", "=", "10016");

        Calendar c = Calendar.getInstance();
        Date myDate;

        Double monthsRate = ((rate / 12) / 100);

        for (int i = 1; i <= payMonths; i++) {
            HashMap<String, String> loans = new HashMap<>();

            myDate=Date.from(LocalDate.now().plusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date newDate = new java.sql.Date(myDate.getTime());

            loans.put("idloan", Customer.getIdCustomer() + "" + payMonths + rate.intValue());
            loans.put("dueDate", newDate.toString());
            loans.put("loan_principle_amount", String.valueOf(Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1)));
            loans.put("loan_interest", String.valueOf(Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1)));
            loans.put("loan_sum_pi_amount", String.valueOf(Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount) * -1)));
            loans.put("loan_status", "N");
            loans.put("loan_customer_id", String.valueOf(Customer.getIdCustomer()));
            loans.put("loan_account_id", String.valueOf(selectedBankAccount.getIdbank_acocunt()));
            loans.put("loan_months", String.valueOf(i));

            cmd.insert_cmd("Loan", loans);
            LoanFile.add(new loanFile(Customer.getIdCustomer() + "" + payMonths + rate.intValue(), newDate, Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount) * -1), 'N', Customer.getIdCustomer(), selectedBankAccount.getIdbank_acocunt()));

        }

    }

    public void showloanFile() {
        for (int i = 0; i < LoanFile.size(); i++) {
            System.out.println(LoanFile.get(i).getIdloan() + "   " + LoanFile.get(i).getLoan_principle_amount() + "  " + LoanFile.get(i).getLoan_interest() + "  " + LoanFile.get(i).getLoan_sum_pi_amount() + "   " + LoanFile.get(i).getLoan_status() + "    " + LoanFile.get(i).getId_customer_loan());
        }
    }

}
