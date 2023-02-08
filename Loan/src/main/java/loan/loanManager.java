package loan;

import DB.commandSQL;

import java.math.BigDecimal;


public class loanManager {
commandSQL cmd=new commandSQL();

    public Boolean loanRequest(BigDecimal Amount, int monthsToPay) {
        if (cmd.select_financial_cmd("financial_ressource", "financial_amount", ">=", Amount.toString())) {
            System.out.println("you can get the loan");
            return true;
        } else {
            System.out.println("you can not Get Loan!!!");
            return false;
        }

    }
}
