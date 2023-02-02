package Entities;

import java.math.BigDecimal;

public class bank_account {
    private int idbank_acocunt;
    private BigDecimal bank_account_balance;
    private char kind_of_currency;

    public bank_account(int idbank_acocunt, BigDecimal bank_account_balance, char kind_of_currency) {
        this.idbank_acocunt = idbank_acocunt;
        this.bank_account_balance = bank_account_balance;
        this.kind_of_currency = kind_of_currency;
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
}
