package Entities;

import java.math.BigDecimal;

public class BankAccount {
    public int idbank_account;
    private BigDecimal bank_account_balance;
    private char kind_of_currency;
    private int idcustomer_bank_acount;

    public BankAccount() {
    }

    public BankAccount(int idbank_account, BigDecimal bank_account_balance, char kind_of_currency, int idcustomer_bank_acount) {
        this.idbank_account = idbank_account;
        this.bank_account_balance = bank_account_balance;
        this.kind_of_currency = kind_of_currency;
        this.idcustomer_bank_acount = idcustomer_bank_acount;

    }

    public int getIdbank_account() {
        return idbank_account;
    }

    public void setIdbank_acocunt(int idbank_acocunt) {
        this.idbank_account = idbank_acocunt;
    }

    public BigDecimal getBank_account_balance() {
        return bank_account_balance;
    }

    public void setBank_account_balance(BigDecimal bank_account_balance) {
        this.bank_account_balance = bank_account_balance;
    }

    public char getKind_of_currency() {
        return kind_of_currency;
    }

    public void setKind_of_currency(char kind_of_currency) {
        this.kind_of_currency = kind_of_currency;
    }

    public int getIdcustomer_bank_acount() {
        return idcustomer_bank_acount;
    }

    public void setIdcustomer_bank_acount(int idcustomer_bank_acount) {
        this.idcustomer_bank_acount = idcustomer_bank_acount;
    }
}
