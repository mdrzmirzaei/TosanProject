import DB.commandSQL;
import Entities.bank_account;
import Entities.customer;
import Entities.transactions;
import org.apache.poi.ss.formula.functions.Finance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class loanCalculate implements transactions {
    static Double rate;
    static int payMonths;
    static Double paymentAmount;
    ArrayList<installments> installments = new ArrayList<>();
    commandSQL cmd = new commandSQL();
    Scanner scanner = new Scanner(System.in);
    private BigDecimal remain = null;
    private BigDecimal loanAmount = null;
    private int months = 0;
    private Date dueDate;

    public void createLoanFile(String tableName, HashMap<String, String> colval) {
        cmd.insert_cmd(tableName, colval);
    }


    public BigDecimal loanRequest(BigDecimal Amount, int monthsToPay) {
        if (cmd.select_financial("financial_ressource", "financial_amount", ">=", Amount.toString())) {
            System.out.println("you can get the loan");
            return this.loanAmount;
        } else {
            System.out.println("you can not Get Loan!!!");
            return null;
        }
    }


    // this method is for withdraw from financial ressourse
    @Override
    public BigDecimal withDraw(/*BigDecimal WDamount /*Withdraw_amount*/) {
        System.out.println("please enter Amount of loan :");
        this.loanAmount = scanner.nextBigDecimal();
        System.out.println("please enter months to pay :");
        this.months = scanner.nextInt();
        if (loanRequest(this.loanAmount, this.months) != null) {
            HashMap<String, String> financial = new HashMap<>();
            remain = cmd.get_financial_ressource_cmd().subtract(this.loanAmount);
            financial.put("financial_amount", remain.toString());
            cmd.update_cmd("financial_ressource", 1, financial);
            return this.loanAmount;
        } else
            return null;
    }

    // this method work for withdraw into customer bank account
    @Override
    public boolean deposit() {

        if (withDraw() != null) {
            bank_account selected_bank_account = new bank_account();
            HashMap<String, String> bankaccount = new HashMap();
            HashMap<String, String> transaction = new HashMap();


            System.out.println("pls enter customer ID : ");
            int idcustomer = scanner.nextInt();
            ArrayList<bank_account> bank_accountArray = cmd.select_bank_account("bank_account_customer_id", " = ", String.valueOf(idcustomer));

            if (bank_accountArray != null) {
                System.out.println("i find Bank Account!!!");
                System.out.println("please select acccount :");
                int sel = scanner.nextInt();
                for (int i = 0; i < bank_accountArray.size(); i++) {
                    if (bank_accountArray.get(i).getIdbank_acocunt() == sel) {
                        selected_bank_account = bank_accountArray.get(i);
                    }
                }
                dueDate = new Date();
                java.sql.Date newDate = new java.sql.Date(dueDate.getTime());

                bankaccount.put("bank_account_balance", String.valueOf(selected_bank_account.getBank_account_balance().add(loanAmount)));
                cmd.update_cmd("bank_account", selected_bank_account.getIdbank_acocunt(), bankaccount);
                transaction.put("transaction_date", newDate.toString());
                transaction.put("transaction_time", newDate.getHours() + ":" + newDate.getMinutes());
                transaction.put("transaction_amount", loanAmount.toString());
                transaction.put("transaction_status", String.valueOf("T".charAt(0)));
                transaction.put("transaction_customer_id", String.valueOf(selected_bank_account.getIdcustomer_bank_acount()));
                transaction.put("transaction_origin", "1");
                transaction.put("transaction_destination", String.valueOf(selected_bank_account.getIdbank_acocunt()));
                cmd.insert_cmd("transaction", transaction);
                return true;
            } else {
                System.out.println("sorry !!!! bank account is not found ");
                return false;
            }
        }
        return true;
    }

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

            myDate = Date.from(LocalDate.now().plusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date newDate = new java.sql.Date(myDate.getTime());

            loans.put("idloan", Customer.getIdCustomer() + "" + payMonths + rate.intValue());
            loans.put("dueDate", newDate.toString());
            loans.put("loan_principle_amount", String.valueOf(Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1)));
            loans.put("loan_interest", String.valueOf(Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1)));
            loans.put("loan_sum_pi_amount", String.valueOf(Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount) * -1)));
            loans.put("loan_status", "T");
            loans.put("loan_customer_id", String.valueOf(Customer.getIdCustomer()));
            loans.put("loan_account_id", String.valueOf(selectedBankAccount.getIdbank_acocunt()));
            loans.put("loan_months", String.valueOf(i));

            cmd.insert_cmd("Loan", loans);
            installments.add(new installments(Customer.getIdCustomer() + "" + payMonths + rate.intValue(), newDate, Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount) * -1), 'N', Customer.getIdCustomer(), selectedBankAccount.getIdbank_acocunt()));

        }

    }


}
