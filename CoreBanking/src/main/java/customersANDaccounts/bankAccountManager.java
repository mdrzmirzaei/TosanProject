package customersANDaccounts;

import DB.commandSQL;
import Entities.bank_account;
import Entities.transactions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class bankAccountManager implements transactions {

    DB.commandSQL cmd = new commandSQL();
    Scanner scanner = new Scanner(System.in);

    @Override
    public BigDecimal withDraw() {
        return null;
    }

    @Override
    public boolean deposit() {
        if (withDraw()==null) {
            bank_account selected_bank_account = new bank_account();
            HashMap<String, String> bankaccount = new HashMap();
            HashMap<String, String> transaction = new HashMap();
            System.out.println("pls enter customer ID : ");
            int idcustomer = scanner.nextInt();
            ArrayList<bank_account> bank_accountArray = cmd.select_bank_accounts("bank_account_customer_id", " = ", String.valueOf(idcustomer));

            if (bank_accountArray != null) {
                System.out.println("i find Bank Account!!!");
                System.out.println("please select acccount :");
                int sel = scanner.nextInt();
                for (int i = 0; i < bank_accountArray.size(); i++) {
                    if (bank_accountArray.get(i).getIdbank_acocunt() == sel) {
                        selected_bank_account = bank_accountArray.get(i);
                    }
                }

                System.out.println("please enter amount of deposit");
                BigDecimal Amount = scanner.nextBigDecimal();
                bankaccount.put("bank_account_balance", String.valueOf(selected_bank_account.getBank_account_balance().add(Amount)));
                cmd.update_cmd("bank_account", selected_bank_account.getIdbank_acocunt(), bankaccount);

                transaction.put("transaction_amount", Amount.toString());
                transaction.put("transaction_status", String.valueOf("T".charAt(0)));
                transaction.put("transaction_customer_id", String.valueOf(selected_bank_account.getIdcustomer_bank_acount()));
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
}
