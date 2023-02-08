package installments;

import DB.commandSQL;
import Entities.bank_account;
import Entities.customer;
import Entities.transactions;
import loan.loanManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class installmentPayment implements transactions {
    ArrayList<installments> installments = new ArrayList<>();
    HashMap<String, String> transaction = new HashMap();

    commandSQL cmd = new commandSQL();
    Scanner scanner = new Scanner(System.in);
    loanManager loanManager = new loanManager();
    bank_account selectedBankAccount = null;
    private double installmentAmount;
    private Date dueDate;
    private int installmentsNumber;


    @Override
    public BigDecimal withDraw() {
        System.out.println("please enter id of customer for select bank account : ");
        selectedBankAccount = cmd.select_one_bank_account("bank_account_customer_id", " = ", String.valueOf(scanner.nextInt()));
        System.out.println("please enter number of of installment :");
        installmentsNumber = scanner.nextInt();
        customer Customer = cmd.select_customer_cmd("idcustomer", " = ", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
        installments = cmd.select_installment_cmd(Customer);
        for (int i = 1; i < installments.size(); i++) {
            if (installments.get(i).getInstallments_status() == 'N' && i <= installmentsNumber) {
                System.out.println(installments.get(i).getInstallments_customer_id() + "  " + installments.get(i).getInstallments_interest() + "    " + installments.get(i).getInstallments_months() + "    " + installments.get(i).getInstallments_status()+"  "+installments.get(i).getInstallments_sum_pi_amount());
                installmentAmount=installmentAmount+installments.get(i).getInstallments_sum_pi_amount();

            } else break;
        }
        // cmd.update_cmd("bank_acocunt",bank_account.getIdbank_acocunt(),);
        System.out.println("the Total of installment amount is : " + installmentAmount);
        System.out.println("are Customer is ok? press Enter for cancel press another key");
        int ok=scanner.nextInt();
        if ( ok==1 && installmentAmount != 0 && selectedBankAccount != null) {
            dueDate = new Date();
            java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
            HashMap<String, String> ba_changes = new HashMap<>();
            ba_changes.put("bank_account_balance", String.valueOf(selectedBankAccount.getBank_account_balance().subtract(BigDecimal.valueOf(installmentAmount))));
            cmd.update_cmd("bank_account", selectedBankAccount.getIdbank_acocunt(), ba_changes);
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", dueDate.getHours() + ":" + dueDate.getMinutes());
            transaction.put("transaction_amount", String.valueOf(installmentAmount));
            transaction.put("transaction_status", String.valueOf("T".charAt(0)));
            transaction.put("transaction_customer_id", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
            transaction.put("transaction_origin", String.valueOf(selectedBankAccount.getIdbank_acocunt()));
            transaction.put("transaction_destination", "1");
            cmd.insert_cmd("transaction", transaction);
            transaction.clear();


            return BigDecimal.valueOf(installmentAmount);
        } else {
            System.out.println("the operation as canceled ");
            return null;
        }
    }

    @Override
    public boolean deposit() {
        if (withDraw() == null) {
            System.out.println("the operation as canceled ");
            return false;
        } else {

            HashMap<String, String> colval_ins = new HashMap<>();
            colval_ins.put("installments_status","T");
            HashMap<String, String> colval_financialr = new HashMap<>();


            for (int i = 0; i <= installmentsNumber; i++) {
                installmentAmount=installmentAmount-installments.get(i).getInstallments_sum_pi_amount();
                cmd.update_cmd("installments",installments.get(i).getInstallments_status(),colval_ins);
                colval_financialr.put("financial_amount",String.valueOf(cmd.get_financial_ressource_cmd().add(BigDecimal.valueOf(installments.get(i).getInstallments_sum_pi_amount()))));
                cmd.update_cmd("financial_ressource",1,colval_financialr);

                dueDate = new Date();
                java.sql.Date newDate = new java.sql.Date(dueDate.getTime());
                transaction.put("transaction_date", newDate.toString());
                transaction.put("transaction_time", dueDate.getHours() + ":" + dueDate.getMinutes());
                transaction.put("transaction_amount", String.valueOf(installmentAmount));
                transaction.put("transaction_status", "T");
                transaction.put("transaction_customer_id", String.valueOf(selectedBankAccount.getIdcustomer_bank_acount()));
                transaction.put("transaction_origin",String.valueOf(selectedBankAccount.getIdbank_acocunt()));
                transaction.put("transaction_destination", "1");
                cmd.insert_cmd("transaction", transaction);
                transaction.clear();

            }
        }


        return true;
    }

}

