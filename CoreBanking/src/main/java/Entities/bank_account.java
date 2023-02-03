package Entities;

import java.math.BigDecimal;

public class bank_account {
    private int idbank_acocunt;
    private BigDecimal bank_account_balance;
    private char kind_of_currency;
    private int idcustomer_bank_acount;

    public bank_account(int idbank_acocunt, BigDecimal bank_account_balance, char kind_of_currency, int idcustomer_bank_acount) {
        this.idbank_acocunt = idbank_acocunt;
        this.bank_account_balance = bank_account_balance;
        this.kind_of_currency = kind_of_currency;
        this.idcustomer_bank_acount= idcustomer_bank_acount;

    }

    public void setBank_account_balance(BigDecimal bank_account_balance) {
        this.bank_account_balance = bank_account_balance;
    }

    public void setKind_of_currency(char kind_of_currency) {
        this.kind_of_currency = kind_of_currency;
    }

    public int getIdbank_acocunt() {
        return idbank_acocunt;
    }

    public BigDecimal getBank_account_balance() {
        return bank_account_balance;
    }

    public char getKind_of_currency() {
        return kind_of_currency;
    }

    public void setIdbank_acocunt(int idbank_acocunt) {
        this.idbank_acocunt = idbank_acocunt;
    }

    public int getIdcustomer_bank_acount() {
        return idcustomer_bank_acount;
    }

    public void setIdcustomer_bank_acount(int idcustomer_bank_acount) {
        this.idcustomer_bank_acount = idcustomer_bank_acount;
    }
}
