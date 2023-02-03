package customersANDaccounts;

import DB.commandSQL;
import Entities.bank_account;
import Entities.transactions;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class bankAccountManager implements transactions {

    DB.commandSQL cmd = new commandSQL();
    Scanner scanner = new Scanner(System.in);


    @Override
    public boolean deposit() {
        HashMap<String, String> bankaccount = new HashMap();
        HashMap<String, String> transaction = new HashMap();
        System.out.println("pls enter customer ID : ");
        int idcustomer = scanner.nextInt();
        ArrayList<bank_account> bank_accountArray = cmd.select_bank_account("bank_account_customer_id", " = ", String.valueOf(idcustomer));

        if (bank_accountArray != null) {
            System.out.println("find Bank Account!!!");
            System.out.println("please select acccount :");
            int sel = scanner.nextInt();
            bank_accountArray.stream().filter(bank_account -> bank_account.getIdbank_acocunt() == sel);
            System.out.println("please enter amount of deposit");
            BigDecimal Amount = scanner.nextBigDecimal();
            bankaccount.put("bank_account_balance", String.valueOf(bank_accountArray.get(1).getBank_account_balance().add(Amount)));
            cmd.update_cmd("bank_account", bank_accountArray.get(0).getIdbank_acocunt(), bankaccount);

            transaction.put("transaction_amount", Amount.toString());
            transaction.put("transaction_status", String.valueOf("F".charAt(0)));
            transaction.put("transaction_customer_id", String.valueOf(bank_accountArray.get(0).getIdcustomer_bank_acount()));
            transaction.put("transaction_destination", String.valueOf(bank_accountArray.get(0).getIdbank_acocunt()));


            cmd.insert_cmd("transaction", transaction);
            return true;
        } else {
            System.out.println("sorry !!!! bank account is not found ");
            return false;

        }


    }



 }
