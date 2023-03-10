package LoanManager;

import CoreBankingManager.CommandSQL;
import Entities.BankAccount;
import Entities.Customer;
import Entities.Installments;
import Entities.TransactionsInterface;
import org.apache.poi.ss.formula.functions.Finance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class InstallmentCalculate implements TransactionsInterface {
    ArrayList<Installments> installments = new ArrayList<>();
    CommandSQL cmd = new CommandSQL();
    Scanner scanner = new Scanner(System.in);
    LoanManager loanManager = new LoanManager();
    LocalDateTime ldt = LocalDateTime.now();
    BankAccount selected_bankAccount = new BankAccount();
    Customer customer = new Customer();
    private BigDecimal ressourceRemain = null;
    private Date dueDate = new Date();
    private Double rate;
    private int payMonths;
    private Double paymentAmount;


    public InstallmentCalculate(Double rate, int payMonths, Double paymentAmount) {
        this.rate = rate;
        this.payMonths = payMonths;
        this.paymentAmount = paymentAmount;
    }


    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getPayMonths() {
        return payMonths;
    }

    public void setPayMonths(int payMonths) {
        this.payMonths = payMonths;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    @Override
    public BigDecimal withDraw( String idCustomer /*BigDecimal WDamount /*Withdraw_amount*/) {
        try {
//            System.out.println("please enter Amount of loan :");
//            setPaymentAmount(scanner.nextDouble());
//            System.out.println("please enter months to pay :");
//            setPayMonths(scanner.nextInt());
//            if (new LoanManager().loanRequest(getPaymentAmount(), getPayMonths()) != false) {

//                {
            ressourceRemain = cmd.get_financial_ressource_cmd().subtract(BigDecimal.valueOf(getPaymentAmount()));
            cmd.update_cmd("financial_ressource", 1, "financial_amount", ressourceRemain.toString());

            HashMap<String, String> transaction = new HashMap();
            java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
            transaction.put("kind_of_transaction", "withdraw");
            transaction.put("transaction_amount", getPaymentAmount().toString());
            transaction.put("transaction_status", String.valueOf("T".charAt(0)));
            //transaction.put("transaction_customer_id", "1");
            transaction.put("transaction_origin", "1");
            transaction.put("transaction_destination", String.valueOf(selected_bankAccount.getIdbank_account()));
            cmd.insert_cmd("transaction", transaction);


            return BigDecimal.valueOf(this.paymentAmount);

//            } else
//                return null;
        } catch (Exception e) {
            HashMap<String, String> transaction = new HashMap();
            java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
            transaction.put("kind_of_transaction", "withdraw");
            transaction.put("transaction_amount", getPaymentAmount().toString());
            transaction.put("transaction_status", String.valueOf("F".charAt(0)));
            //transaction.put("transaction_customer_id", "1");
            transaction.put("transaction_origin", "1");
            //transaction.put("transaction_destination", String.valueOf(selected_bank_account.getIdbank_acocunt()));
            cmd.insert_cmd("transaction", transaction);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return null;
        }
    }

    // this method work for withdraw into customer bank account
    @Override
    public boolean deposit(String idcustomer, String bankAccountID, String amount) {

        try {
            if (selected_bankAccount != null) {


                HashMap<String, String> transaction = new HashMap();


//            System.out.println("pls enter customer ID : ");
//            int idcustomer = scanner.nextInt();
/*
            ArrayList<BankAccount> bankAccountArray = cmd.select_bank_accounts("bank_account_customer_id", " = ", String.valueOf(idcustomer));

            if (bankAccountArray != null) {
                System.out.println("find Bank Accounts!!!");
                System.out.println("please select acccount :");
                int sel = scanner.nextInt();
                for (int i = 0; i < bankAccountArray.size(); i++) {
                    if (bankAccountArray.get(i).getIdbank_acocunt() == sel) {
                        selected_bankAccount = bankAccountArray.get(i);
                    }
                }

 */

                java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
                System.out.println(selected_bankAccount.getIdbank_account() + "   " + selected_bankAccount.getBank_account_balance());
                cmd.update_cmd("bank_account", selected_bankAccount.getIdbank_account(), "bank_account_balance", String.valueOf(selected_bankAccount.getBank_account_balance().add(BigDecimal.valueOf(paymentAmount))));
                transaction.put("transaction_date", newDate.toString());
                transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
                transaction.put("kind_of_transaction", "withdraw");
                transaction.put("transaction_amount", getPaymentAmount().toString());
                transaction.put("transaction_status", String.valueOf("T".charAt(0)));
                transaction.put("transaction_customer_id", String.valueOf(selected_bankAccount.getIdcustomer_bank_acount()));
                transaction.put("transaction_origin", "1");
                transaction.put("transaction_destination", String.valueOf(selected_bankAccount.getIdbank_account()));
                cmd.insert_cmd("transaction", transaction);
                return true;
            } else {
                System.out.println("sorry !!!! bank account is not found ");
                return false;
            }
        } catch (Exception e) {

            HashMap<String, String> transaction = new HashMap();
            java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
            transaction.put("kind_of_transaction", "deposit");
            transaction.put("transaction_amount", getPaymentAmount().toString());
            transaction.put("transaction_status", String.valueOf("F".charAt(0)));
            transaction.put("transaction_customer_id", String.valueOf(selected_bankAccount.getIdcustomer_bank_acount()));
            transaction.put("transaction_origin", "1");
            transaction.put("transaction_destination", String.valueOf(selected_bankAccount.getIdbank_account()));
            cmd.insert_cmd("transaction", transaction);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return false;
        }
    }


    @Override
    public boolean accountToaccount(Customer customer) {
        try {
            if (withDraw(String.valueOf(this.customer.getIdCustomer())) != null) {

                deposit(String.valueOf(customer.getIdCustomer()), String.valueOf(selected_bankAccount.getIdbank_account()), String.valueOf(getPaymentAmount()));

                return true;
            }
        } catch (Exception e) {
            e.getMessage();
            e.getCause();
        }

        return false;
    }


    public boolean fillInstallmentsData(BankAccount selected_bankAccount) {
//        System.out.println("pls enter customer ID : ");
//        int idcustomer = scanner.nextInt();
        try {
            customer = cmd.select_customer_cmd("idCustomer", "=", String.valueOf(selected_bankAccount.getIdcustomer_bank_acount()));
            this.selected_bankAccount = selected_bankAccount;
            //BankAccount selectedBankAccount=new BankAccount();

            Date myDate;
            Double monthsRate = ((getRate() / 12d) / 100);


            for (int i = 1; i <= payMonths; i++) {
                HashMap<String, String> installs = new HashMap<>();

                myDate = Date.from(LocalDate.now().plusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date newDate = new java.sql.Date(myDate.getTime());

                installs.put("installments_idloan", customer.getIdCustomer() + "" + payMonths + rate.intValue());
                installs.put("installments_dueDate", newDate.toString());
                installs.put("installments_principle_amount", String.valueOf(Math.round(Finance.ppmt(monthsRate, i, payMonths, this.paymentAmount) * -1)));
                installs.put("installments_interest", String.valueOf(Math.round(Finance.ipmt(monthsRate, i, payMonths, this.paymentAmount) * -1)));
                installs.put("installments_sum_pi_amount", String.valueOf(Math.round(Finance.pmt(monthsRate, payMonths, this.paymentAmount) * -1)));
                installs.put("installments_status", "F");
                installs.put("installments_customer_id", String.valueOf(customer.getIdCustomer()));
                installs.put("installments_account_id", String.valueOf(selected_bankAccount.getIdbank_account()));
                installs.put("installments_months", String.valueOf(i));

                cmd.insert_cmd("installments", installs);
                installments.add(new Installments(customer.getIdCustomer() + "" + payMonths + rate.intValue(), i, Math.round(Finance.ppmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.ipmt(monthsRate, i, payMonths, paymentAmount) * -1), Math.round(Finance.pmt(monthsRate, payMonths, paymentAmount.doubleValue()) * -1), 'F', customer.getIdCustomer(), selected_bankAccount.getIdbank_account(), newDate));


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getCause();
            return false;
        }
        return true;
    }


}
