package installments;

import DB.commandSQL;
import Entities.bank_account;
import Entities.customer;
import Entities.transactions;
import loan.loanManager;
import org.apache.poi.ss.formula.functions.Finance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class installmentCalculate implements transactions {
    static Double rate;
    static int payMonths;
    static Double paymentAmount;
    ArrayList<installments> installments = new ArrayList<>();
    commandSQL cmd = new commandSQL();
    Scanner scanner = new Scanner(System.in);
    loanManager loanManager = new loanManager();
    private BigDecimal ressourceRemain = null;
    private BigDecimal loanAmount = null;
    private int months = 0;
    private Date dueDate;

//    public void createLoanFile(String tableName, HashMap<String, String> colval) {
//        cmd.insert_cmd(tableName, colval);
//    }


    // this method is for withdraw from financial ressourse
    @Override
    public BigDecimal withDraw(/*BigDecimal WDamount /*Withdraw_amount*/) {
        System.out.println("please enter Amount of loan :");
        this.loanAmount = scanner.nextBigDecimal();
        System.out.println("please enter months to pay :");
        this.months = scanner.nextInt();
        if (new loanManager().loanRequest(this.loanAmount, this.months) != false) {
            ressourceRemain = cmd.get_financial_ressource_cmd().subtract(this.loanAmount);
            cmd.update_cmd("financial_ressource", 1, "financial_amount", ressourceRemain.toString());
            return this.loanAmount;
        } else
            return null;
    }

    // this method work for withdraw into customer bank account
    @Override
    public boolean deposit() {

        if (withDraw() != null) {
            bank_account selected_bank_account = new bank_account();
            HashMap<String, String> transaction = new HashMap();


            System.out.println("pls enter customer ID : ");
            int idcustomer = scanner.nextInt();
            ArrayList<bank_account> bank_accountArray = cmd.select_bank_accounts("bank_account_customer_id", " = ", String.valueOf(idcustomer));

            if (bank_accountArray != null) {
                System.out.println("find Bank Accounts!!!");
                System.out.println("please select acccount :");
                int sel = scanner.nextInt();
                for (int i = 0; i < bank_accountArray.size(); i++) {
                    if (bank_accountArray.get(i).getIdbank_acocunt() == sel) {
                        selected_bank_account = bank_accountArray.get(i);
                    }
                }
                dueDate = new Date();
                java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
                cmd.update_cmd("bank_account", selected_bank_account.getIdbank_acocunt(), "bank_account_balance", String.valueOf(selected_bank_account.getBank_account_balance().add(loanAmount)));
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

    public void fillInstallmentsData() {
        System.out.println("pls enter customer ID : ");
        int idcustomer = scanner.nextInt();
        customer Customer = cmd.select_customer_cmd("idCustomer", "=", String.valueOf(idcustomer));
        bank_account selectedBankAccount = cmd.select_one_bank_account("bank_account_customer_id", "=", String.valueOf(idcustomer));

        Calendar c = Calendar.getInstance();
        Date myDate;

        Double monthsRate = ((rate / 12) / 100);

        for (int i = 1; i <= payMonths; i++) {
            HashMap<String, String> installs = new HashMap<>();

            myDate = Date.from(LocalDate.now().plusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date newDate = new java.sql.Date(myDate.getTime());

            installs.put("installments_idloan", Customer.getIdCustomer() + "" + payMonths + rate.intValue());
            installs.put("installments_dueDate", newDate.toString());
            installs.put("installments_principle_amount", String.valueOf(Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1)));
            installs.put("installments_interest", String.valueOf(Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1)));
            installs.put("installments_sum_pi_amount", String.valueOf(Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount) * -1)));
            installs.put("installments_status", "T");
            installs.put("installments_customer_id", String.valueOf(Customer.getIdCustomer()));
            installs.put("installments_account_id", String.valueOf(selectedBankAccount.getIdbank_acocunt()));
            installs.put("installments_months", String.valueOf(i));

            cmd.insert_cmd("installments", installs);
            installments.add(new installments(Customer.getIdCustomer() + "" + payMonths + rate.intValue(), i, Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount) * -1), 'F', Customer.getIdCustomer(), selectedBankAccount.getIdbank_acocunt(), newDate));


        }

    }


}
