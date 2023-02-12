package LoanManager;

import CoreBankingManager.CommandSQL;
import DatabaseManager.InitDB;
import Entities.BankAccount;
import Entities.Customer;
import Entities.Installments;
import Entities.TransactionsInterface;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class InstallmentPayment implements AutoCloseable, TransactionsInterface {
    ArrayList<Installments> installments = new ArrayList<>();
    HashMap<String, String> transaction = new HashMap();

    CommandSQL cmd = new CommandSQL();
    Scanner scanner = new Scanner(System.in);
    LoanManager loanManager = new LoanManager();
    BankAccount selectedBankAccount = null;
    String idBankAccout="";
    Customer customer = new Customer();
    LocalDateTime ldt = LocalDateTime.now();
    private double installmentAmount;
    private Date dueDate;
    private int installmentsNumber;


    public InstallmentPayment(String idBankAccout, int installmentsNumber) {
        this.idBankAccout = idBankAccout;
        this.installmentsNumber = installmentsNumber;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }


    public String getIdBankAccout() {
        return idBankAccout;
    }

    public Double checkInstallments(BankAccount bankAccount){
        //System.out.println("please enter id of customer for select bank account : ");
try {

    customer = cmd.select_customer_cmd("idcustomer", " = ", String.valueOf(bankAccount.getIdcustomer_bank_acount()));
    this.selectedBankAccount = bankAccount;

//            System.out.println("please enter number of of installment :");
//            installmentsNumber = scanner.nextInt();

    installments = cmd.select_installment_cmd(customer);
    for (int i = 1; i < installments.size(); i++) {
        if (installments.get(i).getInstallments_status() == 'F' && i <= installmentsNumber) {
            System.out.println(installments.get(i).getInstallments_customer_id() + "  " + installments.get(i).getInstallments_interest() + "    " + installments.get(i).getInstallments_months() + "    " + installments.get(i).getInstallments_status() + "  " + installments.get(i).getInstallments_sum_pi_amount());
            installmentAmount += installments.get(i).getInstallments_sum_pi_amount();

        } else break;
    }
}
catch (Exception e)
{
    System.out.println(e.getMessage());
    e.getCause();
    return null;
}
        return installmentAmount;
    }

    @Override
    public BigDecimal withDraw(String idCustomer) {
        try {
//            System.out.println("the Total of installment amount is : " + installmentAmount);
//            System.out.println("are Customer is ok? press Enter for cancel press another key");
//            int ok = scanner.nextInt();
            if ( installmentAmount != 0 && selectedBankAccount != null) {
                dueDate = new Date();
                java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
                HashMap<String, String> ba_changes = new HashMap<>();
                ba_changes.put("bank_account_balance", String.valueOf(selectedBankAccount.getBank_account_balance().subtract(BigDecimal.valueOf(installmentAmount))));
                cmd.update_cmd("bank_account", selectedBankAccount.getIdbank_account(), ba_changes);
                transaction.put("transaction_date", newDate.toString());
                transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
                transaction.put("transaction_amount", String.valueOf(installmentAmount));
                transaction.put("transaction_status", String.valueOf("T".charAt(0)));
                transaction.put("transaction_customer_id", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
                transaction.put("kind_of_transaction", "withdraw");
                transaction.put("transaction_origin", String.valueOf(selectedBankAccount.getIdbank_account()));
                transaction.put("transaction_destination", "1");
                cmd.insert_cmd("transaction", transaction);
                return BigDecimal.valueOf(installmentAmount);
            } else {
                System.out.println("the operation as canceled ");
                return null;
            }
        } catch (Exception e) {
            HashMap<String, String> transaction = new HashMap();
            dueDate = new Date();
            java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
            transaction.put("kind_of_transaction", "withdraw");
            transaction.put("transaction_amount", String.valueOf(installmentAmount));
            transaction.put("transaction_status", String.valueOf("F".charAt(0)));
            transaction.put("transaction_customer_id", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
            transaction.put("transaction_origin", String.valueOf(selectedBankAccount.getIdbank_account()));
            transaction.put("transaction_destination", "1");
            cmd.insert_cmd("transaction", transaction);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return null;
        }
    }

    @Override
    public boolean deposit(String idcustomer, String bankAccountID, String amount) {
        try {
            for (int i = 0; i <= installmentsNumber; i++) {
                installmentAmount -= installments.get(i).getInstallments_sum_pi_amount();
                cmd.update_cmd("installments", installments.get(i).getInstallments_status(), "installments_status", "T");
                cmd.update_cmd("financial_ressource", 1, "financial_amount", String.valueOf(cmd.get_financial_ressource_cmd().add(BigDecimal.valueOf(installments.get(i).getInstallments_sum_pi_amount()))));

                dueDate = new Date();
                java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
                transaction.put("transaction_date", newDate.toString());
                transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
                transaction.put("transaction_amount", String.valueOf(installmentAmount));
                transaction.put("transaction_status", "T");
                transaction.put("kind_of_transaction", "deposit");
                transaction.put("transaction_customer_id", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
                transaction.put("transaction_origin", String.valueOf(selectedBankAccount.getIdbank_account()));
                transaction.put("transaction_destination", "1");
                cmd.insert_cmd("transaction", transaction);
                transaction.clear();

            }
            return true;
        } catch (Exception e) {
            HashMap<String, String> transaction = new HashMap();
            dueDate = new Date();
            java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
            transaction.put("kind_of_transaction", "deposit");
            transaction.put("transaction_amount", String.valueOf(installmentAmount));
            transaction.put("transaction_status", String.valueOf("F".charAt(0)));
            transaction.put("transaction_customer_id", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
            transaction.put("transaction_origin", String.valueOf(selectedBankAccount.getIdbank_account()));
            transaction.put("transaction_destination", "1");
            cmd.insert_cmd("transaction", transaction);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return false;
        }
    }


    @Override
    public boolean accountToaccount(Customer customer) {
        if (withDraw(String.valueOf(customer.getIdCustomer())) != null) {
            try {
                deposit(String.valueOf(customer.getIdCustomer()), String.valueOf(selectedBankAccount.getIdbank_account()), String.valueOf(this.installmentAmount));
                Connection connection = InitDB.ConnectOk();
                connection.setAutoCommit(Boolean.TRUE);
                return true;
            } catch (Exception e) {
                e.getMessage();
                e.getCause();
            }
        }
        return false;
    }


    @Override
    public void close() throws Exception {
        try {
            cmd.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

    }


}

