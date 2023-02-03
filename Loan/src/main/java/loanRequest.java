import DB.commandSQL;

import java.math.BigDecimal;
import java.util.HashMap;

public class loanRequest {


    private commandSQL cmd = new commandSQL();

    public boolean acceptRequet(BigDecimal Amount, int monthsToPay) {
        if (cmd.select_financial("financial_resource", "financial_amount", ">=", Amount.toString())) {
            System.out.println("you can get the loan");
            return true;
        } else {
            System.out.println("you can not Get Loan!!!");
            return false;
        }
    }

    public void createLoanFile(String tableName, HashMap<String, String> colval) {

        cmd.insert_cmd(tableName, colval);

    }
}
