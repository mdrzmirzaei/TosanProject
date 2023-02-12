package LoanManager;

import CoreBankingManager.CommandSQL;


public class LoanManager {
    CommandSQL cmd = new CommandSQL();

    public Boolean  loanRequest(Double Amount, int monthsToPay) {
        if (cmd.select_financial_cmd("financial_ressource", "financial_amount", ">=", Amount.toString())) {
            System.out.println("you can get the loan");
            return true;
        } else {
            System.out.println("you can not Get Loan!!!");
            return false;
        }
    }

    public Double getLoanRate() {
        Double loanRate = 0d;
        try {
            cmd.cachedRowSet.setCommand("select loanRate from loan");
            cmd.cachedRowSet.execute();
            while (cmd.cachedRowSet.next()) {
                loanRate = cmd.cachedRowSet.getDouble("loanRate");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return 0d;
        }
        return loanRate;
    }

}
