package CoreBankingManager;

import DatabaseManager.InitDB;
import Entities.BankAccount;
import Entities.Customer;
import Entities.TransactionsInterface;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class BankAccountManager implements TransactionsInterface {
    LocalDateTime ldt = LocalDateTime.now();
    CommandSQL cmd = new CommandSQL();
    Scanner scanner = new Scanner(System.in);
    BigDecimal Amount = null;
    BankAccount selected_bankAccount = new BankAccount();
    String idcustomer = "";
    String bankAccountId = "";
    private Date date;
    Customer customer=new Customer();

    @Override
    public BigDecimal withDraw(String idCustomer) {
        return null;
    }

    @Override
    public boolean deposit(String idcustomer, String bankAccountID, String amount) {
        this.idcustomer = idcustomer;
        try {
            if (withDraw(idcustomer) == null) {

                HashMap<String, String> bankaccount = new HashMap();
                HashMap<String, String> transaction = new HashMap();
//                System.out.println("pls enter customer ID : ");
//                int idcustomer = scanner.nextInt();

                ArrayList<BankAccount> bankAccountArray = cmd.select_bank_accounts("bank_account_customer_id", " = ", String.valueOf(idcustomer));

                if (bankAccountArray != null) {
//                    System.out.println("i find Bank Account!!!");
//                    System.out.println("please select acccount :");
//                    int sel = scanner.nextInt();
                    int sel = Integer.valueOf(bankAccountID);
                    for (int i = 0; i < bankAccountArray.size(); i++) {
                        if (bankAccountArray.get(i).getIdbank_account() == sel) {
                            selected_bankAccount = bankAccountArray.get(i);
                        }
                    }
                    this.bankAccountId =String.valueOf(selected_bankAccount.getIdbank_account()) ;
//                    System.out.println("please enter amount of deposit");
//                    Amount = scanner.nextBigDecimal();
                    Amount = new BigDecimal(amount.toString());
                    bankaccount.put("bank_account_balance", String.valueOf(selected_bankAccount.getBank_account_balance().add(Amount)));
                    cmd.update_cmd("bank_account", selected_bankAccount.getIdbank_account(), bankaccount);
                    transaction.put("transaction_amount", Amount.toString());
                    transaction.put("transaction_status", String.valueOf("T".charAt(0)));
                    transaction.put("transaction_customer_id", String.valueOf(selected_bankAccount.getIdcustomer_bank_acount()));
                    transaction.put("transaction_destination", String.valueOf(selected_bankAccount.getIdbank_account()));
                    cmd.insert_cmd("transaction", transaction);

                    return true;
                } else {
                    System.out.println("sorry !!!! bank account is not found ");
                    return false;
                }
            }
        } catch (Exception e) {

            HashMap<String, String> transaction = new HashMap();
            date = new Date();
            java.sql.Date newDate = new java.sql.Date(date.getTime());
            transaction.put("transaction_date", newDate.toString());
            transaction.put("transaction_time", ldt.getHour() + ":" + ldt.getMinute());
            transaction.put("kind_of_transaction", "withdraw");
            transaction.put("transaction_amount", Amount.toString());
            transaction.put("transaction_status", String.valueOf("F".charAt(0)));
            transaction.put("transaction_customer_id", String.valueOf(selected_bankAccount.getIdcustomer_bank_acount()));
            transaction.put("transaction_origin", "1");
            transaction.put("transaction_destination", String.valueOf(selected_bankAccount.getIdbank_account()));
            cmd.insert_cmd("transaction", transaction);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }


    @Override
    public boolean accountToaccount(Customer customer) {
        if (withDraw(idcustomer) != null) {
            try {
                deposit(idcustomer,bankAccountId,Amount.toString());
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
}
